package de.Laplace.gui;

import de.Laplace.logic.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvent implements KeyListener {

    private Engine engine;

    public KeyboardEvent(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) engine.setMenuOpen(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
