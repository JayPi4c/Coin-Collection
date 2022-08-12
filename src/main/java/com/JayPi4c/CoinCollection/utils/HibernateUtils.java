package com.JayPi4c.CoinCollection.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtils {

	@Getter
	private static final SessionFactory sessionFactory;

	static {
		System.setProperty("derby.stream.error.file", "logs/database.log"); // make sure log-file is in logs folder
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) {
			log.error("Initial SessionFactory creation failed: {}", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}