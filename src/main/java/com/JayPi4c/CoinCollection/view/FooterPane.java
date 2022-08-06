package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FooterPane extends Pane {

	private Label informationLabel;

	public FooterPane() {
		informationLabel = new Label();
		informationLabel.textProperty().bind(createBinding("footer.text"));
		getChildren().add(informationLabel);
		setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
	}

}
