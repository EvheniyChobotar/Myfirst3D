package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.lwjgl.util.vector.Vector3f;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Face;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;

public class ModelsLoader {
    public static Model loadModel(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Model model = new Model();
            
            String line;
            while((line = reader.readLine()) != null) {
                if(line.startsWith("v ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    
                    model.vertices.add(new Vector3f(x,y,z));
                } else if (line.startsWith("vn ")) {
                    float x = Float.valueOf(line.split(" ")[1]);
                    float y = Float.valueOf(line.split(" ")[2]);
                    float z = Float.valueOf(line.split(" ")[3]);
                    
                    model.normals.add(new Vector3f(x,y,z));
                } else if (line.startsWith("f ")) {
                    Vector3f vertexIndeces = new Vector3f(  Float.valueOf(line.split(" ")[1].split("/")[0]),
                                                            Float.valueOf(line.split(" ")[2].split("/")[0]),
                                                            Float.valueOf(line.split(" ")[3].split("/")[0]));
                    
                    Vector3f normalIndeces = new Vector3f(  Float.valueOf(line.split(" ")[1].split("/")[2]),
                                                            Float.valueOf(line.split(" ")[2].split("/")[2]),
                                                            Float.valueOf(line.split(" ")[3].split("/")[2]));
                    
                    model.faces.add(new Face(vertexIndeces, normalIndeces));
                    
                }
                
            }
            reader.close();
            return model;
        } catch (IOException io) {
            return null;
        }
    }
}
