package de.htwg.go.model;

public class Player {
	private String name;
	private int score;

	
	public Player() {
		this.name = "unknown";
		score = 0;
	}
	
	public Player(String name) {
		this.name = name;
		score = 0;
	}

	public String getName() {
		return name;
	}
	
	public int addPoints(int scoretoAdd) {
		score += scoretoAdd;
		return this.score;
	}
	
	public int getScore() {
		return this.score;
	}
}
