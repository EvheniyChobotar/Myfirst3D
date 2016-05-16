package ua.edu.cdu.pm3.ChobotarEV.rendering;

import java.io.File;
import org.lwjgl.opengl.Display;
import ua.edu.cdu.pm3.ChobotarEV.managment.TexturesManagement;
// get static members of GL 11
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.ModelsLoader;
import ua.edu.cdu.pm3.ChobotarEV.mathPart.ColorsMap;
import ua.edu.cdu.pm3.ChobotarEV.mathPart.HeightsBiLinearInterpolation;

public class Render {
    
    String  resPath     = "res/textures",
            texFloor    = "floor.jpg",
            texStone    = "test.jpg";
    
    public static Texture   textureFloor,
                            textureStone;
    public static Model     grass;
    public static int       terrainList,
                            objectList;
    
    public static float     zoom = 0.25f;
    public static final int maxY = 50;
    public static HeightsBiLinearInterpolation heights ;
    public static ColorsMap colors;
    
    public void initializeTextures() {
        textureFloor = new TexturesManagement().loadTextures(resPath, texFloor);
        textureStone = new TexturesManagement().loadTextures(resPath, texStone);
    }
    
    public void initializeModels() {
        grass = ModelsLoader.loadModel(new File("res/models/Grass/Grass_02.obj"));
    }
    
    public void generateTerrain() {
        heights = new HeightsBiLinearInterpolation();
        colors  = new ColorsMap();
        objectList = glGenLists(2);
        glNewList(objectList, GL_COMPILE); {
        glBegin(GL_TRIANGLES);

            
        for(Face face : Render.grass.faces) {
            
            glColor3f(1,0,0);
            Vector3f normal1 = Render.grass.normals.get((int)face.normals.x - 1);
            glNormal3f(normal1.x, normal1.y, normal1.z);
            Vector3f vertices1 = Render.grass.vertices.get((int)face.vertex.x - 1);
            glTexCoord3f(vertices1.x, vertices1.y, vertices1.z);
            glVertex3f(vertices1.x, vertices1.y, vertices1.z);
            

            Vector3f normal2 = Render.grass.normals.get((int)face.normals.y - 1);
            glNormal3f(normal2.x, normal2.y, normal2.z);
            Vector3f vertices2 = Render.grass.vertices.get((int)face.vertex.y - 1);
            glTexCoord3f(vertices2.x, vertices2.y, vertices2.z);
            glVertex3f(vertices2.x, vertices2.y, vertices2.z);

            Vector3f normal3 = Render.grass.normals.get((int)face.normals.z - 1);
            glNormal3f(normal3.x, normal3.y, normal3.z);
            Vector3f vertices3 = Render.grass.vertices.get((int)face.vertex.z - 1);
            glTexCoord3f(vertices3.x, vertices3.y, vertices3.z);
            glVertex3f(vertices3.x, vertices3.y, vertices3.z);
            
            glTranslatef(10, heights.getHeightAt(10, 15)+5, 15);
        }
        
        glEnd();
        }
        glEndList();
        
        
        terrainList = glGenLists(1);
        glNewList(terrainList, GL_COMPILE);
        //textureStone.bind();
//      determines thr boundaries of primitiv`s verticies  (GL_QUADS every 4 verticies determines rectanglar) 
        glBegin(GL_QUADS);
        for(int x=0; x<heights.height.length; x++)
            for(int y=0; y<heights.height[x].length; y++) {
                glColor3f(colors.getRedAt(x, y)*heights.getHeightAt(x, y), 
                          colors.getGreenAt(x, y)*heights.getHeightAt(x, y), 
                          colors.getBlueAt(x, y)*heights.getHeightAt(x, y));
//              glTexCoord2f(0, 0);
//                          x                   y                                   z
                glVertex3f(x*zoom,      heights.getHeightAt(x, y)*maxY,         y*zoom);
//              glTexCoord2f(1, 0);
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x + 1, y)*maxY,     y*zoom);
//              glTexCoord2f(1, 1);
                glVertex3f((x+1)*zoom,  heights.getHeightAt(x+1, y+1)*maxY,     (y+1)*zoom);
//              glTexCoord2f(0, 1);
                glVertex3f(x*zoom,      heights.getHeightAt(x, y+1)*maxY,       (y+1)*zoom);
            }
        glEnd();
        glEndList();
    }
    
    public void initialize3D() {
        //Start 3D Stuff
//      matrix for projection all objects in 3d to display
        glMatrixMode(GL_PROJECTION);
//      chenge set current matrix to identity 
        glLoadIdentity();
        
//      glu(visionAngle,cutObjetsOverVisionZoneNear,cutObjetsOverVisionZoneFar,visionDepth)
        GLU.gluPerspective((float) 60, Display.getDisplayMode().getWidth()/ Display.getDisplayMode().getHeight(), 0.001f, 150);
        glEnable(GL_BLEND);
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
        //Clear the screen
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glLoadIdentity();
    }
}
