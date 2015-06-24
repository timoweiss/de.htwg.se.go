package de.htwg.go.aview;

import org.apache.log4j.Logger;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class TextUI implements IObserver {

	private IGoController controller;
	private Logger logger = Logger.getLogger("de.htwg.go");
	private String newLine = System.getProperty("line.separator");

	public TextUI(IGoController controller) {
		this.controller = controller;
		controller.addObserver(this);
		printTui();
	}

	@Override
	public void update(Event e) {
		printTui();
	}

	private void printTui() {
		logger.info(newLine + controller.tuiToString());
		logger.info(newLine + controller.getStatus());
		logger.info(newLine + "white Player score: "
				+ controller.getwhitePlayerScore() + newLine
				+ "black Player score: " + controller.getblackPlayerScore());

	}

	public boolean inputLine(String line) {
		boolean operate = true;

		if (line.equals("-quit")) {
			operate = false;
			logger.info("bye");
			return false;

		} else if (line.equals("-pass")) {

			if (controller.pass()) {
				operate = false;
				logger.info("game has ended");
			}

		} else if (line.equals("-save")) {
			controller.saveGame();
			logger.info("Saved Game");

		} else if (line.matches("[0-9][0-9]")) {
			controller.setStone(Character.getNumericValue(line.charAt(0)),
					Character.getNumericValue(line.charAt(1)));
		} else {

			logger.info("incorrect command");
		}

		return operate;
	}

}
