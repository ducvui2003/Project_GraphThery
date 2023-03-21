package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DirectGraph;
import model.Graph;
import model.NonDirectGraph;
import view.MyFrame;

public class ShowCheckBox implements ActionListener {
	MyFrame frame;

	public ShowCheckBox(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.getCheckBoxNonNegative() && frame.getCheckBoxNonNegative().isSelected()) {
			frame.getCheckBoxNonDirect().setEnabled(true);
			Graph dir = new DirectGraph(null);
		}
		if (e.getSource() == frame.getCheckBoxNonDirect()) {
			Graph un = new NonDirectGraph(null);
		}

	}
}
