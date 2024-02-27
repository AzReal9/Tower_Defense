
package scenes;

import java.awt.Graphics;

import helpz.LevelBuild;
import helpz.LoadSave;
import game.Game;
import managers.TileManager;
import objects.Tile;
import ui.ActionBar;
import ui.MyButton;

import static game.GameStates.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	
	private ActionBar bottomBar;
	private int mouseX, mouseY;
	

	public Playing(Game game) {
		super(game);

		loadDefaultLevel();
		
		bottomBar = new ActionBar(0, 640, 640, 100, this);


		
		

	}

	

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLvlData("new_level");

	}

	public void setLevel(int[][] lvl){
            this.lvl = lvl;
        }

	@Override
	public void render(Graphics g) {

		drawLevel(g);

		bottomBar.draw(g);
		

	}
        
          private void drawLevel(Graphics g){
        for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
       

    }
        
        private BufferedImage getSprite(int sprieid){
            return game.getTileManager().getSprite(sprieid);
        }

	

	

	


	

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640) {
			bottomBar.mouseClicked(x, y);
		} 
	}

	@Override
	public void mouseMoved(int x, int y) {
            
            if (y >= 640) {
			bottomBar.mouseMoved(x, y);
			
		} else {
			
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}

	
	}

	@Override
	public void mousePressed(int x, int y) {
		bottomBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y) {

		bottomBar.mouseReleased(x, y);

	}

	@Override
	public void mouseDragged(int x, int y) {
            
		
	}

    
      


}
