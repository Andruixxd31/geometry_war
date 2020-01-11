
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;


public class FatEnemy extends GameObject {

    private Handler handler;

    public FatEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 2; 
        velY = 2;
    }

    @Override
    public void tick(){
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 96) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, 64, 64);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,64,64);
    }
    
}