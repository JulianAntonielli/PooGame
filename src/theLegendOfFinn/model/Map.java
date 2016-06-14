package theLegendOfFinn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.model.entity.character.Boss;
import theLegendOfFinn.model.entity.character.EnemyCharacter;
import theLegendOfFinn.model.entity.character.PlayerCharacter;
import theLegendOfFinn.model.exceptions.PositionOccupiedException;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int CELL_SIZE = 32;

	public static final int WIDTH = 640 / CELL_SIZE;
	public static final int HEIGHT = ((WIDTH * 3) / 4);
	
	public static final Position TOP_LEFT_CORNER = new Position(0, 0);
	public static final Position BOTTOM_LEFT_CORNER = new Position(0, (HEIGHT - 1) * CELL_SIZE);
	public static final Position TOP_RIGHT_CORNER = new Position((WIDTH - 1) * CELL_SIZE, 0);
	public static final Position BOTTOM_RIGHT_CORNER = new Position((WIDTH - 1) * CELL_SIZE, (HEIGHT - 1) * CELL_SIZE);

	private PlayerCharacter player;
	private List<EnemyCharacter> enemyList;
	
	private Grid grid;

	/*
	public Map(){
		player = new PlayerCharacter(1);
		grid = new CharacterGrid();
		try {
			grid.add(player);
			//add(player);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
	}
	*/
	public Map(PlayerCharacter player) {
		this(player, new ArrayList<EnemyCharacter>());
	}

	public Map(PlayerCharacter player, List<EnemyCharacter> enemyList) {
		grid = new Grid();
		this.player = player;
		try {
			grid.add(player);
		} catch (PositionOccupiedException e) {
			e.printStackTrace();
		}
		this.enemyList = enemyList;
	}
	
	// Lo hice villerisimo...
	/*
	public void add(Character character) throws PositionOccupiedException {
		if (grid[character.getPosition().getX()/CELL_SIZE][character.getPosition().getY()/CELL_SIZE] != null) {
			throw new PositionOccupiedException(
					"La posicion [" + character.getPosition().getX() + "]" + "[" + character.getPosition().getY() + "]" + " esta ocupada, imposible a�adir " + character + ".");
		}
		grid[character.getPosition().getX()/CELL_SIZE][character.getPosition().getY()/CELL_SIZE] = character;
	}
	*/
	
	// cambiar luego;
	/*
	public void remove(Position pos) {
		grid[pos.getX()/CELL_SIZE][pos.getY()/CELL_SIZE] = null;
	}
	*/

	public PlayerCharacter getPlayer() {
		return player;
	}
	
	public List<EnemyCharacter> getEnemies() {
		return enemyList;
	}
	
	
	public Boss getBoss(){
		if(enemyList.get(0) instanceof Boss)
			return (Boss)enemyList.get(0);
		return null;
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public void setRound(Round round){
		enemyList = round.getEnemies();
	}
	

	
}
