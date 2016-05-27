package ua.edu.cdu.pm3.ChobotarEV.managment.fileLoaders;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SoundLoader {

    public void loadSound(String audio) {
        try{
            AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(
                    this.getClass().getResource(audio));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            }
            catch(Exception ex){}
    }
}
