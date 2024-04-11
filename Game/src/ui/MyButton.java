
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// MyButton class represents a clickable button in the UI
public class MyButton {

    public int x, y, width, height, id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    // Constructor for normal buttons
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    // Constructor for buttons associated with tiles
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }

    // Initialize button bounds
    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    // Draw the button
    public void draw(Graphics g) {
        // Draw button body
        drawBody(g);

        // Draw button border
        drawBorder(g);

        // Draw button text
        drawText(g);
    }

    // Draw button border
    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        if (mousePressed) {
            g.drawRect(x + 1, y + 1, width - 2, height - 2);
            g.drawRect(x + 2, y + 2, width - 4, height - 4);
        }
    }

    // Draw button body
    private void drawBody(Graphics g) {
        if (mouseOver)
            g.setColor(Color.gray);
        else
            g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    // Draw button text
    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w / 2 + width / 2, y + h / 2 + height / 2);
    }

    // Reset mouse over and mouse pressed states
    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    // Set the button text
    public void setText(String text) {
        this.text = text;
    }

    // Set mouse pressed state
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // Set mouse over state
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Check if mouse is over the button
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Check if the button is pressed
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Get the bounds of the button
    public Rectangle getBounds() {
        return bounds;
    }

    // Get the ID of the button (used for tiles)
    public int getId() {
        return id;
    }
}
