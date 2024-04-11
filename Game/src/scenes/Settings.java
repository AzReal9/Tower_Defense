package scenes;

import java.awt.Color;
import java.awt.Graphics;

import game.Game;
import ui.MyButton;

import static game.GameStates.*;

// Settings class represents the settings scene in the game
public class Settings extends GameScene implements SceneMethods {

    private MyButton menuButton;

    // Constructor initializes the settings scene with necessary components
    public Settings(Game game) {
        super(game);
        initButtons();
    }

    // Initialize buttons for the settings scene
    private void initButtons() {
        menuButton = new MyButton("Menu", 2, 2, 100, 30);
    }

    // Render the settings scene
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 640, 640);
        drawButtons(g);
    }

    // Draw buttons on the settings scene
    private void drawButtons(Graphics g) {
        menuButton.draw(g);
    }

    // Handle mouse click events on the settings scene
    @Override
    public void mouseClicked(int x, int y) {
        if (menuButton.getBounds().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    // Handle mouse movement events on the settings scene
    @Override
    public void mouseMoved(int x, int y) {
        menuButton.setMouseOver(menuButton.getBounds().contains(x, y));
    }

    // Handle mouse press events on the settings scene
    @Override
    public void mousePressed(int x, int y) {
        if (menuButton.getBounds().contains(x, y)) {
            menuButton.setMousePressed(true);
        }
    }

    // Handle mouse release events on the settings scene
    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    // Reset button states
    private void resetButtons() {
        menuButton.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
        
    }
}