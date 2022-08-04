package com.JayPi4c.CoinCollection;

import com.JayPi4c.CoinCollection.gui.Frame;
import com.JayPi4c.CoinCollection.utils.Init;
import com.JayPi4c.CoinCollection.utils.Util;

/**
 * 
 * @author JayPi4c
 * @version 2.0.0
 *
 */
public class Launcher {

	/**
	 * Die main-Methode wird bei jedem Programmstart ausgef&uumlhrt. Es wird die
	 * PreInit-Klasse, sowie die Init Klasse ausgef&uumlhrt, um die wichtigsten
	 * Informationen zu speichern. Nachdem die Init-Methoden abgeschlossen sind,
	 * wird eine neue Instanz der Frame-Klasse erstellt, die jegliche Aufgaben in
	 * der GUI &uumlbernimmt.
	 * 
	 * @param args die Parameter, die &uumlbergeben werden, wenn das Programm
	 *             &uumlber die Konsole mit weiteren Parameter gestartet werden.
	 * 
	 * @since 1.0.0
	 */
	public static void main(String args[]) {
		Util.initializeLogger();
		Util.loadProperties();
		Init.begin();
		Util.log.info("Initialization successfully!");
		new Frame("CoinCollectionRefactored");
	}
}
