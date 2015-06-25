package de.htwg.go.persistence.hibernate;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by michaelknoch on 24.06.15.
 */

@Entity
@Table(name = "player")
public class PersistentPlayer implements Serializable {


    private int score;
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "gameid")
    private PersistentGame game;

    private Integer value = 0;


    public PersistentGame getGame() {
        return game;
    }

    public void setGame(PersistentGame game) {
        this.game = game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
