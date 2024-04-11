
package objects;

import java.awt.geom.Point2D;

/**
 * The Projectile class represents a projectile fired by towers to attack enemies.
 */

public class Projectile {

	private Point2D.Float pos;
	private int id, projectileType, dmg;
	private float xSpeed, ySpeed, rotation;
	private boolean active = true;

	public Projectile(float x, float y, float xSpeed, float ySpeed, int dmg, float rotation, int id, int projectileType) {
		pos = new Point2D.Float(x, y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.dmg = dmg;
		this.rotation = rotation;
		this.id = id;
		this.projectileType = projectileType;
	}

        /**
     * Moves the projectile based on its speed.
     */
        
	public void move() {
		pos.x += xSpeed;
		pos.y += ySpeed;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}

	public int getProjectileType() {
		return projectileType;
	}

	public boolean isActive() {
		return active;
	}

        /**
     * Sets the activity status of the projectile.
     * @param active The new activity status.
     */
        
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRotation() {
		return rotation;
	}

}


