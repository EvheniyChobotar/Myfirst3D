package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Material;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Face;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.MTLLoader;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.OBJLoader;

public class Model {
    public List<Vector3f>   vertices        = new ArrayList<>();
    public List<Vector3f>   normals         = new ArrayList<>();
    public List<Vector2f>   textureUV       = new ArrayList<>();
    public List<Face>       faces           = new ArrayList<Face>();
    public static 
            List<Material>  materials       = new ArrayList<Material>();
    public int              posX;
    public float            posY;
    public int              posZ;
    public static int       grassList1,
                            grassList2,
                            grassList3,
                            houseList;
    public static Model     house;
    public static Model     grass;
   
    
    public void initializeModels() {
        int maxY = Terrain.maxY;
        
        grass = OBJLoader.parseOBJfile(new File("Grass/Grass_02.obj"));
        MTLLoader.parseMTLfile(grass,new File("Grass/Grass_02.mtl"));
        posX = 15; posZ = 15;
        posY = Terrain.heights.getHeightAt(posX*4, posZ*4)*maxY;
        grassList1 = drawModel(grass,posX,posY,posZ,4);
        
        posX = 12; posZ = 35;
        posY = Terrain.heights.getHeightAt(posX*4, posZ*4)*maxY;
        grassList2 = drawModel(grass,posX,posY-0.7f,posZ,5);
        
        posX = 16; posZ = 39;
        posY = Terrain.heights.getHeightAt(posX*4, posZ*4)*maxY;
        grassList3 = drawModel(grass,posX,posY-0.7f,posZ,6);
//        
        house = OBJLoader.parseOBJfile(new File("House/house.obj"));
        MTLLoader.parseMTLfile(house,new File("House/house.mtl"));
        posX = 70; posZ = 60;
        posY = Terrain.heights.getHeightAt(posX*4, posZ*4)*maxY;
        houseList = drawModel(house, posX, posY, posZ, 7);
    }
    
    public int drawModel(Model model,int posX,float posY,int posZ,int numList) {
//        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        int list = glGenLists(numList);
        glNewList(list, GL_COMPILE);
        int currentMaterial = 0;
        Material material = model.materials.get(currentMaterial);
        setMaterial(material.texture,
                    material.ambientColor,
                    material.diffuseColor,
                    material.specularColor,
                    material.specularExponent);
        for(Face face : model.faces) {
            if(!face.oName.equals(material.materialName)) {
                material = model.materials.get(findInd(model,face.oName));
                
                setMaterial(material.texture,
                        material.ambientColor,
                        material.diffuseColor,
                        material.specularColor,
                        material.specularExponent);
            }
            glBegin(GL_TRIANGLE_FAN);
                Vector3f vertices1      = model.vertices.get((int)face.vertices.x - 1);
                Vector3f normal1        = model.normals.get((int)face.normals.x - 1);
                glVertex3f(vertices1.x+posX, vertices1.y+posY, vertices1.z+posZ);
                glNormal3f(normal1.x, normal1.y, normal1.z);
//                try {
//                    Vector2f textureCoord1  = model.textureUV.get((int)face.textures.x - 1);
//                    glTexCoord2f(textureCoord1.x,textureCoord1.y);
//                } catch (ArrayIndexOutOfBoundsException e) {}
                   

                Vector3f vertices2      = model.vertices.get((int)face.vertices.y - 1);
                Vector3f normal2        = model.normals.get((int)face.normals.y - 1);
                glVertex3f(vertices2.x+posX, vertices2.y+posY, vertices2.z+posZ);
                glNormal3f(normal2.x, normal2.y, normal2.z);
//                try {
//                    Vector2f textureCoord2  = model.textureUV.get((int)face.textures.y - 1);
//                    glTexCoord2f(textureCoord2.x,textureCoord2.y);                
//                } 
//                catch (ArrayIndexOutOfBoundsException e) {}
            
                Vector3f vertices3      = model.vertices.get((int)face.vertices.z - 1);
                Vector3f normal3        = model.normals.get((int)face.normals.z - 1);
                glVertex3f(vertices3.x+posX, vertices3.y+posY, vertices3.z+posZ);
                glNormal3f(normal3.x, normal3.y, normal3.z);
//                try {
//                    Vector2f textureCoord3  = model.textureUV.get((int)face.textures.z - 1);
//                    glTexCoord2f(textureCoord3.x,textureCoord3.y);
//                }
//                catch (ArrayIndexOutOfBoundsException e) {}

                try{
                    Vector3f vertices4      = model.vertices.get((int)face.vertices.w - 1);
                    Vector3f normal4        = model.normals.get((int)face.normals.w - 1);
                    glVertex3f(vertices4.x+posX, vertices4.y+posY, vertices4.z+posZ);
                    glNormal3f(normal4.x, normal4.y, normal4.z);
//                    Vector2f textureCoord4  = model.textureUV.get((int)face.textures.w - 1);
//                    glTexCoord2f(textureCoord4.x,textureCoord4.y);
                    
                } catch (ArrayIndexOutOfBoundsException e) {}
            
                glVertex3f(vertices1.x+posX, vertices1.y+posY, vertices1.z+posZ);
                glNormal3f(normal1.x, normal1.y, normal1.z);
            
            glEnd();
             
        }
        glEndList();
        
        return list;
    }
    
    public void setMaterial(Texture texture, FloatBuffer ka, FloatBuffer kd, FloatBuffer ks, float ns) {
//        glBindTexture(GL_TEXTURE_2D,texture.getTextureID());
        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, ka);
//        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, kd);
        glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, ks);
//        glMaterial(GL_FRONT_AND_BACK, GL_EMISSION, 0f);
        glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, ns); 
    }
    
    public int findInd(Model model,String oN) {
        int ind = 1;
        for(Material material : model.materials) {
            if(material.materialName.equals(oN))
                return ind;
            ind++;
        }
        return 0;
    }
}
