package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Material;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;

public class MTLLoader {

    public static void parseMTLfile(Model model,File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("res/models/"+file));
            String  line;
            String name = "";
            float ns = 0;
            float d = 0;
            float[]  ka = null;
            float[]  kd = null;
            float[]  ks = null;
            Texture tex = null;
            while((line = reader.readLine()) != null) {
                if(line.startsWith("newmtl ") ) {
                    name = line.split(" ")[1];
                }else
                    
                if(line.startsWith("Ns ")) {
                    ns = Float.valueOf(line.split(" ")[1]);
                }else
                    
                if(line.startsWith("Ka ")) {
                    ka = new float[4];
                    ka[0] = Float.valueOf(line.split(" ")[1]);
                    ka[1] = Float.valueOf(line.split(" ")[2]);
                    ka[2] = Float.valueOf(line.split(" ")[3]);
                    ka[3] = 0;
                }else
                    
                if(line.startsWith("Kd ")) {
                    kd = new float[4];
                    kd[0] = Float.valueOf(line.split(" ")[1]);
                    kd[1] = Float.valueOf(line.split(" ")[2]);
                    kd[2] = Float.valueOf(line.split(" ")[3]);
                    kd[3] = 0;
                }else
                    
                if(line.startsWith("Ks ")) {
                    ks = new float[4];
                    ks[0] = Float.valueOf(line.split(" ")[1]);
                    ks[1] = Float.valueOf(line.split(" ")[2]);
                    ks[2] = Float.valueOf(line.split(" ")[3]);
                    ks[3] = 0;
                }else
                if(line.startsWith("Ke ")) {
                    //TODO
                }else
                if(line.startsWith("Ni ")) {
                    //TODO
                }else
                    
                if(line.startsWith("Ni ")) {
                //TODO
                }else
                if(line.startsWith("d ")) {
                    d = Float.valueOf(line.split(" ")[1]);
                    
                }else
                if(line.startsWith("map_Kd ")) {
                    tex = TexturesLoader.loadTextures(line.split(" ")[1]);
//                    System.err.println(name+" "+ tex.getTextureID()+" "+ ka[1]+" "+ kd[1]+" "+ ks[1]+" "+ ns+" "+d);
                    model.materials.add(new Material(name, tex, ka, kd, ks, ns, d));
                }
                   
            }
            reader.close();
        } catch (Exception e) {}
    }
}
