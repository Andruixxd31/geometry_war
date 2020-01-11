import java.util.Random;

public class Spawn {

    private Handler handler; 
    private HUD hud;

    private Random rand = new Random();

    private int scoreKeep = 1;
    private int timer = 1000; //timer encargado de lanzar healthTokens
    private int timer2; //timer encargado de lanzar oleadas de enemigos

    public Spawn(Handler handler, HUD hud){
        this.handler =handler;
        this.hud = hud;
    }

    public void tick(){
        timer--;

        if (handler.object.size() == 1) {
            hud.setLevel(hud.getLevel()+1);
            //TODO remplazar el dummy con algo menos laborioso y mas logico
            handler.addObject(new Dummy(1000,1000,ID.Dummy, handler));
            scoreKeep = 1;
        }

        if (hud.getLevel() == 2 && scoreKeep == 1 ||
            hud.getLevel() == 3 && scoreKeep == 1 || 
            hud.getLevel() == 5 && scoreKeep == 1 ||
            hud.getLevel() == 7 && scoreKeep == 1) {
            timer2 = 1000;
            scoreKeep = 0;
        } 


        // -------------------- Control de los niveles --------------------
        if (hud.getLevel() == 1 && scoreKeep == 1) {
            handler.addObject(new WeakBoss(Game.WIDTH - 100, Game.HEIGHT / 2, ID.WeakBoss, handler));
            scoreKeep = 0;
        }
        if (hud.getLevel() == 2 && timer2 % 100 == 0) {
            handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            scoreKeep = 0;
        }
        if (hud.getLevel() == 3 ) {
            if (timer2 % 200 == 0) {
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }
            if (timer2 % 300 == 0) {
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            }
            scoreKeep = 0;
        }
        if (hud.getLevel() == 4 && scoreKeep == 1) {
            handler.addObject(new BossEnemy(Game.WIDTH + 100, Game.HEIGHT / 2, ID.BossEnemy, handler));
            scoreKeep = 0;
        }
        if (hud.getLevel() == 5){
            if (timer2 % 200 == 0) {
                System.out.println(timer2);
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }
            if (timer2 % 300 == 0) {
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            }
            if (timer2 % 400 == 0) {
                handler.addObject(new FatEnemy(rand.nextInt(Game.WIDTH - 90), rand.nextInt(Game.HEIGHT - 90), ID.FatEnemy, handler));
            }
        }
        if (hud.getLevel() == 6 & scoreKeep == 1){
            handler.addObject(new FastBoss(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.FastBoss, handler));
            handler.addObject(new SmartEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            handler.addObject(new SmartEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            scoreKeep = 0;
        }
        if (hud.getLevel() == 7) {
            if (timer2 % 250 == 0) {
                System.out.println(timer2);
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }
            if (timer2 % 300 == 0) {
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
            }
            if (timer2 % 300 == 0) {
                handler.addObject(new FatEnemy(rand.nextInt(Game.WIDTH - 90), rand.nextInt(Game.HEIGHT - 90), ID.FatEnemy, handler));
            }
            if (timer2 % 400 == 0) {
                handler.addObject(new SmartEnemy(rand.nextInt(Game.WIDTH - 50), rand.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
            }
        }

        //timers
        if (timer <= 0) {
            handler.addObject(new  HealthToken(rand.nextInt(Game.WIDTH - 50), 25, ID.HealthToken, handler));
            timer = 100; 
            int randNum = rand.nextInt((7-3)+1)+ 3;
            timer *= randNum;
        }
        else timer--;

        //Condicional que hace se eliminen los enemigos y se de paso al siguinete nivel
        
        
        if (hud.getLevel() == 2 ||
            hud.getLevel() == 3 || 
            hud.getLevel() == 5 ||
            hud.getLevel() == 7) {
            timer2--;
            if (timer2 <= 0) {
                this.handler.clearEnemies();
            } else timer2--;
        } 
    }
}