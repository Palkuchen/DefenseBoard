package de.Laplace.gui;

import de.Laplace.logic.Engine;

import javax.swing.*;
import java.awt.*;

public class CustomWindow extends JFrame {

    private CustomCanvas canvas;

    public CustomWindow(Engine engine) {
        setTitle("Defenseboard");
        setSize(engine.getSizeX(), engine.getSizeY());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        setVisible(true);

        canvas = new CustomCanvas(engine);
        add(canvas);
    }
}
