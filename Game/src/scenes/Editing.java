
package scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Game;
import helpz.LoadSave;
import objects.PathPoint;
import objects.Tile;
import ui.ToolBar;

import static helpz.Constants.Tiles.ROAD_TILE;

// Editing class represents the editing scene where players can create or edit levels
public class Editing extends GameScene implements SceneMethods {

    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private ToolBar toolbar;
    private PathPoint start, end;

    // Constructor initializes the editing scene
    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        toolbar = new ToolBar(0, 640, 640, 160, this);
    }

    // Load default level data
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");

        if (points != null && !points.isEmpty()) {
            if (points.size() >= 2) {
                start = points.get(0);
                end = points.get(1);
            } else {
                System.out.println("Error: Not enough data for path points in 'new_level.txt'.");
                // Handle this situation accordingly, for example, by setting default values for start and end.
                start = new PathPoint(0, 0);
                end = new PathPoint(0, 0);
            }
        } else {
            System.out.println("Error: Failed to load path points for 'new_level.txt'.");
            // Handle this situation accordingly, for example, by setting default values for start and end.
            start = new PathPoint(0, 0);
            end = new PathPoint(0, 0);
        }
    }

    // Update method for the editing scene
    public void update() {
        updateTick();
    }

    // Render method for the editing scene
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolbar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    // Draw path points on the editing scene
    private void drawPathPoints(Graphics g) {
        if (start != null)
            g.drawImage(toolbar.getStartPathImg(), start.getxCord() * 32, start.getyCord() * 32, 32, 32, null);

        if (end != null)
            g.drawImage(toolbar.getEndPathImg(), end.getxCord() * 32, end.getyCord() * 32, 32, 32, null);
    }

    // Draw the level grid on the editing scene
    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    // Draw the selected tile on the editing scene
    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    // Save the level
    public void saveLevel() {
        LoadSave.SaveLevel("new_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);
    }

    // Set the selected tile
    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    // Change the tile on the editing scene
    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (selectedTile.getId() >= 0) {
                if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if (game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
                    if (selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    else
                        end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    // Handle mouse click events
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            toolbar.mouseClicked(x, y);
        } else {
            changeTile(mouseX, mouseY);
        }
    }

    // Handle mouse movement events
    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            toolbar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    // Handle mouse press events
    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640)
            toolbar.mousePressed(x, y);
    }

    // Handle mouse release events
    @Override
    public void mouseReleased(int x, int y) {
        toolbar.mouseReleased(x, y);
    }

    // Handle mouse drag events
    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {

        } else {
            changeTile(x, y);
        }
    }

    // Handle key press events
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_R)
            toolbar.rotateSprite();
    }
}