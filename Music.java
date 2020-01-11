import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


//Codigo conseguido en https://www.youtube.com/watch?v=TErboGLHZGA&list=LL39LswCJAVOs9IuZ6T4HtsA&index=2&t=11s
public class Music {
    
    public void playMusic(String musicLocation){

        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void shootingSound(String musicLocation){
        try {
            File musicPath = new File(musicLocation);

            if (musicPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } else {
                System.out.println("Can't find file");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}