package ua.edu.cdu.pm3.ChobotarEV.rendering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;
import ua.edu.cdu.pm3.ChobotarEV.managment.ModelsLoader;

public class Model {
    public List<Vector3f>   vertices    = new ArrayList<>();
    public List<Vector3f>   normals     = new ArrayList<>();
    public List<Face>       faces       = new ArrayList<Face>();
    public static int       objectList;
    
    public static Model     grass;
    public Model() {
        
    }
    
    public void initializeModels() {
        grass = ModelsLoader.loadModel(new File("res/models/Grass/Grass_02.obj"));
    }
    
    public void drawModel() {
        objectList = glGenLists(2);
        glNewList(objectList, GL_COMPILE); 
        for(Face face : grass.faces) {
            glBegin(GL_TRIANGLES);
            glColor3f(1,0,0);
            Vector3f normal1 = grass.normals.get((int)face.normals.x - 1);
            glNormal3f(normal1.x, normal1.y, normal1.z);
            Vector3f vertices1 = grass.vertices.get((int)face.vertex.x - 1);
            glTexCoord3f(vertices1.x, vertices1.y, vertices1.z);
            glVertex3f(vertices1.x, vertices1.y, vertices1.z);
            

            Vector3f normal2 = grass.normals.get((int)face.normals.y - 1);
            glNormal3f(normal2.x, normal2.y, normal2.z);
            Vector3f vertices2 = grass.vertices.get((int)face.vertex.y - 1);
            glTexCoord3f(vertices2.x, vertices2.y, vertices2.z);
            glVertex3f(vertices2.x, vertices2.y, vertices2.z);

            Vector3f normal3 = grass.normals.get((int)face.normals.z - 1);
            glNormal3f(normal3.x, normal3.y, normal3.z);
            Vector3f vertices3 = grass.vertices.get((int)face.vertex.z - 1);
            glTexCoord3f(vertices3.x, vertices3.y, vertices3.z);
            glVertex3f(vertices3.x, vertices3.y, vertices3.z);
            glEnd();

        }
        glEndList();
        
    }
}
