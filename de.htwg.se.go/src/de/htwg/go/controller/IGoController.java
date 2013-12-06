package de.htwg.go.controller;

import de.htwg.go.util.observer.IObservable;

public interface IGoController extends IObservable {

	void createField();

	String tuiToString();

	void setStone(int x, int y);
	void setStone(int x, int y, int status);

	String getStatus();
}
