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
		PropertyConfigurator.configure("log4j.properties");
		Injector injector = Guice.createInjector(new GoModule());
		controller = injector.getInstance(IGoController.class);
		controller.createField(9);
	}

	private static Scanner scanner;
	private static TextUI tui;
	private static Go instance = null;
	private IGoController controller;

	public static Go getInstance() {
		if (instance == null) {
			instance = new Go();
		}
		return instance;
	}

	public static void main(final String args[]) {

		Go.getInstance();

		tui = new TextUI(getInstance().controller);
		new GraphicalUI(getInstance().controller);

		boolean goAhead = true;
		scanner = new Scanner(System.in);

		while (goAhead) {
			goAhead = tui.inputLine(scanner.next());
		}

	}

	public IGoController getController() {
		return controller;
	}
}
