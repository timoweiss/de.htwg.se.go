package de.htwg.go.aview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class Gui extends JFrame implements IObserver, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane, wrapperpanel, stats;
	
	private IGoController controller;
	private JButton cell;
	private JPanel play;
	
	private JLabel whiteScore;
	private JLabel blackScore;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Gui(IGoController controller) {
		frame = new JFrame("Go");
		frame.setSize(750, 600);
		frame.setResizable(false);

		setResizable(false);
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);

		wrapperpanel = new JPanel();
		contentPane.add(wrapperpanel, BorderLayout.CENTER);
		wrapperpanel.setLayout(null);

		play = new JPanel();
		play.setBounds(10, 11, 500, 500);
		wrapperpanel.add(play);
		play.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg"));
		lblNewLabel.setBounds(66, 48, 203, 147);
		play.add(lblNewLabel);

		print();

		stats = new JPanel();
		stats.setBounds(520, 11, 204, 500);
		wrapperpanel.add(stats);
		stats.setLayout(null);

		JLabel blackLabel = new JLabel("black Score:");
		blackLabel.setBounds(10, 42, 69, 14);
		stats.add(blackLabel);

		JLabel whiteLabel = new JLabel("white Score:");
		whiteLabel.setBounds(10, 92, 69, 14);
		stats.add(whiteLabel);

		blackScore = new JLabel("0");
		blackScore.setBounds(89, 42, 69, 14);
		stats.add(blackScore);

		whiteScore = new JLabel("0");
		whiteScore.setBounds(89, 67, 46, 14);
		stats.add(whiteScore);

		JPanel bottom = new JPanel();
		bottom.setBounds(10, 518, 714, 46);
		wrapperpanel.add(bottom);
		
		btnNewButton = new JButton("New button");
		bottom.add(btnNewButton);

		frame.setVisible(true);
	}

	public void print() {
		// Labels //

		int x = 0;
		int y = 0;
		for (int i = 0; i < 9; i++) {

			for (int j = 0; j < 9; j++) {

				cell = new JButton();
				cell.setActionCommand(i + "" + j);
				cell.addActionListener(this);

				cell.setSize(20, 20);
				cell.setOpaque(true);
				cell.setLocation(x, y);

				if (controller.getCellStatus(i, j) == 1) {
					cell.setBackground(Color.white);
				} else if (controller.getCellStatus(i, j) == 2) {
					cell.setBackground(Color.black);
				} else {
					cell.setBackground(Color.green);
				}

				play.add(cell);
				y = y + 40;
			}
			x = x + 40;
			y = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.setStone((int) (e.getActionCommand().charAt(0) - '0'),
				(int) (e.getActionCommand().charAt(1) - '0'));
		repaint();
	}

	@Override
	public void update(Event e) {
		play.removeAll();
		wrapperpanel.removeAll();
		
		
		print();
		play.updateUI();
		wrapperpanel.updateUI();
		stats.updateUI();
		contentPane.updateUI();
		

		whiteScore.setText(controller.getwhitePlayerScore() + "");
		blackScore.setText(controller.getblackPlayerScore() + "");
	}
}
