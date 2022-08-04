package com.JayPi4c.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.JayPi4c.utils.Util;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = -5553959081997675549L;

	private JButton add;
	private JButton remove;
	private SelectionPanel sp;
	public JLabel label;

	public ActionPanel(JLabel label) {
		this.label = label;
		this.setLayout(null);
		setSize(130, 50);

		add = new JButton("Add");
		add.setEnabled(false);
		add.setBounds(0, 0, 130, 20);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String content[] = sp.getValues();
				try {
					Util.updateRegistry(content[0], Double.parseDouble(content[2]), Integer.parseInt(content[1]), true);
					add.setEnabled(false);
					remove.setEnabled(true);
					label.setText("State successfully updated! State: 1");
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		add.setVisible(true);
		this.add(add);

		remove = new JButton("Remove");
		remove.setEnabled(false);
		remove.setBounds(0, 30, 130, 20);
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String content[] = sp.getValues();
				try {
					Util.updateRegistry(content[0], Double.parseDouble(content[2]), Integer.parseInt(content[1]),
							false);
					remove.setEnabled(false);
					add.setEnabled(true);
					label.setText("State successfuly updated! State: 0");
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		remove.setVisible(true);
		this.add(remove);
	}

	public void setPanel(SelectionPanel sp) {
		this.sp = sp;
	}

	public void relocate(int width, int height) {
		this.setLocation(width - 150, 20);
	}

	public void setRemove(boolean state) {
		remove.setEnabled(state);
	}

	public void setAdd(boolean state) {
		add.setEnabled(state);
	}
}
