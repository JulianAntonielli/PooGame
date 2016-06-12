package theLegendOfFinn.model.entity.character;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.entity.Entity;
import theLegendOfFinn.view.Renderer;

/**
 * Holds the stats and methods of the playable character.
 * 
 * @author LCDPCJL
 *
 */
public class PlayerCharacter extends Character {
	private static final long serialVersionUID = 1L;

	public static final int[] PLAYER_MAX_HP = { 6, 10 };
	public static final int[] PLAYER_ATTACK = { 1, 2 };
	public static final int[] PLAYER_VELOCITY = { 5, 5 };

	private int level;
	
	public PlayerCharacter(int level) {
		super(new Position(Map.WIDTH * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE,
				Map.HEIGHT * Renderer.CELL_SIZE / 2 - Renderer.CELL_SIZE / 2), Direction.DOWN, PLAYER_MAX_HP[level],
				PLAYER_ATTACK[level], PLAYER_VELOCITY[level]);
		this.level = level; 

	}

	/**
	 * Attack method that takes into account that killing a character makes the Player
	 * recover life.
	 */
	public boolean attack(Entity entity) {
		boolean attacked = super.attack(entity);
		if (!(entity instanceof Character)) return false;
		
		Character character = (Character) entity;
		if (attacked && !character.isAlive()) recoverLife(character);
		return attacked;
	}
	
	/**
	 * Recovers life from a given character's HP Bounty.
	 * 
	 * @param character Character from which get HP bounty.
	 */
	private void recoverLife(Character character) {
		int hpRestored = getCurrentHP() + ((EnemyCharacter) character).getHPBounty();
		setCurrentHP(hpRestored <= getMaxHP() ? hpRestored : getMaxHP());
	}
	
	/**
	 * Increases the level of the character by one, increasing
	 * his stats and healing him. 
	 */
	public void levelUp(){
		level += 1;
		setMaxHP(PLAYER_MAX_HP[level]);
		setAttack(PLAYER_ATTACK[level]);
		setVelocity(PLAYER_VELOCITY[level]);
		setCurrent(getMaxHP());
	}
}