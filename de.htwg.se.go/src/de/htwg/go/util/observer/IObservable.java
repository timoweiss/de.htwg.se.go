package de.htwg.go.util.observer;

/**
 * Observer-Pattern by Marko Boger.
 * https://github.com/markoboger/de.htwg.se.sudoku
 *
 */

public interface IObservable {

	/**
	 * adds the obj to the observer-list.
	 * @param s IObserver
	 *
	 */
	void addObserver(IObserver s);
	
	/**
	 * removes the obj from the observer-list.
	 * @param s IObserver
	 *
	 */
	void removeObserver(IObserver s);
	
	/**
	 * clears the observer-list.
	 * @param s IObserver
	 * 
	 */
	void removeAllObservers();
	
	/**
	 * notifies the observer that the state has changed.
	 *
	 */
    void notifyObservers();
    /**
	 * notifies the observer that the state has changed.
	 * @param e Event
	 */
    void notifyObservers(Event e);
}
