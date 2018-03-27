package com.JayPi4c.gui;

import javax.swing.JTabbedPane;

public class TabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 7270368939760559248L;
	private MainPanel mp;
	private FeaturePanel fp;

	public TabbedPane() {
		super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		mp = new MainPanel();
		this.addTab("Main", mp);
		fp = new FeaturePanel();
		this.addTab("Features", fp);
	}

	@Override
	public void resize(int width, int height) {
		MainPanel m = (MainPanel) this.getComponentAt(0);
		m.relocate(width, height);
		FeaturePanel f = (FeaturePanel) this.getComponentAt(1);
		f.relocate(width, height);
	}

}
