
package scenes;

import game.Game;
import static game.GameStates.PLAYING;
import static game.GameStates.SETTINGS;
import static game.GameStates.SetGameState;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import ui.MyButton;

public class Menu extends GameScene implements SceneMethods{
    
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Random random;
    
    private MyButton bPlaying, bSettings, bQuit;
    
    
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImg();
        loadSprites();
        initButtons();
    }
    
    private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);

	}

    @Override
    public void render(Graphics g) {
           bPlaying.draw(g);
           bSettings.draw(g);
	   bQuit.draw(g); 
    }
    
    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
    }
    
    public void importImg(){
       
       InputStream is = getClass().getResourceAsStream("/Sprites/spriteatlas.png"); //Finds the Path for the images
       try {       
           img = ImageIO.read(is);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
    
    private void loadSprites() {
    
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }
    
    private int getRndInt() {
        return random.nextInt(100);
    }

    @Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		} else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

    @Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}

    @Override
    public void mouseDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
}
