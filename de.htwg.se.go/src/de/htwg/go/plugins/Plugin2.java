package de.htwg.go.plugins;

import de.htwg.go.controller.IGoController;
import de.htwg.go.model.IGameField;
import de.htwg.go.util.observer.Event;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by michaelknoch on 24.06.15.
 */
public class Plugin2 extends JFrame implements GoPlugins {

    IGoController controller;
    String pluginName = "plugin2";

    @Override
    public void update(Event e) {

    }


    ArrayList<String> gameList;


    @Override
    public void enable(IGoController controller) {
        this.controller = controller;

        java.util.List<IGameField> games = this.controller.getAllGames();
        ArrayList<String> arrayList = new ArrayList<String>();

        for (IGameField game : games) {
            String id = game.getId();
            if (id != null) {
                arrayList.add(id);
            }
        }

        final JList gList = new JList(arrayList.toArray());

        gList.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Load: " + gList.getSelectedValue());
                    selectGame(gList.getSelectedValue().toString());
                }
            }
        });


        add(gList);


        // Create a JList that displays strings from an array


        setTitle(this.getName());

        setSize(500, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void selectGame(String gameid) {
        this.controller.loadGameById(gameid);
        this.controller.notifyObservers();
    }


    @Override
    public void deenable() {
        setVisible(false);
    }

    @Override
    public String getName() {
        return this.pluginName;
    }
}
