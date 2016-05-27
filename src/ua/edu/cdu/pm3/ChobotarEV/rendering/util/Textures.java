package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.TexturesLoader;

public class Textures {
    String  resourcesPath   = "textures/",
            sky             = "sky1.jpg",
            clouds          = "Clouds.png",
            colorMap        = "colors.png",
            water           = "water.jpg";
    
    public static Texture   textureSky,
                            textureMap,
                            textureClouds,
                            textureWater;
    public void initializeTextures() {
        textureSky   = new TexturesLoader().loadTextures(resourcesPath+sky);
        textureMap   = new TexturesLoader().loadTextures(resourcesPath+colorMap);
        textureClouds   = new TexturesLoader().loadTextures(resourcesPath+clouds);
        textureWater   = new TexturesLoader().loadTextures(resourcesPath+water);
    }
}
