package de.htwg.go.aview;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class GraphicalUI extends JFrame implements IObserver, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JButton cell;
	JTextField textField;
	IGoController controller;
	JPanel panel;

	public GraphicalUI(IGoController controller) {
		this.controller = controller;
		controller.addObserver(this);

		// Frame//
		JFrame frame = new JFrame("GO");
		frame.setSize(750, 600);
		frame.setResizable(false);

		// Playpanel //
		panel = new JPanel();
		panel.setSize(500, 500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		frame.add(panel);

		// Labels //
		int x = 15;
		int y = 15;
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {

				cell = new JButton();
				cell.setActionCommand(i + "" + j);
				cell.addActionListener(this);

				cell.setSize(20, 20);
				cell.setOpaque(true);
				cell.setLocation(x, y);
				cell.setBackground(Color.black);

				panel.add(cell);
				y = y + 55;
			}
			x = x + 55;
			y = 15;
		}

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

	@Override
	public void update(Event e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

		char c = e.getActionCommand().charAt(0);
		int x = c - '0';
		System.out.println(x);

		controller.setStone((int) (e.getActionCommand().charAt(0) - '0'),
				(int) (e.getActionCommand().charAt(1) - '0'));

	}

}
