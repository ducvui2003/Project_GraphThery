package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import run.FrameDemo;
import view.MyFrame;

public class ShowDialogFile implements ActionListener {
	JFrame frameDemo = FrameDemo.frame;
	String linkFile;

	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter txtFile = new FileNameExtensionFilter(".txt", "txt");
		fileChooser.setFileFilter(txtFile);
		fileChooser.setMultiSelectionEnabled(false);
		int x = fileChooser.showDialog(frameDemo, "Choose File");
		if (x == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			linkFile = f.getAbsolutePath();
			System.out.println(linkFile);
		}
	}

	public String getLinkFile() {
		return linkFile;
	}

	public void setLinkFile(String linkFile) {
		this.linkFile = linkFile;
	}
}
