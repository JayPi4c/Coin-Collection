package com.JayPi4c.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.JayPi4c.utils.Util;

public class FeaturePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton reset;
	private JLabel worth;

	public FeaturePanel() throws IOException {
		this.setLayout(null);

		reset = new JButton("reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(null,
						"Sind Sie sicher, dass sie die gesamte sammlung löschen möchten?", "Sammlung löschen",
						JOptionPane.YES_NO_OPTION);

				if (dialogButton == JOptionPane.YES_OPTION) {
					System.out.println("All files are getting deleted!");
					try {
						Util.resetCollection();
						System.out.println("reset is done!");
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else
					System.out.println("Nothing will happen");
				try {
					refresh();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		reset.setSize(100, 20);
		reset.setVisible(true);
		this.add(reset);

		worth = new JLabel();
		refresh();
		worth.setSize(1000, 20);
		worth.setVisible(true);
		add(worth);

	}

	public void relocate(int width, int height) {
		reset.setLocation(width - 120, height - 80);
	}

	public void refresh() throws IOException {
		worth.setText("Der Wert der Sammlung beträgt: " + Util.getWorth() + "€.");
	}

}
