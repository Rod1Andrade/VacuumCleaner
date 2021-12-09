package com.github.rod1andrade.app;

import com.github.rod1andrade.inputs.KeyboardInput;
import com.github.rod1andrade.render.ManagerRender;
import com.github.rod1andrade.window.Window;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        ManagerRender managerRender = new ManagerRender(new KeyboardInput());
        new Window(managerRender, "Vacuum Cleaner");

        managerRender.initResources();
        managerRender.start();
    }
}
