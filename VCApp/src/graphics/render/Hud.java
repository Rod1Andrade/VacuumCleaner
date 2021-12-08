package graphics.render;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Hud extends JPanel {

	private static final long serialVersionUID = 3659569702026144016L;
	
	public Hud(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}

}
