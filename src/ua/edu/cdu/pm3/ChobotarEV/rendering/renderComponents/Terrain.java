package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Textures;
import static org.lwjgl.opengl.GL11.*;
import ua.edu.cdu.pm3.ChobotarEV.map.HeightsMap;

public class Terrain {
    
    public static int       terrainList;
    public static float     zoom = 0.25f;
    public static final int maxY = 40;
    public static HeightsMap heights ;
//    public static ColorsMap  colors;
    
    public void drawTerrain() {
        heights = new HeightsMap();
//        colors  = new ColorsMap();
        terrainList = glGenLists(1);
        terrainSetMaterial();
        glNewList(terrainList, GL_COMPILE);
//        
//        float[] d = new float[4]; d[0] = 0; d[1] = 0; d[2] = 0; d[3] = 0;
//        float[] s = new float[4]; s[0] = 0; s[1] = 0; s[2] = 0; s[3] = 0;
//        float[] as = new float[4]; as[0] = 0; as[1] = 0; as[2] = 0; as[3] = 0;
//        
//       
//        FloatBuffer kd = BufferUtils.createFloatBuffer(4);
//        kd.put(d); kd.position(0);
//        FloatBuffer ks = BufferUtils.createFloatBuffer(4);
//        ks.put(s); ks.position(0);
//        FloatBuffer ka = BufferUtils.createFloatBuffer(4);
//        ka.put(a); ka.position(0);
        
//        glMaterial(GL_FRONT, GL_AMBIENT, ka);
//        glMaterial(GL_FRONT, GL_DIFFUSE, kd);
//        glMaterial(GL_FRONT, GL_SPECULAR, ks);
//        glMaterial(GL_FRONT, GL_EMISSION, 0f);
//        glMaterialf(GL_FRONT, GL_SHININESS, ns); 
        
//        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        glBindTexture(GL_TEXTURE_2D,Textures.textureMap.getTextureID());
        float textureBit = 1.0f/512f;
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

    
    public void terrainSetMaterial() {
        float[] diffuse = new float[4]; diffuse[0] = 1f; diffuse[1] = 1f ; diffuse[2] =1f; diffuse[3] = 1f;
        FloatBuffer diffuseBuffer = BufferUtils.createFloatBuffer(4);
        diffuseBuffer.put(diffuse); diffuseBuffer.position(0);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, diffuseBuffer);
    }
}
