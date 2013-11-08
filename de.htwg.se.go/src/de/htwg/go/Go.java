package de.htwg.go;

import java.util.Scanner;

import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public final class Go {
	
	private Go() {}
	
	private static Scanner scanner;
	private static TextUI tui;

	
	
	public static void main(final String args[]) {

		GoController controller = new GoController();
		tui = new TextUI(controller);

		controller.createField();

		boolean goAhead = true;
		scanner = new Scanner(System.in);
		while (goAhead) {
			goAhead = tui.inputLine(scanner.next());
		}

	}
}
