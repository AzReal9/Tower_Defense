
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game.GameStates;
import helpz.LoadSave;
import objects.Tile;
import scenes.Editing;

// ToolBar class represents the toolbar in the editing scene
public class ToolBar extends Bar {

    private Editing editing;
    private MyButton bMenu, bSave;
    private MyButton bPathStart, bPathEnd;
    private BufferedImage pathStart, pathEnd;
    private Tile selectedTile;

    private Map<MyButton, ArrayList<Tile>> map = new HashMap<>();

    private MyButton bGrass, bWater, bRoadS, bRoadC, bWaterC, bWaterB, bWaterI;
    private MyButton currentButton;
    private int currentIndex = 0;

    // Constructor initializes the toolbar
    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initPathImgs();
        initButtons();
    }

    // Initialize path images
    private void initPathImgs() {
        pathStart = LoadSave.getSpriteAtlas().getSubimage(7 * 32, 2 * 32, 32, 32);
        pathEnd = LoadSave.getSpriteAtlas().getSubimage(8 * 32, 2 * 32, 32, 32);
    }

    // Initialize toolbar buttons
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bSave = new MyButton("Save", 2, 674, 100, 30);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);
        int i = 0;

        bGrass = new MyButton("Grass", xStart, yStart, w, h, i++);
        bWater = new MyButton("Water", xStart + xOffset, yStart, w, h, i++);

        initMapButton(bRoadS, editing.getGame().getTileManager().getRoadsS(), xStart, yStart, xOffset, w, h, i++);
        initMapButton(bRoadC, editing.getGame().getTileManager().getRoadsC(), xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterC, editing.getGame().getTileManager().getCorners(), xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterB, editing.getGame().getTileManager().getBeaches(), xStart, yStart, xOffset, w, h, i++);
        initMapButton(bWaterI, editing.getGame().getTileManager().getIslands(), xStart, yStart, xOffset, w, h, i++);

        bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, i++);
        bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, i++);
    }

    // Initialize map buttons with associated tiles
    private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
        b = new MyButton("", x + xOff * id, y, w, h, id);
        map.put(b, list);
    }

    // Save the level
    private void saveLevel() {
        editing.saveLevel();
    }

    // Rotate sprite
    public void rotateSprite() {
        currentIndex++;
        if (currentIndex >= map.get(currentButton).size())
            currentIndex = 0;
        selectedTile = map.get(currentButton).get(currentIndex);
        editing.setSelectedTile(selectedTile);
    }

    // Draw the toolbar
    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        // Buttons
        drawButtons(g);
    }

    // Draw toolbar buttons
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bSave.draw(g);

        drawPathButton(g, bPathStart, pathStart);
        drawPathButton(g, bPathEnd, pathEnd);

        drawNormalButton(g, bGrass);
        drawNormalButton(g, bWater);
        drawSelectedTile(g);
        drawMapButtons(g);
    }

    // Draw path buttons
    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g, b);
    }

    // Draw normal buttons
    private void drawNormalButton(Graphics g, MyButton b) {
        g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g, b);
    }

    // Draw map buttons
    private void drawMapButtons(Graphics g) {
        for (Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();
            g.drawImage(img, b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }

    // Draw selected tile
    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null) {
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(550, 650, 50, 50);
        }
    }

    // Get button image by ID
    public BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    // Handle mouse click events
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            GameStates.SetGameState(GameStates.MENU);
        else if (bSave.getBounds().contains(x, y))
            saveLevel();
        else if (bWater.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bWater.getId());
            editing.setSelectedTile(selectedTile);
            return;
        } else if (bGrass.getBounds().contains(x, y)) {
            selectedTile = editing.getGame().getTileManager().getTile(bGrass.getId());
            editing.setSelectedTile(selectedTile);
            return;
        } else if (bPathStart.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathStart, -1, -1);
            editing.setSelectedTile(selectedTile);
        } else if (bPathEnd.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathEnd, -2, -2);
            editing.setSelectedTile(selectedTile);
        } else {
            for (MyButton b : map.keySet())
                if (b.getBounds().contains(x, y)) {
                    selectedTile = map.get(b).get(0);
                    editing.setSelectedTile(selectedTile);
                    currentButton = b;
                    currentIndex = 0;
                    return;
                }
        }
    }

    // Handle mouse movement events
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bWater.setMouseOver(false);
        bGrass.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);

        for (MyButton b : map.keySet())
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMouseOver(true);
        else if (bWater.getBounds().contains(x, y))
            bWater.setMouseOver(true);
        else if (bGrass.getBounds().contains(x, y))
            bGrass.setMouseOver(true);
        else if (bPathStart.getBounds().contains(x, y))
            bPathStart.setMouseOver(true);
        else if (bPathEnd.getBounds().contains(x, y))
            bPathEnd.setMouseOver(true);
        else {
            for (MyButton b : map.keySet())
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
        }
    }

    // Handle mouse press events
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bSave.getBounds().contains(x, y))
            bSave.setMousePressed(true);
        else if (bWater.getBounds().contains(x, y))
            bWater.setMousePressed(true);
        else if (bGrass.getBounds().contains(x, y))
            bGrass.setMousePressed(true);
        else if (bPathStart.getBounds().contains(x, y))
            bPathStart.setMousePressed(true);
        else if (bPathEnd.getBounds().contains(x, y))
            bPathEnd.setMousePressed(true);
        else {
            for (MyButton b : map.keySet())
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
        }
    }

    // Handle mouse release events
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        bGrass.resetBooleans();
        bWater.resetBooleans();
        bPathStart.resetBooleans();
        bPathEnd.resetBooleans();
        for (MyButton b : map.keySet())
            b.resetBooleans();
    }

    // Get the start path image
    public BufferedImage getStartPathImg() {
        return pathStart;
    }

    // Get the end path image
    public BufferedImage getEndPathImg() {
        return pathEnd;
    }
}