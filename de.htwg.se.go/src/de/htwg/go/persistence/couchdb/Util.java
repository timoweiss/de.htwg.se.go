package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.ICell;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.IPlayer;

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

        // verbose getBlackList stuff
        Set<PersistenceCell> setPersistenceCellBlackList = new HashSet<PersistenceCell>();
        for (ICell current: gameField.getBlackList()) {
            PersistenceCell persistenceCell = new PersistenceCell();

            persistenceCell.setChecked(current.isChecked());
            persistenceCell.setCoords(current.getCoords());
            persistenceCell.setStatus(current.getStatus());

            setPersistenceCellBlackList.add(persistenceCell);
        }

        // verbose getWhiteList stuff
        Set<PersistenceCell> setPersistenceCellWhiteList = new HashSet<PersistenceCell>();
        for (ICell current: gameField.getWhiteList()) {
            PersistenceCell persistenceCell = new PersistenceCell();

            persistenceCell.setChecked(current.isChecked());
            persistenceCell.setCoords(current.getCoords());
            persistenceCell.setStatus(current.getStatus());

            setPersistenceCellWhiteList.add(persistenceCell);
        }

        PersistencePlayer persistencePlayerBlackPlayer = new PersistencePlayer();
        IPlayer playerBlackPlayer = gameField.getblackPlayer();
        persistencePlayerBlackPlayer.setName(playerBlackPlayer.getName());
        persistencePlayerBlackPlayer.setScore(playerBlackPlayer.getScore());

        PersistencePlayer persistencePlayerWhitePlayer = new PersistencePlayer();
        IPlayer playerWhitePlayer = gameField.getWhitePlayer();
        persistencePlayerWhitePlayer.setName(playerWhitePlayer.getName());
        persistencePlayerWhitePlayer.setScore(playerWhitePlayer.getScore());


        return null;
    }
}
