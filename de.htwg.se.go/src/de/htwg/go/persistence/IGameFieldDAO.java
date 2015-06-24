package de.htwg.go.persistence;

import de.htwg.go.model.IGameField;

import java.util.List;

public interface IGameFieldDAO {

    void saveGame(IGameField gameField);

    IGameField getGameById(final String id);

    void deleteGameById(final String id);

    List<IGameField> getAllGames();

    public void closeDB();

    void updateGameById(final String id, IGameField gameField);

    boolean contains(final String id);

}
