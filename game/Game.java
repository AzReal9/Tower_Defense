
package game;

import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Game extends JFrame {
    
   private GameScreen gameScreen;
   
   private BufferedImage grass;

   public Game(){
       
       importGrass();
       
       setSize(640, 640);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       gameScreen = new GameScreen(grass);
       add(gameScreen);
       
       
   }
   
   public void importGrass(){
       
       InputStream is = getClass().getResourceAsStream("/Sprites/grass.png"); //Finds the Path for the images
       try {       
           grass = ImageIO.read(is);
       } catch (IOException ex) {
           Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
       }
      
         
       
       
       
   }
   
   
   //Creates Window and calls "gameScreen" Class
    public static void main(String[] args) {
        Game game = new Game();
        //Calls the Constructor that opens window
    }
    
}
