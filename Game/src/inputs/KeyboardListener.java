
package inputs;

import game.Game;
import game.GameStates;
import static game.GameStates.EDIT;
import static game.GameStates.MENU;
import static game.GameStates.PLAYING;
import static game.GameStates.SETTINGS;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyboardListener implements KeyListener {
    
    private Game game;
    
    public KeyboardListener(Game game){
        this.game = game;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
            if(GameStates.gameState == EDIT){
                game.getEditer().keyPressed(e);
            }

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}