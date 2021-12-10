package com.github.rod1andrade.inputs;

import java.awt.event.KeyEvent;

/**
 * @author Rodrigo Andrade
 */
public class KeyboardInput extends KeyInput {

    private boolean[] keys = new boolean[255];

    private boolean validate(int key) {
        return key >= 0 && key < keys.length;
    }

    /**
     * Verifica se foi pressionado.
     *
     * @param key KeyEvent code
     * @return TRUE caso tenha sido pressionado e FALSE caso contrario.
     */
    public synchronized boolean hasPressed(int key) {
        if (!validate(key)) return false;
        return keys[key];
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (!validate(e.getKeyCode())) return;

        keys[e.getKeyCode()] = true;
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        if (!validate(e.getKeyCode())) return;

        keys[e.getKeyCode()] = false;
    }
}
