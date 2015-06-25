package de.htwg.go.model.impl;


import de.htwg.go.model.IPlayer;
import de.htwg.go.model.IScore;

public class Player implements IPlayer {
	private String name;
	private IScore score;

	public Player() {
		
		this.name = "unknown";
		this.score = new Score();
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score.getScore();
	}

	public int addScore(int scoreToAdd) {
		return score.addPoints(scoreToAdd);
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score.addPoints(score);
    }
}
