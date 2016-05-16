package ua.edu.cdu.pm3.ChobotarEV.components;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
    
    public boolean[] keys = new boolean[256];
    
    public boolean createKeyboard() {
        try {
            Keyboard.create();
            Mouse.setGrabbed(true);

            return true;
        } catch (LWJGLException e) {
            System.err.println("Keyboard creation failed");
            e.printStackTrace();
            return false;
        }
    }
    public void update() {
//      Update keys
        for(int i=0;i<keys.length;i++) {
            keys[i] = Keyboard.isKeyDown(i);
            if(keys[Keyboard.KEY_W]) {
                Camera.setMoveForward(true);
            }
            
            if(keys[Keyboard.KEY_S]) {
                Camera.setMoveBackward(true);
            }
            
            if(keys[Keyboard.KEY_A]) {
                Camera.setStrafeLeft(true);
            }
            if(keys[Keyboard.KEY_D]) {
                Camera.setStrafeRight(true);
            }
        }
    }
    
    public void dispose() {
        Keyboard.destroy();
    }


}
