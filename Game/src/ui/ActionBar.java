
package ui; 

import static game.GameStates.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import helpz.Constants.Towers;
import objects.Tower;
import scenes.Playing;

public class ActionBar extends Bar {

    private Playing playing;
    private MyButton bMenu, bPause;

    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    private MyButton sellTower, upgradeTower;

    private DecimalFormat formatter;

    private int gold = 100;
    private boolean showTowerCost;
    private int towerCostType;

    private int lives = 1;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        formatter = new DecimalFormat("0.0");

        initButtons();
    }

    // Reset all values in the action bar
    public void resetEverything() {
        lives = 1;
        towerCostType = 0;
        showTowerCost = false;
        gold = 100;
        selectedTower = null;
        displayedTower = null;
    }

    // Initialize action bar buttons
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        bPause = new MyButton("Pause", 2, 682, 100, 30);

        towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);

        for (int i = 0; i < towerButtons.length; i++)
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);

        sellTower = new MyButton("Sell", 420, 702, 80, 25);
        upgradeTower = new MyButton("Upgrade", 545, 702, 80, 25);
    }

    // Decrease player lives by 1
    public void removeOneLife() {
        lives--;
        if (lives <= 0)
            SetGameState(GAME_OVER);
    }

    // Draw action bar graphics
    public void draw(Graphics g) {
        // Draw background
        g.setColor(new Color(220, 123, 15));
        g.fillRect(x, y, width, height);

        // Draw buttons
        drawButtons(g);

        // Draw displayed tower info
        drawDisplayedTower(g);

        // Draw wave info
        drawWaveInfo(g);

        // Draw gold amount
        drawGoldAmount(g);

        // Draw tower cost
        if (showTowerCost)
            drawTowerCost(g);

        // Draw game paused text
        if (playing.isGamePaused()) {
            g.setColor(Color.black);
            g.drawString("Game is Paused!", 110, 790);
        }

        // Draw lives
        g.setColor(Color.black);
        g.drawString("Lives: " + lives, 110, 750);
    }

    // Draw action bar buttons
    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bPause.draw(g);

        for (MyButton b : towerButtons) {
            g.setColor(Color.gray);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }

    // Draw tower cost
    private void drawTowerCost(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(280, 650, 120, 50);
        g.setColor(Color.black);
        g.drawRect(280, 650, 120, 50);

        g.drawString("" + getTowerCostName(), 285, 670);
        g.drawString("Cost: " + getTowerCostCost() + "g", 285, 695);

        // Show if player lacks gold for the selected tower
        if (isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.RED);
            g.drawString("Can't Afford", 270, 725);
        }
    }

    // Check if tower cost exceeds current gold
    private boolean isTowerCostMoreThanCurrentGold() {
        return getTowerCostCost() > gold;
    }

    // Get tower name based on tower type
    private String getTowerCostName() {
        return Towers.GetName(towerCostType);
    }

    // Get tower cost based on tower type
    private int getTowerCostCost() {
        return Towers.GetTowerCost(towerCostType);
    }

    // Draw gold amount
    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: " + gold + "g", 110, 725);
    }

    // Draw wave information
    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    // Draw remaining waves information
    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + " / " + size, 425, 770);
    }

    // Draw remaining enemies information
    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManger().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 425, 790);
    }

    // Draw wave timer information
    private void drawWaveTimerInfo(Graphics g) {
        if (playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedText = formatter.format(timeLeft);
            g.drawString("Time Left: " + formattedText, 425, 750);
        }
    }

    // Draw displayed tower information
    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            // Draw displayed tower details
            g.setColor(Color.gray);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.black);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null);
            g.setFont(new Font("LucidaSans", Font.BOLD, 15));
            g.drawString("" + Towers.GetName(displayedTower.getTowerType()), 480, 660);
            g.drawString("ID: " + displayedTower.getId(), 480, 675);
            g.drawString("Tier: " + displayedTower.getTier(), 560, 660);
            drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);

            // Draw sell button
            sellTower.draw(g);
            drawButtonFeedback(g, sellTower);

            // Draw upgrade button if applicable
            if (displayedTower.getTier() < 3 && gold >= getUpgradeAmount(displayedTower)) {
                upgradeTower.draw(g);
                drawButtonFeedback(g, upgradeTower);
            }

            // Display cost information based on mouse hover
            if (sellTower.isMouseOver()) {
                g.setColor(Color.red);
                g.drawString("Sell for: " + getSellAmount(displayedTower) + "g", 480, 695);
            } else if (upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedTower)) {
                g.setColor(Color.blue);
                g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g", 480, 695);
            }
        }
    }

    // Get upgrade cost for the displayed tower
    private int getUpgradeAmount(Tower displayedTower) {
        return (int) (Towers.GetTowerCost(displayedTower.getTowerType()) * 0.3f);
    }

    // Get sell amount for the displayed tower
    private int getSellAmount(Tower displayedTower) {
        int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
        upgradeCost *= 0.5f;

        return Towers.GetTowerCost(displayedTower.getTowerType()) / 2 + upgradeCost;
    }

    // Draw range of the displayed tower
    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange() * 2) / 2,
                displayedTower.getY() + 16 - (int) (displayedTower.getRange() * 2) / 2,
                (int) displayedTower.getRange() * 2, (int) displayedTower.getRange() * 2);
    }

    // Draw border of the displayed tower
    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    // Display tower details
    public void displayTower(Tower t) {
        displayedTower = t;
    }

    // Handle sell tower action
    private void sellTowerClicked() {
        playing.removeTower(displayedTower);
        gold += Towers.GetTowerCost(displayedTower.getTowerType()) / 2;

        int upgradeCost = (displayedTower.getTier() - 1) * getUpgradeAmount(displayedTower);
        upgradeCost *= 0.5f;
        gold += upgradeCost;

        displayedTower = null;
    }

    // Handle upgrade tower action
    private void upgradeTowerClicked() {
        playing.upgradeTower(displayedTower);
        gold -= getUpgradeAmount(displayedTower);
    }

    // Toggle game pause state
    private void togglePause() {
        playing.setGamePaused(!playing.isGamePaused());

        if (playing.isGamePaused())
            bPause.setText("Unpause");
        else
            bPause.setText("Pause");
    }

    // Handle mouse click event
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);
        else if (bPause.getBounds().contains(x, y))
            togglePause();
        else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTowerClicked();
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3 && gold >= getUpgradeAmount(displayedTower)) {
                    upgradeTowerClicked();
                    return;
                }
            }

            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    if (!isGoldEnoughForTower(b.getId()))
                        return;

                    selectedTower = new Tower(0, 0, -1, b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    // Check if gold is sufficient for a tower purchase
    private boolean isGoldEnoughForTower(int towerType) {
        return gold >= Towers.GetTowerCost(towerType);
    }

    // Handle mouse move event
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bPause.setMouseOver(false);
        showTowerCost = false;
        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);

        for (MyButton b : towerButtons)
            b.setMouseOver(false);

        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bPause.getBounds().contains(x, y))
            bPause.setMouseOver(true);
        else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTower.setMouseOver(true);
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMouseOver(true);
                    return;
                }
            }

            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                    return;
                }
        }
    }

    // Handle mouse press event
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bPause.getBounds().contains(x, y))
            bPause.setMousePressed(true);
        else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    sellTower.setMousePressed(true);
                    return;
                } else if (upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3) {
                    upgradeTower.setMousePressed(true);
                    return;
                }
            }

            for (MyButton b : towerButtons)
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
        }
    }

    // Handle mouse release event
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bPause.resetBooleans();
        for (MyButton b : towerButtons)
            b.resetBooleans();
        sellTower.resetBooleans();
        upgradeTower.resetBooleans();
    }

    // Deduct gold amount for a tower purchase
    public void payForTower(int towerType) {
        this.gold -= Towers.GetTowerCost(towerType);
    }

    // Add gold to the player's balance
    public void addGold(int getReward) {
        this.gold += getReward;
    }

    // Get the current number of lives
    public int getLives() {
        return lives;
    }
}

