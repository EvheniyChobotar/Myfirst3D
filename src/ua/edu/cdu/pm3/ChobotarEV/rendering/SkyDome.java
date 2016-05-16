package ua.edu.cdu.pm3.ChobotarEV.rendering;

import static org.lwjgl.opengl.GL11.*;

public class SkyDome {
    public static int list;
    
    public void drawSphere() {
        list = glGenLists(1);
        int radius = 200;
        final double deg_to_rad = Math.PI / 180;
        final double rad_to_deg = 180 / Math.PI;
 
        double x, y, z;
 
        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
 
 
        glNewList(list, GL_COMPILE);
        for (double phi = 0.0; phi <= 80.0; phi += 10.0) {
            
            glBegin(GL_QUAD_STRIP);
                for (double theta = -180.0; theta <= 180.0; theta += 10.0) {
                    
                    glColor3f(0,0,0.1f);
                          x = radius * Math.sin(Math.PI/180 * theta) * Math.cos(Math.PI/180 * phi);
                          z = radius * Math.cos(Math.PI/180 * theta) * Math.cos(Math.PI/180 * phi);
                          y = radius * Math.sin(Math.PI/180 * phi);
 
                  glVertex3d (x,y,z);
                          x = radius * Math.sin(Math.PI/180 * theta) * Math.cos(Math.PI/180 * (phi + 10.0));
                          z = radius * Math.cos(Math.PI/180 * theta) * Math.cos(Math.PI/180 * (phi + 10.0));
                          y = radius * Math.sin(Math.PI/180 * (phi + 10.0));
                  glVertex3d (x,y,z);
        }
            glEnd();
      }
        glEndList();
    }
    
    public void initSkyTexture() {
        
    }
}
