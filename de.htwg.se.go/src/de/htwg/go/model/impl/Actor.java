package de.htwg.go.model.impl;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import de.htwg.go.model.IGameField;

/**
 * Created by michaelknoch on 27.06.15.
 */
public class Actor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Actor() {
        log.info("Update Actor created");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        log.info("Msg Received");
        if (msg instanceof ActorMessage) {
            String message = ((ActorMessage) msg).getMessage();

            if (message == "update") {
                log.info("There was an update, updating");
                ((ActorMessage) msg).getController().notifyObservers();
            }

        }
    }

}