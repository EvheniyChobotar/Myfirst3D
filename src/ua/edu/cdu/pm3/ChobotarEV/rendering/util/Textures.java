package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.TexturesLoader;

public class Textures {
    String  resourcesPath   = "textures/",
            sky             = "sky.jpg",
            white           = "white.jpg",
            colorMap        = "colors1.png";
    
    public static Texture   textureSky,
                            textureWhite,
                            textureMap;
    public void initializeTextures() {
        textureSky   = new TexturesLoader().loadTextures(resourcesPath+sky);
        textureWhite = new TexturesLoader().loadTextures(resourcesPath+white);
        textureMap   = new TexturesLoader().loadTextures(resourcesPath+colorMap);
    }
}
