package de.htwg.go.persistence.hibernate;

import de.htwg.go.model.IGameField;
import de.htwg.go.model.impl.GameField;
import de.htwg.go.persistence.IGameFieldDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelknoch on 24.06.15.
 */
public class GoHibernateDAO implements IGameFieldDAO {

    private Session session;

    public GoHibernateDAO() {
        this.session = HibernateUtil.getInstance().openSession();
    }


    @Override
    public void saveGame(IGameField gameField) {
        Transaction tx = null;

        try {
            tx = this.session.beginTransaction();

            PersistentGame pGame = transformToHibernate(gameField);

            this.session.saveOrUpdate(pGame);
            /*for (PersistentPlayer pPlayer : pGame.getPlayers()) {
                this.session.saveOrUpdate(pPlayer);
            }*/

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }

    @Override
    public IGameField getGameById(String id) {
        this.session.beginTransaction();
        return transformFromHibernate((PersistentGame) this.session.get(
                PersistentGame.class, id));
    }

    @Override
    public void deleteGameById(String id) {

        Transaction tx = null;

        try {
            tx = this.session.beginTransaction();

            PersistentGame pGame = (PersistentGame) this.session.get(
                    PersistentGame.class, id);
            /*for (PersistentPlayer c : pGame.getPlayers()) {
                this.session.delete(c);
            }*/
            this.session.delete(pGame);

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }

    @Override
    public List<IGameField> getAllGames() {

        this.session.beginTransaction();

        Criteria criteria = this.session.createCriteria(PersistentGame.class);

        @SuppressWarnings("unchecked")
        List<PersistentGame> results = criteria.list();

        List<IGameField> games = new ArrayList<IGameField>();
        for (PersistentGame current : results) {
            IGameField game = transformFromHibernate(current);
            games.add(game);
        }

        return games;

    }

    @Override
    public void closeDB() {
        System.out.println("Close Success");
    }

    @Override
    public void updateGameById(String id, IGameField gameField) {
        saveGame(gameField);
    }

    @Override
    public boolean contains(String id) {
        return getGameById(id) != null;
    }


    private IGameField transformFromHibernate(PersistentGame persistentGame) {
        IGameField gamefield = new GameField(persistentGame.getLength());

        return gamefield;

    }


    private PersistentGame transformToHibernate(IGameField gameField) {

        return new PersistentGame();
    }

}
