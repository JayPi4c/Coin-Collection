package com.JayPi4c.CoinCollection.view;

import static com.JayPi4c.CoinCollection.utils.I18nUtils.createBinding;

import com.JayPi4c.CoinCollection.controller.ActionController;
import com.JayPi4c.CoinCollection.controller.HeaderController;
import com.JayPi4c.CoinCollection.controller.SnackbarController;
import com.JayPi4c.CoinCollection.view.panes.ActionPane;
import com.JayPi4c.CoinCollection.view.panes.MainPane;
import com.JayPi4c.CoinCollection.view.panes.SettingsPane;
import com.JayPi4c.CoinCollection.view.panes.TablePane;
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
	private HeaderController headerController;
	private ActionController actionController;

	// UI
	private StackPane container;
	private HeaderPane headerPane;
	private FooterPane footerPane;

	private Drawer drawer;

	private JFXDrawersStack drawersStack;

	private MainPane mainPane;
	private ActionPane actionPane;
	private SettingsPane settingsPane;
	private TablePane tablePane;

	private JFXSnackbar snackbar;

	static {
		log.debug("Loading images");
		icon = new Image("/img/icon.jpg");
	}

	public MainStage() {
		getIcons().add(icon);
		titleProperty().bind(createBinding("collection.main.title"));

		container = new StackPane();
		mainPane = new MainPane();
		actionPane = new ActionPane();
		settingsPane = new SettingsPane();
		tablePane = new TablePane();

		container.getChildren().add(mainPane);
		VBox.setVgrow(container, Priority.ALWAYS);

		headerPane = new HeaderPane();
		footerPane = new FooterPane();

		var vBox = new VBox(headerPane, container, footerPane);
		vBox.setFillWidth(true);

		SnackbarController.getInstance().init(vBox);

		drawer = new Drawer(container, mainPane, actionPane, tablePane, settingsPane);

		drawersStack = new JFXDrawersStack();
		drawersStack.setContent(vBox);

		headerController = new HeaderController(headerPane, drawer, drawersStack);
		actionController = new ActionController(actionPane);

		Scene scene = new Scene(drawersStack);
		setScene(scene);

		setResizable(true);
		setMinWidth(MIN_WIDTH);
		setMinHeight(MIN_HEIGHT);

		show();
		log.debug("finished loading MainStage");

	}

}
