package graphics.window;

import java.awt.Canvas;

import javax.swing.JFrame;

/**
 * @author Rodrigo Andrade
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 3912653148780270523L;

	public Window(Canvas canvas, String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(canvas);

        setResizable(false);
        pack();

        setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
