package theLegendOfFinn.model.character;

import java.nio.channels.NonWritableChannelException;
import java.util.concurrent.TimeUnit;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Position;
import theLegendOfFinn.model.CharacterGrid;
import theLegendOfFinn.view.Renderer;

public class Character {
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}

	private int maxHP;
	private Position pos;
	//private int x, y;
	private int velocity;
	private int currentHP;
	private int attack;
	private Direction direction;

	private long lastMoveTime;
	private long nowMoveTime;
	private Direction moveDirection;
	private boolean moving = false;
	private int moveRemaining;

	public Character(Position pos, Direction direction, int maxHP, int attack, int velocity) {
	//public Character(int x, int y, Direction direction, int maxHP, int attack, int velocity) {
		//this.x = x;
		//this.y = y;
		this.pos = pos;
		this.direction = direction;
		this.velocity = velocity;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = attack;
	}
	
	public static void test(){
		return;
	}
	
	public Position getPosition() {
		return pos;
	}
	/*
	public int getX() {
		return pos.getX();
		//return x;
	}

	public int getY() {
		return pos.getY();
		//return y;
	}
	*/
	public Direction getDirection() {
		return direction;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public int getAttack() {
		return attack;
	}

	protected void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	protected void setX(int x) {
		pos.setX(x);
		//this.x = x;
	}

	protected void setY(int y) {
		pos.setY(y);
		//this.y = y;
	}
	
	protected void setPosition(Position pos) {
		this.pos = pos;
	}

	//public boolean canMove(Direction direction) {
	public void tryToMove(Direction direction, CharacterGrid grid) {
		Position destination;
		boolean canMove = true;
		
		if (moving == true)
			return;
					
		switch (direction) {
		case LEFT:
			destination = new Position(getPosition().getX() - Map.CELL_SIZE, getPosition().getY());
			break;
		case RIGHT:
			destination = new Position(getPosition().getX() + Map.CELL_SIZE, getPosition().getY());
			break;
		case UP:
			destination = new Position(getPosition().getX(), getPosition().getY() - Map.CELL_SIZE);
			break;
		case DOWN:
			destination = new Position(getPosition().getX(), getPosition().getY() + Map.CELL_SIZE);
			break;
		default:
			// should throw an exception, this is madness.
			destination = new Position(0, 0);
		}
		
		if (destination.getX() < 0 || destination.getX() >= Map.WIDTH * Map.CELL_SIZE
				|| destination.getY() < 0 || destination.getY() >= Map.HEIGHT * Map.CELL_SIZE
				|| !grid.isFreePosition(destination))
			canMove = false;
		
		if (canMove) {
		//if (canMove(direction)) {
			moveDirection = direction;
			moving = true;
			moveRemaining = 32;
			lastMoveTime = System.currentTimeMillis();
			grid.occupyPosition(this, destination);
			grid.freePosition(this.getPosition());
		}
	}

	/*
	 * se podria evitar hacer dos switchs iguales haciendo que cuando pregunta
	 * si no se pasa de los limites automaticamente modifique la posicion, pero
	 * quedaria raro nose. Osea que primero se verifique si hay algun enemigo en
	 * esa posicion y si no hay mande move y ahi chequee si no se pasa de
	 * limites y mueva.
	 */

	// Revisar el movimiento, se mueve trabado.
	
	/*
	private boolean canMove(Position finalPos) {
	//private boolean canMove(Direction direction) {
		boolean canMove = true;

		nowMoveTime = System.currentTimeMillis();
		if (moving == true)
			return false;
		
		if (finalPos.getX() < 0 || finalPos.getX() >= Map.WIDTH * Map.CELL_SIZE
				|| finalPos.getY() < 0 || finalPos.getY() >= Map.HEIGHT * Map.CELL_SIZE)
			canMove = false;
		return canMove;
		
		switch (direction) {
		case LEFT:
			//if ((getX() - Renderer.CELL_SIZE) < 0 )
			if (getPosition().getX() - Map.CELL_SIZE < 0)
				canMove = false;
			break;
		case RIGHT:
			//if ((getX() + Renderer.CELL_SIZE) >= Map.WIDTH * Renderer.CELL_SIZE)
			if ((getPosition().getX() + Map.CELL_SIZE) >= Map.WIDTH * Map.CELL_SIZE)
				canMove = false;
			break;
		case UP:
			//if ((getY() - Renderer.CELL_SIZE) < 0)
			if (getPosition().getY() - Map.CELL_SIZE < 0)
				canMove = false;
			break;
		case DOWN:
			//if ((getY() + Renderer.CELL_SIZE) >= Map.HEIGHT * Renderer.CELL_SIZE)
			if ((getPosition().getY() + Map.CELL_SIZE) >= Map.HEIGHT * Map.CELL_SIZE)
				canMove = false;
			break;
		}

		return canMove;
		
	}
	*/

	// Falta usar la velocity

	// Hay que tener cuidado con el sleep(10), quizas habria que hacer
	// otro thread que maneje ticker, pero no estoy seguro de coomo se hace todo
	// eso.

	public void move() {
		int yIncrement = 0, xIncrement = 0;
		if(moveRemaining == 0){ 
			moving = false;
			return;
		}
		moveRemaining--;
		switch (moveDirection) {
		case UP:
			yIncrement = -1;
			xIncrement = 0;
			break;
		case LEFT:
			yIncrement = 0;
			xIncrement = -1;
			break;
		case DOWN:
			yIncrement = 1;
			xIncrement = 0;
			break;
		case RIGHT:
			yIncrement = 0;
			xIncrement = 1;
			break;
		}
		pos.incPos(xIncrement, yIncrement);
		//setY(getY() + yIncrement);
		//setX(getX() + xIncrement);

	}

	// private?
	private void attack(Character character) {
		if (character == null)
			return;
		character.receiveAttack(this);
	}

	private void receiveAttack(Character character) {
		setCurrentHP(getCurrentHP() - character.getAttack());
	}
}
