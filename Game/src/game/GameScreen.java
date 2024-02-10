
package game;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class GameScreen extends JPanel {
    
    private Random random;
    private BufferedImage img;    
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    
    
    
    public GameScreen(BufferedImage img){
        this.img = img;
        
        //loadSprites();
        random = new Random(); 
    }
    
    /*private void loadSprites() {
        
        for(int y = 0; y < 10; y++){
            for (int x = 0; x < 10; x++){
                
            }
        }
       
    }*/
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for(int y = 0; y < 29; y++){
            for(int x = 0; x < 29; x++){
                g.drawImage(img, x *32, y * 32, null);
            }
            
        }
 
       /* for(int y = 0; y < 29; y++){
            for(int x = 0; x < 29; x++){
              g.setColor(getRandColor());
            g.fillRect(x * 32, y * 32, 32, 32);
            }  
        }*/

    }
   
    
    private Color getRandColor(){
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        
        return new Color(r, g, b);
    }

    
}
