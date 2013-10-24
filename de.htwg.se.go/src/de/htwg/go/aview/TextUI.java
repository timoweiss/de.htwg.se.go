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
	}

}
