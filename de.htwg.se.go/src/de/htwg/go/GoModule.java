package de.htwg.go;

import com.google.inject.AbstractModule;

import com.google.inject.Singleton;
import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.IScore;
import de.htwg.go.persistence.IGameFieldDAO;

public class GoModule extends AbstractModule {
	@Override
	protected void configure() {

		bind(IPlayer.class).to(de.htwg.go.model.impl.Player.class);

		bind(IScore.class).to(de.htwg.go.model.impl.Score.class);

		bind(IGoController.class).to(
				de.htwg.go.controller.impl.GoController.class).in(Singleton.class);

        bind(IGameFieldDAO.class).to(de.htwg.go.persistence.db4o.GameFieldDb4oDAO.class);
        //bind(IGameFieldDAO.class).to(de.htwg.go.persistence.db4o.GameFieldDb4oDAO.class);
        //bind(IGameFieldDAO.class).to(de.htwg.go.persistence.db4o.GameFieldDb4oDAO.class);

	}
}
