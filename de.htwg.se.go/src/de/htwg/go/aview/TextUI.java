package de.htwg.go.aview;

import java.util.logging.Logger;

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
	}

	@Override
	public void update(Event e) {
		printTui();
	}

	private void printTui() {
		logger.info(newLine + controller.tuiToString());
		logger.info(newLine + controller.getStatus());
	}

	public boolean inputLine(String line) {
		boolean operate = true;

		if (line.equals("-quit")) {
			operate = false;
			logger.info("bye");
			return false;
		}

		/*
		 * switch (line) { case "-quit": operate = false;
		 * System.out.println("Bye"); return false; }
		 */

		// if the input has "123" set a Stone at 1, 2 with status 3
		if (line.matches("[0-9][0-9]")) {
			controller.setStone(Character.getNumericValue(line.charAt(0)),
					Character.getNumericValue(line.charAt(1)));
		} else {
			logger.info("incorrect command");
		}

		return operate;
	}

}
