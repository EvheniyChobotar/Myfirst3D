package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

public class Light {
    public  Light() {
       
    }
    public void init() {
         glClearColor(1f, 1f, 1f, 1f);
        glEnable(GL_LIGHTING);
        
        
        float[] ambient     = new float[4]; ambient[0] = 1f;    ambient[1] = 1f;     ambient[2] = 1f;    ambient[3] = 1f;
        float[] dirrection  = new float[4]; dirrection[0] =1f;  dirrection[1] = 1f; dirrection[2] = 1f; dirrection[3] = 1f;
        float[] position    = new float[4]; position[0] = 1f;   position[1] = 1f;  position[2] =1f;    position[3] = 1f;
        FloatBuffer difBuf = BufferUtils.createFloatBuffer(4);
        difBuf.put(ambient);
        difBuf.position(0);
        
        FloatBuffer dirBuf = BufferUtils.createFloatBuffer(4);
        dirBuf.put(dirrection);
        dirBuf.position(0);
        
        FloatBuffer posBuf = BufferUtils.createFloatBuffer(4);
        posBuf.put(position);
        posBuf.position(0);
        
        glLightModel(GL_LIGHT_MODEL_AMBIENT, difBuf);
        glEnable(GL_LIGHT0);
        glLight(GL_LIGHT0, GL_POSITION, posBuf);
        glLight(GL_LIGHT0, GL_SPOT_DIRECTION, dirBuf);
    }

    public void update() {
        float[] material_diffuse = new float[4];
        material_diffuse[0] = 1f;
        material_diffuse[1] = 1f;
        material_diffuse[2] = 1f;
        material_diffuse[3] = 0f;
        FloatBuffer b = BufferUtils.createFloatBuffer(4);
        b.put(material_diffuse);
        b.position(0);
        glMaterial(GL_FRONT, GL_DIFFUSE, b);
        
        float[] light = new float[4];
        light[0] = 1f;
        light[1] = 1f;
        light[2] = 0f;
        light[3] = 0f;
        
        FloatBuffer l = BufferUtils.createFloatBuffer(4);
        l.put(light);
        l.position(0);
        
        
        float[] lightPOs = new float[4];
        lightPOs[0] = 5f;
        lightPOs[1] = 50f;
        lightPOs[2] = 5f;
        lightPOs[2] = 0f;
        
        FloatBuffer p = BufferUtils.createFloatBuffer(4);
        p.put(lightPOs);
        p.position(0);
         
        
        glEnable(GL_LIGHT1);
        
        glLight(GL_LIGHT1, GL_DIFFUSE, l);
        glLight(GL_LIGHT1, GL_POSITION, p);
        
        glLightf(GL_LIGHT1, GL_CONSTANT_ATTENUATION, 0.0f);
        glLightf(GL_LIGHT1, GL_LINEAR_ATTENUATION, 0.2f);
        glLightf(GL_LIGHT1, GL_QUADRATIC_ATTENUATION, 0.4f);
    }
    
    
}
