package theLegendOfFinn.model.gameData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.entity.character.enemy.EnemyCharacter;
import theLegendOfFinn.model.entity.character.enemy.boss.Boss;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

/**
 * Contains the characters of the game and the grid of entities.
 */
public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	// width and height of a single position of the grid.
	public static final int CELL_SIZE = 32;

	// width and height of the map measured in grid positions.
	public static final int WIDTH = 640 / CELL_SIZE;
	public static final int HEIGHT = ((WIDTH * 3) / 4);

	// entities interacting in the grid
	private PlayerCharacter player;
	private List<EnemyCharacter> enemyList;
	
	// entities containing grid
	private Grid grid;

	public Map(PlayerCharacter player) {
		this(player, new ArrayList<EnemyCharacter>());
	}

	public Map(PlayerCharacter player, List<EnemyCharacter> enemyList) {
		grid = new Grid();
		this.player = player;
		try {
			grid.add(player);
		} catch (PositionOccupiedException e) {
			Manager.LOGGER.log(Level.FINE, "Tried to override occupied position", e);
		}
		this.enemyList = enemyList;
	}

	/**
	 * Gets the playable character
	 * @return the playable character
	 */
	public PlayerCharacter getPlayer() {
		return player;
	}
	
	/**
	 * Get the list of enemies in the current map.
	 * @return the enemies' list.
	 */
	public List<EnemyCharacter> getEnemies() {
		return enemyList;
	}
	
	/**
	 * Gets the boss in the map if there is one, null otherwise
	 * @return the boss
	 */
	public Boss getBoss(){
		if(enemyList.get(0) instanceof Boss)
			return (Boss)enemyList.get(0);
		return null;
	}

	/**
	 * Gets the matrix of entities called grid.
	 * 
	 * @return grid The grid.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Loads the enemies of a round to the enemyList of the map.
	 * 
	 * @param round a round with an array of enemies.
	 */
	public void setRound(Round round){
		enemyList = round.getRoundEnemies();
	}
	

	
}
