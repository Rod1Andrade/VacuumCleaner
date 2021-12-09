package com.github.rod1andrade.app;

import com.github.rod1andrade.inputs.KeyboardInput;
import com.github.rod1andrade.render.WindowRender;
import com.github.rod1andrade.window.Window;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        WindowRender windowRender = new WindowRender(new KeyboardInput());
        new Window(windowRender, "Vacuum Cleaner");

        windowRender.initResources();
        windowRender.start();
    }
}
