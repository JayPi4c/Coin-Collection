package com.JayPi4c.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Init {
	public static void begin() throws IOException {
		File dataDir = new File(Util.getExecutionPath() + "/data");
		if (!dataDir.exists())
			dataDir.mkdir();

		for (int i = 1999; i <= 2017; i++) {
			File CoinRegistry = new File(Util.getPath(i));
			System.out.println("Coinregistry" + i + " exists: " + CoinRegistry.exists());
			if (!CoinRegistry.exists()) {
				System.out.println("generate: " + CoinRegistry.getAbsolutePath());
				Util.genRegistry(i);
			} else {
				boolean damaged = false;

				if (CoinRegistry.length() == 0) {
					System.out.println("file is damaged!");
					System.out.println("Errocode: 0; File is empty");
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
							System.out.println("file is damaged!");
							System.out.println("Errorcode: 1; Line: " + j);
							damaged = true;
							break;
						}

						if (!damaged) {
							for (int k = 0; k < str.length; k++) {
								String[] part = str[k].split(",");
								if (part.length != 3) {
									System.out.println("file is damaged!");
									System.out.println("Errorcode: 2; Line: " + (j + 1));
									damaged = true;
									break;
								}
								if (!part[0].equals("" + j)) {
									System.out.println("file is damaged!");
									System.out.println("Errorcode: 3; Line: " + (j + 1));
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
			System.out.println("'coinages" + i + ".co' is accessible");
		}
	}

}
