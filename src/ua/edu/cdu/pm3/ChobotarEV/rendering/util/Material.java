package ua.edu.cdu.pm3.ChobotarEV.rendering.util;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;

public class Material {
    public String        materialName;
    public Texture       texture;
    public FloatBuffer   ambientColor    =  BufferUtils.createFloatBuffer(4);
    public FloatBuffer   diffuseColor    =  BufferUtils.createFloatBuffer(4);
    public FloatBuffer   specularColor   =  BufferUtils.createFloatBuffer(4);
    public float         specularExponent;
    public float         disolved;
    
    public Material(String name,Texture tex,float[] ka,float[] kd,float[] ks,float spec,float disolve) {
        this.materialName   = name;
        this.texture        = tex;
        this.ambientColor.put(ka);
        this.ambientColor.position(0);
        this.diffuseColor.put(kd);
        this.diffuseColor.position(0);
        this.specularColor.put(ks);
        this.specularColor.position(0);
        this.specularExponent = spec;
        this.disolved       = disolve;
    }
    
}
