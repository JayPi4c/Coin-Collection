package com.JayPi4c.CoinCollection.controller;

import com.JayPi4c.CoinCollection.model.Coin;
import com.JayPi4c.CoinCollection.service.CoinService;
import com.JayPi4c.CoinCollection.view.panes.ActionPane;

import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionController {

	private ActionPane actionPane;

	public ActionController(ActionPane actionPane) {
		this.actionPane = actionPane;

		actionPane.getCheckButton().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> handleCheck());

		actionPane.getToggleButton().addEventHandler(MouseEvent.MOUSE_PRESSED, e -> handleToggle());
	}

	private void handleCheck() {
		int year = Integer.parseInt(actionPane.getYearsComboBox().getValue());
		double value = Double.parseDouble(actionPane.getValuesComboBox().getValue());
		String country = actionPane.getCountryComboBox().getValue();

		Coin c = CoinService.getCoin(year, value, country);
		if (c == null) {
			log.debug("Could not find coin for {}, {}, {}", year, value, country);
			SnackbarController.getInstance().showMessage("Coin not available");
		} else {
			log.debug("Found coin for {}, {}, {}. Possession: {}", year, value, country, c.getPossession());
			SnackbarController.getInstance()
					.showMessage("Coin" + (Boolean.TRUE.equals(c.getPossession()) ? " " : " not ") + "available");
		}
	}

	private void handleToggle() {
		int year = Integer.parseInt(actionPane.getYearsComboBox().getValue());
		double value = Double.parseDouble(actionPane.getValuesComboBox().getValue());
		String country = actionPane.getCountryComboBox().getValue();

		Coin c = CoinService.getCoin(year, value, country);
		if (c == null) {
			log.debug("Could not find coin for {}, {}, {}", year, value, country);
			c = new Coin(year, value, country, true);
			CoinService.save(c);
			SnackbarController.getInstance().showMessage("New coin added to collection");
		} else {
			log.debug("Found coin for {}, {}, {}. Possession: {}", year, value, country, c.getPossession());
			c.setPossession(!c.getPossession());
			CoinService.update(c);
			SnackbarController.getInstance().showMessage(
					"Coin is" + (Boolean.TRUE.equals(c.getPossession()) ? " now " : " no more ") + "in possession");

		}

	}

}
