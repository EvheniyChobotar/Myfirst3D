package ua.edu.cdu.pm3.ChobotarEV.components;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
    int frameRate = 120;
    
    public void createWindow(int width, int height, String title) {
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (Exception e) {
            System.err.println("Display creation failed");
            e.printStackTrace();
        }
    }
    
    public void update() {
        Display.update();
        Display.sync(frameRate);
    }
    
    public void dispose() {
        Display.destroy();
    }
}
