package de.htwg.go.util.observer;

/**
 * Observer-Pattern by Marko Boger.
 * https://github.com/markoboger/de.htwg.se.sudoku
 *
 */

public interface IObserver {
	
	/**
	 * if something has changed, update will be called
	 * @param e Event
	 */
    void update(Event e);
}