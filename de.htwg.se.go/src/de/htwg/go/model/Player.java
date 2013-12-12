package de.htwg.go.model;

public class Player {
	private String name;
	private Score score;	
	
	public Player() {
		this.name = "unknown";
		score = new Score();
	}
	
	public Player(String name) {
		this.name = name;
		score = new Score();
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
	
	
	
	
	
}
