package com.JayPi4c.CoinCollection.view;

import com.jfoenix.controls.JFXButton;

import javafx.beans.binding.StringBinding;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Getter;

@Getter
public abstract class ContentPane extends Pane {

	protected JFXButton drawerButton;

	protected ContentPane(StringBinding drawerButtonBinding) {
		setMinHeight(100);
		VBox.setVgrow(this, Priority.ALWAYS);

		drawerButton = new JFXButton();
		drawerButton.textProperty().bind(drawerButtonBinding);
	}

}
