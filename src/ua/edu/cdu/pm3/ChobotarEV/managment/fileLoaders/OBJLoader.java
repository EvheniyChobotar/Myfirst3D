package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Face;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;

public class OBJLoader {
    
    public static Model parseOBJfile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("res/models/"+file));
            Model model = new Model();
            String line;
            String oName = "";
            
            while((line = reader.readLine()) != null) {
                if(line.startsWith("usemtl ") && !oName.equals(line.split(" ")[1])) {
                    oName = String.valueOf(line.split(" ")[1]);
                } else
//              get vertices
                if(line.startsWith("v ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    
                    model.vertices.add(new Vector3f(x,y,z));
                } else 
//              get texture texture cordinates
                if(line.startsWith("vt ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    model.textureUV.add(new Vector2f(x,y));
                } else 
//              get normals 
                if (line.startsWith("vn ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    model.normals.add(new Vector3f(x,y,z));
//              get faces
                } else 
                if (line.startsWith("f ")) {
                // vertex indexes
                    float   vx = Float.valueOf(line.split(" ")[1].split("/")[0]),
                            vy = Float.valueOf(line.split(" ")[2].split("/")[0]),
                            vz = 0,
                            vw = 0;
                    try {
                        vz = Float.valueOf(line.split(" ")[3].split("/")[0]);
                        vw = Float.valueOf(line.split(" ")[4].split("/")[0]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    Vector4f vertexIndeces = new Vector4f(vx,vy,vz,vw);

                    // texture indexes
                    
                    float   tx = 0,
                            ty =0,
                            tz =0,
                            tw = 0;
                    try {
                            tx = Float.valueOf(line.split(" ")[1].split("/")[1]);
                            ty = Float.valueOf(line.split(" ")[2].split("/")[1]) ;
                            tz = Float.valueOf(line.split(" ")[3].split("/")[1]);
                            tw = Float.valueOf(line.split(" ")[4].split("/")[1]);
                    
                        
                    }   catch (ArrayIndexOutOfBoundsException n) {}
                        catch (NumberFormatException n) {}
                    Vector4f textureIndeces = new Vector4f(tx,ty,tz,tw);

                    // normal indexes
                    float   nx = 0,
                            ny = 0,
                            nz = 0,
                            nw = 0;
                    try {
                        nx = Float.valueOf(line.split(" ")[1].split("/")[2]);
                        ny = Float.valueOf(line.split(" ")[2].split("/")[2]);
                        nz = Float.valueOf(line.split(" ")[3].split("/")[2]);
                        nw = Float.valueOf(line.split(" ")[4].split("/")[2]);
                    } catch (ArrayIndexOutOfBoundsException e) {}
                      catch (NumberFormatException n) {}

                    Vector4f normalIndeces = new Vector4f(nx,ny,nz,nw);
                    model.faces.add(new Face(vertexIndeces, normalIndeces, textureIndeces, oName));
                }
            }
            reader.close();
            return model;
        } catch (IOException io) {
            return null;
        }
    }
    
}
