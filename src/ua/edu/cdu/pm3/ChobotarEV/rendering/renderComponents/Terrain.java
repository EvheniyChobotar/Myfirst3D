package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Textures;
import static org.lwjgl.opengl.GL11.*;
import ua.edu.cdu.pm3.ChobotarEV.map.HeightsMap;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Buffers;

public class Terrain {
    public static int       terrainList;
    public static float     zoom = 0.25f;
    public float textureBit = 1.0f/512f;
    public static final int maxY = 40;
    public static HeightsMap heights = new HeightsMap() ;
//    public static ColorsMap  colors;
    
    public void drawTerrain() {
        heights.calculateHeights();
//        colors  = new ColorsMap();
        terrainList = glGenLists(1);
        glNewList(terrainList, GL_COMPILE);
        
        glMaterial(GL_FRONT, GL_AMBIENT, Buffers.toFloatBuffer(0.5f, 0.5f, 0.5f, 1f));
        glMaterial(GL_FRONT, GL_DIFFUSE, Buffers.toFloatBuffer(0.5f, 0.5f, 0.5f, 1f));

//        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        glBindTexture(GL_TEXTURE_2D,Textures.textureMap.getTextureID());
       
        for(int x=1; x<(heights.height.length-1); x+=2)
            for(int z=1; z<(heights.height[x].length-1); z++) {
                glBegin(GL_TRIANGLE_FAN);
//                glColor3f(colors.getRedAt(x, y)*heights.getHeightAt(x, y), 
//                          colors.getGreenAt(x, y)*heights.getHeightAt(x, y), 
//                          colors.getBlueAt(x, y)*heights.getHeightAt(x, y));
                                                                                          glTexCoord2f((x  )*textureBit, (z  )*textureBit);
                glVertex3f((x  )*zoom,  heights.getHeightAt(x  ,z  )*maxY,  (z  )*zoom); glTexCoord2f((x+1)*textureBit, (z  )*textureBit);
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x+1,z  )*maxY,  (z  )*zoom); glTexCoord2f((x+1)*textureBit, (z+1)*textureBit);   
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x+1,z+1)*maxY,  (z+1)*zoom); glTexCoord2f((x  )*textureBit, (z+1)*textureBit);
                glVertex3f((x  )*zoom,  heights.getHeightAt(x  ,z+1)*maxY,  (z+1)*zoom); glTexCoord2f((x-1)*textureBit, (z+1)*textureBit);
                glVertex3f((x-1)*zoom,  heights.getHeightAt(x-1,z+1)*maxY,  (z+1)*zoom); glTexCoord2f((x-1)*textureBit, (z  )*textureBit);
                glVertex3f((x-1)*zoom,  heights.getHeightAt(x-1,z  )*maxY,  (z  )*zoom); glTexCoord2f((x-1)*textureBit, (z-1)*textureBit);
                glVertex3f((x-1)*zoom,  heights.getHeightAt(x-1,z-1)*maxY,  (z-1)*zoom); glTexCoord2f((x  )*textureBit, (z-1)*textureBit);
                glVertex3f((x  )*zoom,  heights.getHeightAt(x  ,z-1)*maxY,  (z-1)*zoom); glTexCoord2f((x+1)*textureBit, (z-1)*textureBit);
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x+1,z-1)*maxY,  (z-1)*zoom); glTexCoord2f((x  )*textureBit, (z  )*textureBit);
                glVertex3f((x  )*zoom,  heights.getHeightAt(x  ,z  )*maxY,  (z  )*zoom);
               
                glEnd();
            }
        
        glEndList();
    }
}
