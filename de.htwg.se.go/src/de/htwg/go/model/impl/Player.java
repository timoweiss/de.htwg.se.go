package de.htwg.go.model.impl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwg.go.GoModule;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.IScore;

public class Player implements IPlayer {
	private String name;
	private IScore score;

	@Inject
	public Player() {
		Injector injector = Guice.createInjector(new GoModule());

		this.name = "unknown";
		this.score = injector.getInstance(IScore.class);
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
