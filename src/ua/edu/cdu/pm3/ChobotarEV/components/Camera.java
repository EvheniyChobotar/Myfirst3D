package ua.edu.cdu.pm3.ChobotarEV.components;

import ua.edu.cdu.pm3.ChobotarEV.rendering.Render;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.SkyDome;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Terrain;

public class Camera extends Terrain{

    public Vector3f moveVector     = new Vector3f(20,0,20);
    public Vector3f rotation       = new Vector3f();
    public static boolean   moveForward     = false, 
                            moveBackward    = false,
                            strafeLeft      = false, 
                            strafeRight     = false;

    public final float      speed       = 0.15f;
 
    public void update() {
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
//      This is the code that changes 3D perspective to the camera's perspective.
        glRotatef(rotation.x, 1, 0, 0);
        glRotatef(rotation.y, 0, 1, 0);
        glRotatef(rotation.z, 0, 0, 1);
//      -moveVector.y-1.4f means that your y is your feet, and y+2.4 is your head.
        glTranslatef(-moveVector.x, -moveVector.y-1.4f, -moveVector.z);
        
//      -moveVector.y-1.4f means that your y is your feet, and y+2.4 is your head.
//        moveVector.y = Terrain.heights.calculateHeightAt(moveVector.x*4, moveVector.z*4)*Terrain.maxY;
//        glCallList(Terrain.terrainList);
//        
//        glCallList(Model.grassList1); 
//        glCallList(Model.grassList2);
//        glCallList(Model.grassList3);
        glCallList(Model.houseList);
        glCallList(SkyDome.skyList);
        SkyDome.rotation();
        glCallList(SkyDome.cloudsList);
    }

    public void input() {
        if(Mouse.isGrabbed()){
            float mouseDX = Mouse.getDX() * 0.16f;
            float mouseDY = Mouse.getDY() * 0.16f;
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
