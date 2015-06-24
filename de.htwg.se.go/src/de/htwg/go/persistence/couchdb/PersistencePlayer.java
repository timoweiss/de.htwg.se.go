package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.IScore;
import org.ektorp.support.CouchDbDocument;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistencePlayer extends CouchDbDocument {
    private String name;
    private IScore score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IScore getScore() {
        return score;
    }

    public void setScore(IScore score) {
        this.score = score;
    }
}
