
package game;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;


public class Game extends JFrame implements Runnable {
    
   private GameScreen gameScreen;
   private Thread gameThread;
   private final double UPS_SET = 60.0;
   private final double FPS_SET = 120.0;
   private MyMouseListener myMouseListener;
   private KeyboardListener keyboardListener;
   
   //Classes
   private Render render;
   private Menu menu;
   private Playing playing;
   private Settings settings;
        

   public Game(){
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null); 
       initClasses();
       add(gameScreen);
       pack();
       setVisible(true);
   }
   
   private void initClasses() {
       render = new Render(this);
       gameScreen = new GameScreen(this);
       menu = new Menu(this);
       playing = new Playing(this);
       settings = new Settings(this);
   }
   
   private void initInputs() {
       myMouseListener = new MyMouseListener();
       keyboardListener = new KeyboardListener();
       
       addMouseListener(myMouseListener);
       addMouseMotionListener(myMouseListener);
       addKeyListener(keyboardListener);
       
       requestFocus();
   }
   
   private void start() {
       gameThread = new Thread(this) {};
       
       gameThread.start();
   }
 
   
   private void updateGame() {
 
   }

   //Creates Window and calls "gameScreen" Class
    public static void main(String[] args) {
        Game game = new Game();
        game.initInputs();
        game.start();
        //Calls the Constructor that opens window
    }

    @Override
    public void run() {

         //used to keep frame rate constant
         double timePerFrame = 1000000000.0 / FPS_SET;
         double timePerUpdate = 1000000000.0 / UPS_SET;
         long lastFrame = System.nanoTime();
         long lastTimeCheck = System.currentTimeMillis();
         long lastUpdate = System.nanoTime();
         int frames = 0;
         int updates = 0;
         long now;
         
        
        while(true) {
            
            now = System.nanoTime();
            //render
            if(now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
                }else {
           //do nothing
                }
            //update
            if(now - lastUpdate >= timePerUpdate) {
               updateGame();
               lastUpdate = now;
               updates++;
           }
            //display FPS and UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
            System.out.println("FPS: " + frames + " | UPS: " + updates);
            frames = 0;
            updates = 0;
            lastTimeCheck = System.currentTimeMillis();
        }
        }
    }
    
    //Getters and setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }


}