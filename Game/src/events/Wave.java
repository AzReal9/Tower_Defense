
package events;

import java.util.ArrayList;

public class Wave {
	private ArrayList<Integer> enemyList;

        // Constructor for creating a wave with the given enemy list.
	public Wave(ArrayList<Integer> enemyList) {
		this.enemyList = enemyList;
	}

        // Getter method to retrieve the list of enemies in the wave.
	public ArrayList<Integer> getEnemyList() {
		return enemyList;
	}

}

