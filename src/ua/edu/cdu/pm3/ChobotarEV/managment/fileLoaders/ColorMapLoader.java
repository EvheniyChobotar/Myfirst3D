package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ColorMapLoader {
    
    public float[][] loadColorsMap(String fname, float[][] colorMap, int needed) {
       
        try {
            BufferedImage image = ImageIO.read(new File(fname));
            colorMap = new float[image.getWidth()][image.getHeight()];
            for(int x=0;x<image.getWidth();x++) 
                for(int y=0;y<image.getHeight();y++) {
                   
                    Color col = new Color(image.getRGB(x, y));
                    
                    switch(needed) {
                        case 1:
                            colorMap[x][y] = (col.getRed())/255f;
                                break;
                        case 2:
                            colorMap[x][y] = (col.getGreen())/255f;
                                break;
                        case 3:
                            colorMap[x][y] = (col.getBlue())/255f;
                                break;
                        default:
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return colorMap;
    }
}
