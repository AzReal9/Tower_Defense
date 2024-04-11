
package objects;

import java.awt.image.BufferedImage;

/**
 * The Tile class represents a tile in the game world.
 */
public class Tile {

	private BufferedImage[] sprite;
	private int id, tileType;

        
        //Constructs a Tile object with a single sprite image
	public Tile(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}

        //Constructs a Tile object with an array of sprite images.
	public Tile(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public int getTileType() {
		return tileType;
	}

        //Gets the sprite image of the tile for a specific animation index.
	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex];
	}

	public BufferedImage getSprite() {
		return sprite[0];
	}

	public boolean isAnimation() {
		return sprite.length > 1;
	}

	public int getId() {
		return id;
	}

}



