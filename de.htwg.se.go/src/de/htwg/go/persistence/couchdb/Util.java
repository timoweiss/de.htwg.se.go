package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.ICell;
import de.htwg.go.model.IGameField;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by timoweiss on 24/06/15.
 */
public class Util {

    PersistenceGameField persistentGameField;

    public PersistenceGameField getTransformedGameField(IGameField gameField) {
        persistentGameField = new PersistenceGameField();
        persistentGameField.setId(gameField.getId());
        persistentGameField.setLength(gameField.getLength());
        persistentGameField.setPass(gameField.passed());
        persistentGameField.setWhiteIsNext(gameField.isWhiteIsNext());

        Set<PersistenceCell> setPersistenceCell = new HashSet<PersistenceCell>();
        for (ICell current: gameField.getBlackList()) {
            PersistenceCell persistenceCell = new PersistenceCell();

            persistenceCell.setChecked(current.isChecked());
            persistenceCell.setCoords(current.getCoords());
            persistenceCell.setStatus(current.getStatus());

            setPersistenceCell.add(persistenceCell);
        }
        persistentGameField.setBlackList(setPersistenceCell);
        return null;
    }
}
