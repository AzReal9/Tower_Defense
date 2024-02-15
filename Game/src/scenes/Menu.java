
package scenes;

import game.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import javax.imageio.ImageIO;

public class Menu extends GameScene implements SceneMethods{
    
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    private BufferedImage Img;
    
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
                      for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                g.drawImage(sprites.get(getRndInt()), x *32, y * 32, null);
            } 
        }
    }
    
    public void importImg(){
       
       InputStream is = getClass().getResourceAsStream("/Sprites/spriteatlas.png"); //Finds the Path for the images
       try {       
           img = ImageIO.read(is);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
    
    private void loadSprites() {
    
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }
    
    private int getRndInt() {
        return random.nextInt(100);
    }
    
}
