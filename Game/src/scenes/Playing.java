
package scenes;

import game.Game;
import static game.GameStates.MENU;
import static game.GameStates.SetGameState;
import helpz.LevelBuild;
import java.awt.Color;
import java.awt.Graphics;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;
import ui.MyButton;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private TileManager tileManager;
        private Tile selectedTile;
	private int mouseX, mouseY;
        private boolean drawSelect;
        private BottomBar bottomBar;
        private int lastTileX, lastTileY, lastTileId;

	public Playing(Game game) {
		super(game);

		
		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
                bottomBar = new BottomBar(0, 640, 640, 100, this);

	}

	
	@Override
	public void render(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}

		
                bottomBar.draw(g);
                drawSelectedTile(g);

	}
        
        public TileManager getTileManager(){
            return tileManager;
        }

	

	@Override
	public void mouseClicked(int x, int y) {
              if(y >= 640){
                bottomBar.mouseMoved(x, y); 
            }else{
                  changeTile(mouseX, mouseY);
              }
		

	}

	@Override
	public void mouseMoved(int x, int y) {
            
            if(y >= 640){
                bottomBar.mouseMoved(x, y); 
                drawSelect = false;
            }else{
                drawSelect = true;
                mouseX = (x /32) * 32;
                mouseY = (y /32) * 32;
            
            }
		
	}

	@Override
	public void mousePressed(int x, int y) {
		  if(y >= 640){
                bottomBar.mouseMoved(x, y); 
            }
	}

	@Override
	public void mouseReleased(int x, int y) {
		  
         bottomBar.mouseMoved(x, y); 
            

	}

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
            drawSelect = true;
        }
        
    }
    
    public void setSelected(Tile tile){
        this.selectedTile = tile;
    }

    private void changeTile(int x, int y) {
        if(selectedTile != null){
        int tileX = x / 32;
        int tileY = y / 32;
        
        
        if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getID())
            return;
        
        lastTileX = tileX;
        lastTileY = tileY;
        lastTileId = selectedTile.getID();
           
       lvl[tileY][tileX] = selectedTile.getID();
        }
       
    }

    @Override
    public void mouseDragged(int x, int y) {
        if( y >= 640){
            
        }else{
            changeTile(x, y);
        }
    }
    


}
