package de.htwg.go.aview;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;
import de.htwg.go.util.observer.IObserver;

public class TextUI implements IObserver {

	private IGoController controller;

	public TextUI(IGoController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public void update(Event e) {
		printTui();
	}

	private void printTui() {
		System.out.println(controller.tuiToString());
		System.out.println(controller.getStatus());
		System.out.println();
	}

	public boolean inputLine(String line) {
		boolean operate = true;
		switch (line) {
		case "q":
			operate = false;
			System.out.println("Bye");
			break;
		}

		//if the input has "123" set a Stone at 1, 2 with status 3
		if (line.matches("[0-8][0-8]")) {
			controller.setStone(Character.getNumericValue(line.charAt(0)),
					Character.getNumericValue(line.charAt(1)));
		}

		return operate;
	}

}
