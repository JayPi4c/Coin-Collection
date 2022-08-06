package com.JayPi4c.CoinCollection;

import com.JayPi4c.CoinCollection.utils.I18nUtils;
import com.JayPi4c.CoinCollection.utils.PropertiesLoader;
import com.JayPi4c.CoinCollection.view.MainStage;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

	/**
	 * Application entry point
	 * 
	 * @param args the arguments from the command line
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		I18nUtils.setLocale(PropertiesLoader.getInstance().getProperty("lang"));
		log.info("Initialization successful!");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		log.info("Starting Application");
		new MainStage();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		PropertiesLoader.getInstance().finish();
		log.info("Stopped successful!");
	}

}
