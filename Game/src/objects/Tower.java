
package objects;

import static helpz.Constants.Towers.*;

public class Tower {

    private int x; // X-coordinate of the tower
    private int y; // Y-coordinate of the tower
    private int id; // Identifier of the tower
    private int towerType; // Type of the tower
    private int cdTick; // Cooldown tick
    private int dmg; // Damage
    private float range; // Attack range
    private float cooldown; // Cooldown duration
    private int tier; // Tower tier


	public Tower(int x, int y, int id, int towerType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.towerType = towerType;
		tier = 1;
		setDefaultDmg();
		setDefaultRange();
		setDefaultCooldown();
	}

	public void update() {
		cdTick++;
	}

         /**
     * Upgrades the tower to the next tier.
     */
        
	public void upgradeTower() {
		this.tier++;

		switch (towerType) {
		case ARCHER:
			dmg += 2;
			range += 20;
			cooldown -= 5;
			break;
		case CANNON:
			dmg += 5;
			range += 20;
			cooldown -= 15;
			break;
		case WIZARD:
			range += 20;
			cooldown -= 10;
			break;
		}
	}

	public boolean isCooldownOver() {

		return cdTick >= cooldown;
	}

        /**
     * Resets the tower's cool down tick to zero.
     */
        
	public void resetCooldown() {
		cdTick = 0;
	}

	private void setDefaultCooldown() {
		cooldown = helpz.Constants.Towers.GetDefaultCooldown(towerType);

	}

	private void setDefaultRange() {
		range = helpz.Constants.Towers.GetDefaultRange(towerType);

	}

	private void setDefaultDmg() {
		dmg = helpz.Constants.Towers.GetStartDmg(towerType);

	}

        //getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTowerType() {
		return towerType;
	}

	public void setTowerType(int towerType) {
		this.towerType = towerType;
	}

	public int getDmg() {
		return dmg;
	}

	public float getRange() {
		return range;
	}

	public float getCooldown() {
		return cooldown;
	}

	public int getTier() {
		return tier;
	}

}






