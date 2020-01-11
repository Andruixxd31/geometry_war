
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;


public class FastBoss extends GameObject {

    private Handler handler;

    public FastBoss(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 9; 
        velY = 9;
        this.health = 3;
    }

    @Override
    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        collission();
        if(this.health <= 0) {
            this.handler.clearEnemies();
            this.handler.setLevel(this.handler.getLevel()+1);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }

    private void collission(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Shot) { 
                if(getBounds().intersects(tempObject.getBounds())) {
                    health-= 20;  
                    System.out.println(health);
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    
}