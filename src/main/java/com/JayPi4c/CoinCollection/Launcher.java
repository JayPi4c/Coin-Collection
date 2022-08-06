package com.JayPi4c.CoinCollection;

import javax.swing.JOptionPane;

/**
 * 
 * @author JayPi4c
 * @version 2.0.0
 *
 */

public class Launcher {

	/**
	 * Application entry point
	 * 
	 * @param args the arguments from the command line
	 *
	 * @since 1.0.0
	 */
	public static void main(String args[]) {
		try {
			App.main(args);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, """
					Failed to load application.
					Further information can be obtained running the
					application in the command line.
					Please consider submitting your error.
					""");
		}
	}
}
