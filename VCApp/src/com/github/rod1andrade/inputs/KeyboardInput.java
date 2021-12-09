package com.github.rod1andrade.inputs;

import java.awt.event.KeyEvent;

/**
 * @author Rodrigo Andrade
 */
public class KeyboardInput extends KeyInput {

    private boolean[] keys = new boolean[524];

    /**
     * Verifica se foi pressionado.
     *
     * @param key KeyEvent code
     * @return TRUE caso tenha sido pressionado e FALSE caso contrario.
     */
    public synchronized boolean hasPressed(int key) {
        return keys[key];
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
