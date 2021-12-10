package com.github.rod1andrade.app;

import com.github.rod1andrade.inputs.KeyboardInput;
import com.github.rod1andrade.states.MainManagerState;
import com.github.rod1andrade.window.Window;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        MainManagerState mainManagerState = new MainManagerState(new KeyboardInput());
        new Window(mainManagerState, "Vacuum Cleaner");

        mainManagerState.initResources();
        mainManagerState.start();
    }
}
