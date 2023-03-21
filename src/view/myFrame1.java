package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import controller.ShowDialogFile;

public class myFrame1 extends JFrame {

	private JFrame frame;
	String nameProgram = "Group 9";
	ShowDialogFile showDialogFile = new ShowDialogFile();
	JButton buttonOpenChooseFile;
	MyDraw myDraw = new MyDraw();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myFrame1 window = new myFrame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myFrame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		setJMenuBar(createMenuBar());
		this.setLayout(new BorderLayout());
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(Color.red);
		this.add(panelNorth, BorderLayout.NORTH);
		panelNorth.add(createPanelRequire1_2_3());

		JPanel panelWest = new JPanel();
		this.add(panelWest, BorderLayout.EAST);
		panelWest.setBackground(Color.black);
		panelWest.add(createPanelRequire4_5());

		JPanel panelCenter = new JPanel(new BorderLayout());
		panelCenter.add(myDraw, BorderLayout.CENTER);
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.add(createPanelShowGraphic());

		JPanel panelSouth = new JPanel(new BorderLayout());
		this.add(panelSouth, BorderLayout.SOUTH);
		JTextArea area = new JTextArea(4, 5);
		panelSouth.add(area, BorderLayout.CENTER);
		area.setEditable(false);
		display();
	}

	public void display() {
		this.setTitle(nameProgram);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public JMenuBar createMenuBar() {
		JMenu menu = new JMenu("File");

		menu.add(createMenuItem("New"));
		menu.add(createMenuItem("Open"));
		menu.add(createMenuItem("Save"));
		menu.addSeparator();
		menu.add(createMenuItem("Exit"));

		JMenu menuHelp = new JMenu("Help");
		menuHelp.add(createMenuItem("Help"));
		menuHelp.add(createMenuItem("About"));

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		menuBar.add(menuHelp);
		return menuBar;
	}

	public JMenuItem createMenuItem(String title) {
		JMenuItem menuItem = new JMenuItem(title);
//		menuItem.addActionListener((ActionListener) controller);
		return menuItem;
	}

	public JPanel createPanelRequire1_2_3() {
//	Panel North 
		JPanel panelChild = new JPanel();
		panelChild.setLayout(new BoxLayout(panelChild, BoxLayout.X_AXIS));
		RectanglePanel panel1 = new RectanglePanel("Chọn file");
		buttonOpenChooseFile = new JButton("Choose file");
		buttonOpenChooseFile.addActionListener(showDialogFile);
		panel1.add(buttonOpenChooseFile);
		panel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		RectanglePanel panel2 = new RectanglePanel("Yêu cầu 1");
		panel2.add(new Label("kjaslkdsja"));
		panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		RectanglePanel panel3 = new RectanglePanel("Yêu cầu 2");
		panel3.add(new Label("kjaslkdsja"));
		panel3.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelChild.add(panel1);
		panelChild.add(panel2);
		panelChild.add(panel3);
		JPanel panelParent = new JPanel();
		panelParent.setLayout(new BorderLayout());
		panelParent.add(panelChild, BorderLayout.CENTER);
		return panelParent;

	}

	public JPanel createPanelShowGraphic() {
		JPanel panel = new JPanel(new BorderLayout());
		MyDraw drawPanel = new MyDraw();
		drawPanel.setLayout(new BoxLayout(drawPanel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.yellow);
		panel.add(drawPanel, BorderLayout.CENTER);
		return panel;
	}

	public JPanel createPanelRequire4_5() {
		JPanel panelChild = new JPanel();
		panelChild.setLayout(new BoxLayout(panelChild, BoxLayout.Y_AXIS));
		RectanglePanel panel1 = new RectanglePanel("Yêu cầu 3");
		panel1.setAlignmentX(Component.TOP_ALIGNMENT);
		RectanglePanel panel2 = new RectanglePanel("Yêu cầu 4");
		panel2.add(new Label("kjaslkdsja"));
		panel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		RectanglePanel panel3 = new RectanglePanel("Yêu cầu 5");
		panel3.add(new Label("kjaslkdsja"));
		panel3.setAlignmentX(Component.BOTTOM_ALIGNMENT);
		panelChild.add(panel1);
		panelChild.add(panel2);
		panelChild.add(panel3);
		JPanel panelParent = new JPanel();
		panelParent.setLayout(new BorderLayout());
		panelParent.add(panelChild, BorderLayout.CENTER);
		return panelParent;
	}
}