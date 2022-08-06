package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class FooterPane extends StackPane {

	private Label informationLabel;

	public FooterPane() {
		informationLabel = new Label();
		informationLabel.textProperty().bind(createBinding("footer.text"));
		getChildren().add(informationLabel);
		setAlignment(informationLabel, Pos.CENTER);

		// setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY,
		// Insets.EMPTY)));
	}

}
