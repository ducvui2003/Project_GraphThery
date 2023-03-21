package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import model.DirectGraph;
import model.Graph;
import model.NonDirectGraph;
import model.Util;
import view.MyFrame;

public class ShowFileChoose implements ActionListener {
	MyFrame frame;

	public ShowFileChoose(MyFrame frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("src\\data"));
		FileNameExtensionFilter txtFile = new FileNameExtensionFilter("Text files (*.txt)", "txt");
		fileChooser.setFileFilter(txtFile);
		fileChooser.setMultiSelectionEnabled(false);
		int x = fileChooser.showDialog(frame, "Choose File");
		if (x == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			frame.setLinkFile(f.getAbsolutePath());
		}
		System.out.println(frame.getLinkFile());
		showMatrix(frame.linkFile);
	}

	public void showMatrix(String linkFile) {
		if (linkFile == null) {
			frame.getAreaMatrix().setText("Không thấy ma trận");
		} else {
			Graph unGraph = new DirectGraph(linkFile);
			System.out.println(Util.formatMatrix(unGraph.getMatrix()) + "");
			frame.areaMatrix.setText(Util.formatMatrix(unGraph.getMatrix()));
		}
	}
}
