package de.htwg.go.persistence.couchdb;

import de.htwg.go.model.IScore;
import org.ektorp.support.CouchDbDocument;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistencePlayer extends CouchDbDocument {
    private String name;
    private IScore score;
}
