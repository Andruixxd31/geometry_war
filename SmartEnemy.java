
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.lang.Math;


public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getID() == ID.Player) player = handler.object.get(i);
        }
    }

    @Override
    public void tick(){
        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

        velX = (int) ((-1.0/distance) * diffX*3);
        velY = (int) ((-1.0/distance) * diffY*3);

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds(){
        return new Rectangle(x,y,16,16);
    }
    
}