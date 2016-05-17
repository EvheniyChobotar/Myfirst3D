package ua.edu.cdu.pm3.ChobotarEV.mathPart;

import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.HeightsMapLoader;



public class HeightsMap {
    
    public String path;
    //An array of points to represent the y value at a given point (x,z)
    public float[][]    height;
   
    
    public  HeightsMap() {
        HeightsMapLoader heightsMapLoader = new HeightsMapLoader();
        this.path   = "res/heights.png";
        this.height = heightsMapLoader.loadHeightsMap(path, height);
        
    }

    
   
    public float getHeightAt(int x, int z){
        //Just returns the y value at the point x,z
        try {
            return height[x][z];
        }catch (Exception e) {
            return 0;
        }
    }
//  bi-linear interpolation
    public float calculateHeight(float x, float z){

        float x1, x2, y1, y2, Q11, Q12, Q21, Q22;
        x1 = (int) Math.floor(x);
        x2 = (int) Math.floor(x+1);
        y1 = (int) Math.floor(z);
        y2 = (int) Math.floor(z+1);

        Q11 = getHeightAt((int) x1, (int) y1);
        Q12 = getHeightAt((int) x1, (int) y2);
        Q21 = getHeightAt((int) x2, (int) y1);
        Q22 = getHeightAt((int) x2, (int) y2);

        return (1f/( (x2-x1)*(y2-y1) ))*( Q11*(x2-x)*(y2-z)+
            Q21*(x-x1)*(y2-z)+
            Q12*(x2-x)*(z-y1)+
            Q22*(x-x1)*(z-y1));
    }

    public float calculateHeightRounded(float x, float z) {
        return getHeightAt(Math.round(x), Math.round(z));
    }
    
}
