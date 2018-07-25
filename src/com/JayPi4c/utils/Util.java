package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * 
 * @author JayPi4c
 * @version 1.1.0
 */
public class Util {
	/**
	 * Mit der Variablen n wird immer eine neue Zeile angefangen, unabh&aumlngig des
	 * OSs.
	 * 
	 * @since 1.0.0
	 */
	public static final String n = System.getProperty("line.separator");

	/**
	 * Ein Logger, mit dem schneller Errors gespeichert werden k&oumlnnen.
	 * 
	 * @since 1.1.0
	 */
	public static final Logger log = Logger.getLogger(Util.class.getName());

	/**
	 * Um auch nach dem Programmstart Informationen und Fehler erkennen zu
	 * k&oumlnnen werden diese Informationen auch in einer log.txt gespeichert.
	 * Dieser wird in dieser Funktion initialisert.
	 * 
	 * @throws IOException
	 * @since 1.1.0
	 */
	public static void initializeLogger() throws IOException {
		log.info("Starting up...");
		FileHandler handler = new FileHandler("log.txt");
		log.addHandler(handler);
	}

	/**
	 * 
	 * @return Gibt den absoluten Pfad an, der zu der .jar Datei f&uumlhrt, um zum
	 *         Beispiel den "/data" Ordner finden bzw generieren lassen zu
	 *         k&oumlnnen.
	 * @since 1.0.0
	 */
	public static String getExecutionPath() {
		String absolutePath = new File(".").getAbsolutePath();
		File file = new File(absolutePath);
		absolutePath = file.getParentFile().toString();
		return absolutePath;
	}

	/**
	 * Es wird der Pfad zur CoinagesYEAR.co Datei ermittelt und zur&uumlckgegeben,
	 * der zu dem Parameter passt.
	 * 
	 * @param year Das Jahr, welches zur gesuchten Datei geh&oumlrt.
	 * @return Der Pfad zur CoinagesYEAR.co Datei.
	 * @since 1.0.0
	 */
	public static String getPath(int year) {
		String path = getExecutionPath() + "/data/coinages" + year + ".co";
		return path;
	}

	/**
	 * Diese Funktion erstellt die Datei, die zu dem Parameterjahr geh&oumlrt und
	 * f&uumlllt diese mit den zugeh&oumlrigen Elementen. Diese werden auch daran
	 * angepasst, dass es verschiedene Anzahlen an L&aumlndern zu untschiedlichen
	 * Jahren im &euro gab. Diese unterschiedliche Anzahl an L&aumlndern in
	 * unterschiedlichen Jahren wird schon in der Funktion ber&uumlcksichtigt.
	 * 
	 * @param year Das Jahr, f&uumlr welches die CoinagesYEAR.co Datei generiert
	 *             werden soll.
	 * @throws IOException
	 * @since 1.0.0
	 */
	public static void genRegistry(int year) throws IOException {
		File file = new File(getExecutionPath() + "/data/coinages" + year + ".co");
		file.createNewFile();

		BufferedWriter CoinBW = new BufferedWriter(new FileWriter(file));

		// looped durch jedes Land, das in dem Jahr den Euro hatte
		for (int i = 0; i < getMembersFromYear(year); i++) {
			System.out.println("write in line " + (i + 1));
			for (int j = 0; j < 2; j++) {
				int k = (int) Math.pow(10, j);
				CoinBW.write(i + "," + 0.01 * k + ",0;");
				CoinBW.write(i + "," + 0.02 * k + ",0;");
				CoinBW.write(i + "," + 0.05 * k + ",0;");
			}
			CoinBW.write(i + "," + 1 + ",0;");
			CoinBW.write(i + "," + 2 + ",0;");
			CoinBW.write(n);
		}
		CoinBW.close();
		System.out.println("generating successfully!");
	}

