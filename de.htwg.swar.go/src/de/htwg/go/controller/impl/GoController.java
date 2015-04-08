package de.htwg.go.controller.impl;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IGameField;
import de.htwg.go.model.impl.GameField;
import de.htwg.go.util.observer.Observable;

public class GoController extends Observable implements IGoController {

    private IGameField gamefield;
    private String statusLine;
    private boolean operate;

    public GoController() {
        operate = true;
    }

    @Override
    public void createField(int length) {
        operate = true;
        gamefield = new GameField(length);

        statusLine = "Gamefield " + gamefield.getGameFieldSize() + "x"
                + gamefield.getGameFieldSize() + " successfully created, \n"
                + gamefield.getNext() + " is next";
        notifyObservers();
    }

    @Override
    public String tuiToString() {
        return gamefield.toString();
    }

    @Override
    public boolean setStone(int x, int y) {

        if (!operate) {
            statusLine = "Game already closed, not allowed to set a stone";
            notifyObservers();
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

        notifyObservers();

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
            notifyObservers();
            return;
        }

        gamefield.setStone(x, y, status);
        notifyObservers();
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
            notifyObservers();
            return false;
        }

        boolean pass = gamefield.pass();
        if (pass) {
            stop();
        } else {
            statusLine = "Player passed, " + gamefield.getNext() + " is next";
            notifyObservers();
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
        notifyObservers();
    }

    public boolean getOperate() {
        return operate;
    }

    @Override
    public boolean getPassed() {
        return gamefield.passed();
    }

}
