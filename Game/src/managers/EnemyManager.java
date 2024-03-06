
package managers;

import Enemies.Enemy;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import scenes.Playing;




public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImg;
    private Enemy testEnemy;
    
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImg = new BufferedImage[4];
        testEnemy = new Enemy(96, 288, 0, 0);
         loadEnemyImgs();  
        
    }
    
    public void update(){
        
        testEnemy.move( 0.5f, 0);
    }
    
    public void draw(Graphics g){
        
        drawEnemy(testEnemy, g);
        
    }

    private void drawEnemy(Enemy e, Graphics g) {
        
        g.drawImage(enemyImg[0], (int) e.getX(),(int) e.getY(), null);
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        enemyImg[0] = atlas.getSubimage(0, 32, 32, 32);
        enemyImg[1] = atlas.getSubimage(32, 32, 32, 32);
        enemyImg[2] = atlas.getSubimage(64 , 32, 32, 32);
        enemyImg[3] = atlas.getSubimage(96 , 32, 32, 32);
    }
}
