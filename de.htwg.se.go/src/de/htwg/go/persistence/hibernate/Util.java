package de.htwg.go.persistence.hibernate;

import com.google.gson.Gson;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.IPlayer;
import de.htwg.go.model.impl.GameField;

/**
 * Created by michaelknoch on 25.06.15.
 */
public class Util {

    // lokal --> DB
    public static PersistentGameField getTransformedGameField(IGameField gameField) {
        PersistentGameField persistentGameField = new PersistentGameField();
        persistentGameField.setId(gameField.getId());
        persistentGameField.setLength(gameField.getLength());
        persistentGameField.setPass(gameField.passed());
        persistentGameField.setWhiteIsNext(gameField.isWhiteIsNext());

        // verbose getBlackList stuff
        Gson jsonField = new Gson();
        persistentGameField.setBlackList(jsonField.toJson(gameField.getBlackList()));


        Gson jsonField2 = new Gson();
        persistentGameField.setWhiteList(jsonField2.toJson(gameField.getWhiteList()));

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


        Gson jsonField3 = new Gson();
        persistentGameField.setGameField(jsonField3.toJson(gameField.getGameField()));


        return persistentGameField;
    }

    public static IGameField getReTransformedGameField(PersistentGameField gameField) {

        IGameField nonPersistentGamefield = new GameField(gameField.getLength());
        nonPersistentGamefield.setId(gameField.getId());
        nonPersistentGamefield.setLength(gameField.getLength());
        nonPersistentGamefield.setPass(gameField.isPass());
        nonPersistentGamefield.setWhiteIsNext(gameField.isWhiteIsNext());

       /* // verbose getBlackList stuff
        Set<ICell> setCellBlackList = new HashSet<ICell>();
        for (PersistentCell current: gameField.getBlackList()) {

            Cell cell = new Cell(current.getCoordX(), current.getCoordY());

            cell.setChecked(current.isChecked());
            cell.setStatus(current.getStatus());

            setCellBlackList.add(cell);
        }

        Gson gson = new Gson();



        nonPersistentGamefield.setBlackList(gson.fromJson(gameField.getBlackList(), Set<ICell>.class));

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
        nonPersistentGamefield.setGameField(b);*/

        return nonPersistentGamefield;
    }
}
