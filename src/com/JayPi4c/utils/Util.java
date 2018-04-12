package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * 
 * @author JayPi4c
 * @version 1.0.0
 */
public class Util {
	/**
	 * Mit der Variablen n wird immer eine neue Zeile angefangen, unabhängig des
	 * OSs.
	 * 
	 * @since 1.0.0
	 */
	public static final String n = System.getProperty("line.separator");

	/**
	 * 
	 * @return Gibt den absoluten Pfad an, der zu der .jar Datei führt, um zum
	 *         Beispiel den "/data" Ordner finden bzw generieren lassen zu können.
	 * @since 1.0.0
	 */
	public static String getExecutionPath() {
		String absolutePath = new File(".").getAbsolutePath();
		File file = new File(absolutePath);
		absolutePath = file.getParentFile().toString();
		return absolutePath;
	}

	/**
	 * Es wird der Pfad zur CoinagesYEAR.co Datei ermittelt und zurückgegeben, der
	 * zu dem Parameter passt.
	 * 
	 * @param year
	 *            Das Jahr, welches zur gesuchten Datei gehört.
	 * @return Der Pfad zur CoinagesYEAR.co Datei.
	 * @since 1.0.0
	 */
	public static String getPath(int year) {
		String path = getExecutionPath() + "/data/coinages" + year + ".co";
		return path;
	}

	/**
	 * Diese Funktion erstellt die Datei, die zu dem Parameterjahr gehört und füllt
	 * diese mit den zugehörigen Elementen. Diese werden auch daran angepasst, dass
	 * es verschiedene Anzahlen an Ländern zu untschiedlichen Jahren im € gab. Diese
	 * unterschiedliche Anzahl an Ländern in unterschiedlichen Jahren wird schon in
	 * der Funktion berücksichtigt.
	 * 
	 * @param year
	 *            Das Jahr, für welches die CoinagesYEAR.co Datei generiert werden
	 *            soll.
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
	 * Wenn eine Datei beschädigt ist, was durch die
	 * Init.{@link com.JayPi4c.utils.Init#begin() begin()}-Methode festgestellt
	 * werden kann, wird mit dieser Methode versucht, so viele Informationen, wie
	 * möglich zu retten.
	 * <p>
	 * Hierfür werden die verbleibenden Informationen in einem Stringarray
	 * gespeichert und eine neue Datei generiert, welche mit den gesicherten
	 * Informationen gefüllt wird, sodass die gesicherten Informationen automatisch
	 * wieder in die Datei eingepflegt werden können.
	 * 
	 * @param file
	 *            Die Datei, die überprüft und repariert werden soll.
	 * @param year
	 *            Das Jahr der Datei, für die Anzahl der Länder in dem Jahr und
	 *            weiteres.
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
				fullDataInParts[i] = country + "," + value + "," + 0;
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
	 * Die Funktion gibt den Status einer Münze zurück
	 * 
	 * @param land
	 *            Das Land, aus dem die Münze stammt.
	 * @param value
	 *            Der Wert der Münze (1ct, 2ct, 5ct, 10ct, 20ct, 50ct, 1€, 2€)
	 * @param year
	 *            Das Jahr, aus dem die Münze stammt.
	 * @return true, wenn die Münze im Besitz ist, false, wenn die Münze nicht im
	 *         Besitz ist.
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
													// allerdings muss die grÃ¶ÃŸe
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
	 * Der Key, der zu einem Land gehört, die Zeile des Landes
	 * 
	 * @param year
	 *            Das Jahr, für das man den key haben will
	 * @param land
	 *            Das Land, für das man den Key haben will
	 * @return gibt den key des Landes zurück.
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

	public static void updateRegistry(String land, double value, int year, boolean holding) throws IOException {
		String content[];
		int multiplicator = getLineMultiplicator(value);
		int countryKey = countryKey(year, land);

		File CoinRegistry = new File(getPath(year));

		FileReader CoinFR = new FileReader(CoinRegistry);
		BufferedReader CoinBR = new BufferedReader(CoinFR);

		String[] line = new String[getMembersFromYear(year)]; // erstellen ein
																// Array in der
																// GrÃ¶ÃŸe der
																// Anzahl der
																// EuroStaaten
																// des Jahres
		for (int i = 0; i < getMembersFromYear(year); i++) { // fÃ¼lle das Array
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
								// "Liste" eingefÃ¼gt
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
		return worth;
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

		JOptionPane.showOptionDialog(null, "Feddig", "Feddifg", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
	}

}
