
package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
    
    private int x, y, width, height;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    
    public MyButton(String text, int x, int y, int width, int height) {
        this.text  = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        initBounds();
    }
    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }
    
    public void draw(Graphics g) {
        
        drawBody(g);
        
        drawBorder(g);
        
        drawText(g);
        
    }
    
    private void drawBody(Graphics g) {
        if(mouseOver) {
            g.setColor(Color.gray);
        } else {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        }
    }
    
    private void drawBorder(Graphics g) {
        if(mousePressed) {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        g.drawRect(x, y, width-4, height+4);
        } else {
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
        }
    }
    
    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width / 2, y + h /2 + height /2);
    }
    
    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }
    
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }


}
