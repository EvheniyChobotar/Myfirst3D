package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;

import java.io.BufferedReader;
import java.io.FileReader;

public class ShaderLoader {

    public static String loadShader(String file) {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try {
            shaderReader = new BufferedReader(new FileReader("./res/shaders/"+file));
            String line;
            while ((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            shaderReader.close();
        } catch (Exception e) {
            System.err.println("Shader not loaded");}
        
        return  shaderSource.toString();
    }
}
