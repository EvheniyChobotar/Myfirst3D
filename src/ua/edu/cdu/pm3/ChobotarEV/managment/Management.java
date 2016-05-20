package ua.edu.cdu.pm3.ChobotarEV.managment;

import ua.edu.cdu.pm3.ChobotarEV.components.Camera;
import ua.edu.cdu.pm3.ChobotarEV.components.Window;
import ua.edu.cdu.pm3.ChobotarEV.rendering.Render;
import ua.edu.cdu.pm3.ChobotarEV.components.Input;
import ua.edu.cdu.pm3.ChobotarEV.main.Main;

import org.lwjgl.opengl.Display;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.SkyDome;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Terrain;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Textures;

public class Management {

    Render      render;
    Input       keyboard;
    Window      window;
    Camera      camera;
    Terrain     terrain;
    Model       model;
    Textures    textures;
    SkyDome     sky;
    
    boolean isRunning;
    
    public void initialize() {
//      init window 
        window      = new Window();
            window.createWindow(Main.WIDTH,Main.HEIGHT,Main.TITLE);
            
//      init input system (keyoard and mouse)                        
        keyboard    = new Input();	
            keyboard.createKeyboard();
            
        terrain = new Terrain();
//            terrain.drawTerrain();
        textures = new Textures();
//            textures.initializeTextures();            

        model = new Model();
            model.initializeModels();
            model.drawModel();
            
        
            
        sky = new SkyDome();
//            sky.calculateDomeCoordinates();
//            sky.drawDome();
            
        render      = new Render();
            render.initialize3D();
            
        //      init player behavior 
        camera      = new Camera();
        //Generate the terrain and store in video memory
               
//      var for flexible run customization                        
        isRunning = false;
//      if all good 
        start();
    }
    
    public void start() {
        if(isRunning)
            return;
        run();
    }
    
    private void run() {
        isRunning = true;
        while(!Display.isCloseRequested()){
            update();
        }
        stop();
    }
    
    
    public  void update() {
        window.update();
        keyboard.update();
        camera.render();
        camera.update();

    }
    
    public void stop() {
        window.dispose();
        keyboard.dispose();
    }
    
}
