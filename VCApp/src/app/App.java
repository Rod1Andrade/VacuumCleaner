package app;

import graphics.render.WindowRender;
import graphics.window.Window;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        WindowRender windowRender = new WindowRender();
        Window window = new Window(windowRender, "Vacuum Cleaner");

        windowRender.initResources();
        windowRender.start();
    }
}
