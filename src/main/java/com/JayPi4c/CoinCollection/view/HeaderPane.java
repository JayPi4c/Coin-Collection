package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import com.jfoenix.controls.JFXHamburger;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;

@Getter
public class HeaderPane extends Pane {

	private Label informationLabel;

	private JFXHamburger hamburger;

	public HeaderPane() {

		hamburger = new JFXHamburger();

		informationLabel = new Label();
		informationLabel.textProperty().bind(createBinding("header.text"));
		var hBox = new HBox(hamburger, informationLabel);
		getChildren().add(hBox);
		// setBackground(new Background(new BackgroundFill(Color.GREEN,
		// CornerRadii.EMPTY, Insets.EMPTY)));
	}

}