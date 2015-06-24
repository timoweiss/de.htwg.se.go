package de.htwg.go.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import de.htwg.go.model.IGameField;
import de.htwg.go.persistence.IGameFieldDAO;

import java.util.List;

public class GameFieldDb4oDAO implements IGameFieldDAO {

    private ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "db4o_go.data");

    @Override
    public void saveGame(IGameField gameField) {
        this.db.store(gameField);
    }

    @Override
    public IGameField getGameById(final String id) {
        List<IGameField> games = db.query(new Predicate<IGameField>() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean match(IGameField game) {
                return (game.getId().equals(id));
            }
        });

        if (games.isEmpty()) {
            // no game found
            return null;
        }

        // return the retrieved game.
        return games.get(0);
    }

    @Override
    public void deleteGameById(String id) {
        db.delete(getGameById(id));
    }

    @Override
    public List<IGameField> getAllGames() {
        return db.query(IGameField.class);
    }

    @Override
    public void closeDB() {
        db.close();
    }

    @Override
    public void updateGameById(String id, IGameField gameField) {
        db.delete(gameField.getId());
        saveGame(gameField);
    }

    @Override
    public boolean contains(String id) {
        IGameField game = getGameById(id);
        return (game != null);
    }
}
