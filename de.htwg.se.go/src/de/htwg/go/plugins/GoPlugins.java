package de.htwg.go.plugins;

import de.htwg.go.controller.IGoController;
import de.htwg.go.util.observer.IObserver;

/**
 * Created by michaelknoch on 24.06.15.
 */
public interface GoPlugins extends IObserver {
    void enable(IGoController controller);
    void deenable();
    String getName();
}
