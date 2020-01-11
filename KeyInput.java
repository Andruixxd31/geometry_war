
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Music music;
    private boolean[] KeyDown = new boolean[4];
    private GameObject object;

    public KeyInput(Handler handler) {
        this.handler = handler;
        KeyDown[0] = false;
        KeyDown[1] = false;
        KeyDown[2] = false;
        KeyDown[3] = false;
        music = new Music();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); 
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //Iterates through all the objects of the handler to know wich key corresponds to each object.
            if(tempObject.getID() == ID.Player){

                if (key == KeyEvent.VK_W) { tempObject.setVelY(-5); KeyDown[0] = true;}
                if (key == KeyEvent.VK_S) { tempObject.setVelY(5); KeyDown[1] = true; }
                if (key == KeyEvent.VK_D) { tempObject.setVelX(5); KeyDown[2] = true; }
                if (key == KeyEvent.VK_A) { tempObject.setVelX(-5); KeyDown[3] = true; }
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setShooting(1); 
                    String shootingFile = "shooting.mp3";
                    music.shootingSound(shootingFile);
                }
            } 
        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode(); 
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //Iterates through all the objects of the handler to know wich key corresponds to each object.
            if(tempObject.getID() == ID.Player){

                if (key == KeyEvent.VK_W) KeyDown[0] = false;
                if (key == KeyEvent.VK_S) KeyDown[1] = false;
                if (key == KeyEvent.VK_D) KeyDown[2] = false;
                if (key == KeyEvent.VK_A) KeyDown[3] = false;
                if (key == KeyEvent.VK_RIGHT) tempObject.setShooting(0); 

                //Vertical movement
                if(!KeyDown[0] && !KeyDown[1]) tempObject.setVelY(0);
                //Horizontal movement
                if(!KeyDown[2] && !KeyDown[3]) tempObject.setVelX(0);
                
            } 
        }
    }
    
}