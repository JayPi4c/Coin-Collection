package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class PreInit {
	public static String[] content;

	public static void begin() {

		try {
			FileReader PropFR = new FileReader("./src/com/JayPi4c/resource/properties.prop");
			// FileReader PropFR = new FileReader("./properties.prop"); //Pfad
			// zur prop Datei im selben Ordner wie die .jar Datei
			BufferedReader PropBR = new BufferedReader(PropFR);

			String[] lines = PropBR.readLine().split("//");

			int linesOfProperties = Integer.parseInt(lines[0]);

			String[] properties = new String[linesOfProperties];

			for (int i = 0; i < properties.length; i++)
				properties[i] = PropBR.readLine();

			content = properties[0].split("=");
			Attributes.setFirstStart((content[1].contains("true")));

			content = properties[1].split("=");
			Attributes.setSpecialCoinages(Integer.parseInt(content[1]));

			content = properties[2].split("=");
			Attributes.setCountries(Integer.parseInt(content[1]));

			content = properties[3].split("=");
			Attributes.setCountries1999(Integer.parseInt(content[1]));

			content = properties[4].split("=");
			Attributes.setCountries2001(Integer.parseInt(content[1]));

			content = properties[5].split("=");
			Attributes.setCountries2007(Integer.parseInt(content[1]));

			content = properties[6].split("=");
			Attributes.setCountries2008(Integer.parseInt(content[1]));

			content = properties[7].split("=");
			Attributes.setCountries2009(Integer.parseInt(content[1]));

			content = properties[8].split("=");
			Attributes.setCountries2011(Integer.parseInt(content[1]));

			content = properties[9].split("=");
			Attributes.setCountries2014(Integer.parseInt(content[1]));

			content = properties[10].split("=");
			Attributes.setCountries2015(Integer.parseInt(content[1]));

			Attributes.setCountryNames(new String[Attributes.getCountries()]);
			for (int i = 0; i < Attributes.getCountries(); i++) {
				content = properties[i + (linesOfProperties - Attributes.getCountries()) - 1].split("=");
				Attributes.setCountryName(i, content[0]);
			}

			PropBR.close();
			PropFR.close();
			System.out.println("load 'properties.prop': Successfully");

		} catch (Exception ex) {
			System.out.println("load 'properties.prop': failed");
			System.out.println("Error log: " + ex);
			System.out.println("Exiting ...");
			System.exit(0);
		}
	}

}
