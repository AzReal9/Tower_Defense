
package inputs;

import game.GameStates;
import static game.GameStates.MENU;
import static game.GameStates.PLAYING;
import static game.GameStates.SETTINGS;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardListener implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            GameStates.gameState = MENU;
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            GameStates.gameState = PLAYING;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            GameStates.gameState = SETTINGS;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
