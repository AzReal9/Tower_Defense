
package game;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Game extends JFrame implements Runnable {
    
   private GameScreen gameScreen;
   private BufferedImage grass;
   private Thread gameThread;
   
   private final double UPS_SET = 60.0;
   private final double FPS_SET = 120.0;

   public Game(){

       importGrass();
       
       setSize(640, 640);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       gameScreen = new GameScreen(grass);
       add(gameScreen);
       setVisible(true);

   }
   
   public void importGrass(){
       
       InputStream is = getClass().getResourceAsStream("/Sprites/grass.png"); //Finds the Path for the images
       try {       
           grass = ImageIO.read(is);
       } catch (IOException ex) {
           Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
       }

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
         
        
        while(true) {
            //render
            if(System.nanoTime() - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
                }else {
           //do nothing
                }
            //update
            if(System.nanoTime() - lastUpdate >= timePerUpdate) {
               updateGame();
               lastUpdate = System.nanoTime();
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
}
