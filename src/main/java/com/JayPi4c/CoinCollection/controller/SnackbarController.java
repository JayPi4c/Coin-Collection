package com.JayPi4c.CoinCollection.controller;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSnackbarLayout;

import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SnackbarController {

	private JFXSnackbar snackbar;

	private static SnackbarController instance;

	public static SnackbarController getInstance() {
		if (instance == null)
			instance = new SnackbarController();
		return instance;
	}

	public void init(Pane root) {
		snackbar = new JFXSnackbar(root);
	}

	public void showMessage(String message) {
		log.debug("Showing snackbar-message: {}", message);
		snackbar.fireEvent(new SnackbarEvent(new JFXSnackbarLayout(message)));
	}

}
