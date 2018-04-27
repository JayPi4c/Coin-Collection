package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author JayPi4c
 * @version 1.0.0
 */
public class Init {
	/**
	 * Die statische Funktion begin() &uumlberpr&uumlft zu Beginn, ob der "data"
	 * Ordner existiert. Darauf folgend werden alle "Coinages.co" Dateien auf
	 * vorhanden sein &uumlberpr&uumlft. Ist eine Datei nicht vorhanden, wird diese
	 * generiert. Sollte die Datei vorhanden sein, wird die Datei auf Fehler
	 * &uumlberpr&uumlft, die das Programm evtl. zum Absturz bringen k&oumlnnen.
	 * Verschiedene Fehler sorgen f&uumlr verschiedene Fehlermeldungen:
	 * <p>
	 * <li>Ist die Datei leer, dann wird der Fehlercode 0 ausgegeben
	 * <li>Enth&aumllt eine Zeile nicht 8 Elemente, so wird der Fehlercode 1
	 * ausgegeben. Die 8 Elemente stehen f�r 8 M&uumlnzen pro Jahr (1ct, 2ct, 5ct,
	 * 10ct, 20ct, 50ct, 1&euro, 2&euro). Des Weiteren beinhaltet ein Element noch
	 * eine Zahl f�r das Land, den CountryKey, und eine Zahl, die wiederspiegelt, ob
	 * das Element im Besitz des Users ist.
	 * <li>Besteht ein Element nicht aus 3 Teilen, so wir der Fehlercode 2
	 * ausgegeben.
	 * <li>Entspricht der CountryKey nicht der Zeile in der Datei, so wird der
	 * Fehlercode 3 ausgegeben.
	 * <p>
	 * Sollte ein Fehler erkannt werden, so wird die Datei als Fehlerhaft markiert
	 * und die Datei wird neu generiert, jedoch wird versucht, so viele
	 * Informationen zu retten, wie es nur m&oumlglich ist. Dies geschieht durch die
	 * {@link com.JayPi4c.utils.Util#rescueData(File file, int year) rescueData}
	 * Methode aus der Util-Klasse.
	 * <p>
	 * Gibt es keine Fehler oder die Daten wurde gerettet, so wird abschlie&szligend
	 * die Information ausgegeben, dass die Datei erreichbar ist.
	 * 
	 * 
	 * @throws IOException
	 * @since 1.0.0
	 */
	public static void begin() throws IOException {
		File dataDir = new File(Util.getExecutionPath() + "/data");
		if (!dataDir.exists())
			dataDir.mkdir();

		for (int i = 1999; i <= 2017; i++) {
			File CoinRegistry = new File(Util.getPath(i));
			// System.out.println("Coinregistry" + i + " exists: " + CoinRegistry.exists());
			// Util.log.info("Coinregistry" + i + " exists: " + CoinRegistry.exists());
			if (!CoinRegistry.exists()) {
				// System.out.println("generate: " + CoinRegistry.getAbsolutePath());
				Util.log.info("generate: " + CoinRegistry.getAbsolutePath());
				Util.genRegistry(i);
			} else {
				boolean damaged = false;

				if (CoinRegistry.length() == 0) {
					// System.out.println("file is damaged!");
					// System.out.println("Errocode: 0; File is empty");
					Util.log.info("file is damaged!");
					Util.log.info("Errocode: 0; File is empty");
					damaged = true;
					Util.genRegistry(i);
				}
				if (!damaged) {
					BufferedReader CR = new BufferedReader(new FileReader(CoinRegistry));
					String[] lines = new String[Util.getMembersFromYear(i)];
					for (int j = 0; j < Util.getMembersFromYear(i); j++) {
						lines[j] = CR.readLine();

						String[] str = lines[j].split(";");
						if (str.length != 8) {
							// System.out.println("file is damaged!");
							// System.out.println("Errorcode: 1; Line: " + j);
							Util.log.info("file is damaged!");
							Util.log.info("Errorcode: 1; Line: " + j);
							damaged = true;
							break;
						}

						if (!damaged) {
							for (int k = 0; k < str.length; k++) {
								String[] part = str[k].split(",");
								if (part.length != 3) {
									// System.out.println("file is damaged!");
									// System.out.println("Errorcode: 2; Line: " + (j + 1));
									Util.log.info("file is damaged!");
									Util.log.info("Errorcode: 2; Line: " + (j + 1));
									damaged = true;
									break;
								}
								if (!part[0].equals("" + j)) {
									// System.out.println("file is damaged!");
									// System.out.println("Errorcode: 3; Line: " + (j + 1));
									Util.log.info("file is damaged!");
									Util.log.info("Errorcode: 3; Line: " + (j + 1));
									damaged = true;
									break;
								}
							}
							if (damaged)
								break;
						}
					}

					CR.close();
					if (damaged)
						Util.rescueData(CoinRegistry, i);
				}
			}
			// System.out.println("'coinages" + i + ".co' is accessible");
			// Util.log.info("'coinages" + i + ".co' is accessible");
		}
	}

}
