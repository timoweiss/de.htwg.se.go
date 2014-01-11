package de.htwg.go.model;

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
