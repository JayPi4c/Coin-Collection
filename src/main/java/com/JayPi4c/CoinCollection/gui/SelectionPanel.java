package com.JayPi4c.CoinCollection.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.JayPi4c.CoinCollection.utils.Util;

public class SelectionPanel extends JPanel {

	private static final long serialVersionUID = 3472383949829184148L;

	private JComboBox<String> ValueBox;
	private JComboBox<String> YearBox;
	private JComboBox<String> CountryBox;
	private JComboBox<String> GermanPerfecture;
	private ActionPanel ap;
	public JLabel label;

	public SelectionPanel(JLabel label) {
		this.label = label;

		this.setLayout(null);
		this.setBounds(20, 20, 150, 110);

		ValueBox = new JComboBox<String>(Util.getValues());
		ValueBox.setBounds(0, 0, 150, 20);
		ValueBox.setSelectedIndex(7);
		ValueBox.setVisible(true);
		ValueBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					activateActionPanel();
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(ValueBox);

		YearBox = new JComboBox<String>(Util.getYears());
		YearBox.setBounds(0, 30, 150, 20);
		YearBox.setSelectedIndex(4);
		YearBox.setVisible(true);
		YearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					activateActionPanel();
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(YearBox);

		CountryBox = new JComboBox<String>(Util.getCountryNames());
		CountryBox.setBounds(0, 60, 150, 20);
		CountryBox.setSelectedIndex(1);
		CountryBox.setVisible(true);
		CountryBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (CountryBox.getSelectedItem().equals("Deutschland"))
					GermanPerfecture.setVisible(true);
				else {
					GermanPerfecture.setVisible(false);
					try {
						activateActionPanel();
					} catch (NumberFormatException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		add(CountryBox);

		GermanPerfecture = new JComboBox<String>(Util.getPerfectures());
		GermanPerfecture.setBounds(0, 90, 150, 20);
		GermanPerfecture.setSelectedIndex(1);
		GermanPerfecture.setVisible(true);
		add(GermanPerfecture);

		this.setVisible(true);
	}

	public void setPanel(ActionPanel ap) {
		this.ap = ap;
	}

	private void activateActionPanel() throws NumberFormatException, IOException {
		String Country = (String) CountryBox.getSelectedItem();
		String Year = (String) YearBox.getSelectedItem();
		String Value = (String) ValueBox.getSelectedItem();
		if (Util.getMembersFromYear(Integer.parseInt(Year)) < CountryBox.getSelectedIndex()) {
			Util.log.info(Country + "+" + Year + "+" + Value + " do not match!");
			label.setText("That's not possible at all!");
			ap.setAdd(false);
			ap.setRemove(false);
		} else if (Util.getStatus(Country, Double.parseDouble(Value), Integer.parseInt(Year))) {
			Util.log.info("Coin is in possesion!");
			label.setText("Coin is in your possesion");
			ap.setRemove(true);
			ap.setAdd(false);
		} else {
			Util.log.info("Coin is not in possesion!");
			label.setText("Coin is not in your possesion!");
			ap.setRemove(false);
			ap.setAdd(true);
		}
	}

	public String[] getValues() {
		String content[] = new String[3];
		content[0] = (String) CountryBox.getSelectedItem();
		content[1] = (String) YearBox.getSelectedItem();
		content[2] = (String) ValueBox.getSelectedItem();
		return content;
	}

}
