package de.htwg.go.persistence.db4o;

import de.htwg.go.model.IGameField;
import de.htwg.go.persistence.IGameFieldDAO;

/**
 * Created by timoweiss on 24/06/15.
 */
public class GameFieldDb4oDAO implements IGameFieldDAO {
    @Override
    public void saveGame(IGameField gameField) {

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
