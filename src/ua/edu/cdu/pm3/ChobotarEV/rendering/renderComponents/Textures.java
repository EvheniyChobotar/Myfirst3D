package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.TexturesLoader;

public class Textures {
    String  resourcesPath   = "textures/",
            floor           = "floor.jpg",
            stone           = "test.jpg",
            sky             = "sky.jpg",
            white           = "white.jpg";
    
    public static Texture   textureFloor,
                            textureStone,
                            textureSky,
                            textureWhite;
    public void initializeTextures() {
        textureFloor = new TexturesLoader().loadTextures(resourcesPath+floor);
        textureStone = new TexturesLoader().loadTextures(resourcesPath+stone);
        textureSky   = new TexturesLoader().loadTextures(resourcesPath+sky);
        textureWhite = new TexturesLoader().loadTextures(resourcesPath+white);
        
    }
}
