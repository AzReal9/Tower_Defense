
package ui;

import java.awt.Color;
import java.awt.Graphics;

// Bar class represents a generic rectangular bar used in UI elements
public class Bar {

    protected int x, y, width, height;

    // Constructor to initialize the bar's position and dimensions
    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Method to draw feedback for buttons, indicating mouse interaction
    protected void drawButtonFeedback(Graphics g, MyButton b) {
        // Change color based on mouse state
        if (b.isMouseOver())
            g.setColor(Color.white); // MouseOver color
        else
            g.setColor(Color.BLACK); // Default color

        // Draw button border
        g.drawRect(b.x, b.y, b.width, b.height);

        // Draw additional border for pressed state
        if (b.isMousePressed()) {
            g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
            g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
        }
    }
}