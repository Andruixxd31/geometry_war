
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Player extends GameObject {

    Handler handler;
    private int wait = 0;

    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler; 
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 55);

        collission();

        if (wait > 0) {
            wait--;
        }
        
        if (shoot == 1 && wait == 0) {
            handler.addObject(new Shot((int)x + 8, (int)y + 8, ID.Shot, handler));
            wait += 7;
        }
    }

    private void collission(){
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.BasicEnemy 
                || tempObject.getID() == ID.FastEnemy 
                || tempObject.getID() == ID.SmartEnemy) { 
                    if(getBounds().intersects(tempObject.getBounds())){
                        HUD.HEALTH -= 8;
                        handler.removeObject(tempObject);
                    }
            }
            if (tempObject.getID() == ID.FastBoss) { 
                    if(getBounds().intersects(tempObject.getBounds())){
                        HUD.HEALTH -= 6;
                    }
            }
            if (tempObject.getID() == ID.FatEnemy) {
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 4;
                    handler.removeObject(tempObject);
                }
            }
            if (tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.WeakBoss) {
                if(getBounds().intersects(tempObject.getBounds())) 
                    HUD.HEALTH -= 100;
            }
            if (tempObject.getID() == ID.HealthToken) {
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH += 2;
                    handler.removeObject(tempObject);
                }
            }
            // if (tempObject.getID() == ID.BossEnemyBullets) {
            //     if(getBounds().intersects(tempObject.getBounds())){
            //         HUD.HEALTH -= 2;
            //         handler.removeObject(tempObject);
            //     }
            // }
        }
    }

    @Override
    public void render(Graphics g){
        if(id == ID.Player) g.setColor(Color.GREEN);
        // else if (id == ID.Player2) g.setColor(Color.ORANGE);
        g.fillRect(x,y,32,32);
    }
    
}