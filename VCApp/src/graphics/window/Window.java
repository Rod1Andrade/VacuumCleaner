package graphics.window;

import javax.swing.*;
import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public class Window extends JFrame {

    public Window(Canvas canvas, String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(canvas);
        setResizable(false);

        pack();

        setVisible(true);
        setLocationRelativeTo(null);
    }

}
