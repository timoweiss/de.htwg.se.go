package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.ICell;
import de.htwg.go.model.IPlayer;
import org.ektorp.support.CouchDbDocument;

import java.util.Collection;
import java.util.Set;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistenceGameField extends CouchDbDocument{
    private boolean pass = false;

    private String id;
    private ICell gameField[][];
    private boolean whiteIsNext = true;

    private Set<PersistenceCell> blackList;
    private Set<PersistenceCell> whiteList;

    private Collection<Set<PersistenceCell>> blackRegions;
    private Collection<Set<PersistenceCell>> whiteRegions;

    private IPlayer whitePlayer;
    private IPlayer blackPlayer;

    // size of the gamefield LENGTH x LENGTH
    private int length;


    public PersistenceGameField() {

    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public ICell[][] getGameField() {
        return gameField;
    }

    public void setGameField(ICell[][] gameField) {
        this.gameField = gameField;
    }

    public boolean isWhiteIsNext() {
        return whiteIsNext;
    }

    public void setWhiteIsNext(boolean whiteIsNext) {
        this.whiteIsNext = whiteIsNext;
    }

    public Set<PersistenceCell> getBlackList() {
        return blackList;
    }

    public void setBlackList(Set<PersistenceCell> blackList) {
        this.blackList = blackList;
    }

    public Set<PersistenceCell> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(Set<PersistenceCell> whiteList) {
        this.whiteList = whiteList;
    }

    public Collection<Set<PersistenceCell>> getBlackRegions() {
        return blackRegions;
    }

    public void setBlackRegions(Collection<Set<PersistenceCell>> blackRegions) {
        this.blackRegions = blackRegions;
    }

    public Collection<Set<PersistenceCell>> getWhiteRegions() {
        return whiteRegions;
    }

    public void setWhiteRegions(Collection<Set<PersistenceCell>> whiteRegions) {
        this.whiteRegions = whiteRegions;
    }

    public IPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(IPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public IPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(IPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

}
