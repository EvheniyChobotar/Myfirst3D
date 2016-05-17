package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.TexturesLoader;

public class Textures {
    String  resPath     = "res/textures",
            texFloor    = "floor.png",
            texStone    = "test.jpg",
            texSky      = "Sky_horiz_15_4096.jpg";
    
    public static Texture   textureFloor,
                            textureStone,
                            textureSky;
    public void initializeTextures() {
        textureFloor = new TexturesLoader().loadTextures(resPath, texFloor);
        textureStone = new TexturesLoader().loadTextures(resPath, texStone);
        textureSky =  new TexturesLoader().loadTextures(resPath, texSky);
    }
}
