package theLegendOfFinn.controller;

import java.util.ArrayList;
import java.util.List;

import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.model.Map;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.EnemyCharacter;
import theLegendOfFinn.model.character.EnemyFactory;
import theLegendOfFinn.model.character.EnemyWarrior;
import theLegendOfFinn.model.character.PlayerCharacter;
import theLegendOfFinn.model.character.Character.Direction;
import theLegendOfFinn.view.MapRenderer;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.RenderFactory;

public class Manager {
	private MasterRenderer masterRenderer;
	
	private RenderManager renderManager;
	private EventManager eventManager;
	private ModelManager modelManager;
	
	private Ticker ticker;

	//cambiar dsp
	private EnemyFactory enemyFactory;
	private RenderFactory renderFactory;


	public Manager() {
		List<EnemyCharacter> enemyList = new ArrayList<EnemyCharacter>();
		//Aca meti un enemigo de prueba
		enemyList.add(new EnemyWarrior(0, 0, Direction.LEFT));
		enemyList.add(new EnemyWarrior(32 * 14, 32 * 14, Direction.LEFT));
		
		ticker = new Ticker(new Map(new PlayerCharacter(1), enemyList));

		masterRenderer = new MasterRenderer(new Delegate(this));
		masterRenderer.setMapRenderer(new MapRenderer(ticker.getMap()));

		enemyFactory = new EnemyFactory();
		renderFactory = new RenderFactory();
		//ver como sacar esto de aca y meterlo en render manager
		masterRenderer.addCharacterRenderer(renderFactory.getPlayerRenderer(ticker.getPlayer()));
		// Aca renderizo los enemigos creo
		for(EnemyCharacter enemy: ticker.getEnemies()) {
			masterRenderer.addCharacterRenderer(renderFactory.getHorseRenderer(enemy));
		}
		renderManager = new RenderManager(masterRenderer);
		eventManager = new EventManager(masterRenderer, ticker);
		modelManager = new ModelManager(ticker);

		renderManager.initialize();
	}

		/*
		Iterator<EnemyCharacter> iter = enemies.iterator();
		while (iter.hasNext()) {
			Character character = iter.next();
			masterRenderer.addCharacterRenderer(rFactory.getCharacterRenderer(character, character.getType()));
		}
		*/

	public void setStage(Stage stage) {
		renderManager.setStage(stage);
	}

	public Stage getStage() {
		return renderManager.getStage();
	}

	public void keyChange(int key) {
		setStage(eventManager.handleEvent(key, getStage()));
	}
	
	public static void main(String[] args) {
		Manager manager = new Manager();
	}
}