
import java.util.LinkedList;
import java.awt.*;

public class Handler {
    //We create a list for the game objects since we don't know how many we are going to have
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    private int level = 0;

    
    public void setLevel(int level) {
    	this.level = level;
    }
    
    public int getLevel() {
    	return this.level;
    }

    public void tick(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void clearEnemies(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if (tempObject.getID() == ID.Player) {
                object.clear();
                addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(),ID.Player, this));
            }
        }
    }
    
}