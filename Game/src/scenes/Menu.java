
package scenes;

import game.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Menu extends GameScene implements SceneMethods{
    
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    private BufferedImage grass;
    
    public Menu(Game game) {
        super(game);
        random = new Random();
        importGrass();
        //loadSprites();
    }

    @Override
    public void render(Graphics g) {
                      for(int y = 0; y < 29; y++){
            for(int x = 0; x < 29; x++){
                g.drawImage(grass, x *32, y * 32, null);
            } 
        }
    }
    
        /*private void loadSprites() {
        
        for(int y = 0; y < 10; y++){
            for (int x = 0; x < 10; x++){
                
            }
        }
       
    }*/
    
    private int getRndInt() {
        return random.nextInt(100);
    }
    
    public void importGrass(){
       
       InputStream is = getClass().getResourceAsStream("/Sprites/grass.png"); //Finds the Path for the images
       try {       
           grass = ImageIO.read(is);
       } catch (IOException ex) {
           Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
       }

   }
    
}
