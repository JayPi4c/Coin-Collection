package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author JayPi4c
 * @version 1.0.0
 *
 */
public class PreInit {

	private static String[] content;

	/**
	 * Die statische Funktion begin() l&aumldt alle Informationen aus der
	 * properties.prop Datei in statische Variablen in der
	 * {@link com.JayPi4c.utils.Attributes Attributes} Klasse. Aus dieser Attributes
	 * Klasse sind alle Variablen global erreichbar.
	 * <p>
	 * Ist das Laden abgeschlossen, so wird in der Konsole ausgegeben, dass das
	 * Laden erfolgreich war. Gibt es einen Fehler beim Laden der Datei so wird das
	 * Programm beendet, da ohnehin weitere Informationen für das Programm fehlen.
	 * 
	 * @since 1.0.0
	 */
	public static void begin() {

		try {
			BufferedReader PropBR = new BufferedReader(
					new InputStreamReader(PreInit.class.getResourceAsStream("/com/JayPi4c/resource/properties.prop")));

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
			// PropFR.close();
			// System.out.println("load 'properties.prop': Successfully");
			Util.log.info("load 'properties.prop': Successfully");

		} catch (Exception ex) {
			// System.out.println("load 'properties.prop': failed");
			// System.out.println("Error log: " + ex);
			// System.out.println("Exiting ...");
			Util.log.info("load 'properties.prop': failed");
			Util.log.info("Error log: " + ex);
			Util.log.info("Exiting...");
			System.exit(0);
		}
	}

}
