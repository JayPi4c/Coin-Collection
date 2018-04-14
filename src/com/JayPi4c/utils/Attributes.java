package com.JayPi4c.utils;

/**
 * Die Attributes Klasse speichert alle wichtigen Informationen in statischen
 * Variablen und l&aumlsst diese &uumlber die jeweiligen Getter- und
 * Settermethoden erreichen.
 * 
 * @author JayPi4c
 * @version 1.0.0
 */
public class Attributes {
	private static String[] countryNames;
	private static String[] Years = { "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
			"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" };
	private static String[] Values = { "0.01", "0.02", "0.05", "0.1", "0.2", "0.5", "1", "2" };
	private static String[] Perfectures = { "A; Berlin", "D; M\u00fcnchen", "F; Stuttgart", "G; Karlsruhe",
			"J; Hamburg" };
	private static boolean firstStart;
	private static int countries;
	private static int specialCoinages;
	private static int countries1999;
	private static int countries2001;
	private static int countries2007;
	private static int countries2008;
	private static int countries2009;
	private static int countries2011;
	private static int countries2014;
	private static int countries2015;

	/**
	 * Die Methode setzt die CountryNames Variable.
	 * 
	 * @param names
	 *            Die Methode nimmt ein Stringarray als Paramter an, welche in der
	 *            countryNames Variable gespeichert wird.
	 * @since 1.0.0
	 */
	public static void setCountryNames(String[] names) {
		countryNames = names;
	}

	/**
	 * Die Methode setzt einen bestimmten CountryName in dem CountryNames array.
	 * 
	 * 
	 * @param index
	 *            setzt den Index des Namens des Landes, welcher ge&aumlndert werden
	 *            soll.
	 * @param text
	 *            der String, der den Namen des Landes beinhaltet.
	 * 
	 * @since 1.0.0
	 */
	public static void setCountryName(int index, String text) {
		countryNames[index] = text;
	}

	/**
	 * 
	 * @return Gibt das Stringarray der CountryNames zur&uumlck.
	 * @since 1.0.0
	 */
	public static String[] getCountryNames() {
		return countryNames;
	}

	/**
	 * Diese Funktion gibt den Namen, der zu dem CountryKey geh&oumlrt zur&uumlck.
	 * 
	 * @param index
	 *            CountryKey, der zu dem Namen geh&oumlrt.
	 * @return gibt den Namen an der Stelle des Arrays zur&uumlck (Der Name, der zum
	 *         CountryKey geh&oumlrt)
	 * @since 1.0.0
	 */
	public static String getCounryName(int index) {
		return countryNames[index];
	}

	/**
	 * 
	 * @param years
	 *            ein Stringarray, welches alle Jahre der &Aumlra &euro besitzt.
	 * @since 1.0.0
	 */

	public static void setYears(String[] years) {
		Years = years;
	}

	/**
	 * 
	 * @return Gibt das Stringarray zur&uumlck, welches als einzelne Elemente die
	 *         Jahre der &Aumlra beinhaltet.
	 * @since 1.0.0
	 */
	public static String[] getYears() {
		return Years;
	}

	/**
	 * 
	 * @param vals
	 *            nimmt ein Stringarray entgegen, welches die 8 Werte der M&uumlnzen
	 *            beinhalten.
	 * @since 1.0.0
	 */
	public static void setValues(String[] vals) {
		Values = vals;
	}

	/**
	 * 
	 * @return gibt das Stringarray zur&uumlck, welche als Elemente die 8 Werte der
	 *         &euro-M&uumlnzen hat.
	 * @since 1.0.0
	 */
	public static String[] getValues() {
		return Values;
	}

	/**
	 * Deutschland ist das einzige Land, welches mehrere Pr&aumlgest&aumltte
	 * besitzt. Um diese Informationen verarbeiten zu k&oumlnnen m&uumlssen die
	 * Pr&aumlgst&aumltten bekannt sein.
	 * 
	 * @param perfects
	 *            ist ein Stringarry, welches die verschiedenen deutschen
	 *            Pr&aumlgest&aumltten beinhaltet.
	 * @since 1.0.0
	 */
	public static void setPerfectures(String[] perfects) {
		Perfectures = perfects;
	}

	/**
	 * 
	 * @return gibt die Pr&aumlgest&aumltten in eine Stringarray zur&uumlck.
	 * @since 1.0.0
	 */
	public static String[] getPerfectures() {
		return Perfectures;
	}

	/**
	 * 
	 * @param b
	 *            setzt, ob das Programm das erste mal startet.
	 * @since 1.0.0
	 */
	public static void setFirstStart(boolean b) {
		firstStart = b;
	}

	/**
	 * 
	 * @return gibt zur&uumlck, ob das Programm das erste Mal gestartet wird, oder
	 *         nicht.
	 * @since 1.0.0
	 */
	public static boolean getFirstStart() {
		return firstStart;
	}

	/**
	 * 
	 * @param c
	 *            setzt die Anzahl aller L&aumlnder, die sich aktuell im &euro
	 *            befinden.
	 * @since 1.0.0
	 */
	public static void setCountries(int c) {
		countries = c;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die sich aktuell im &euro
	 *         befinden.
	 * @since 1.0.0
	 */
	public static int getCountries() {
		return countries;
	}

	/**
	 * 
	 * @param specialCoins
	 *            Anzahl der Gedenkm&uumlnzen (Sonderm&uumlnzen)
	 * @since 1.0.0
	 */
	public static void setSpecialCoinages(int specialCoins) {
		specialCoinages = specialCoins;
	}

	/**
	 * 
	 * @return gibt die Anzahl der Gedenkm&uumlnzen (Sonderm&uumlnzen) zur&uumlck.
	 * @since 1.0.0
	 */
	public static int getSpecialCoinages() {
		return specialCoinages;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 1999 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries1999(int countr) {
		countries1999 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 1999 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries1999() {
		return countries1999;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2001 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2001(int countr) {
		countries2001 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2001 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2001() {
		return countries2001;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2007 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2007(int countr) {
		countries2007 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2007 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2007() {
		return countries2007;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2008 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2008(int countr) {
		countries2008 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2008 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2008() {
		return countries2008;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2009 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2009(int countr) {
		countries2009 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2009 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2009() {
		return countries2009;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2011 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2011(int countr) {
		countries2011 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 20011 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2011() {
		return countries2011;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2014 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2014(int countr) {
		countries2014 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2014 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2014() {
		return countries2014;
	}

	/**
	 * 
	 * @param countr
	 *            setzt die Anzahl der L&aumlnder, die 2015 im &euro waren.
	 * @since 1.0.0
	 */
	public static void setCountries2015(int countr) {
		countries2015 = countr;
	}

	/**
	 * 
	 * @return gibt die Anzahl der L&aumlnder zur&uumlck, die 2015 im &euro waren.
	 * @since 1.0.0
	 */
	public static int getCountries2015() {
		return countries2015;
	}
}
