package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

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
            List<Material>   materials       = new ArrayList<Material>();
    public float posX       =10;//60;
    public float posY       =0;//30;
    public float posZ       =10;//55;
    public static int       modelList;
    public static Model     house;
   
    
    public void initializeModels() {
        house = OBJLoader.parseOBJfile(new File("House1/house.obj"));
        MTLLoader.parseMTLfile(house,new File("House1/house.mtl"));
        drawModel(house);
    }
    
    public void drawModel(Model model) {
//        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
//        glColor3f(0.7f, 0, 0);
        modelList = glGenLists(2);
       
        glNewList(modelList, GL_COMPILE);
        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_AMBIENT);
        glEnable(GL_DIFFUSE);
        glEnable(GL_SPECULAR);
        
        int currentMaterial = 0;
        Material material = model.materials.get(currentMaterial);
        setMaterial(material.texture,material.ambientColor,material.diffuseColor,material.specularColor,material.specularExponent);
        for(Face face : model.faces) {
            if(!face.oName.equals(material.materialName)) {
                material = model.materials.get(findInd(model,face.oName));
                setMaterial(material.texture,material.ambientColor,material.diffuseColor,material.specularColor,material.specularExponent);
            }
            
            glBegin(GL_QUADS);
            
            Vector3f vertices1      = model.vertices.get((int)face.vertices.x - 1);
//            Vector2f textureCoord1  = model.textureUV.get((int)face.textures.x - 1);
            Vector3f normal1        = model.normals.get((int)face.normals.x - 1);
            glVertex3f(vertices1.x+posX, vertices1.y+posY, vertices1.z+posZ);
            glNormal3f(normal1.x, normal1.y, normal1.z);
//            glTexCoord2f(textureCoord1.x,textureCoord1.y);

            Vector3f vertices2      = model.vertices.get((int)face.vertices.y - 1);
//            Vector2f textureCoord2  = model.textureUV.get((int)face.textures.y - 1);
            Vector3f normal2        = model.normals.get((int)face.normals.y - 1);
            glVertex3f(vertices2.x+posX, vertices2.y+posY, vertices2.z+posZ);
            glNormal3f(normal2.x, normal2.y, normal2.z);
//            glTexCoord2f(textureCoord2.x,textureCoord2.y);
            
//
            Vector3f vertices3      = model.vertices.get((int)face.vertices.z - 1);
//            Vector2f textureCoord3  = model.textureUV.get((int)face.textures.z - 1);
            Vector3f normal3        = model.normals.get((int)face.normals.z - 1);
            glVertex3f(vertices3.x+posX, vertices3.y+posY, vertices3.z+posZ);
            glNormal3f(normal3.x, normal3.y, normal3.z);
//            glTexCoord2f(textureCoord3.x,textureCoord3.y);

            try {
                Vector3f vertices4      = model.vertices.get((int)face.vertices.w - 1);
//                Vector2f textureCoord4  = model.textureUV.get((int)face.textures.w - 1);
                Vector3f normal4        = model.normals.get((int)face.normals.w - 1);
                glVertex3f(vertices4.x+posX, vertices4.y+posY, vertices4.z+posZ);
                glNormal3f(normal4.x, normal4.y, normal4.z);
//                glTexCoord2f(textureCoord4.x,textureCoord4.y);
            } catch (ArrayIndexOutOfBoundsException e) {}
            
            glEnd();

        }
        System.err.println("finish");
        glEndList();
        
    }
    
    public void setMaterial(Texture texture,FloatBuffer ka,FloatBuffer kd,FloatBuffer ks,float ns) {
        
//        System.err.println(texture.getTextureID());
//        glBindTexture(GL_TEXTURE_2D,texture.getTextureID());
        glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, ka);
        glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, kd);
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
