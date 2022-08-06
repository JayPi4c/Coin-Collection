package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

public class SettingsPane extends ContentPane {

	protected SettingsPane() {
		super(createBinding("settingsPane.drawerButton"));

		LanguageChoiceBox languageChoiceBox = new LanguageChoiceBox();

		getChildren().addAll(languageChoiceBox);
	}

}
