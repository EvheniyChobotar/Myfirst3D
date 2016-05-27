package ua.edu.cdu.pm3.ChobotarEV.media;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javazoom.jl.player.Player;

public class MusicPlayer extends Thread{

    public MusicPlayer() {
        this.start();
    }

    @Override
    public void run(){
        try {
            File file = new File("/home/eugenej/Documents/course/MyFirst3D/res/sounds/mainTheme.mp3");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            try {
                Player player = new Player(bis);
                player.play();
            } catch (Exception ex) {}
        } catch (IOException e) {}
    }
    
    public void stopPlayer(){
        this.stop();
    }
    
}
