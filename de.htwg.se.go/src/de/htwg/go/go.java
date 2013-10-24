package de.htwg.go;

import java.util.Scanner;

import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public class go {
	private static Scanner scanner;
	
	public static void main(final String args[]) {

		GoController controller = new GoController();
		TextUI textui = new TextUI(controller);
		
		controller.setStone(1, 1, "w");

		scanner = new Scanner(System.in);
		scanner.hasNext()
	}
}
