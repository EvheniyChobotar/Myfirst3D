package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;


import org.lwjgl.util.vector.Vector3f;

public class Face {
    public Vector3f vertex = new Vector3f();  // three indices
    public Vector3f normals  = new Vector3f();  // three indices
    
    public Face(Vector3f vertex,Vector3f normal) {
        this.vertex = vertex;
        this.normals = normal;
        
    }
    
}
