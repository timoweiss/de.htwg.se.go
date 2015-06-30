package de.htwg.go.persistence.hibernate;

import de.htwg.go.model.IGameField;
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

            PersistentGameField pGame = Util.getTransformedGameField(gameField);

            this.session.saveOrUpdate(pGame);

                this.session.saveOrUpdate(pGame.getBlackList());
                this.session.saveOrUpdate(pGame.getWhiteList());

                this.session.saveOrUpdate(pGame.getBlackRegions());
                this.session.saveOrUpdate(pGame.getWhiteRegions());


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
        return Util.getReTransformedGameField((PersistentGameField) this.session.get(
                PersistentGameField.class, id));
    }

    @Override
    public void deleteGameById(String id) {

        Transaction tx = null;

        try {
            tx = this.session.beginTransaction();

            PersistentGameField pGame = (PersistentGameField) this.session.get(
                    PersistentGameField.class, id);

                this.session.delete(pGame.getBlackList());
                this.session.delete(pGame.getWhiteList());

                this.session.delete(pGame.getBlackRegions());
                this.session.delete(pGame.getWhiteRegions());


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

        Criteria criteria = this.session.createCriteria(PersistentGameField.class);

        @SuppressWarnings("unchecked")
        List<PersistentGameField> results = criteria.list();

        List<IGameField> games = new ArrayList<IGameField>();
        for (PersistentGameField current : results) {
            IGameField game = Util.getReTransformedGameField(current);
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

}
