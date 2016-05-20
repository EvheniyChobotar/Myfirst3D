package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TexturesLoader {
    
     public Texture loadTextures(String path) {
        Texture nullTexture = null;
        try {
            return TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/"+path));
        } catch (Exception e) {
            System.err.println("Failed to load texture: ");
            e.printStackTrace();
            return nullTexture;
        }
   }
}
