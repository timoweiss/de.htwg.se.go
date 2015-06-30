package de.htwg.go.persistence.hibernate;

import de.htwg.go.persistence.couchdb.PersistenceCell;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Created by michaelknoch on 24.06.15.
 */

@Entity
@Table(name = "game")
public class PersistentGameField implements Serializable {

    private boolean pass = false;

    private String gameField;
    private boolean whiteIsNext = true;

    private String blackList;

    private String whiteList;

    //@OneToMany(mappedBy = "game")
    private String blackRegions;
    private String whiteRegions;

    @OneToOne
    private PersistentPlayer whitePlayer;

    @OneToOne
    private PersistentPlayer blackPlayer;

    // size of the gamefield LENGTH x LENGTH
    private int length;

    private Integer value = 0;



    @Id
    @Column(name = "id")
    private String id;

    public PersistentGameField() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getGameField() {
        return gameField;
    }

    public void setGameField(String gameField) {
        this.gameField = gameField;
    }

    public boolean isWhiteIsNext() {
        return whiteIsNext;
    }

    public void setWhiteIsNext(boolean whiteIsNext) {
        this.whiteIsNext = whiteIsNext;
    }

    public String getBlackList() {
        return blackList;
    }

    public void setBlackList(String blackList) {
        this.blackList = blackList;
    }

    public String getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(String whiteList) {
        this.whiteList = whiteList;
    }

    public String getBlackRegions() {
        return blackRegions;
    }

    public void setBlackRegions(String blackRegions) {
        this.blackRegions = blackRegions;
    }

    public String getWhiteRegions() {
        return whiteRegions;
    }

    public void setWhiteRegions(String whiteRegions) {
        this.whiteRegions = whiteRegions;
    }
    public PersistentPlayer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(PersistentPlayer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public PersistentPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(PersistentPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


}
