package de.htwg.go.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

import java.awt.*;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistenceCell extends CouchDbDocument {

    private int status;
    private Point coords;
    private boolean checked;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
