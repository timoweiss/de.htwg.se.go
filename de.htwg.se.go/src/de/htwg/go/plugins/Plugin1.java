package de.htwg.go.plugins;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.Event;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by michaelknoch on 24.06.15.
 */
public class Plugin1 extends JFrame implements GoPlugins {


    String pluginName = "plugin1";
    IGoController controller;
    private JLabel label;


    @Override
    public void update(Event e) {
        label.setText(new Date().toString());
    }

    @Override
    public void enable(IGoController controller) {

        this.controller = controller;
        /** frame options **/
        setTitle(this.getName());
        /* set minimum size */
        Dimension dimension = new Dimension(200, 200);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        label = new JLabel();
        label.setFont(new Font("Arial", Font.CENTER_BASELINE, 25));
        add(label, BorderLayout.CENTER);
        label.setText(new Date().toString());
        setVisible(true);


    }

    public void deenable() {
        setVisible(false);
    }

    @Override
    public String getName() {
        return this.pluginName;
    }
}
