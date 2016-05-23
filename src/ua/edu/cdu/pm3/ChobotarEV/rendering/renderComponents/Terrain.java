package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import static org.lwjgl.opengl.GL11.*;
import ua.edu.cdu.pm3.ChobotarEV.mathPart.ColorsMap;
import ua.edu.cdu.pm3.ChobotarEV.mathPart.HeightsMap;

public class Terrain {
    
    public static int       terrainList;
    public static float     zoom = 0.25f;
    public static final int maxY = 50;
    public static HeightsMap heights ;
    public static ColorsMap  colors;
    
    public void drawTerrain() {
        heights = new HeightsMap();
        colors  = new ColorsMap();
        
        terrainList = glGenLists(1);
        glNewList(terrainList, GL_COMPILE);
        for(int x=0; x<(heights.height.length-1); x++)
            for(int y=0; y<(heights.height[x].length-1); y++) {
//                          x                   y                                   z
                glBegin(GL_TRIANGLE_STRIP);
                glColor3f(colors.getRedAt(x, y)*heights.getHeightAt(x, y), 
                          colors.getGreenAt(x, y)*heights.getHeightAt(x, y), 
                          colors.getBlueAt(x, y)*heights.getHeightAt(x, y));
                
                glVertex3f(x*zoom,      heights.getHeightAt(x, y)*maxY,         y*zoom);
                glColor3f(colors.getRedAt(x+1, y)*heights.getHeightAt(x+1, y), 
                          colors.getGreenAt(x+1, y)*heights.getHeightAt(x+1, y), 
                          colors.getBlueAt(x+1, y)*heights.getHeightAt(x+1, y));
                
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x + 1, y)*maxY,     y*zoom);
                glColor3f(colors.getRedAt(x, y+1)*heights.getHeightAt(x, y+1), 
                          colors.getGreenAt(x, y+1)*heights.getHeightAt(x, y+1), 
                          colors.getBlueAt(x, y+1)*heights.getHeightAt(x, y)); 
                glVertex3f(x*zoom,      heights.getHeightAt(x, y+1)*maxY,       (y+1)*zoom);
                glColor3f(colors.getRedAt(x+1, y+1)*heights.getHeightAt(x+1, y+1), 
                          colors.getGreenAt(x+1, y+1)*heights.getHeightAt(x+1, y+1), 
                          colors.getBlueAt(x+1, y+1)*heights.getHeightAt(x+1, y+1));
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x+1, y+1)*maxY,     (y+1)*zoom);
                glEnd();
            }
        
        glEndList();
    }

}