	/**
	 * Wenn eine Datei besch&aumldigt ist, was durch die
	 * Init.{@link com.JayPi4c.utils.Init#begin() begin()}-Methode festgestellt
	 * werden kann, wird mit dieser Methode versucht, so viele Informationen, wie
	 * m&oumlglich zu retten.
	 * <p>
	 * Hierf&uumlr werden die verbleibenden Informationen in einem Stringarray
	 * gespeichert und eine neue Datei generiert, welche mit den gesicherten
	 * Informationen gef&uumlllt wird, sodass die gesicherten Informationen
	 * automatisch wieder in die Datei eingepflegt werden k&oumlnnen.
	 * 
	 * @param file Die Datei, die &uumlberpr&uumlft und repariert werden soll.
	 * @param year Das Jahr der Datei, f&uumlr die Anzahl der L&aumlnder in dem Jahr
	 *             und weiteres.
	 * @throws IOException
	 * @since 1.0.0
	 */
	public static void rescueData(File file, int year) throws IOException {
		// erstelle einen String, der alle Daten speichert, aufgeteilt in die Parts
		// jeder Zeile jede Zeile(getMembersFromYear) mal die Anzahl Parts, die jede
		// Zeile hat(8)
		String[] fullDataInParts = new String[getMembersFromYear(year) * 8];
		// System.out.println("IndexSize: "+ fullDataInParts.length);
		for (int i = 0; i < fullDataInParts.length; i++)
			fullDataInParts[i] = null;

		BufferedReader BR = new BufferedReader(new FileReader(file));
		String[] textLine = new String[getMembersFromYear(year)];

		for (int i = 0; i < getMembersFromYear(year); i++)
			textLine[i] = BR.readLine();

		BR.close();

		for (int i = 0; i < textLine.length; i++) {// loop durch jede Zeile
			// System.out.println("textline:" + i + "; content:" + textLine[i]);
			// System.out.println("null: " + textLine[i]);
			if (textLine[i] != null && !textLine[i].equals("")) {
				String[] contentOfLine = textLine[i].split(";");
				for (int j = 0; j < contentOfLine.length; j++) {// loop durch
																// jeden Part
					String[] contentOfPart;
					contentOfPart = contentOfLine[j].split(",");
					if (contentOfPart.length == 3) {
						// System.out.println("contentOfPart[0]: " +
						// (Integer.parseInt(contentOfPart[0])+1));
						// (System.out.println("contentOfPart[1]: " +
						// (getLineMultiplicator(Double.parseDouble(contentOfPart[1]))+1));
						// System.out.println(contentOfLine[j]);
						// Berechnung des Indexes: z = f(x,y) = (((x-1)*8)+y)-1,
						// wobei x die Zeile ist und y der Part in der Zeile
						// #lineMultiplicator
						int indexPos = getIndexPos(Integer.parseInt(contentOfPart[0]) + 1,
								(getLineMultiplicator(Double.parseDouble(contentOfPart[1])) + 1));
						// System.out.println("IndexPosition: "+ indexPos);
						fullDataInParts[indexPos] = contentOfLine[j];
					}
				}
			}
		}

		for (int i = 0; i < fullDataInParts.length; i++) {
			// System.out.println("DataPart: " + fullDataInParts[i]);
			if (!(fullDataInParts[i] != null && !fullDataInParts[i].equals(""))) {
				int country = i / 8;
				int valueKey = i % 8;
				double value = getValueFromMultiplicator(valueKey);
				// umgeht das .0, wenn 2.0 oder 1.0 in eine Datei eingeschrieben werden muss,
				// damit die Datei nach dem Reparieren wieder wie im originalen Zustand
				// aussieht!
				String part;
				if (value >= 1)
					part = "" + (int) (value);
				else
					part = "" + value;
				fullDataInParts[i] = country + "," + part + ",0";
				System.out.println(fullDataInParts[i] + " was generated!");
			}
		}

		// schreibe alle Daten wieder in die Datei

		BufferedWriter BW = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < fullDataInParts.length; i++) {
			BW.write(fullDataInParts[i]);
			BW.write(";");
			// System.out.println("fullDataInParts[" + i + "]: " +
			// fullDataInParts[i]);
			// System.out.println( (i+1) + "%8 = " + ((i+1)%8));
			if ((i + 1) % 8 == 0)
				BW.write(n);
		}
		BW.close();

