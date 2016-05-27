package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import static org.lwjgl.opengl.GL11.*;
import ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders.ShaderLoader;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Buffers;

public class Light {
    private Shader shader;
    public  Light() {
       shader = new Shader();
       shader.addVertexShader(ShaderLoader.loadShader("vShader.vs"));
       shader.addFragmentShader(ShaderLoader.loadShader("fShader.fs"));
       shader.compileShader();
    }
    public void render() {
        
//        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT1);
        glLight(GL_LIGHT1, GL_DIFFUSE, Buffers.toFloatBuffer(1f, 1f, 1f, 1f));
        glLight(GL_LIGHT1, GL_AMBIENT, Buffers.toFloatBuffer(0.1f, 0.1f, 0.1f, 1f));
        glLight(GL_LIGHT1, GL_SPECULAR, Buffers.toFloatBuffer(1f, 1f, 1f, 1f));
        glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_AMBIENT_AND_DIFFUSE);
        glEnable(GL_COLOR_MATERIAL);
        glLight(GL_LIGHT1, GL_POSITION, Buffers.toFloatBuffer(15f, 30f, 15f, 1f));

//        shader.bind();
    }
    
}
