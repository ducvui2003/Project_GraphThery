package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

public class ButtonFileChoose extends JButton implements ActionListener {
	JButton button;
	String linkFile;

	public ButtonFileChoose(String name) {
		super();
		this.button = new JButton(name);
		this.button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter txtFile = new FileNameExtensionFilter("File dang txt", ".txt");
		fileChooser.setFileFilter(txtFile);
		fileChooser.setMultiSelectionEnabled(false);
		MyFrame frame = new MyFrame();
		int x = fileChooser.showDialog(frame, "Choose File");
		if (x == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			linkFile = f.getAbsolutePath();
			System.out.println(linkFile);
		}
	}
}
