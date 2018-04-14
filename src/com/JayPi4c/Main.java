package com.JayPi4c;

import java.io.IOException;

import com.JayPi4c.gui.Frame;
import com.JayPi4c.utils.Init;
import com.JayPi4c.utils.PreInit;

/**
 * 
 * @author JayPi4c
 * @version 1.0.0
 *
 */
public class Main {
	/**
	 * Die main-Methode wird bei jedem Programmstart ausgef&uumlhrt. Es wird die
	 * PreInit-Klasse, sowie die Init Klasse ausgef&uumlhrt, um die wichtigsten
	 * Informationen zu speichern. Nachdem die Init-Methoden abgeschlossen sind,
	 * wird eine neue Instanz der Frame-Klasse erstellt, die jegliche Aufgaben in
	 * der GUI �bernimmt.
	 * 
	 * @param args
	 *            die Parameter, die &uumlbergeben werden, wenn das Programm
	 *            &uumlber die Konsole mit weiteren Parameter gestartet werden.
	 * @throws IOException
	 *             Da bei jedem Start des Programms Daten aus externen Dateien
	 *             ausgelesen werden m&uumlssen k&oumlnnen immer Fehler entstehen,
	 *             wenn zum Beispiel der "data" Ordner nicht in dem directory ist.
	 * @since 1.0.0
	 */
	public static void main(String args[]) throws IOException {
		PreInit.begin();
		Init.begin();
		new Frame("CoinCollectionRefactored");
	}
}
