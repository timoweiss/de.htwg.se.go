package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.IGameField;

/**
 * Created by timoweiss on 24/06/15.
 */
public class Util {

    PersistenceGameField persistentGameField;

    public PersistenceGameField getTransformedGameField(IGameField gameField) {
        persistentGameField = new PersistenceGameField();
        persistentGameField.setId(gameField.getId());
        persistentGameField.setLength(gameField.getLength());

        return null;
    }
}
