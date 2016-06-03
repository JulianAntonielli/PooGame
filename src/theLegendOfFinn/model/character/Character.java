package theLegendOfFinn.model.character;

import java.util.concurrent.TimeUnit;

import theLegendOfFinn.model.Map;
import theLegendOfFinn.view.Renderer;

public class Character {
	public enum Direction {
		UP, RIGHT, DOWN, LEFT;
	}

	private static final int MOVE_COOLDOWN = 1;


	private int maxHP;
	private int x, y;
	private int velocity;
	private int currentHP;
	private int attack;
	private Direction direction;

	private long lastMoveTime;
	private long nowMoveTime;
	private boolean moving = false;

	public Character(int x, int y, Direction direction, int maxHP, int attack, int velocity) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.velocity = velocity;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.attack = attack;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

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
		this.x = x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	public void tryToMove(Direction direction) {
		if (canMove(direction)) {
			moveCharacter(direction);
			lastMoveTime = System.currentTimeMillis();
			moving = true;
		}
	}

	/*
	 * se podria evitar hacer dos switchs iguales haciendo que cuando pregunta
	 * si no se pasa de los limites automaticamente modifique la posicion, pero
	 * quedaria raro nose. Osea que primero se verifique si hay algun enemigo en
	 * esa posicion y si no hay mande move y ahi chequee si no se pasa de
	 * limites y mueva.
	 */

	// DEJEN DE USAR LAS VARIABLES, MIERDA!! USEN LOS GETTERS/SETTERS.
	// Hay que ver lo de que no se superpongan tipitos/enemigos.
	
	//Revisar el movimiento, se mueve trabado.
	private boolean canMove(Direction direction) {
		boolean canMove = true;

		nowMoveTime = System.currentTimeMillis();
		if (moving == true) {
			if (nowMoveTime - lastMoveTime > MOVE_COOLDOWN) {
				moving = false;
			} else
				return false;
		}
		switch (direction) {
		case LEFT:
			if ((getX() - Renderer.CELL_SIZE) < 0)
				canMove = false;
			break;
		case RIGHT:
			if ((getX() + Renderer.CELL_SIZE) >= Map.WIDTH * Renderer.CELL_SIZE)
				canMove = false;
			break;
		case UP:
			if ((getY() - Renderer.CELL_SIZE) < 0)
				canMove = false;
			break;
		case DOWN:
			if ((getY() + Renderer.CELL_SIZE) >= Map.HEIGHT * Renderer.CELL_SIZE)
				canMove = false;
			break;
		}

		return canMove;
	}

	// Falta usar la velocity
	
	// Hay que tener cuidado con el sleep(10), quizas habria que hacer 
	// otro thread que maneje ticker, pero no estoy seguro de coomo se hace todo eso.
	
	private void moveCharacter(Direction direction) {
		int yIncrement = 0, xIncrement = 0;
		switch (direction) {
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

		for (int i = 0; i < Renderer.CELL_SIZE; i++) {
			setY(getY() + yIncrement);
			setX(getX() + xIncrement);
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (Exception e){
			}
		}

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
