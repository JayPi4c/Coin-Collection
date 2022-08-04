package com.JayPi4c.CoinCollection.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MainPanel extends JLabel {

	private static final long serialVersionUID = 3031270467197343738L;

	private JButton Exit;
	private SelectionPanel sp;
	private ActionPanel ap;
	private JLabel label;

	public MainPanel() {
		this.setLayout(null);
		Exit = new JButton("Exit");
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exit.setSize(60, 25);
		Exit.setVisible(true);
		this.add(Exit);

		label = new JLabel("Welcome to 'Coin Collection'");
		label.setSize(300, 40);
		label.setVisible(true);
		this.add(label);

		sp = new SelectionPanel(label);
		ap = new ActionPanel(label);
		sp.setPanel(ap);
		ap.setPanel(sp);
		ap.setVisible(true);
		sp.setVisible(true);
		this.add(sp);
		this.add(ap);
	}

	public void relocate(int width, int height) {
		Exit.setLocation(getWidth() - Exit.getWidth() - 10, getHeight() - Exit.getHeight() - 10);
		ap.relocate(width, height);
		label.setLocation(20, getHeight() - label.getHeight());
	}

}
