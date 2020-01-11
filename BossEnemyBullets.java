
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;


public class BossEnemyBullets extends GameObject {

    private Handler handler;
    private Random rand = new Random();

    public BossEnemyBullets(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = (rand.nextInt(5 - -5) + -5); 
        velY = 5;
    }

    @Override
    public void tick(){
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,16,16);
    }
    
}