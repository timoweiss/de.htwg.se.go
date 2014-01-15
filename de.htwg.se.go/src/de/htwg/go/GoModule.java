package de.htwg.go;

import com.google.inject.AbstractModule;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.IScore;


public class GoModule extends AbstractModule {
	@Override
	protected void configure() {

		bind(IPlayer.class).to(de.htwg.go.model.impl.Player.class);

		bind(IScore.class).to(de.htwg.go.model.impl.Score.class);

		bind(IGoController.class).to(
				de.htwg.go.controller.impl.GoController.class);
	}
}
