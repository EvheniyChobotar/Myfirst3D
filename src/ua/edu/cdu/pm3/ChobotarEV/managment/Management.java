package ua.edu.cdu.pm3.ChobotarEV.managment;

import ua.edu.cdu.pm3.ChobotarEV.components.Camera;
import ua.edu.cdu.pm3.ChobotarEV.components.Window;
import ua.edu.cdu.pm3.ChobotarEV.rendering.Render;
import ua.edu.cdu.pm3.ChobotarEV.components.Input;
import ua.edu.cdu.pm3.ChobotarEV.main.Main;

import org.lwjgl.opengl.Display;
import ua.edu.cdu.pm3.ChobotarEV.media.MusicPlayer;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Light;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Model;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.SkyDome;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Terrain;
import ua.edu.cdu.pm3.ChobotarEV.rendering.renderComponents.Water;
import ua.edu.cdu.pm3.ChobotarEV.rendering.util.Textures;

public class Management {

    private Render      render;
    private Input       keyboard;
    private Window      window;
    private Camera      camera;
    private Terrain     terrain;
    private Model       model;
    private Textures    textures;
    private SkyDome     sky;
    private Light       light;
    private Water       water;
    private MusicPlayer audioPlayer;
    
    boolean isRunning;
    
    public void initialize() {
//      init window 
        window      = new Window();
            window.createWindow(Main.WIDTH,Main.HEIGHT,Main.TITLE);
            
//      init input system (keyoard and mouse)                        
        keyboard    = new Input();	
            keyboard.createKeyboard();
        textures = new Textures();
            textures.initializeTextures();
            
        terrain = new Terrain();
            terrain.drawTerrain();  
        water = new Water();
        
        sky = new SkyDome();

        model = new Model();
            model.initializeModels();
            
        light = new Light();
            
        render      = new Render();
            render.initialize3D();
            
        //      init player behavior 
        camera      = new Camera();
        audioPlayer = new MusicPlayer();
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
        light.render();
    }
    
    public void stop() {
        window.dispose();
        keyboard.dispose();
        audioPlayer.stopPlayer();
    }
    
}
