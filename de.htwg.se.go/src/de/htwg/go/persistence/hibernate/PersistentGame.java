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
public class PersistentGame implements Serializable {

    private boolean pass = false;

    private PersistentCell gameField[][];
    private boolean whiteIsNext = true;

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = PersistentCell.class)
    private Set<PersistentCell> blackList;

    @OneToMany(mappedBy = "game", cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = PersistentCell.class)
    private Set<PersistentCell> whiteList;

    //@OneToMany(mappedBy = "game")
   /* private Collection<Set<PersistenceCell>> blackRegions;
    private Collection<Set<PersistenceCell>> whiteRegions;*/

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

    public PersistentGame() {

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

    public PersistentCell[][] getGameField() {
        return gameField;
    }

    public void setGameField(PersistentCell[][] gameField) {
        this.gameField = gameField;
    }

    public boolean isWhiteIsNext() {
        return whiteIsNext;
    }

    public void setWhiteIsNext(boolean whiteIsNext) {
        this.whiteIsNext = whiteIsNext;
    }

    public Set<PersistentCell> getBlackList() {
        return blackList;
    }

    public void setBlackList(Set<PersistentCell> blackList) {
        this.blackList = blackList;
    }

    public Set<PersistentCell> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(Set<PersistentCell> whiteList) {
        this.whiteList = whiteList;
    }

   /* public Collection<Set<PersistenceCell>> getBlackRegions() {
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
*/
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
