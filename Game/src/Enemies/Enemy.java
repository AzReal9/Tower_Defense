
package Enemies;

import java.awt.Rectangle;


public class Enemy {
    
    private float x, y;
    private Rectangle bounds;
    private int health;
    private int ID;
    private int EnemyType;
    
    
    public Enemy(float x, float y, int ID, int enemyType){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.EnemyType = enemyType;
        
        bounds = new Rectangle((int) x, (int) y, 32, 32);
        
        
        
    }
    
    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getEnemyType(){
        return EnemyType;
    }
}
