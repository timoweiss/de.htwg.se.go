package de.htwg.go;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.go.aview.GraphicalUI;
import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.IGoController;

public final class Go {

	private Go() {
	}

	private static Scanner scanner;
	private static TextUI tui;
	private static final int NINE = 9;

	public static void main(final String args[]) {

		PropertyConfigurator.configure("log4j.properties");

		Injector injector = Guice.createInjector(new GoModule());
		IGoController controller = injector.getInstance(IGoController.class);

		controller.createField(NINE);

		tui = new TextUI(controller);
		new GraphicalUI(controller);

		boolean goAhead = true;
		scanner = new Scanner(System.in);

		while (goAhead) {
			goAhead = tui.inputLine(scanner.next());
		}

	}
}
