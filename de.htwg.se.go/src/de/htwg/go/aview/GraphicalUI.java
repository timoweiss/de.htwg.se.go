package de.htwg.go.aview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class GraphicalUI extends JFrame implements IObserver, ActionListener {

	private static final long serialVersionUID = 1L;

	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JButton cell;
	JTextField textField;
	IGoController controller;
	JPanel panel;
	JFrame frame;

	JLabel whitePoints;
	JLabel blackPoints;

	JLabel statustext;
	JLabel background;

	Image back;

	ImageIcon backgroundplay;
	ImageIcon whiteButton;
	ImageIcon blackButton;
	

	public GraphicalUI(IGoController controller) {

		// backgroundplay = new ImageIcon(
		// "de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\gamefield99.jpg");

		backgroundplay = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\gamefield99.jpg");

		try {
			whiteButton = new ImageIcon(ImageIO.read(new File(
					"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\whiteStone.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			blackButton = new ImageIcon(ImageIO.read(new File(
					"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\blackStone.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.controller = controller;
		controller.addObserver(this);

		// Frame//
		frame = new JFrame("GO");
		frame.setSize(750, 600);
		frame.setResizable(false);

		// Wrapperpanel //

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new GridLayout());
		wrapper.setLayout(new BorderLayout(0, 0));
		wrapper.setLayout(null);

		frame.add(wrapper);

		// Playpanel //
		panel = new JPanel();
		panel.setSize(500, 500);

		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		wrapper.add(panel);

		// Statspanel //
		JPanel statspanel = new JPanel();
		statspanel.setLayout(null);
		statspanel.setBounds(500, 0, 250, 500);
		statspanel.setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel whiteLabel = new JLabel("Score white: ");
		JLabel blackLabel = new JLabel("Score black: ");

		whitePoints = new JLabel("0");
		blackPoints = new JLabel("0");

		whiteLabel.setBounds(10, 20, 100, 100);
		whitePoints.setBounds(150, 20, 100, 100);

		blackLabel.setBounds(10, 40, 100, 100);
		blackPoints.setBounds(150, 40, 100, 100);

		statspanel.add(whiteLabel);
		statspanel.add(whitePoints);

		statspanel.add(blackLabel);
		statspanel.add(blackPoints);

		wrapper.add(statspanel);

		print();

		// Statuspanel //
		JPanel statuspanel = new JPanel();
		statspanel.setLayout(null);
		statspanel.setBounds(0, 500, 750, 100);
		statspanel.setBorder(BorderFactory.createLineBorder(Color.black));
		statustext = new JLabel("status");
		statustext.setBounds(0, 0, 500, 20);
		statspanel.add(statustext);
		wrapper.add(statuspanel);

		// Menu//
		menuBar = new JMenuBar();

		// Gamemenu//
		menu = new JMenu("Game");
		menuBar.add(menu);
		menuItem = new JMenuItem("New Game");
		menu.add(menuItem);
		menuItem = new JMenuItem("Exit");
		menu.add(menuItem);

		// Settings //
		menu = new JMenu("Settings");
		menuBar.add(menu);
		menuItem = new JMenuItem("Appearance");
		menu.add(menuItem);
		menuItem = new JMenuItem("Time settings");
		menu.add(menuItem);

		// Help //
		menu = new JMenu("Help");
		menuBar.add(menu);
		menuItem = new JMenuItem("Rules");
		menu.add(menuItem);
		menuItem = new JMenuItem("View Sourcecode");
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("About Go");
		menu.add(menuItem);

		frame.setJMenuBar(menuBar);
		frame.setVisible(true);

	}

	public void print() {

		// Labels //

		int x = 18;
		int y = 20;
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {

				cell = new JButton();
				cell.setActionCommand(i + "" + j);
				cell.addActionListener(this);

				cell.setSize(36, 36);
				cell.setOpaque(true);
				cell.setLocation(x, y);

				cell.setOpaque(false);
				cell.setContentAreaFilled(false);
				cell.setBorderPainted(false);
				
				if (controller.getCellStatus(i, j) == 1) {
					// cell.setBackground(Color.white);
					cell.setIcon(whiteButton);
				} else if (controller.getCellStatus(i, j) == 2) {
					// cell.setBackground(Color.black);
					cell.setIcon(blackButton);
				} else {

					cell.setBackground(Color.green);
				}

				panel.add(cell);
				y = y + 54;
			}
			x = x + 54;
			y = 20;
		}

		// background //
		background = new JLabel();
		background.setBounds(0, 0, 500, 500);
		background.setIcon(backgroundplay);
		panel.add(background);

	}

	@Override
	public void update(Event e) {
		panel.removeAll();
		print();
		panel.updateUI();

		whitePoints.setText(controller.getwhitePlayerScore() + "");
		blackPoints.setText(controller.getblackPlayerScore() + "");
		statustext.setText(controller.getStatus());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.setStone((int) (e.getActionCommand().charAt(0) - '0'),
				(int) (e.getActionCommand().charAt(1) - '0'));
		repaint();
	}

}
