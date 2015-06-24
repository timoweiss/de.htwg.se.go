package de.htwg.go.persistence.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by michaelknoch on 24.06.15.
 */

@Entity
@Table(name = "game")
public class PersistentGame implements Serializable {

    public PersistentGame() {

    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
