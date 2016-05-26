package ua.edu.cdu.pm3.ChobotarEV.rendering;

import org.lwjgl.opengl.Display;
// get static members of GL 11
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class Render {
    
    public void initialize3D() {
//      Start 3D Stuff
//      matrix for projection all objects in 3d to display
        glMatrixMode(GL_PROJECTION);
//      chenge set current matrix to identity 
        glLoadIdentity();
        
//      glu(visionAngle,cutObjetsOverVisionZoneNear,cutObjetsOverVisionZoneFar,visionDepth)
        GLU.gluPerspective((float) 60, Display.getDisplayMode().getWidth()/ Display.getDisplayMode().getHeight(), 0.001f,350);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
//      matrix of currenc active camera
        glMatrixMode(GL_MODELVIEW);
         
        glEnable(GL_TEXTURE_2D);
       
//      set smooth, another parameter is flat       
        glShadeModel(GL_SMOOTH);
//      clear to defoult color        
        glClearColor(1.0f, 1.0f, 1.0f, 0.5f);
//      clear Z-buffer        
        glClearDepth(1.0f);
//      enavle depth check. If disable this objects are not cover each other
        glEnable(GL_DEPTH_TEST);
        /*В качестве функции глубины используется glDepthFunc(GL_LEQUAL); GL_LEQUAL означает "меньше или равно", 
        то есть рисуется все, что имеет глубину, меньшую или равную текущей. Значение для буфера глубины рассчитывается как значение 1/Z. 
        То есть Z-значение от 2 меньше, чем Z-значение у 1, поскольку 1/1 > 1/2. Для GL_LEQUAL
        Z-значение от 2 будет рисоваться впереди фигуры с Z = 1.
*/
        glDepthFunc(GL_LEQUAL);
    }
    
    public void clearScreen() {
//      Clear the screen
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        
    }
}
