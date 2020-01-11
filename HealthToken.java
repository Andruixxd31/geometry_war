
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;


public class HealthToken extends GameObject {

    private Handler handler;
    private Random rand = new Random();

    private int timer = 100;

    public HealthToken(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 0; 
        velY = 3;
    }

    @Override
    public void tick(){
        

        x += velX;
        y += velY;

        if(y >= Game.HEIGHT) handler.removeObject(this);

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 10, 10);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,10,10);
    }
    
}