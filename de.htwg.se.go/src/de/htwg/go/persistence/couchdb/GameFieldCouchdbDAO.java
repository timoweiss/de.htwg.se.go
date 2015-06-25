package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.IGameField;
import de.htwg.go.persistence.IGameFieldDAO;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.List;

public class GameFieldCouchdbDAO implements IGameFieldDAO {

    private static final String HTTP_LENNY2 = "http://lenny2.in.htwg-konstanz.de:5984";
    private CouchDbConnector db;
    private Util util;

    public GameFieldCouchdbDAO() {
        util = new Util();
        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder().url(HTTP_LENNY2).build();
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("htwg_go", true);
        db.createDatabaseIfNotExists();
    }

    @Override
    public void saveGame(IGameField gameField) {
        PersistenceGameField game = util.getTransformedGameField(gameField);
        db.create(game);
    }

    @Override
    public IGameField getGameById(String id) {
        try {
            PersistenceGameField game = db.get(PersistenceGameField.class, id);
            return util.getReTransformedGameField(game);
        } catch (DocumentNotFoundException e) {
            return null;
        }
    }

    @Override
    public void deleteGameById(String id) {
        db.delete(util.getTransformedGameField(getGameById(id)));
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
