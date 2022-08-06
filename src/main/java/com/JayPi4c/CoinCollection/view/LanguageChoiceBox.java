package com.JayPi4c.CoinCollection.view;

import java.util.Locale;

import com.JayPi4c.CoinCollection.controller.SnackbarController;
import com.JayPi4c.CoinCollection.utils.I18nUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

/**
 * Class to handle language choice
 *
 * @author Jonas Pohl
 * @since 2.0.0
 */
public class LanguageChoiceBox extends ChoiceBox<LanguageChoiceBox.LanguageEntry> {

	private final ObservableList<LanguageEntry> languageEntries;

	public LanguageChoiceBox() {
		languageEntries = FXCollections.observableArrayList();
		languageEntries.addAll(new LanguageEntry("Deutsch", Locale.GERMANY), new LanguageEntry("English", Locale.UK));
		setItems(languageEntries);
		selectID(I18nUtils.getLocale());

		I18nUtils.bundleProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				selectID(newValue.getLocale());
				SnackbarController.getInstance().showMessage(I18nUtils.i18n("settings.language.changed.snackbar"));
			}
		});

		setOnAction(e -> I18nUtils.setLocale(getValue().locale()));
	}

	private void selectID(Locale locale) {
		languageEntries.stream().filter(e -> e.locale().equals(locale)).findFirst()
				.ifPresent(le -> getSelectionModel().select(le));
	}

	record LanguageEntry(String language, Locale locale) {

		public String toString() {
			return language;
		}

		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (o instanceof LanguageEntry languageEntry)
				return locale.equals(languageEntry.locale);
			return false;
		}
	}

}