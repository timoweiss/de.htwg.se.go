package de.htwg.go;

import com.google.inject.AbstractModule;

import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.IScore;
import de.htwg.go.persistence.IGameFieldDAO;
import de.htwg.go.plugins.GoPlugins;

public class GoModule extends AbstractModule {
	@Override
	protected void configure() {

		bind(IPlayer.class).to(de.htwg.go.model.impl.Player.class);

		bind(IScore.class).to(de.htwg.go.model.impl.Score.class);

		bind(IGoController.class).to(
				de.htwg.go.controller.impl.GoController.class).in(Singleton.class);

        bind(IGameFieldDAO.class).to(de.htwg.go.persistence.db4o.GameFieldDb4oDAO.class);
        //bind(IGameFieldDAO.class).to(de.htwg.go.persistence.couchdb.GameFieldCouchdbDAO.class);
        //bind(IGameFieldDAO.class).to(de.htwg.go.persistence.hibernate.GoHibernateDAO.class);

		Multibinder<GoPlugins> plugins = Multibinder.newSetBinder(binder(), GoPlugins.class);
		plugins.addBinding().to(de.htwg.go.plugins.Plugin1.class);
		plugins.addBinding().to(de.htwg.go.plugins.Plugin2.class);


	}
}
