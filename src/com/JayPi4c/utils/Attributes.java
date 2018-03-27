package com.JayPi4c.utils;

public class Attributes {
	protected static String[] countryNames;
	protected static String[] Years = { "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
			"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017" };
	protected static String[] Values = { "0.01", "0.02", "0.05", "0.1", "0.2", "0.5", "1", "2" };
	protected static String[] Perfectures = { "A; Berlin", "D; München", "F; Stuttgart", "G; Karlsruhe", "J; Hamburg" };
	protected static boolean firstStart;
	protected static int countries;
	protected static int specialCoinages;
	protected static int countries1999;
	protected static int countries2001;
	protected static int countries2007;
	protected static int countries2008;
	protected static int countries2009;
	protected static int countries2011;
	protected static int countries2014;
	protected static int countries2015;

	public static void setCountryNames(String[] names) {
		countryNames = names;
	}

	public static void setCountryName(int index, String text) {
		countryNames[index] = text;
	}

	public static String[] getCountryNames() {
		return countryNames;
	}

	public static void setYears(String[] years) {
		Years = years;
	}

	public static String[] getYears() {
		return Years;
	}

	public static void setValues(String[] vals) {
		Values = vals;
	}

	public static String[] getValues() {
		return Values;
	}

	public static void setPerfectures(String[] perfects) {
		Perfectures = perfects;
	}

	public static String[] getPerfectures() {
		return Perfectures;
	}

	public static void setFirstStart(boolean b) {
		firstStart = b;
	}

	public static boolean getFirstStart() {
		return firstStart;
	}

	public static void setCountries(int c) {
		countries = c;
	}

	public static int getCountries() {
		return countries;
	}

	public static void setSpecialCoinages(int specialCoins) {
		specialCoinages = specialCoins;
	}

	public static int getSpecialCoinages() {
		return specialCoinages;
	}

	public static void setCountries1999(int countr) {
		countries1999 = countr;
	}

	public static int getCountries1999() {
		return countries1999;
	}

	public static void setCountries2001(int countr) {
		countries2001 = countr;
	}

	public static int getCountries2001() {
		return countries2001;
	}

	public static void setCountries2007(int countr) {
		countries2007 = countr;
	}

	public static int getCountries2007() {
		return countries2007;
	}

	public static void setCountries2008(int countr) {
		countries2008 = countr;
	}

	public static int getCountries2008() {
		return countries2008;
	}

	public static void setCountries2009(int countr) {
		countries2009 = countr;
	}

	public static int getCountries2009() {
		return countries2009;
	}

	public static void setCountries2011(int countr) {
		countries2011 = countr;
	}

	public static int getCountries2011() {
		return countries2011;
	}

	public static void setCountries2014(int countr) {
		countries2014 = countr;
	}

	public static int getCountries2014() {
		return countries2014;
	}

	public static void setCountries2015(int countr) {
		countries2015 = countr;
	}

	public static int getCountries2015() {
		return countries2015;
	}
}
