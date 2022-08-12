package com.JayPi4c.CoinCollection.view.panes;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import com.JayPi4c.CoinCollection.view.LanguageChoiceBox;

public class SettingsPane extends ContentPane {

	public SettingsPane() {
		super(createBinding("settingsPane.drawerButton"));

		LanguageChoiceBox languageChoiceBox = new LanguageChoiceBox();

		getChildren().addAll(languageChoiceBox);
	}

}