
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class WeakBoss extends GameObject {

    Handler handler;
    private int health = 1;

    public WeakBoss(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler; 
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,96,96);
    }

    @Override
    public void tick() {
        collission();
        if (health <= 0) {
            handler.clearEnemies();
        }
    }

    private void collission(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Shot) { 
                if(getBounds().intersects(tempObject.getBounds())) {
                    health-= 1;  
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,96,96);
    }
    
}