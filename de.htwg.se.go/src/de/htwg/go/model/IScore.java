package de.htwg.go.model;

/**
 * Interface IScore sets the score to a given player.
 * It's also able to return the current score.
 *
 */

public interface IScore {
	/**
	 * returns the score value
	 * @param the score to add in int
	 * @return the new score
	 */
	int addPoints(int scoretoAdd);
	
	/**
	 * returns the score value
	 * @return the score value
	 */
	int getScore();
}
