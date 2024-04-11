
package scenes;

import java.awt.image.BufferedImage;

import game.Game;

// GameScene class represents a scene in the game
public class GameScene {

    protected Game game;
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;

    // Constructor initializes the game scene
    public GameScene(Game game) {
        this.game = game;
    }

    // Get the game associated with the scene
    public Game getGame() {
        return game;
    }

    // Check if the sprite ID corresponds to an animated sprite
    protected boolean isAnimation(int spriteID) {
        return game.getTileManager().isSpriteAnimation(spriteID);
    }

    // Update the animation tick
    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4)
                animationIndex = 0;
        }
    }

    // Get the sprite image based on the sprite ID
    protected BufferedImage getSprite(int spriteID) {
        return game.getTileManager().getSprite(spriteID);
    }

    // Get the animated sprite image based on the sprite ID and animation index
    protected BufferedImage getSprite(int spriteID, int animationIndex) {
        return game.getTileManager().getAniSprite(spriteID, animationIndex);
    }
}