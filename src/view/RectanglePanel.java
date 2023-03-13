package view;

import java.awt.BasicStroke;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class RectanglePanel extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private String nameTitleBorder;

	public RectanglePanel(String nameTitleBorder) {
		super();
		this.nameTitleBorder = nameTitleBorder;
		setBorder();
	}

	public void setBorder() {
		TitledBorder titledBorder = new TitledBorder(nameTitleBorder);
		setBorder(titledBorder);
//		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5)));
	}
}
