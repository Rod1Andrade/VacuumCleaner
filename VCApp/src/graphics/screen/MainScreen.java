package graphics.screen;

import graphics.main.VacuumCleaner;

import javax.swing.*;

public class Screen extends JFrame {

    public Screen(VacuumCleaner vacuumCleaner) {
        setTitle(vacuumCleaner.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(vacuumCleaner);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
