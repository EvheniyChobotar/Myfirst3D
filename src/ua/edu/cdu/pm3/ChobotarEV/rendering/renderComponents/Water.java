package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import static org.lwjgl.opengl.GL11.*;
import static ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Terrain.heights;
import static ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Terrain.zoom;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Textures;

public class Water extends Terrain{
    
    public Water() {
        waterList = drawWater(8);
    }
    public static int waterList;
    public float posY = 20;
    public static float dy = 24;
    
    public int drawWater(int numList) {
        int list = glGenLists(numList);
        glNewList(list, GL_COMPILE);
        glBindTexture(GL_TEXTURE_2D,Textures.textureWater.getTextureID());
        
        for(int x=1; x<(heights.height.length-1); x+=2)
            for(int z=1; z<(heights.height[x].length-1); z++) {
                glBegin(GL_TRIANGLE_FAN);
                                                             glTexCoord2f((x  )*textureBit, (z  )*textureBit);
                glVertex3f((x  )*zoom,  posY,  (z  )*zoom); glTexCoord2f((x+1)*textureBit, (z  )*textureBit);
                glVertex3f((x+1)*zoom,  posY,  (z  )*zoom); glTexCoord2f((x+1)*textureBit, (z+1)*textureBit);   
                glVertex3f((x+1)*zoom,  posY,  (z+1)*zoom); glTexCoord2f((x  )*textureBit, (z+1)*textureBit);
                glVertex3f((x  )*zoom,  posY,  (z+1)*zoom); glTexCoord2f((x-1)*textureBit, (z+1)*textureBit);
                glVertex3f((x-1)*zoom,  posY,  (z+1)*zoom); glTexCoord2f((x-1)*textureBit, (z  )*textureBit);
                glVertex3f((x-1)*zoom,  posY,  (z  )*zoom); glTexCoord2f((x-1)*textureBit, (z-1)*textureBit);
                glVertex3f((x-1)*zoom,  posY,  (z-1)*zoom); glTexCoord2f((x  )*textureBit, (z-1)*textureBit);
                glVertex3f((x  )*zoom,  posY,  (z-1)*zoom); glTexCoord2f((x+1)*textureBit, (z-1)*textureBit);
                glVertex3f((x+1)*zoom,  posY,  (z-1)*zoom); glTexCoord2f((x  )*textureBit, (z  )*textureBit);
                glVertex3f((x  )*zoom,  posY,  (z  )*zoom);
               
                glEnd();
            }
        
        glEndList();
        return list;
    }
}
