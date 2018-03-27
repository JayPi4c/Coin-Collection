package com.JayPi4c.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = -8888683961737387330L;

	private int minWidth = 425;
	private int minHeight = 250;

	private TabbedPane tb;

	public Frame(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(640, 360);
		this.setLocationRelativeTo(null);

		tb = new TabbedPane();
		tb.setVisible(true);
		this.add(tb);

		this.getRootPane().addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {

			}

			@Override
			public void componentResized(ComponentEvent e) {
				if (getWidth() < minWidth)
					setSize(minWidth, getHeight());
				if (getHeight() < minHeight)
					setSize(getWidth(), minHeight);
				tb.resize(getWidth(), getHeight());
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {

			}
		});

		this.setVisible(true);
	}

}
