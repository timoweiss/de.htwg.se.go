package de.htwg.go.controller.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.google.inject.Inject;
import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.impl.Actor;
import de.htwg.go.model.impl.ActorMessage;
import de.htwg.go.model.impl.GameField;
import de.htwg.go.persistence.IGameFieldDAO;
import de.htwg.go.util.observer.Observable;

import java.util.List;

public class GoController extends Observable implements IGoController {

    private IGameField gamefield;
    private String statusLine;
    private boolean operate;
    private IGameFieldDAO database;

    final ActorSystem actorSystem = ActorSystem.create("actor-system");
    final ActorRef actorRef = actorSystem.actorOf(Props.create(Actor.class), "actor");

    @Inject
    public GoController(IGameFieldDAO gamefield) {
        operate = true;
        this.database = gamefield;
    }

    public void saveGame() {
        this.database.saveGame(gamefield);
    }

    @Override
    public void deleteGameById(String id) {
        this.database.deleteGameById(id);
    }

    public List<IGameField> getAllGames() {
        return this.database.getAllGames();
    }

    public void loadGameById(String id) {
        this.gamefield = this.database.getGameById(id);
    }

    @Override
    public void createField(int length) {
        operate = true;
        gamefield = new GameField(length);

        statusLine = "Gamefield " + gamefield.getGameFieldSize() + "x"
                + gamefield.getGameFieldSize() + " successfully created, \n"
                + gamefield.getNext() + " is next";


        //update
        actorRef.tell(new ActorMessage("update", this), null);
    }

    @Override
    public String tuiToString() {
        return gamefield.toString();
    }

    @Override
    public boolean setStone(int x, int y) {

        if (!operate) {
            statusLine = "Game already closed, not allowed to set a stone";
            //update
            actorRef.tell(new ActorMessage("update", this), null);

            return false;
        }

        boolean status;

        String next = gamefield.getNext();
        if (gamefield.setStone(x, y)) {
            statusLine = ("set " + next.toUpperCase() + " at (" + x + "," + y
                    + ") \n" + gamefield.getNext() + " is next");
            status = true;

        } else {
            statusLine = ("unable to set stone at (" + x + "," + y + ")\n"
                    + next + " is still next");
            status = false;
        }

        //update
        actorRef.tell(new ActorMessage("update", this), null);

        if ((gamefield.getblackPlayer().getScore() + gamefield.getwhitePlayer()
                .getScore()) == (gamefield.getGameFieldSize() * gamefield
                .getGameFieldSize())) {
            this.stop();
        }

        return status;
    }

    public void setStone(int x, int y, int status) {

        if (!operate) {
            statusLine = "Game already closed, not allowed to set a stone";
            //update
            actorRef.tell(new ActorMessage("update", this), null);
            return;
        }

        gamefield.setStone(x, y, status);
        //update
        actorRef.tell(new ActorMessage("update", this), null);
    }

    public String getStatus() {
        return statusLine;
    }

    @Override
    public int getwhitePlayerScore() {
        return gamefield.getwhitePlayer().getScore();
    }

    @Override
    public int getblackPlayerScore() {
        return gamefield.getblackPlayer().getScore();
    }

    @Override
    public int getCellStatus(int x, int y) {
        return gamefield.getCellStatus(x, y);
    }

    @Override
    public String getNext() {
        return gamefield.getNext();
    }

    @Override
    public boolean pass() {
        if (!operate) {
            statusLine = "Game already closed, not allowed to pass";
            //update
            actorRef.tell(new ActorMessage("update", this), null);
            return false;
        }

        boolean pass = gamefield.pass();
        if (pass) {
            stop();
        } else {
            statusLine = "Player passed, " + gamefield.getNext() + " is next";
            //update
            actorRef.tell(new ActorMessage("update", this), null);
        }
        return pass;
    }

    @Override
    public int getGameFieldSize() {
        return gamefield.getGameFieldSize();
    }

    @Override
    public void stop() {
        operate = false;
        statusLine = "Game has ended";
        //update
        actorRef.tell(new ActorMessage("update", this), null);
    }

    public boolean getOperate() {
        return operate;
    }

    @Override
    public boolean getPassed() {
        return gamefield.passed();
    }



}
