package theLegendOfFinn.view;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.model.character.Character.State;
import theLegendOfFinn.model.character.EnemyHorse;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;


public class SpriteLoader {
	private Map<Class<?>, Map<State, Map<Direction, Image>>> classMap;
	
	private Map<State, Map<Direction, Image>> playerMap;
	private Map<State, Map<Direction, Image>> warriorMap;
	private Map<State, Map<Direction, Image>> horseMap;
	
	private Map<Direction, Image> playerIdleSprites;
	private Map<Direction, Image> playerMovingSprites;
	private Map<Direction, Image> playerAttackingSprites;
	private Map<Direction, Image> horseSprites;
	private Map<Direction, Image> warriorIdleSprites;
	private Map<Direction, Image> warriorMovingSprites;
	
	public SpriteLoader(){	
		playerIdleSprites = new HashMap<>();
		playerMovingSprites = new HashMap<>();
		playerAttackingSprites = new HashMap<>();
		
		horseSprites = new HashMap<>();
		warriorIdleSprites = new HashMap<>();
		warriorMovingSprites = new HashMap<>();
		
		try {
			playerIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/FinnIdleUp2.png")));
			playerIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/FinnIdleRight2.png")));
			playerIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/FinnIdleDown2.png")));
			playerIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/FinnIdleLeft2.png")));
			
			playerMovingSprites.put(Direction.UP, new ImageIcon("./Assets/FinnMovingUp2.gif").getImage());
			playerMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/FinnMovingRight2.gif").getImage());
			playerMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/FinnMovingDown2.gif").getImage());
			playerMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/FinnMovingLeft2.gif").getImage());
			//CAMBIAR, FALTAN LOS SPRITES
			playerAttackingSprites.put(Direction.UP, new ImageIcon("./Assets/FinnAttackingRight2.gif").getImage());
			playerAttackingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/finnAttackingRight.gif").getImage());
			playerAttackingSprites.put(Direction.DOWN, new ImageIcon("./Assets/finnAttackingRight.gif").getImage());
			playerAttackingSprites.put(Direction.LEFT, new ImageIcon("./Assets/finnAttackingLeft.gif").getImage());
			
			warriorIdleSprites.put(Direction.UP, ImageIO.read(new File("./Assets/EnemyIdleUp.png")));
			warriorIdleSprites.put(Direction.RIGHT, ImageIO.read(new File("./Assets/EnemyIdleRight.png")));
			warriorIdleSprites.put(Direction.DOWN, ImageIO.read(new File("./Assets/EnemyIdleDown.png")));
			warriorIdleSprites.put(Direction.LEFT, ImageIO.read(new File("./Assets/EnemyIdleLeft.png")));
			
			warriorMovingSprites.put(Direction.UP, new ImageIcon("./Assets/EnemyMovingUp.gif").getImage());
			warriorMovingSprites.put(Direction.RIGHT, new ImageIcon("./Assets/EnemyMovingRight.gif").getImage());
			warriorMovingSprites.put(Direction.DOWN, new ImageIcon("./Assets/EnemyMovingDown.gif").getImage());
			warriorMovingSprites.put(Direction.LEFT, new ImageIcon("./Assets/EnemyMovingLeft.gif").getImage());
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerMap = new HashMap<>();
		playerMap.put(State.IDLE, playerIdleSprites);
		playerMap.put(State.MOVING, playerMovingSprites);
		//cambiar dsp
		playerMap.put(State.ATTACKING, playerAttackingSprites);
		
		warriorMap = new HashMap<>();
		warriorMap.put(State.IDLE, warriorIdleSprites);
		//cambiar dsp
		warriorMap.put(State.MOVING, warriorMovingSprites);
		warriorMap.put(State.ATTACKING, warriorIdleSprites);
		
		
		classMap = new HashMap<>();
		classMap.put(PlayerCharacter.class, playerMap);
		classMap.put(EnemyHorse.class, horseMap);
		classMap.put(EnemyWarrior.class, warriorMap);
	}

	public Map<Direction, Image> getSprites(Character character){
		return classMap.get(character.getClass()).get(character.getState());
	}
}
