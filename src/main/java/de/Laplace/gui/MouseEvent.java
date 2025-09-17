package de.Laplace.gui;

import de.Laplace.logic.Engine;

import java.awt.event.MouseListener;

public class MouseEvent implements MouseListener {

    private final Engine engine;

    public MouseEvent(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
            System.out.println("click");
            int x = e.getX();
            int y = e.getY();

            if (engine.isMenuOpen()) {
                engine.getPlayerLevel().onClick(x,y);
                return;
            } else {
                System.out.println("a");
                if (engine.existGoldenBall()) {
                    System.out.println("b");
                    int dx = engine.getGoldenBall().getX()+Resource.GOLDEN_BALL.getWidth()/2 - x;
                    int dy = engine.getGoldenBall().getY()+Resource.GOLDEN_BALL.getWidth()/2 - y;
                    if (Math.sqrt(dx*dx+dy*dy) < 60) {
                        System.out.println("c");
                        engine.getGoldenBall().despawn();
                    }
                }
            }

            int dx = x - engine.getSizeX()/2;
            int dy = y - engine.getSizeY()/2;

            if (Math.sqrt(dx*dx+dy*dy) < 150) {
                // Player Clicked
                engine.setMenuOpen(true);
            }
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
