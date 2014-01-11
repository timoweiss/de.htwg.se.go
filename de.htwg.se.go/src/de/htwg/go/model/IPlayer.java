package de.htwg.go.model;

public interface IPlayer {
	/**
	 *  returns the name of a Player
	 * @return name as string
	 */
	public String getName();
	
	/**
	 * returns the score of a player
	 * @return score in int
	 */
	public int getScore();

	/**
	 * adds some score to a player and returns the new score
	 * @param the score to add
	 * @return the new score as int
	 */	
	public int addScore(int scoreToAdd);

}
