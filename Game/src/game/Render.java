package game;

import java.awt.Graphics;

public class Render {

	private Game game;

	public Render(Game game) {
		this.game = game;
	}

        /**
     * Renders the current state of the game based on the GameState.
     * @param g The Graphics context.
     */
	public void render(Graphics g) {
		switch (GameStates.gameState) {
		case MENU:
			game.getMenu().render(g);
			break;
		case PLAYING:
			game.getPlaying().render(g);
			break;
		case SETTINGS:
			game.getSettings().render(g);
			break;
		case EDIT:
			game.getEditor().render(g);
			break;
		case GAME_OVER:
			game.getGameOver().render(g);
			break;
		default:
			break;

		}

	}

}