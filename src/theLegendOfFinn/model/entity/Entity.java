package theLegendOfFinn.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;

public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Directions to which an entity can be facing.
	 */
	public enum Direction {
		UP, RIGHT, DOWN, LEFT;

		private static final List<Direction> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();

		/**
		 * Returns a random direction.
		 * 
		 * @return A random direction.
		 */
		public static Direction randomDirection() {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}
	}

	public static final int IDLE = 0;

	private Position position;
	protected Direction direction;

	public Entity(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	/**
	 * Gets the current position for this entity.
	 * 
	 * @return current position.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Gets the current direction for this entity.
	 * 
	 * @return the current direction.
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Checks if calling character is close enough to another character given a
	 * delta measured in Map.CELL_SIZEs (using the taxicab norm).
	 * 
	 * @param entity
	 *            The entity whose closeness we want to check.
	 * @return true if the passed entity is close enough to this one, false otherwise.
	 */
	public boolean closeEnough(Entity entity, int delta) {
		boolean closeEnough = false;
		int distanceX = getPosition().distanceX(entity.getPosition());
		int distanceY = getPosition().distanceY(entity.getPosition());
		int distance = Math.abs(distanceX) + Math.abs(distanceY);

		if (distance <= (2 * delta + 1) * Map.CELL_SIZE * 3.0 / 4.0)
			closeEnough = true;
		
		return closeEnough;
	}
}
