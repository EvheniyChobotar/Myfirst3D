package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HeightsMapLoader {
    
    public float[][] loadHeightsMap(String fname, float[][] height) {
        try {
            BufferedImage image = ImageIO.read(new File(fname));

            height = new float[image.getWidth()][image.getHeight()];
            for(int x=0;x<image.getWidth();x++){
                for(int y=0;y<image.getHeight();y++){
                    //Set the Y value. We can use Red because we expect the heightmap
                    //to be black and white, so all the RGB values would be the same.
                    //(Red value = Blue value = Green value) We just use red cause it's cool.
                    Color col = new Color(image.getRGB(x, y));
                    height[x][y] = (col.getRed())/255f;
                    //That also means this would spit out 'true':
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return height;
    }
    
    public float[] loadNewHeightsMap(String fname, float[] map) {
        try {
            BufferedImage image = ImageIO.read(new File(fname));
            int mapSize = image.getWidth()*image.getHeight();
            map = new float[mapSize*3];
            for(int x=0;x<image.getWidth();x++){
                for(int z=0;z<image.getHeight();z++){
                    //Set the Y value. We can use Red because we expect the heightmap
                    //to be black and white, so all the RGB values would be the same.
                    //(Red value = Blue value = Green value) We just use red cause it's cool.
                    
                    Color col = new Color(image.getRGB(x, z));
                    map[x] = x;
                    map[x+1] = (col.getRed())/255f;
                    map[x+2] = z;
                    //That also means this would spit out 'true':
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
