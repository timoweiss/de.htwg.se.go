package de.htwg.go.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import de.htwg.go.model.IGameField;
import de.htwg.go.persistence.IGameFieldDAO;

/**
 * Created by timoweiss on 24/06/15.
 */
public class GameFieldDb4oDAO implements IGameFieldDAO {

    private ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "db4o_go.data");

    @Override
    public void saveGame(IGameField gameField) {
        this.db.store(gameField);
    }

    @Override
    public IGameField getGameById(String id) {
        return null;
    }

    @Override
    public void deleteGameById(String id) {

    }

    @Override
    public void updateGameById(String id, IGameField gameField) {

    }

    @Override
    public boolean contains(String id) {
        return false;
    }
}
