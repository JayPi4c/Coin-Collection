package com.JayPi4c.CoinCollection.view;

import java.util.ArrayList;
import java.util.List;

import com.JayPi4c.CoinCollection.view.panes.ContentPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Drawer extends JFXDrawer {

	private StackPane drawerPane;

	public Drawer(StackPane container, ContentPane... contentPanes) {
		super();
		List<JFXButton> buttons = new ArrayList<>();
		for (ContentPane contentPane : contentPanes) {
			contentPane.getDrawerButton().addEventHandler(MouseEvent.MOUSE_PRESSED,
					e -> switchPanes(container, contentPane));
			buttons.add(contentPane.getDrawerButton());
		}

		VBox drawerVBox = new VBox(buttons.toArray(JFXButton[]::new));

		drawerPane = new StackPane(drawerVBox);

		setSidePane(drawerPane);
		// setDefaultDrawerSize(150);
		setResizeContent(true);
		setOverLayVisible(false);
		setResizableOnDrag(false);
	}

	private void switchPanes(StackPane container, ContentPane content) {
		container.getChildren().clear();
		container.getChildren().add(content);
	}

}
