package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

public class Material {
    public static List<String>     materialName = new ArrayList<String>();
    public static List<Texture>    texture = new ArrayList<Texture>();
    public static List<Vector3f>   Ka = new ArrayList<Vector3f>();
    public static List<Vector3f>   Kd = new ArrayList<Vector3f>();
    public static List<Vector3f>   Ks = new ArrayList<Vector3f>();
    public static List<Vector3f>   Ke = new ArrayList<Vector3f>();
    public static List<Float>    Ns = new ArrayList<Float>();
    public static List<Float>    Ni = new ArrayList<Float>();
    public static List<Float>    d = new ArrayList<Float>();
    
    
    public void addMaterialName(String name) {
        this.materialName.add(name);
    }
    
    public void addMaterialTexture(Texture texture) {
        this.texture.add(texture);
    }
    
    public void addMaterialKa(Vector3f ka) {
        this.Ka.add(ka);
    }
    
    public void addMaterialKd(Vector3f kd) {
        this.Kd.add(kd);
    }
    
    public void addMaterialKs(Vector3f ks) {
        this.Ks.add(ks);
    }
    
    public void addMaterialKe(Vector3f ke) {
        this.Ke.add(ke);
    }
    
    public void addMaterialNs(float ns) {
        this.Ns.add(ns);
    }
    
    public void addMaterialNi(float ni) {
        this.Ni.add(ni);
    }
    
    public void addMaterialD(float d) {
        this.d.add(d);
    }
}
