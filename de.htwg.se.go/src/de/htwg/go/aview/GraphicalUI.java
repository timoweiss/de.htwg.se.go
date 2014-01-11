package de.htwg.go.aview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class GraphicalUI extends JFrame implements IObserver, ActionListener {

	private static final long serialVersionUID = 1L;

	private IGoController controller;
	private JPanel panel;

	private JLabel whitePoints;
	private JLabel blackPoints;

	private JLabel statustext;

	private ImageIcon backgroundplay;
	private ImageIcon whiteButton;
	private ImageIcon blackButton;

	private ImageIcon whiteStatsback;
	private ImageIcon blackStatsback;

	private ImageIcon whiteStatsbackA;
	private ImageIcon blackStatsbackA;

	private JLabel whiteStatsBackground;
	private JLabel blackStatsBackground;

	public GraphicalUI(IGoController controller) {
		// Magic Numbers //

		// Framesize//
		final int framexsize = 750;
		final int frameysize = 600;

		// Playpanelsize //
		final int playxsize = 500;
		final int playysize = 500;

		// Statspanel //
		final int statsxsize = 250;
		final int statsysize = 500;
		final int statsxpos = 500;
		final int statsypos = 0;

		// blackPoints //
		final int blackScorexpos = 0;
		final int blackScoreypos = 100;
		final int blackScorexsize = 36;
		final int blackScoreysize = 36;

		// whitePoints //
		final int whiteScorexpos = 0;
		final int whiteScoreypos = 200;
		final int whiteScorexsize = 36;
		final int whiteScoreysize = 36;

		// statuspanel //
		final int statusxpos = 0;
		final int statusypos = 500;

		final int statusxsize = 750;
		final int statusysize = 100;

		// statusText //
		final int statustxpos = 0;
		final int statustypos = 0;
		final int statustxsize = 500;
		final int statustysize = 20;

		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		JFrame frame;

		// backgroundplay = new ImageIcon(
		// "de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\gamefield99.jpg");

		backgroundplay = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\gamefield99.jpg");

		whiteStatsback = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\whiteScoreboard.jpg");

		whiteStatsbackA = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\whiteScoreboardAct.jpg");

		blackStatsback = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\blackScoreboard.jpg");

		blackStatsbackA = new ImageIcon(
				"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\blackScoreboardAct.jpg");

		try {
			whiteButton = new ImageIcon(
					ImageIO.read(new File(
							"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\whiteStone.png")));

			blackButton = new ImageIcon(
					ImageIO.read(new File(
							"C:\\Users\\michi\\git\\de.htwg.se.go\\de.htwg.se.go\\src\\de\\htwg\\go\\util\\images\\blackStone.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.controller = controller;
		controller.addObserver(this);

		// Frame//
		frame = new JFrame("GO");
		frame.setSize(framexsize, frameysize);
		frame.setResizable(false);

		// Wrapperpanel //

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new GridLayout());
		wrapper.setLayout(new BorderLayout(0, 0));
		wrapper.setLayout(null);

		frame.add(wrapper);

		// Playpanel //
		panel = new JPanel();
		panel.setSize(playxsize, playysize);

		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		wrapper.add(panel);

		// Statspanel //
		JPanel statspanel = new JPanel();
		statspanel.setLayout(null);
		statspanel.setBounds(statsxpos, statsypos, statsxsize, statsysize);
		statspanel.setBorder(BorderFactory.createLineBorder(Color.black));

		whitePoints = new JLabel("0");
		blackPoints = new JLabel("0");

		whitePoints.setBounds(whiteScorexpos, whiteScoreypos, whiteScorexsize,
				whiteScoreysize);

		blackPoints.setBounds(blackScorexpos, blackScoreypos, blackScorexsize,
				blackScoreysize);

		statspanel.add(whitePoints);
		statspanel.add(blackPoints);

		whiteStatsBackground = new JLabel();
		whiteStatsBackground.setBounds(0, 100, 180, 92);
		whiteStatsBackground.setIcon(whiteStatsback);

		blackStatsBackground = new JLabel();
		blackStatsBackground.setBounds(0, 200, 180, 92);
		blackStatsBackground.setIcon(blackStatsback);

		statspanel.add(whiteStatsBackground);
		statspanel.add(blackStatsBackground);

		wrapper.add(statspanel);

		print();

		// Statuspanel //
		JPanel statuspanel = new JPanel();
		statuspanel.setLayout(null);
		statuspanel.setBounds(statusxpos, statusypos, statusxsize, statusysize);
		statuspanel.setBorder(BorderFactory.createLineBorder(Color.black));

		statustext = new JLabel("status");
		statustext.setBounds(statustxpos, statustypos, statustxsize,
				statustysize);
		statuspanel.add(statustext);
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

	private void print() {
		JButton cell;
		JLabel background;

		// Magic Numbers //
		// Cells //
		final int xBeginningPos = 18;
		final int yBeginningPos = 20;
		final int distance = 54;
		final int gameSize = 9;
		final int cellSize = 36;

		// Background //
		final int backgroundxpos = 0;
		final int backgroundypos = 0;
		final int backgroundxsize = 500;
		final int backgroundysize = 500;

		// Labels //

		int x = xBeginningPos;
		int y = yBeginningPos;

		for (int i = 0; i < gameSize; i++) {

			for (int j = 0; j < gameSize; j++) {

				cell = new JButton();
				cell.setActionCommand(i + "" + j);
				cell.addActionListener(this);

				cell.setSize(cellSize, cellSize);
				cell.setOpaque(true);
				cell.setLocation(x, y);

				cell.setOpaque(false);
				cell.setContentAreaFilled(false);
				cell.setBorderPainted(false);

				if (controller.getCellStatus(i, j) == 1) {

					cell.setIcon(whiteButton);
				} else if (controller.getCellStatus(i, j) == 2) {

					cell.setIcon(blackButton);
				} else {

					cell.setBackground(Color.green);
				}

				panel.add(cell);
				y = y + distance;
			}
			x = x + distance;
			y = yBeginningPos;
		}

		// background //
		background = new JLabel();
		background.setBounds(backgroundxpos, backgroundypos, backgroundxsize,
				backgroundysize);
		background.setIcon(backgroundplay);
		panel.add(background);

		if (controller.getNext() == "white") {
			whiteStatsBackground.setIcon(whiteStatsbackA);
			blackStatsBackground.setIcon(blackStatsback);
		} else {
			blackStatsBackground.setIcon(blackStatsbackA);
			whiteStatsBackground.setIcon(whiteStatsback);
		}

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
