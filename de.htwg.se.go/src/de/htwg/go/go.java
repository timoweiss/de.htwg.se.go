package de.htwg.go;



import de.htwg.go.aview.TextUI;
import de.htwg.go.controller.impl.GoController;

public class go {

	
	public static void main(final String args[]) {

		GoController controller = new GoController();
		new TextUI(controller);
		controller.setStone(1, 1, "w");
	}
}
