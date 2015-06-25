package de.htwg.go.model;

/**
 * Represents the Players of the Game.
 * Provides methods to change/get the score or other player-properties
 *
 */

public interface IPlayer {
	/**
	 *  returns the name of a Player
	 * @return name as string
	 */
	String getName();
	
	/**
	 * returns the score of a player
	 * @return score in int
	 */
	int getScore();

	/**
	 * adds some score to a player and returns the new score
	 * @param the score to add
	 * @return the new score as int
	 */	
	int addScore(int scoreToAdd);

    public void setName(String name);

    public void setScore(int score);

}
