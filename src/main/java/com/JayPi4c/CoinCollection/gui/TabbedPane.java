package com.JayPi4c.CoinCollection.gui;

import java.io.IOException;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 7270368939760559248L;
	private MainPanel mp;
	private FeaturePanel fp;
	private TableTabbedPane tp;

	public TabbedPane() throws IOException {
		super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		mp = new MainPanel();
		this.addTab("Main", mp);
		fp = new FeaturePanel();
		this.addTab("Features", fp);
		tp = new TableTabbedPane();
		this.addTab("Tables", tp);

		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (getSelectedIndex() == 1) {
					try {
						fp.refresh();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void resize(int width, int height) {
		MainPanel m = (MainPanel) this.getComponentAt(0);
		m.relocate(width, height);
		FeaturePanel f = (FeaturePanel) this.getComponentAt(1);
		f.relocate(width, height);
	}

}
