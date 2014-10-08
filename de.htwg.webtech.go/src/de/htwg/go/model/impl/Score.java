package de.htwg.go.model.impl;

import de.htwg.go.model.IScore;

public class Score implements IScore {
	private int score;

	public int addPoints(int scoretoAdd) {
		score += scoretoAdd;
		return this.score;
	}

	public int getScore() {
		return score;
	}
}
