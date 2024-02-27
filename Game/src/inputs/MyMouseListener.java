
package inputs;

import game.Game;
import game.GameStates;
import static game.GameStates.EDIT;
import static game.GameStates.MENU;
import static game.GameStates.PLAYING;
import static game.GameStates.SETTINGS;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

	private Game game;

	public MyMouseListener(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU -> game.getMenu().mouseDragged(e.getX(), e.getY());
		case PLAYING -> game.getPlaying().mouseDragged(e.getX(), e.getY());
		case SETTINGS -> game.getSettings().mouseDragged(e.getX(), e.getY());
                case EDIT -> game.getEditer().mouseDragged(e.getX(), e.getY());
		default -> {
                }
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU -> game.getMenu().mouseMoved(e.getX(), e.getY());
		case PLAYING -> game.getPlaying().mouseMoved(e.getX(), e.getY());
		case SETTINGS -> game.getSettings().mouseMoved(e.getX(), e.getY());
                        case EDIT -> game.getEditer().mouseMoved(e.getX(), e.getY());
		default -> {
                }

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {

			switch (GameStates.gameState) {
			case MENU -> game.getMenu().mouseClicked(e.getX(), e.getY());
			case PLAYING -> game.getPlaying().mouseClicked(e.getX(), e.getY());
			case SETTINGS -> game.getSettings().mouseClicked(e.getX(), e.getY());
                                case EDIT -> game.getEditer().mouseClicked(e.getX(), e.getY());
			default -> {
                        }

			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU -> game.getMenu().mousePressed(e.getX(), e.getY());
		case PLAYING -> game.getPlaying().mousePressed(e.getX(), e.getY());
		case SETTINGS -> game.getSettings().mousePressed(e.getX(), e.getY());
                        case EDIT -> game.getEditer().mousePressed(e.getX(), e.getY());
		default -> {
                }

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (GameStates.gameState) {
		case MENU -> game.getMenu().mouseReleased(e.getX(), e.getY());
		case PLAYING -> game.getPlaying().mouseReleased(e.getX(), e.getY());
		case SETTINGS -> game.getSettings().mouseReleased(e.getX(), e.getY());
                        case EDIT -> game.getEditer().mouseReleased(e.getX(), e.getY());
		default -> {
                }

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
