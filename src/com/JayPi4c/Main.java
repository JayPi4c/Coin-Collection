package com.JayPi4c;

import java.io.IOException;

import com.JayPi4c.gui.Frame;
import com.JayPi4c.utils.Init;
import com.JayPi4c.utils.PreInit;

public class Main {
	//kleiner Hello World Kommentar
	public static void main(String args[]) throws IOException {
		PreInit.begin();
		Init.begin();
		new Frame("CoinCollectionRefactored");
	}
}
