package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MyFrame;

public class ShowCheckBox implements ActionListener {
	MyFrame frame;

	public ShowCheckBox(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == frame.getCheckBoxNonNegative()) {
			frame.getCheckBoxNonDirect().setEnabled(true);
		}

	}
}
