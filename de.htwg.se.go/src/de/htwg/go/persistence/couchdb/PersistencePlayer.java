package de.htwg.go.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistencePlayer extends CouchDbDocument {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
