package ua.edu.cdu.pm3.ChobotarEV.rendering;

import org.newdawn.slick.opengl.Texture;
import ua.edu.cdu.pm3.ChobotarEV.managment.TexturesLoader;

public class Textures {
 String  resPath     = "res/textures",
            texFloor    = "floor.jpg",
            texStone    = "test.jpg";
    
    public static Texture   textureFloor,
                            textureStone;
    
    public void initializeTextures() {
        textureFloor = new TexturesLoader().loadTextures(resPath, texFloor);
        textureStone = new TexturesLoader().loadTextures(resPath, texStone);
    }
}
