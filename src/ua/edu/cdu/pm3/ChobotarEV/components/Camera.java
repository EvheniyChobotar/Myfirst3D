package ua.edu.cdu.pm3.ChobotarEV.components;

import ua.edu.cdu.pm3.ChobotarEV.rendering.Render;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    Vector3f moveVector     = new Vector3f(10,0,15);
    Vector3f rotation       = new Vector3f();
    Vector3f vectorPrevious = new Vector3f();
    static boolean  moveForward     = false, 
                    moveBackward    = false,
                    strafeLeft      = false, 
                    strafeRight     = false;

    final float speed       = 0.1f;
 
    public void update() {
        updatePrevious();
        input();
        updateVector();
    }
	
    public void updateVector() {
        if(isMoveForward()) {
            moveVector.x -= (float) (Math.sin(-rotation.y * Math.PI / 180) * speed);
            moveVector.z -= (float) (Math.cos(-rotation.y * Math.PI / 180) * speed);
        }
        setMoveForward(false);
        
        if(isMoveBackward()) {
            moveVector.x += (float) (Math.sin(-rotation.y * Math.PI / 180) * speed);
            moveVector.z += (float) (Math.cos(-rotation.y * Math.PI / 180) * speed);
        }
        setMoveBackward(false);
        
        if(isStrafeLeft()) {
            moveVector.x += (float) (Math.sin((-rotation.y - 90) * Math.PI / 180) * speed);
            moveVector.z += (float) (Math.cos((-rotation.y - 90) * Math.PI / 180) * speed);
        }
        setStrafeLeft(false);
        
        if(isStrafeRight()) {
            moveVector.x += (float) (Math.sin((-rotation.y + 90) * Math.PI / 180) * speed);
            moveVector.z += (float) (Math.cos((-rotation.y + 90) * Math.PI / 180) * speed);
        }
        setStrafeRight(false);
    }
	
    public void render() {
        new Render().clearScreen();
       
       //translatePostion();
//      This is the code that changes 3D perspective to the camera's perspective.
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(rotation.z, 0, 0, 1);
//      -moveVector.y-1.4f means that your y is your feet, and y+2.4 is your head.
        glTranslatef(-moveVector.x, -moveVector.y-1.4f, -moveVector.z);
        
        moveVector.y = Render.heights.calculateHeight(moveVector.x*4, moveVector.z*4)*Render.maxY;
        glCallList(Render.objectList);
        glCallList(Render.terrainList);
        
    }

    public void updatePrevious() {
        //Update last position (for collisions (later))
        vectorPrevious.x = moveVector.x;
        vectorPrevious.y = moveVector.y;
        vectorPrevious.z = moveVector.z;
    }

    public void input() {
        if(Mouse.isGrabbed()){
            float mouseDX = Mouse.getDX() * 0.8f * 0.16f;
            float mouseDY = Mouse.getDY() * 0.8f * 0.16f;
            if (rotation.y + mouseDX >= 360) {
                rotation.y = rotation.y + mouseDX - 360;
                
            } else if (rotation.y + mouseDX < 0) {
                rotation.y = 360 - rotation.y + mouseDX;
                
            } else {
                rotation.y += mouseDX;
            }
            
            if (rotation.x - mouseDY >= -89 && rotation.x - mouseDY <= 89) {
                rotation.x += -mouseDY;
                
            } else if (rotation.x - mouseDY < -89) {
                rotation.x = -89;
                
            } else if (rotation.x - mouseDY > 89) {
                rotation.x = 89;
            }
        }
    }

    
    
    public boolean isMoveForward() {
        return moveForward;
    }

    public static void setMoveForward(boolean state) {
        moveForward = state;
    }

    public boolean isMoveBackward() {
        return moveBackward;
    }

    public static void setMoveBackward(boolean state) {
        moveBackward = state;
    }

    public boolean isStrafeLeft() {
        return strafeLeft;
    }

    public static void setStrafeLeft(boolean state) {
        strafeLeft = state;
    }

    public boolean isStrafeRight() {
        return strafeRight;
    }

    public static void setStrafeRight(boolean state) {
        strafeRight = state;
    }
}
