package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

public class TerrainVertex {
    public static double x[];
    public static double y[];
    public static double z[];

    public TerrainVertex() {
    }
    
    public static double[] getX() {
        return x;
    }

    public static void setX(double[] x) {
        TerrainVertex.x = x;
    }

    public static double[] getY() {
        return y;
    }

    public static void setY(double[] y) {
        TerrainVertex.y = y;
    }

    public static double[] getZ() {
        return z;
    }

    public static void setZ(double[] z) {
        TerrainVertex.z = z;
    }
}
