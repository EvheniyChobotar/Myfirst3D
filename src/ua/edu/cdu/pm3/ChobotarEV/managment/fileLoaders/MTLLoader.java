package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.FileReader;
import org.lwjgl.util.vector.Vector3f;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Material;

public class MTLLoader {

    public static Material loadMTL(String path) {
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader("res/models/"+path));
            String line;
            Material material = new Material();
            String  oName;
            String  texPath;
            while((line = reader.readLine()) != null) {
                if(line.startsWith("newmtl ") ) {
                    oName = line.split(" ")[1];
                    material.addMaterialName(oName);
                }
                if(line.startsWith("Ns ")) {
                    material.addMaterialNs(Float.valueOf(line.split(" ")[1]));
                }
                if(line.startsWith("Ka ")) {
                    Vector3f ka = new Vector3f(Float.valueOf(line.split(" ")[1]),
                                               Float.valueOf(line.split(" ")[2]),
                                               Float.valueOf(line.split(" ")[3]));
                    material.addMaterialKa(ka);
                }
                if(line.startsWith("Kd ")) {
                    Vector3f kd = new Vector3f(Float.valueOf(line.split(" ")[1]),
                                               Float.valueOf(line.split(" ")[2]),
                                               Float.valueOf(line.split(" ")[3]));
                    material.addMaterialKd(kd);
                }
                if(line.startsWith("Ks ")) {
                    Vector3f ks = new Vector3f(Float.valueOf(line.split(" ")[1]),
                                               Float.valueOf(line.split(" ")[2]),
                                               Float.valueOf(line.split(" ")[3]));
                    material.addMaterialKs(ks);
                }
                if(line.startsWith("Ke ")) {
                    Vector3f ke = new Vector3f(Float.valueOf(line.split(" ")[1]),
                                               Float.valueOf(line.split(" ")[2]),
                                               Float.valueOf(line.split(" ")[3]));
                    material.addMaterialKe(ke);
                }
                if(line.startsWith("d ")) {
                    material.addMaterialD(Float.valueOf(line.split(" ")[1]));
                }
                if(line.startsWith("map_Bump ")) {
                    texPath = line.split(" ")[1];
                    material.addMaterialTexture(TexturesLoader.loadTextures(texPath));
                }
                
            }
            return material;
        } catch (Exception e) {
            
        }
        return null;
    }
}