		System.out.println("rescued data: successfully");

		/*
		 * for(int i = 0; i < TextLine.length; i++){ String[] contentLine =
		 * TextLine[i].split(";"); for(int j = 0; j < contentLine.length; j++){ String[]
		 * contentPart = contentLine[j].split(","); if(!contentPart[0].equals(i)){
		 * System.out.println(contentLine[j] + " ist in der falschen Zeile!"); } }
		 * 
		 * }
		 */
	}

	/**
	 * Die Funktion gibt den Status einer M&uumlnze zur&uumlck
	 * 
	 * @param land  Das Land, aus dem die M&uumlnze stammt.
	 * @param value Der Wert der M&uumlnze (1ct, 2ct, 5ct, 10ct, 20ct, 50ct, 1&euro,
	 *              2&euro)
	 * @param year  Das Jahr, aus dem die M&uumlnze stammt.
	 * @return true, wenn die M&uumlnze im Besitz ist, false, wenn die M&uumlnze
	 *         nicht im Besitz ist.
	 * @throws IOException
	 * @since 1.0.0
	 */
	public static boolean getStatus(String land, double value, int year) throws IOException {
		String content[];
		int multiplicator = getLineMultiplicator(value);
		int countryKey = countryKey(year, land);

		File CoinRegistry = new File(getPath(year));
		FileReader CoinFR = new FileReader(CoinRegistry);
		BufferedReader CoinBR = new BufferedReader(CoinFR);

		String[] line = new String[countryKey + 1]; // Arrays basieren auf einem
													// index, der mit 0 beginnt,
													// allerdings muss die größe
													// die gesamte Zahl haben
		for (int i = 0; i <= countryKey; i++) {
			line[i] = CoinBR.readLine();
		}

		content = line[countryKey].split(";");

		content = content[multiplicator].split(",");

		CoinBR.close();
		CoinFR.close();
		return content[2].equals("1");
	}

	/**
	 * Der Key, der zu einem Land geh&oumlrt, die Zeile des Landes
	 * 
	 * @param year Das Jahr, f&uumlr das man den key haben will
	 * @param land Das Land, f&uumlr das man den Key haben will
	 * @return gibt den key des Landes zur&uumlck.
	 * @since 1.0.0
	 */
	public static int countryKey(int year, String land) {
		int key = 42;
		String countryNames[] = Attributes.getCountryNames();
		for (int i = 0; i < getMembersFromYear(year); i++) {
			if (countryNames[i].equals(land)) {
				key = i;
				break;
			}
		}
		return key;
	}

	/**
	 * Gibt den Namen, der zu dem countryKey geh&oumlt, zur&uumlck.
	 * 
	 * @param countryKey Der countryKey, von dem man den String des Namens haben
	 *                   m&oumlchte.
	 * @return Der String des Namens, der zu dem countryKey geh&oumlrt.
	 */
	public static String getNameByCountryKey(int countryKey) {
		return Attributes.getCounryName(countryKey);
	}

	public static void updateRegistry(String land, double value, int year, boolean holding) throws IOException {
		String content[];
		int multiplicator = getLineMultiplicator(value);
		int countryKey = countryKey(year, land);

		File CoinRegistry = new File(getPath(year));

		FileReader CoinFR = new FileReader(CoinRegistry);
		BufferedReader CoinBR = new BufferedReader(CoinFR);

		String[] line = new String[getMembersFromYear(year)]; // erstellen ein
																// Array in der
																// Größe der
																// Anzahl der
																// EuroStaaten
																// des Jahres
		for (int i = 0; i < getMembersFromYear(year); i++) { // fülle das Array
																// mit jeder
																// Zeile der
																// angegebenen
																// Datei
			line[i] = CoinBR.readLine();
		}
		content = line[countryKey].split(";");
		String[] content_ = content;
		content = content[multiplicator].split(",");

		if (holding) {
			content[2] = "1";
		} else
			content[2] = "0";

		String str = countryKey + "," + value + "," + content[2] + ";";
		String out = "";
		for (int i = 0; i < 8; i++) { // setze die einzelnen Teile der Zeile
										// wieder zusammen
			if (i == multiplicator) {
				out = out + str;
			} else
				out = out + content_[i] + ";";
		}

		line[countryKey] = out; // line beinhaltet alle linien der Datei und nun
								// wird die modifizierte Zeile wieder in diese
								// "Liste" eingefügt
		FileWriter CoinFW = new FileWriter(CoinRegistry);
		BufferedWriter CoinBW = new BufferedWriter(CoinFW);
		for (int i = 0; i < line.length; i++) { // loop durch jede Zeile der
												// Datei und schreibe sie alle
												// wieder in die Datei
			CoinBW.write(line[i]);
			CoinBW.write(n);
		}
		CoinBW.close();
		CoinFW.close();
		CoinBR.close();
		CoinFR.close();
	}

	public static int getLineMultiplicator(double value) {
		int lineMultiplicator = 42;
		int switchValue = (int) (value * 100);
		switch (switchValue) {
		case 1:
			lineMultiplicator = 0;
			break;
		case 2:
			lineMultiplicator = 1;
			break;
		case 5:
			lineMultiplicator = 2;
			break;
		case 10:
			lineMultiplicator = 3;
			break;
		case 20:
			lineMultiplicator = 4;
			break;
		case 50:
			lineMultiplicator = 5;
			break;
		case 100:
			lineMultiplicator = 6;
			break;
		case 200:
			lineMultiplicator = 7;
			break;
		}
		return lineMultiplicator;
	}

	public static double getValueFromMultiplicator(int multiplicator) {
		double value = 0;
		switch (multiplicator) {
		case 0:
			value = 0.01;
			break;
		case 1:
			value = 0.02;
			break;
		case 2:
			value = 0.05;
			break;
		case 3:
			value = 0.1;
			break;
		case 4:
			value = 0.2;
			break;
		case 5:
			value = 0.5;
			break;
		case 6:
			value = 1;
			break;
		case 7:
			value = 2;
			break;
		}
		return value;
	}

	public static int getIndexPos(int x, int y) {
		int z = (((x - 1) * 8) + y) - 1;
		return z;
	}

	public static int getMembersFromYear(int year) {
		int members;
		if (year < 2001)
			members = Attributes.getCountries1999();
		else if (year < 2007)
			members = Attributes.getCountries2001();
		else if (year < 2008)
			members = Attributes.getCountries2007();
		else if (year < 2009)
			members = Attributes.getCountries2008();
		else if (year < 2011)
			members = Attributes.getCountries2009();
		else if (year < 2014)
			members = Attributes.getCountries2011();
		else if (year < 2015)
			members = Attributes.getCountries2014();
		else
			members = Attributes.getCountries2015();

		return members;
	}

	public static float getWorth() throws IOException {
		float worth = 0;
		for (int year = 1999; year < 2017; year++) {
			File file = new File(getPath(year));
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			for (int i = 0; i < getMembersFromYear(year); i++) {
				String line = bufferedReader.readLine();
				String[] parts = line.split(";");
				for (int j = 0; j < parts.length; j++) {
					String[] part = parts[j].split(",");
					if (part[2].equals("1"))
						worth += Float.parseFloat(part[1]);
				}

			}
			bufferedReader.close();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		return Float.parseFloat(df.format(worth).replace(',', '.'));
	}

	public static void resetCollection() throws IOException {

		File f = new File(getExecutionPath() + "/data");
		System.out.println(f.getAbsolutePath());

		for (String s : f.list())
			new File(f.getPath(), s).delete();

		if (f.exists())
			Init.begin();

		Object[] options = { "OK" };
		// JOptionPane.showOptionDialog(null, texts.getString("resetDialogOutput"),
		// texts.getString("resetDialogButton"),
		// JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options,
		// options[0]);

		JOptionPane.showOptionDialog(null, "Feddig", "Feddig", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
	}

}
