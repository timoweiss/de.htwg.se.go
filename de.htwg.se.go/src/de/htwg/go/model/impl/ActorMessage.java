package de.htwg.go.model.impl;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IGameField;

import java.io.Serializable;

/**
 * Created by michaelknoch on 27.06.15.
 */


public class ActorMessage implements Serializable {

    String message;
    IGoController controller;

    public ActorMessage(String message, IGoController controller) {
        this.message = message;
        this.controller = controller;
    }

    public String getMessage() {
        return this.message;
    }

    public IGoController getController() {
        return this.controller;
    }


}
