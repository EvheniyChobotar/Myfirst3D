package ua.edu.cdu.pm3.ChobotarEV.components;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
    int frameRate = 60;
    public void createWindow(int width, int height, String title) {
        Display.setTitle(title);
        DisplayMode mode = new DisplayMode(width,height);
        try {
            Display.setDisplayModeAndFullscreen(mode);
            Display.create();
            
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
    
    void supportedInFullscreen() throws LWJGLException{
        DisplayMode[] modes = Display.getAvailableDisplayModes();
 
        for (int i=0;i<modes.length;i++) {
            DisplayMode current = modes[i];
            System.out.println(current.getWidth() + "x" + current.getHeight() + "x" +
                               current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
            }
    }
}
