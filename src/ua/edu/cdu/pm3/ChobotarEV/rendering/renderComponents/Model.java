package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import java.io.File;
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
    public static int       objectList;
    public static Model     model;
   
    
    public void initializeModels() {
        model = OBJLoader.loadModel(new File("House1/house.obj"));
//        MTLLoader.loadMTL("House1/house.mtl");
    }
    
    public void drawModel() {
        glPolygonMode (GL_FRONT_AND_BACK, GL_LINE);
        glColor3f(0.5f, 0, 0);
        objectList = glGenLists(2);
        glNewList(objectList, GL_COMPILE); 
//        bindTexture(0);
//        glColorMaterial ( GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE ) ;
//        glEnable ( GL_COLOR_MATERIAL ) ;
        int i=0;
        for(Face face : model.faces) {
//            if(!face.oName.equals(Material.materialName.get(i))) {
//                i=findInd(face.oName);
//                bindTexture(i);
//            }


            glBegin(GL_QUADS);
            Vector3f vertices1      = model.vertices.get((int)face.vertices.x - 1);
//            Vector2f textureCoord1  = model.textureUV.get((int)face.textures.x - 1);
            Vector3f normal1        = model.normals.get((int)face.normals.x - 1);
            glVertex3f(vertices1.x, vertices1.y, vertices1.z);
            glNormal3f(normal1.x, normal1.y, normal1.z);
//            glTexCoord2f(textureCoord1.x,textureCoord1.y);

            Vector3f vertices2      = model.vertices.get((int)face.vertices.y - 1);
//            Vector2f textureCoord2  = model.textureUV.get((int)face.textures.y - 1);
            Vector3f normal2        = model.normals.get((int)face.normals.y - 1);
            glVertex3f(vertices2.x, vertices2.y, vertices2.z);
            glNormal3f(normal2.x, normal2.y, normal2.z);
//            glTexCoord2f(textureCoord2.x,textureCoord2.y);
            
//
            Vector3f vertices3      = model.vertices.get((int)face.vertices.z - 1);
//            Vector2f textureCoord3  = model.textureUV.get((int)face.textures.z - 1);
            Vector3f normal3        = model.normals.get((int)face.normals.z - 1);
            glVertex3f(vertices3.x, vertices3.y, vertices3.z);
            glNormal3f(normal3.x, normal3.y, normal3.z);
//            glTexCoord2f(textureCoord3.x,textureCoord3.y);

            try {
                Vector3f vertices4      = model.vertices.get((int)face.vertices.w - 1);
//                Vector2f textureCoord4  = model.textureUV.get((int)face.textures.w - 1);
                Vector3f normal4        = model.normals.get((int)face.normals.w - 1);
                glVertex3f(vertices4.x, vertices4.y, vertices4.z);
                glNormal3f(normal4.x, normal4.y, normal4.z);
//                glTexCoord2f(textureCoord4.x,textureCoord4.y);
            } catch (ArrayIndexOutOfBoundsException e) {}
           
            glEnd();

        }
        System.err.println("finish");
        glEndList();
        
    }
    public void bindTexture(int i) {
        if(i>26) i = 0;
        glBindTexture(GL_TEXTURE_2D,Material.texture.get(i).getTextureID());
    }
    
    public int findInd(String oN) {
        int ind = 0;
        for(int i=0;i<Material.materialName.size();i++) {
            if(Material.materialName.get(i).equals(oN))
                ind = i;
        }
        return ind;
    }
}
