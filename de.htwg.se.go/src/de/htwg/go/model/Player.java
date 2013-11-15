package de.htwg.go.model;

public class Player {
	private String name;

	
	public Player() {
		this.name = "unknown";
	}
	
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
