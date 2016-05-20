package ua.edu.cdu.pm3.ChobotarEV.mathPart;

import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.ColorMapLoader;

public class ColorsMap {

    
    public float[][]    red;
    public float[][]    green;
    public float[][]    blue;
    public String       path;
    
    public ColorsMap() {
        ColorMapLoader colorMapLoader    = new ColorMapLoader();
        this.path   = "res/map/colors.png";
        this.red    = colorMapLoader.loadColorsMap(path, red,   1);
        this.green  = colorMapLoader.loadColorsMap(path, green, 2);
        this.blue   = colorMapLoader.loadColorsMap(path, blue,  3);
    }
    
    public float getRedAt(int x,int z) {
        return red[x][z];
    }

    public float getGreenAt(int x,int z) {
        return green[x][z];
    }

    public float getBlueAt(int x,int z) {
        return blue[x][z];
    }

}
