package com.JayPi4c.CoinCollection.controller;

import com.JayPi4c.CoinCollection.view.HeaderPane;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.scene.input.MouseEvent;

public class HeaderController {

	public HeaderController(HeaderPane header, JFXDrawer drawer, JFXDrawersStack drawersStack) {
		HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(header.getHamburger());
		burgerTask.setRate(-1);
		header.getHamburger().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			burgerTask.setRate(burgerTask.getRate() * -1);
			burgerTask.play();
			drawersStack.toggle(drawer);
		});

	}

}
