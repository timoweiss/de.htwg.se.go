package de.htwg.go.model;

public class Score {
	private int score;
	
	public int addPoints(int scoretoAdd) {
		score += scoretoAdd;
		return this.score;
	}
	
	public int getAllScore() {
		return this.score;
	}
	
	public int getScore() {
		return score;
	}
}
