package theLegendOfFinn.model.character;

public abstract class EnemyCharacter extends Character {
	//Esto esta todo ok!
	public enum EnemyType{
		Warrior,
		Horse;
	}
	
	
	// Decidir como se van a guardar las propiedades de cada enemigo y donde. (aca o en la factory)
	//Probablemente sacar velocity.
	public static final int WARRIOR_MAX_HP = 1;
	public static final int WARRIOR_ATTACK = 1;
	public static final int WARRIOR_VELOCITY = 1;
	
	public static final int HORSE_MAX_HP = 2;
	public static final int HORSE_ATTACK = 2;
	public static final int HORSE_VELOCITY = 2;
		
	 
	
	public EnemyCharacter(int x, int y, Direction direction, int velocity, int maxHP, int attack) {
		super(x, y, direction, velocity, maxHP, attack);
	}

	public void chasePlayer(int playerX, int playerY) {
		if (playerY > getY() && playerX > getX())
			tryToMove(Direction.DOWN);
		else if (playerY == getY() && playerX > getX())
			tryToMove(Direction.RIGHT);
		else if (playerY == getY() && playerX < getX())
			tryToMove(Direction.LEFT);
		else if (playerY < getY() && playerX < getX())
			tryToMove(Direction.UP);
		else if (playerX == getX() && playerY > getY())
			tryToMove(Direction.DOWN);
		else if (playerX == getX() && playerY < getY()) {
			System.out.println("ok");
			tryToMove(Direction.UP);
		}
		else if (playerX > getX() && playerY < getY())
			tryToMove(Direction.UP);
		else if (playerX < getX() && playerY > getY())
			tryToMove(Direction.DOWN);
	}
}
