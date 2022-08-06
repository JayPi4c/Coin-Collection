package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import com.JayPi4c.CoinCollection.controller.SnackbarController;
import com.jfoenix.controls.JFXButton;

import javafx.scene.input.MouseEvent;

public class ActionPane extends ContentPane {

	public ActionPane() {
		super(createBinding("actionPane.drawerButton"));

		JFXButton b = new JFXButton("ActionPane");
		b.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> SnackbarController.getInstance().showMessage("action"));
		this.getChildren().add(b);
	}

}
