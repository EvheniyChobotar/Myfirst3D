package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Face;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;

public class OBJLoader {
    
    public static Model loadModel(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("res/models/"+file));
            Model model = new Model();
            Texture currentTexture = null;
            String line;
            
            while((line = reader.readLine()) != null) {
//              get vertices
                if(line.startsWith("v ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    
                    model.vertices.add(new Vector3f(x,y,z));
//              get texture texture cordinates
                } else 
                    if(line.startsWith("vt ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    model.textureUV.add(new Vector2f(x,y));
//              get normals
                }else if (line.startsWith("vn ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    
                    model.normals.add(new Vector3f(x,y,z));
                
//              get faces
                } else if (line.startsWith("f ")) 
                
                {
                    // vertex indexes
                    float   vx = Float.valueOf(line.split(" ")[1].split("/")[0]),
                            vy = Float.valueOf(line.split(" ")[2].split("/")[0]),
                            vz = 0,
                            vw = 1;
                    try {
                        vz = Float.valueOf(line.split(" ")[3].split("/")[0]);
                        vw = Float.valueOf(line.split(" ")[4].split("/")[0]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    Vector4f vertexIndeces = new Vector4f(vx,vy,vz,vw);
                    
                    // texture indexes
                    float   tx = Float.valueOf(line.split(" ")[1].split("/")[1]),
                            ty = Float.valueOf(line.split(" ")[2].split("/")[1]) ,
                            tz = 0,
                            tw = 1;
                    try {
                        tz = Float.valueOf(line.split(" ")[3].split("/")[1]);
                        tw = Float.valueOf(line.split(" ")[4].split("/")[1]);
                    } catch (ArrayIndexOutOfBoundsException n) {
                    }
                    Vector4f textureIndeces = new Vector4f(tx,ty,tz,tw);
                    
                    // normal indexes
                    float   nx = Float.valueOf(line.split(" ")[1].split("/")[2]),
                            ny = Float.valueOf(line.split(" ")[2].split("/")[2]),
                            nz = 0,
                            nw = 1;
                    try {
                        nz = Float.valueOf(line.split(" ")[3].split("/")[2]);
                        nw = Float.valueOf(line.split(" ")[4].split("/")[2]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    
                    Vector4f normalIndeces = new Vector4f(nx,ny,nz,nw);
                    
                    model.faces.add(new Face(vertexIndeces, normalIndeces, textureIndeces));
                } 
            }
            reader.close();
            return model;
        } catch (IOException io) {
            return null;
        }
    }
    
    public static void MTLparser() {
        
    }
    
}
