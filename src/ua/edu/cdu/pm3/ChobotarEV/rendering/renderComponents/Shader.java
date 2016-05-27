package ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int programm;
    
    public Shader() {
        programm = glCreateProgram();
        
    }
    
    public void addVertexShader(String name) {
        addProgram(name, GL_VERTEX_SHADER);
    }
    
    public void addFragmentShader(String name) {
        addProgram(name, GL_FRAGMENT_SHADER);
    }
    
    private void  addProgram(String name,int type) {
        int shader = glCreateShader(type);
        
        if(shader == 0) {
            System.err.println("Shader add failed");
        }
        glShaderSource(shader, name);
        glCompileShader(shader);
        
        if(glGetShader(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
        }
        glAttachShader(programm, shader);
    }
    
    public  void compileShader() {
        glLinkProgram(programm);
        if(glGetShader(programm, GL_LINK_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(programm, 1024));
        }
        glValidateProgram(programm);
        if(glGetShader(programm, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(programm, 1024));
        }
    }
    
    public void bind() {
        glUseProgram(programm);
    }
}
