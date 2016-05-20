package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;


import org.lwjgl.util.vector.Vector4f;

public class Face {
    
    public Vector4f vertices = new Vector4f();  
    public Vector4f normals  = new Vector4f();
    public Vector4f textures  = new Vector4f();
    public int      textureID;
    
    
    public Face(Vector4f vertex, Vector4f normal, Vector4f texture) {

        this.vertices = vertex;
        this.normals = normal;
        this.textures = texture;

    }
    
}