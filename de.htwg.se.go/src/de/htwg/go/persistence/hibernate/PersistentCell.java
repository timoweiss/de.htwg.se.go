package de.htwg.go.persistence.hibernate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by michaelknoch on 24.06.15.
 */

@Entity
@Table(name = "cell")
public class PersistentCell implements Serializable {
    private int coordX;
    private int coordY;
    private boolean checked;
    private int status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gameid")
    private PersistentGameField game;

    private Integer value = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getStatus() {
        return status;
    }

    public PersistentGameField getGame() {
        return game;
    }

    public void setGame(PersistentGameField game) {
        this.game = game;
    }
}

