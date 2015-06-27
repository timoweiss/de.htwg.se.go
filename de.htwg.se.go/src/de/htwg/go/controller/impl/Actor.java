package de.htwg.go.controller.impl;

import akka.actor.UntypedActor;

/**
 * Created by michaelknoch on 27.06.15.
 */
public class Actor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            log.info("Received String message: {}", message);
            getSender().tell(message, getSelf());
        } else
            unhandled(message);
    }
}