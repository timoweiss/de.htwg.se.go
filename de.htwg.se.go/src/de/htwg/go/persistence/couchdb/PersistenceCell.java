package de.htwg.go.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

/**
 * Created by timoweiss on 24/06/15.
 */
public class PersistenceCell extends CouchDbDocument {

    private int status;
    private int coordX;
    private int coordY;
    private boolean checked;

    public int getStatus() {
        return status;
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

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
