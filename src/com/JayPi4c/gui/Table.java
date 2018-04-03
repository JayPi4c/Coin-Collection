package com.JayPi4c.gui;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.JayPi4c.utils.Util;

public class Table extends JTable {

	private static final long serialVersionUID = -8959754359475426474L;

	private int year;

	public Table(String rowData[][], String columnNames[], int year) {
		// super(a, b); a und b müssen gleich groß sein!
		super(rowData, columnNames);
		this.year = year;

		this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				try {
					setBackground(getColor(column, row));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return this;
			}
		});
	}

	public Color getColor(int col, int row) throws IOException {
		File file = new File(Util.getPath(year));
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		for (int i = 0; i < col; i++) {
			line = bufferedReader.readLine();
		}

		String[] parts = line.split(";");
		String[] part = parts[row].split(",");
		bufferedReader.close();
		return (part[2].equals("1") ? Color.GREEN : Color.RED);

	}
}
