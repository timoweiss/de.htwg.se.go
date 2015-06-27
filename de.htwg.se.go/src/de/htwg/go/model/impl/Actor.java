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
        log.info("SimpleActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof ActorMessage) {
            int result =  ((ActorMessage) msg).getGameFieldSize();

            getSender().tell(new Result(result), getSelf());
        }
    }
}