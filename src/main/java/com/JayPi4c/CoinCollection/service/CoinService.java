package com.JayPi4c.CoinCollection.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;

import com.JayPi4c.CoinCollection.model.Coin;
import com.JayPi4c.CoinCollection.utils.HibernateUtils;
import com.JayPi4c.CoinCollection.utils.PropertiesLoader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoinService {

	public static Coin getCoin(int year, double value, String country) {
		return getCoinById(createID(year, value, country));
	}

	public static Coin getCoinById(String id) {
		Coin coin;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			coin = session.get(Coin.class, id);
		}
		return coin;
	}

	public static List<Coin> getCoins() {
		List<Coin> coins;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			coins = session.createQuery("from Coin", Coin.class).list();
		}
		return coins;
	}

	public static void save(Coin coin) {
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.persist(coin);
			session.getTransaction().commit();
		}
	}

	/**
	 * Fills in the database with new entries one year in advance and will also
	 * check the database for abnormalities.
	 */
	public static void sanitize() {
		log.info("Checking Database");
		List<Coin> coins = CoinService.getCoins();
		if (coins == null) {
			log.info("No coins found in database. Filling up database...");
			coins = new ArrayList<>();
		} else {
			log.info("Found coins. Checking for abnormalities...");
		}

		List<Coin> newCoins = new ArrayList<>();

		int beginningYear = 1999;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
		double[] values = { 0.01, 0.02, 0.05, 0.10, 0.20, 0.50, 1, 2 };
		String[] countries = PropertiesLoader.getInstance().getProperty("countries").split(",");

		for (int year = beginningYear; year < currentYear; year++) {
			for (double val : values) {
				for (String country : countries) {
					String id = createID(year, val, country);
					if (coins.stream().filter(coin -> coin.getId().equals(id)).count() == 0) {
						Coin c = new Coin();
						c.setId(id);
						c.setValue(val);
						c.setCountry(country);
						c.setManufacturingYear(year);
						newCoins.add(c);
					}
				}
			}
		}

		log.debug("Adding {} new coins to database.", newCoins.size());

		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			session.beginTransaction();
			for (Coin c : newCoins) {
				session.persist(c);
			}
			session.getTransaction().commit();
		}
	}

	public static String createID(int year, double value, String country) {
		return Integer.toString(year) + Double.toString(value) + country;
	}

}
