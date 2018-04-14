package com.JayPi4c.gui;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.JayPi4c.utils.Attributes;
import com.JayPi4c.utils.Util;

public class TableTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1L;

	public TableTabbedPane() {
		for (int i = 0; i < Attributes.getYears().length; i++) {
			int year = Integer.parseInt(Attributes.getYears()[i]);
			int members = Util.getMembersFromYear(year);
			String rowData[][] = new String[8][members];
			String columnNames[] = new String[members];
			for (int j = 0; j < members; j++) {
				columnNames[j] = Attributes.getCountryNames()[j];
				rowData[0][j] = "1ct";
				rowData[1][j] = "2ct";
				rowData[2][j] = "5ct";
				rowData[3][j] = "10ct";
				rowData[4][j] = "20ct";
				rowData[5][j] = "50ct";
				rowData[6][j] = "1\u20AC";
				rowData[7][j] = "2\u20AC";
			}
			this.add("" + year, new JScrollPane(new Table(rowData, columnNames, year)));
		}
	}

}
