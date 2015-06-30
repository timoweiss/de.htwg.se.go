package de.htwg.go.persistence.hibernate;

import de.htwg.go.model.ICell;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.impl.Cell;
import de.htwg.go.model.impl.GameField;
import de.htwg.go.model.impl.Player;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by michaelknoch on 25.06.15.
 */
public class Util {
    public static PersistentGameField getTransformedGameField(IGameField gameField) {
        PersistentGameField persistentGameField = new PersistentGameField();
        persistentGameField.setId(gameField.getId());
        persistentGameField.setLength(gameField.getLength());
        persistentGameField.setPass(gameField.passed());
        persistentGameField.setWhiteIsNext(gameField.isWhiteIsNext());

        // verbose getBlackList stuff
        Set<PersistentCell> setPersistenceCellBlackList = new HashSet<PersistentCell>();
        for (ICell current: gameField.getBlackList()) {
            PersistentCell persistenceCell = new PersistentCell();

            persistenceCell.setChecked(current.isChecked());
            Point point = current.getCoords();
            persistenceCell.setCoordX(point.x);
            persistenceCell.setCoordY(point.y);
            persistenceCell.setStatus(current.getStatus());

            setPersistenceCellBlackList.add(persistenceCell);
        }

        persistentGameField.setBlackList(setPersistenceCellBlackList);

        // verbose getWhiteList stuff
        Set<PersistentCell> setPersistenceCellWhiteList = new HashSet<PersistentCell>();
        for (ICell current: gameField.getWhiteList()) {
            PersistentCell persistenceCell = new PersistentCell();

            persistenceCell.setChecked(current.isChecked());
            Point point = current.getCoords();
            persistenceCell.setCoordX(point.x);
            persistenceCell.setCoordY(point.y);
            persistenceCell.setStatus(current.getStatus());

            setPersistenceCellWhiteList.add(persistenceCell);
        }

        persistentGameField.setWhiteList(setPersistenceCellWhiteList);

        PersistentPlayer persistencePlayerBlackPlayer = new PersistentPlayer();
        IPlayer playerBlackPlayer = gameField.getblackPlayer();
        persistencePlayerBlackPlayer.setName(playerBlackPlayer.getName());
        persistencePlayerBlackPlayer.setScore(playerBlackPlayer.getScore());

        PersistentPlayer persistencePlayerWhitePlayer = new PersistentPlayer();
        IPlayer playerWhitePlayer = gameField.getWhitePlayer();
        persistencePlayerWhitePlayer.setName(playerWhitePlayer.getName());
        persistencePlayerWhitePlayer.setScore(playerWhitePlayer.getScore());

        persistentGameField.setBlackPlayer(persistencePlayerBlackPlayer);
        persistentGameField.setWhitePlayer(persistencePlayerWhitePlayer);


        PersistentCell a[][] = new PersistentCell[gameField.getGameField().length][gameField.getGameField().length];
        for(int i = 0; i < gameField.getGameField().length; i++) {
            for(int j = 0; j < gameField.getGameField()[i].length; j++) {
                PersistentCell persistenceCell = new PersistentCell();
                persistenceCell.setCoordX(gameField.getGameField()[i][j].getCoords().x);
                persistenceCell.setCoordY(gameField.getGameField()[i][j].getCoords().y);
                persistenceCell.setStatus(gameField.getGameField()[i][j].getStatus());
                a[i][j] = persistenceCell;

            }
        }
        persistentGameField.setGameField(a);




        return persistentGameField;
    }

    public static IGameField getReTransformedGameField(PersistentGameField gameField) {

        IGameField nonPersistentGamefield = new GameField(gameField.getLength());
        nonPersistentGamefield.setId(gameField.getId());
        nonPersistentGamefield.setLength(gameField.getLength());
        nonPersistentGamefield.setPass(gameField.isPass());
        nonPersistentGamefield.setWhiteIsNext(gameField.isWhiteIsNext());

        // verbose getBlackList stuff
        Set<ICell> setCellBlackList = new HashSet<ICell>();
        for (PersistentCell current: gameField.getBlackList()) {

            Cell cell = new Cell(current.getCoordX(), current.getCoordY());

            cell.setChecked(current.isChecked());
            cell.setStatus(current.getStatus());

            setCellBlackList.add(cell);
        }

        nonPersistentGamefield.setBlackList(setCellBlackList);

        // verbose getWhiteList stuff
        Set<ICell> setCellWhiteList = new HashSet<ICell>();
        for (PersistentCell current: gameField.getWhiteList()) {

            Cell cell = new Cell(current.getCoordX(), current.getCoordY());

            cell.setChecked(current.isChecked());
            cell.setStatus(current.getStatus());

            setCellWhiteList.add(cell);
        }

        nonPersistentGamefield.setWhiteList(setCellWhiteList);

        IPlayer nonPerBlackPlayer = new Player();
        PersistentPlayer psbp = gameField.getBlackPlayer();
        nonPerBlackPlayer.setName(psbp.getName());
        nonPerBlackPlayer.setScore(psbp.getScore());
        nonPersistentGamefield.setBlackPlayer(nonPerBlackPlayer);

        IPlayer nonPerWhitePlayer = new Player();
        PersistentPlayer pswp = gameField.getWhitePlayer();
        nonPerWhitePlayer.setName(pswp.getName());
        nonPerWhitePlayer.setScore(pswp.getScore());
        nonPersistentGamefield.setWhitePlayer(nonPerWhitePlayer);


        Cell b[][] = new Cell[gameField.getGameField().length][gameField.getGameField().length];
        for(int i = 0; i < gameField.getGameField().length; i++) {
            for(int j = 0; j < gameField.getGameField()[i].length; j++) {
                Cell npCell = new Cell(gameField.getGameField()[i][j].getCoordX(), gameField.getGameField()[i][j].getCoordY());
                npCell.setStatus(gameField.getGameField()[i][j].getStatus());
                b[i][j] = npCell;

            }
        }
        nonPersistentGamefield.setGameField(b);

        return nonPersistentGamefield;
    }
}
