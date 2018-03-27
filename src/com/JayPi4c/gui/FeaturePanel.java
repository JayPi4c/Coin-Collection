package com.JayPi4c.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FeaturePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton reset;
	private JLabel worth;

	public FeaturePanel() {
		this.setLayout(null);

		reset = new JButton("reset");
		reset.setSize(100, 100);
		reset.setVisible(true);
		this.add(reset);

		worth = new JLabel("Ich bin ein Label");
		worth.setLocation(100, 100);
		worth.setVisible(true);
		add(worth);
	}

	public void relocate(int width, int height) {
		reset.setLocation(250, 250);
	}
}
