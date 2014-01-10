package de.htwg.go;

import java.util.Scanner;

import de.htwg.go.aview.GraphicalUI;
import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.IGoController;
import de.htwg.go.controller.impl.GoController;

public final class Go {
	
	private Go() {}
	
	private static Scanner scanner;
	private static TextUI tui;
	public static void main(final String args[]) {

		IGoController controller = new GoController();
		controller.createField();
		
		tui = new TextUI(controller);
		new GraphicalUI(controller);
		
		

		boolean goAhead = true;
		scanner = new Scanner(System.in);

		while (goAhead) {
			goAhead = tui.inputLine(scanner.next());
		}

	}
}
