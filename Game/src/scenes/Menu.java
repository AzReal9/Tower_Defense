package scenes;

import java.awt.Graphics;

import game.Game;
import ui.MyButton;
import static game.GameStates.*;

// Menu class represents the main menu scene in the game
public class Menu extends GameScene implements SceneMethods {

    private MyButton bPlaying, bEdit, bSettings, bQuit;

    // Constructor initializes the main menu scene with buttons
    public Menu(Game game) {
        super(game);
        initButtons();
    }

    // Initialize buttons for the main menu
    private void initButtons() {
        int buttonWidth = 150;
        int buttonHeight = buttonWidth / 3;
        int buttonX = 640 / 2 - buttonWidth / 2;
        int buttonY = 150;
        int yOffset = 100;

        bPlaying = new MyButton("Play", buttonX, buttonY, buttonWidth, buttonHeight);
        bEdit = new MyButton("Edit", buttonX, buttonY + yOffset, buttonWidth, buttonHeight);
        bSettings = new MyButton("Settings", buttonX, buttonY + yOffset * 2, buttonWidth, buttonHeight);
        bQuit = new MyButton("Quit", buttonX, buttonY + yOffset * 3, buttonWidth, buttonHeight);
    }

    // Render the main menu scene
    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    // Draw buttons on the main menu scene
    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
    }

    // Handle mouse click events on the main menu scene
    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y))
            SetGameState(PLAYING);
        else if (bEdit.getBounds().contains(x, y))
            SetGameState(EDIT);
        else if (bSettings.getBounds().contains(x, y))
            SetGameState(SETTINGS);
        else if (bQuit.getBounds().contains(x, y))
            System.exit(0);
    }

    // Handle mouse movement events on the main menu scene
    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(bPlaying.getBounds().contains(x, y));
        bEdit.setMouseOver(bEdit.getBounds().contains(x, y));
        bSettings.setMouseOver(bSettings.getBounds().contains(x, y));
        bQuit.setMouseOver(bQuit.getBounds().contains(x, y));
    }

    // Handle mouse press events on the main menu scene
    @Override
    public void mousePressed(int x, int y) {
        bPlaying.setMousePressed(bPlaying.getBounds().contains(x, y));
        bEdit.setMousePressed(bEdit.getBounds().contains(x, y));
        bSettings.setMousePressed(bSettings.getBounds().contains(x, y));
        bQuit.setMousePressed(bQuit.getBounds().contains(x, y));
    }

    // Handle mouse release events on the main menu scene
    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    // Reset button states on the main menu scene
    private void resetButtons() {
        bPlaying.resetBooleans();
        bEdit.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
    }
}