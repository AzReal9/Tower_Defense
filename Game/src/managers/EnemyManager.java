package managers;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Enemies.Bat;
import Enemies.Enemy;
import Enemies.Knight;
import Enemies.Orc;
import Enemies.Wolf;
import helpz.LoadSave;
import objects.PathPoint;
import scenes.Playing;
import static helpz.Constants.Direction.*;
import static helpz.Constants.Tiles.*;
import static helpz.Constants.Enemies.*;

/**
 * The EnemyManager class manages enemies in the game.
 */
public class EnemyManager {

    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private PathPoint start, end;
    private int HPbarWidth = 20;
    private BufferedImage slowEffect;

    /**
     * Constructs an EnemyManager object.
     * @param playing The Playing scene in which enemies will be managed.
     * @param start The starting path point for enemies.
     * @param end The ending path point for enemies.
     */
    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        enemyImgs = new BufferedImage[4];
        this.start = start;
        this.end = end;

        loadEffectImg();
        loadEnemyImgs();
    }

    // Load the slow effect image from the sprite atlas
    private void loadEffectImg() {
        slowEffect = LoadSave.getSpriteAtlas().getSubimage(32 * 9, 32 * 2, 32, 32);
    }

    // Load enemy images from the sprite atlas
    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        for (int i = 0; i < 4; i++) {
            enemyImgs[i] = atlas.getSubimage(i * 32, 32, 32, 32);
        }
    }

    // Update method to update enemy movement and state
    public void update() {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                updateEnemyMove(e);
            }
        }
    }

    // Update movement of a single enemy
    public void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            setNewDirectionAndMove(e);
        }

        int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

        if (getTileType(newX, newY) == ROAD_TILE) {
            e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
        } else if (isAtEnd(e)) {
            e.kill();
            playing.removeOneLife();
        } else {
            setNewDirectionAndMove(e);
        }
    }

    // Set new direction and move the enemy accordingly
    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        int xCord = (int) (e.getX() / 32);
        int yCord = (int) (e.getY() / 32);

        fixEnemyOffsetTile(e, dir, xCord, yCord);

        if (isAtEnd(e)) {
            return;
        }

        if (dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE) {
                e.move(GetSpeed(e.getEnemyType()), UP);
            } else {
                e.move(GetSpeed(e.getEnemyType()), DOWN);
            }
        } else {
            int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE) {
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            } else {
                e.move(GetSpeed(e.getEnemyType()), LEFT);
            }
        }
    }

    // Adjust enemy position if it's on a partial tile
    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            case RIGHT:
                if (xCord < 19) {
                    xCord++;
                }
                break;
            case DOWN:
                if (yCord < 19) {
                    yCord++;
                }
                break;
        }

        e.setPos(xCord * 32, yCord * 32);
    }

    // Check if an enemy has reached the end point
    private boolean isAtEnd(Enemy e) {
        return e.getX() == end.getxCord() * 32 && e.getY() == end.getyCord() * 32;
    }

    // Get the tile type at a given position
    private int getTileType(int x, int y) {
        return playing.getTileType(x, y);
    }

    // Get the speed and height based on the direction and enemy type
    private float getSpeedAndHeight(int dir, int enemyType) {
        if (dir == UP) {
            return -GetSpeed(enemyType);
        } else if (dir == DOWN) {
            return GetSpeed(enemyType) + 32;
        }
        return 0;
    }

    // Get the speed and width based on the direction and enemy type
    private float getSpeedAndWidth(int dir, int enemyType) {
        if (dir == LEFT) {
            return -GetSpeed(enemyType);
        } else if (dir == RIGHT) {
            return GetSpeed(enemyType) + 32;
        }
        return 0;
    }

    // Spawn a new enemy
    public void spawnEnemy(int nextEnemy) {
        addEnemy(nextEnemy);
    }

    // Add a new enemy to the list
    public void addEnemy(int enemyType) {
        int x = start.getxCord() * 32;
        int y = start.getyCord() * 32;

        switch (enemyType) {
            case ORC:
                enemies.add(new Orc(x, y, 0, this));
                break;
            case BAT:
                enemies.add(new Bat(x, y, 0, this));
                break;
            case KNIGHT:
                enemies.add(new Knight(x, y, 0, this));
                break;
            case WOLF:
                enemies.add(new Wolf(x, y, 0, this));
                break;
        }
    }

    // Draw enemies on the screen
    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
                drawEffects(e, g);
            }
        }
    }

    // Draw effects on enemies
    private void drawEffects(Enemy e, Graphics g) {
        if (e.isSlowed()) {
            g.drawImage(slowEffect, (int) e.getX(), (int) e.getY(), null);
        }
    }

    // Draw health bars above enemies
    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) e.getX() + 16 - (getNewBarWidth(e) / 2), (int) e.getY() - 10, getNewBarWidth(e), 3);
    }

    // Calculate the width of the health bar based on enemy health
    private int getNewBarWidth(Enemy e) {
        return (int) (HPbarWidth * e.getHealthBarFloat());
    }

    // Draw individual enemies
    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }

    // Get the list of enemies
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // Get the number of alive enemies
    public int getAmountOfAliveEnemies() {
        int size = 0;
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                size++;
            }
        }
        return size;
    }

    // Reward the player for defeating an enemy
    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }

    // Reset the enemy manager
    public void reset() {
        enemies.clear();
    }
}