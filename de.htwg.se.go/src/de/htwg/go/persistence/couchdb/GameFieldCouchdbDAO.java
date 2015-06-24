package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.IGameField;
import de.htwg.go.persistence.IGameFieldDAO;
import org.ektorp.CouchDbConnector;

import java.util.List;

public class GameFieldCouchdbDAO implements IGameFieldDAO {

    private CouchDbConnector db = null;

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
    public List<IGameField> getAllGames() {
        return null;
    }

    @Override
    public void closeDB() {

    }

    @Override
    public void updateGameById(String id, IGameField gameField) {

    }

    @Override
    public boolean contains(String id) {
        return false;
    }
}
