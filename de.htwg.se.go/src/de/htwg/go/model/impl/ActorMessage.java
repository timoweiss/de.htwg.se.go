package de.htwg.go.model.impl;

import de.htwg.go.model.IGameField;

import java.io.Serializable;

/**
 * Created by michaelknoch on 27.06.15.
 */


public class ActorMessage implements Serializable {
    IGameField gamefield;

    public ActorMessage(IGameField gamefield) {
        this.gamefield = gamefield;
    }

    public int getGameFieldSize() {
        return this.gamefield.getGameFieldSize();
    }
}
