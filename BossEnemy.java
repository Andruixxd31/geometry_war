import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class BossEnemy extends GameObject {
	
	private Handler handler;
	private int timer = 120;
	private int timer2= 60;
	private int numBullets = 0;
	Random r = new Random();

	
	
	//Valores Iniciales
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);			
		this.handler = handler;		
		velX = -2;
		velY = 0;
		this.health = 10;
	}	
	
	public Rectangle getBounds() {
		//Regresa un cuadrado para saber en que posicion esta a la hora de colisionar
		return new Rectangle((int)x, (int) y, 128, 128);	
	}
	
	//Elementos a actualizar
	public void tick() {
		x += velX;
		y += velY;
		
		//Ir bajando en la pantalla y detenerse
		if(timer <= 0)velX = 0;
		else timer--;
		//Si ya se detuvo empieza el otro contador
		if(timer <= 0)timer2--;
		//Si ya se detuvo unos momentos empezara a moverse a los lados y  rebotar
		if(timer2 <= 0) {
			if(velY == 0)velY = 5;
			//Aumentar su velocidad progresivamente 
			//Spawnear random si es 0
			int spawn = r.nextInt(5);
			if(spawn == 0) {
				//Se spawnea una nueva bala
				handler.addObject(new BossEnemyBullets((int)x + 64 , (int)y + 64, ID.BasicEnemy, handler));
				//Se suma uno al contador de balas
				numBullets++;
			}
			//Si ya disparo X balas, desaparece
			collission();
			if(this.health <= 0) {
				System.out.println("please");
				this.handler.clearEnemies();
				this.handler.setLevel(this.handler.getLevel()+1);
			}
		}
		
		//Hacer que los enemigo reboten
		//if(y <= 0 || y >= Game.HEIGHT - 128) velY *= -1;		
		if(y <= 0 || y >= Game.HEIGHT - 128) velY *= -1;
		
		//Crear la sombra
		//handler.addObject(new Trail((int)x,(int) y, ID.Trail, new Color(235, 112, 116), 128, 128, 0.08f, handler ));

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

	//Crear elemento grafico
	public void render(Graphics g) {
		g.setColor(new Color(156, 209, 255));		
		g.fillRect((int)x,(int) y, 128, 128);
	} 
	
			
}
