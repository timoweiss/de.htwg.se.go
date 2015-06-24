package de.htwg.go.plugins;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by michaelknoch on 24.06.15.
 */
public class Plugin2 extends JFrame implements GoPlugins {

    IGoController controller;
    private JLabel label;
    String pluginName = "plugin2";

    @Override
    public void update(Event e) {
        label.setText(controller.getStatus());
    }

    @Override
    public void enable(IGoController controller) {
        this.controller = controller;
        setTitle(this.getName());
        Dimension dimension = new Dimension(200, 200);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        setSize(700, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        label = new JLabel();
        label.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
        add(label, BorderLayout.CENTER);


        label.setText(controller.getStatus());
        setVisible(true);
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
