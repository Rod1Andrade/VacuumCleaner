package graphics.inputs;

import java.awt.event.KeyAdapter;

/**
 * @author Rodrigo Andrade
 */
public abstract class KeyInput extends KeyAdapter {
    public abstract boolean hasPressed(int code);
}
