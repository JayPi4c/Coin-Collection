package com.JayPi4c.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class TablePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tb;
	private JScrollPane sp;
	private Table table;

	public TablePanel() {
		this.setLayout(null);
		tb = new JTabbedPane();
		tb.setVisible(true);
		sp = new JScrollPane();
		table = new Table();
		sp.add(table);
		sp.setVisible(true);
		tb.add("erste Tabelle", sp);
		this.setVisible(true);
	}

}
