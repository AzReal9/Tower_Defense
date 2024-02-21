
package game;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;


public class GameScreen extends JPanel {
        
    private Dimension size;
    private Game game;
    
     private MyMouseListener myMouseListener;
   private KeyboardListener keyboardListener;
    

    public GameScreen(Game game){
        this.game = game;
        
        setPanelSize();
    }
    
    public void initInputs() {
       myMouseListener = new MyMouseListener(game);
       keyboardListener = new KeyboardListener();
       
       addMouseListener(myMouseListener);
       addMouseMotionListener(myMouseListener);
       addKeyListener(keyboardListener);
       
       requestFocus();
   }
    
    private void setPanelSize() {
        size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        game.getRender().render(g);
    }

    
}
