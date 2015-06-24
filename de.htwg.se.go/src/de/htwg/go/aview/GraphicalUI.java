package de.htwg.go.aview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class GraphicalUI extends JFrame implements IObserver, ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean operate = true;

	private Logger logger = Logger.getLogger("de.htwg.go");

	private IGoController controller;
	private JPanel panel;

	private JLabel whitePoints;
	private JLabel blackPoints;

	private JLabel statustext;

	private ImageIcon backgroundplay;
	private ImageIcon backgroundplaySmall;

	private ImageIcon whiteButton;
	private ImageIcon blackButton;

	private ImageIcon whiteStatsback;
	private ImageIcon blackStatsback;

	private ImageIcon whiteStatsbackA;
	private ImageIcon blackStatsbackA;

	private JLabel whiteStatsBackground;
	private JLabel blackStatsBackground;

	private static final int NINE = 9;
	private static final int FIVE = 5;

	public GraphicalUI(IGoController controller) {

		// Framesize//
		final int framexsize = 750;
		final int frameysize = 650;

		// Playpanelsize //
		final int playxsize = 500;
		final int playysize = 500;

		final int playxpos = 19;
		final int playypos = 50;

		// Statspanel //
		final int statsxsize = 250;
		final int statsysize = 500;
		final int statsxpos = 500;
		final int statsypos = 0;

		// whitePoints //
		final int whiteScorexpos = 135;
		final int whiteScoreypos = 95;
		final int whiteScorexsize = 80;
		final int whiteScoreysize = 30;

		// blackPoints //
		final int blackScorexpos = 135;
		final int blackScoreypos = 208;
		final int blackScorexsize = 80;
		final int blackScoreysize = 30;

		// statuspanel //
		final int statusxpos = 0;
		final int statusypos = 575;

		final int statusxsize = 750;
		final int statusysize = 50;

		// statusText //
		final int statustxpos = 0;
		final int statustypos = 0;
		final int statustxsize = 500;
		final int statustysize = 20;

		// passbutton //
		final int passbuttonxpos = 100;
		final int passbuttonypos = 300;
		final int passbuttonxsize = 97;
		final int passbuttonysize = 30;

		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;
		JFrame frame;

		backgroundplay = new ImageIcon(
				"./images/gamefield99.jpg");

		backgroundplaySmall = new ImageIcon(
				"./images/gamefield55.jpg");

		ImageIcon passMove;
		passMove = new ImageIcon(
				"./images/passMove.jpg");

		whiteStatsback = new ImageIcon(
				"./images/whiteScoreboard.jpg");

		whiteStatsbackA = new ImageIcon(
				"./images/whiteScoreboardAct.jpg");

		blackStatsback = new ImageIcon(
				"./images/blackScoreboard.jpg");

		blackStatsbackA = new ImageIcon(
				"./images/blackScoreboardAct.jpg");

		ImageIcon backgroundImg;
		backgroundImg = new ImageIcon(
				"./images/guiBackground.jpg");

		try {
			whiteButton = new ImageIcon(ImageIO.read(new File(
					"./images/whiteStone.png")));

			blackButton = new ImageIcon(ImageIO.read(new File(
					"./images/blackStone.png")));

		} catch (IOException e) {
			logger.error("not able to initialize image ressources");
		}

		this.controller = controller;
		controller.addObserver(this);

		// Frame//
		frame = new JFrame("GO");
		frame.setSize(framexsize, frameysize);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Wrapperpanel //

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new GridLayout());
		wrapper.setLayout(new BorderLayout(0, 0));
		wrapper.setLayout(null);

		frame.add(wrapper);

		// Playpanel //
		panel = new JPanel();
		panel.setSize(playxsize, playysize);
		panel.setBounds(playxpos, playypos, playxsize, playysize);

		panel.setLayout(null);
		wrapper.add(panel);

		// Statspanel //
		JPanel statspanel = new JPanel();
		statspanel.setLayout(null);
		statspanel.setBounds(statsxpos, statsypos, statsxsize, statsysize);

		whitePoints = new JLabel("0");
		blackPoints = new JLabel("0");

		whitePoints.setBounds(whiteScorexpos, whiteScoreypos, whiteScorexsize,
				whiteScoreysize);

		blackPoints.setBounds(blackScorexpos, blackScoreypos, blackScorexsize,
				blackScoreysize);

		JButton passButton = new JButton();
		passButton.setBounds(passbuttonxpos, passbuttonypos, passbuttonxsize,
				passbuttonysize);
		passButton.addActionListener(this);
		passButton.setIcon(passMove);
		passButton.setActionCommand("pass");

		statspanel.add(whitePoints);
		statspanel.add(blackPoints);

		final int whitestatsbackgroundxpos = 50;
		final int whitestatsbackgroundypos = 50;
		final int whitestatsbackgroundxsize = 180;
		final int whitestatsbackgroundysize = 92;

		whiteStatsBackground = new JLabel();
		whiteStatsBackground.setBounds(whitestatsbackgroundxpos,
				whitestatsbackgroundypos, whitestatsbackgroundxsize,
				whitestatsbackgroundysize);
		whiteStatsBackground.setIcon(whiteStatsback);

		final int blackstatsbackgroundxpos = 50;
		final int blackstatsbackgroundypos = 164;
		final int blackstatsbackgroundxsize = 180;
		final int blackstatsbackgroundysize = 92;

		blackStatsBackground = new JLabel();
		blackStatsBackground.setBounds(blackstatsbackgroundxpos,
				blackstatsbackgroundypos, blackstatsbackgroundxsize,
				blackstatsbackgroundysize);
		blackStatsBackground.setIcon(blackStatsback);

		statspanel.add(whiteStatsBackground);
		statspanel.add(blackStatsBackground);
		statspanel.add(passButton);
		statspanel.setOpaque(false);

		wrapper.add(statspanel);

		print();

		// Statuspanel //
		JPanel statuspanel = new JPanel();
		statuspanel.setLayout(null);
		statuspanel.setBounds(statusxpos, statusypos, statusxsize, statusysize);

		statustext = new JLabel(controller.getStatus());
		statustext.setBounds(statustxpos, statustypos, statustxsize,
				statustysize);
		statuspanel.add(statustext);
		wrapper.add(statuspanel);

		final int backgroundxsize = 750;
		final int backgroundysize = 600;

		JLabel background = new JLabel();
		background.setIcon(backgroundImg);
		background.setBounds(0, 0, backgroundxsize, backgroundysize);
		wrapper.add(background);

		// Menu//
		menuBar = new JMenuBar();

		// Gamemenu//
		menu = new JMenu("Game");
		menuBar.add(menu);

		menuItem = new JMenuItem("New Game 9x9");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("New Game 5x5");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Help //
		menu = new JMenu("Help");
		menuItem.addActionListener(this);
		menuBar.add(menu);

		menuItem = new JMenuItem("Rules");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("View Sourcecode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();
		menuItem = new JMenuItem("About Go");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		frame.setJMenuBar(menuBar);
		frame.setVisible(true);

	}

	private void print() {
		JButton cell;
		final int gameSize = controller.getGameFieldSize();
		// Magic Numbers //
		// Cells //
		final int xBeginningPos = 18;
		final int yBeginningPos = 20;
		final int distance = 54;
		final int cellSize = 36;

		// Labels //
		int x = xBeginningPos;
		int y = yBeginningPos;

		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				cell = new JButton();
				cell.setActionCommand(i + "" + j);
				cell.addActionListener(this);
				cell.setSize(cellSize, cellSize);
				cell.setOpaque(false);
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

		printBackGorund();

	}

	private void printBackGorund() {
		JLabel background;
		// background //

		// Background //
		final int backgroundxpos = 0;
		final int backgroundypos = 0;
		final int backgroundxsize = 500;
		final int backgroundysize = 500;
		final int gameSize = controller.getGameFieldSize();

		background = new JLabel();
		background.setBounds(backgroundxpos, backgroundypos, backgroundxsize,
				backgroundysize);
		if (gameSize == NINE) {
			background.setIcon(backgroundplay);
		} else if (gameSize == FIVE) {
			background.setIcon(backgroundplaySmall);
		}

		panel.add(background);

		if (controller.getNext().equals("white")) {
			whiteStatsBackground.setIcon(whiteStatsbackA);
			blackStatsBackground.setIcon(blackStatsback);
		} else {
			blackStatsBackground.setIcon(blackStatsbackA);
			whiteStatsBackground.setIcon(whiteStatsback);
		}
	}

	@Override
	public void update(Event e) {
		if (!controller.getOperate()) {
			this.operate = false;

			this.msgend();
		}

		panel.removeAll();
		print();
		panel.updateUI();

		whitePoints.setText(controller.getwhitePlayerScore() + "");
		blackPoints.setText(controller.getblackPlayerScore() + "");
		statustext.setText(controller.getStatus());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();

		if (actionCommand.matches("[0-9][0-9]")) {
			if (operate) {

				controller.setStone(
						(int) (e.getActionCommand().charAt(0) - '0'), (int) (e
								.getActionCommand().charAt(1) - '0'));
				repaint();
			}
		} else if (actionCommand.equals("pass")) {

			guiPass();

		} else if (actionCommand.equals("View Sourcecode")) {

			showCode();

		} else if (actionCommand.equals("Rules")) {

			showRules();

		} else if (actionCommand.equals("Exit")) {
			System.exit(0);

		} else if (actionCommand.equals("New Game 9x9")) {

			controller.createField(NINE);
			operate = true;

		} else if (actionCommand.equals("New Game 5x5")) {
			controller.createField(FIVE);
			operate = true;

		} else if (actionCommand.equals("About Go")) {
			showAboutDialog();
		}

	}

	private void showAboutDialog() {
		JOptionPane
				.showMessageDialog(
						null,
						"Go by \nTimo Weiss \n Michael Knoch \n (c) Copyright Go contributors and others 2014.  \nAll rights reserved.",
						"About Go", JOptionPane.OK_CANCEL_OPTION);
	}

	private void showRules() {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		try {
			desktop.browse(new URL("http://en.wikipedia.org/wiki/Rules_of_Go")
					.toURI());
		} catch (Exception x) {
			logger.error("not able to parse url to visit");
		}
	}

	private void showCode() {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		try {
			desktop.browse(new URL("https://github.com/timoweiss/de.htwg.se.go")
					.toURI());
		} catch (Exception x) {
			logger.error("not able to parse url to visit");
		}
	}

	private void guiPass() {
		if (operate && controller.pass()) {

			controller.stop();
			operate = false;
			statustext.setText(controller.getStatus());

		}
	}

	private void msgend() {
		JOptionPane.showMessageDialog(null, "Game has ended. white score: "
				+ controller.getwhitePlayerScore() + " black player score: "
				+ controller.getblackPlayerScore(), "Game Dialogue",
				JOptionPane.OK_CANCEL_OPTION);
	}
}
