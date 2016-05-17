package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

public class Vertex {
    public static double x[];
    public static double y[];
    public static double z[];
    public static double u[];
    public static double v[];



   
    public Vertex(int NumOfVertices) {
        
        this.x = new double[NumOfVertices];
        this.y = new double[NumOfVertices];
        this.z = new double[NumOfVertices];
        this.u = new double[NumOfVertices];
        this.v = new double[NumOfVertices];
        
    }
    
     public static double getX(int i) {
        return x[i];
    }

    public static double getY(int i) {
        return y[i];
    }

    public static double getZ(int i) {
        return z[i];
    }
    
    public static double getU(int i) {
        return u[i];
    }

    public static double getV(int i) {
        return v[i];
    }
    
}
