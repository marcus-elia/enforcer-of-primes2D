/*
This code is from https://www.youtube.com/watch?v=1gir2R7G9ws
It is a tutorial by RealTutsGML
 */

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 768, HEIGHT = 512;
    private static final int BARWIDTH = 64;
    private Thread thread;
    private boolean running = false;

    private GameManager gameManager;

    public Game()
    {
        gameManager = new GameManager(WIDTH - BARWIDTH, HEIGHT);
        this.addKeyListener(new KeyInput(gameManager));
        //this.addMouseMotionListener(new MouseMotionInput(gameManager));
        //this.addMouseListener(new MouseInput(gameManager));
        new Window(WIDTH, HEIGHT, "Enforcer of Primes", this);

    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }

        }
        stop();
    }

    private void tick()
    {
        gameManager.tick();
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, WIDTH-BARWIDTH, HEIGHT);

        gameManager.render(g2d);

        g2d.setColor(Color.CYAN);
        g2d.fillRect(WIDTH-BARWIDTH, 0, WIDTH, HEIGHT);

        g2d.dispose();
        bs.show();
    }

    public int getWidth()
    {
        return WIDTH;
    }

    public int getHeight()
    {
        return HEIGHT;
    }

    public static void main(String[] args) throws IOException
    {
        new Game();
    }

}