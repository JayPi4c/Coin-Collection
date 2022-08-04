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
	private JButton credits;

	public FeaturePanel() throws IOException {
		this.setLayout(null);

		reset = new JButton("reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(null,
						"Sind Sie sicher, dass sie die gesamte sammlung l\u00F6schen m\u00F6chten?",
						"Sammlung l\u00F6schen", JOptionPane.YES_NO_OPTION);

				if (dialogButton == JOptionPane.YES_OPTION) {
					Util.log.info("All files are getting deleted!");
					try {
						Util.resetCollection();
						Util.log.info("reset is done!");
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else
					Util.log.info("Nothing will happen");
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

		credits = new JButton("Credits");
		credits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Idee: JayPi4c\n" + "Programmer: JayPi4c\n" + "Design: JayPi4c\n"
								+ "Inspirationen: Wolfgang Pohl, Martina Pohl, Oliver Pohl, Lukas Tetzlaff",
						"Credits", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		credits.setSize(100, 20);
		credits.setVisible(true);
		this.add(credits);

	}

	public void relocate(int width, int height) {
		reset.setLocation(width - 120, height - 80);
		credits.setLocation(20, height - 80);
	}

	public void refresh() throws IOException {
		worth.setText("Der Wert der Sammlung betr\u00e4gt: " + Util.getWorth() + "\u20AC.");
	}

}
