package com.JayPi4c.CoinCollection.view;

import com.JayPi4c.CoinCollection.controller.HeaderController;
import com.JayPi4c.CoinCollection.controller.SnackbarController;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXSnackbar;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author Jonas Pohl
 * @version 2.0.0
 */
@Slf4j
@Getter
public class MainStage extends Stage {

	private static final Image icon;
	private static final int MIN_WIDTH = 640;
	private static final int MIN_HEIGHT = 360;
	// controllers
	HeaderController headerController;

	// UI
	StackPane container;
	HeaderPane headerPane;
	FooterPane footerPane;

	Drawer drawer;

	JFXDrawersStack drawersStack;

	MainPane mainPane;
	ActionPane actionPane;
	SettingsPane settingsPane;

	JFXSnackbar snackbar;

	static {
		log.debug("Loading images");
		icon = new Image("/img/icon.jpg");
	}

	public MainStage() {
		getIcons().add(icon);

		container = new StackPane();
		mainPane = new MainPane();
		actionPane = new ActionPane();
		settingsPane = new SettingsPane();

		container.getChildren().add(mainPane);
		VBox.setVgrow(container, Priority.ALWAYS);

		headerPane = new HeaderPane();
		footerPane = new FooterPane();

		var vBox = new VBox(headerPane, container, footerPane);
		vBox.setFillWidth(true);

		SnackbarController.getInstance().init(vBox);

		drawer = new Drawer(container, mainPane, actionPane, settingsPane);

		drawersStack = new JFXDrawersStack();
		drawersStack.setContent(vBox);

		headerController = new HeaderController(headerPane, drawer, drawersStack);

		Scene scene = new Scene(drawersStack);
		setScene(scene);

		setResizable(true);
		setMinWidth(MIN_WIDTH);
		setMinHeight(MIN_HEIGHT);

		show();
		log.debug("finished loading MainStage");

	}

}
