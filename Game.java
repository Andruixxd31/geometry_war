import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private static Music music;

    private Random rand = new Random();

    public Game() {
        handler = new Handler();
        hud = new HUD();

        spawner = new Spawn(handler, hud);

        this.addKeyListener((KeyListener) new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "CrackHead", this);

        // *Players and First Enemies
        handler.addObject(new Player(WIDTH / 2, HEIGHT / 2, ID.Player, handler));
        handler.addObject(new Dummy(2000, 2000, ID.Dummy, handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    // Game loop. It renders everything and makes possible the canges inthe whole
    // canvas
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        final double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            final long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();

        if (hud.HEALTH <= 0) {
            hud.HEALTH = 100;
            handler.object.clear();
        }
    }

    private void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        final Graphics g = bs.getDrawGraphics();
        g.setColor(new Color(49, 64, 87));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);

        hud.render(g);
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;
    }

    public static void main(final String[] args) {
        new Game();
        String filePath = "hitch.wav";
        music = new Music();
        music.playMusic(filePath);
    }
}
