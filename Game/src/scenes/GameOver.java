
package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;
import ui.MyButton;

import static game.GameStates.*;

// GameOver class represents the game over scene
public class GameOver extends GameScene implements SceneMethods {

    private MyButton bReplay, bMenu;

    // Constructor initializes the game over scene
    public GameOver(Game game) {
        super(game);
        initButtons();
    }

    // Initialize buttons for the game over scene
    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 300;
        int yOffset = 100;

        bMenu = new MyButton("Menu", x, y, w, h);
        bReplay = new MyButton("Replay", x, y + yOffset, w, h);
    }

    // Render method for the game over scene
    @Override
    public void render(Graphics g) {
        // Game over text
        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.setColor(Color.RED);
        g.drawString("Game Over!", 160, 80);

        // Buttons
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);
    }

    // Replay the game
    private void replayGame() {
        // Reset everything
        resetAll();

        // Change state to playing
        SetGameState(PLAYING);
    }

    // Reset all game components
    private void resetAll() {
        game.getPlaying().resetEverything();
    }

    // Handle mouse click events
    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
            resetAll();
        } else if (bReplay.getBounds().contains(x, y)) {
            replayGame();
        }
    }

    // Handle mouse movement events
    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(bMenu.getBounds().contains(x, y));
        bReplay.setMouseOver(bReplay.getBounds().contains(x, y));
    }

    // Handle mouse press events
    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else if (bReplay.getBounds().contains(x, y)) {
            bReplay.setMousePressed(true);
        }
    }

    // Handle mouse release events
    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    // Handle mouse drag events
    @Override
    public void mouseDragged(int x, int y) {
        // Not used in this scene
    }
}