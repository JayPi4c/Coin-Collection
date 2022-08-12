package com.JayPi4c.CoinCollection.view.panes;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import java.util.Calendar;

import com.JayPi4c.CoinCollection.utils.PropertiesLoader;
import com.jfoenix.controls.JFXButton;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import lombok.Getter;

@Getter
public class ActionPane extends ContentPane {

	private JFXButton checkButton;
	private JFXButton toggleButton;

	private ComboBox<String> yearsComboBox;
	private ComboBox<String> valuesComboBox;
	private ComboBox<String> countryComboBox;

	public ActionPane() {
		super(createBinding("actionPane.drawerButton"));

		valuesComboBox = new ComboBox<>();
		valuesComboBox.getItems().addAll("0.01", "0.02", "0.05", "0.10", "0.20", "0.50", "1.00", "2.00");
		valuesComboBox.getSelectionModel().selectFirst();

		countryComboBox = new ComboBox<>();
		countryComboBox.getItems().addAll(PropertiesLoader.getInstance().getProperty("countries").split(","));
		countryComboBox.getSelectionModel().selectFirst();

		yearsComboBox = new ComboBox<>();
		for (int i = 1999; i < Calendar.getInstance().get(Calendar.YEAR); i++) {
			yearsComboBox.getItems().add(Integer.toString(i));
		}
		yearsComboBox.getSelectionModel().selectFirst();

		checkButton = new JFXButton("check");
		toggleButton = new JFXButton("toggle");

		VBox vBox = new VBox(valuesComboBox, countryComboBox, yearsComboBox, checkButton, toggleButton);

		this.getChildren().add(vBox);
	}

}
