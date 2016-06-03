package theLegendOfFinn.controller;


import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager.Stage;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.model.character.Character;
import theLegendOfFinn.view.MasterRenderer;
import theLegendOfFinn.view.MenuRenderer;

public class EventManager {

	MasterRenderer masterRenderer;
	Ticker ticker;

	public EventManager(MasterRenderer masterRenderer, Ticker ticker) {
		this.masterRenderer = masterRenderer;
		this.ticker = ticker;
	}

	public Stage handleEvent(int key, Stage stage) {
		Stage newStage;
		switch (stage) {
		case MENU:
			newStage = handleMenu(key);
			break;
		case MAP:
			newStage = handleMap(key);
			break;
		case PAUSE:
			newStage = handlePause(key);
			break;
		default:
			throw new RuntimeException("Illegal value for stage.");
		}

		return newStage;
	}

	public Stage handleMenu(int key) {
		Stage stage = Stage.MENU;
		MenuRenderer menu = masterRenderer.getMenuRenderer();
		switch (key) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			menu.changeOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			if (menu.getOption().equals(MenuRenderer.Option.NUEVO))
				System.out.println("hola");
			;
			stage = Stage.MAP;
			break;
		default:
			break;
		}
		return stage;
	}

	public Stage handleMap(int key) {
		Stage stage = Stage.MAP;
		Character player = ticker.getPlayer();
		switch (key) {
		case KeyEvent.VK_UP:
			player.tryToMove(Character.Direction.UP);
			break;
		case KeyEvent.VK_LEFT:
			player.tryToMove(Character.Direction.LEFT);
			break;
		case KeyEvent.VK_DOWN:
			player.tryToMove(Character.Direction.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			player.tryToMove(Character.Direction.RIGHT);
			break;
		// Con esto atacaria.
		case KeyEvent.VK_A:
			break;
		default:
			break;
		}
		return stage;
	}

	public Stage handlePause(int key) {
		Stage stage = Stage.PAUSE;
		return stage;
	}

}

/*
 * else if (stage.equals(Stage.MAP)) player.tryToMove(Character.Direction.LEFT);
 * break; case KeyEvent.VK_RIGHT: if (stage.equals(Manager.Stage.MENU))
 * window.getMenuR().changeOption(); else if (stage.equals(Stage.MAP))
 * player.tryToMove(Character.Direction.RIGHT); break; case KeyEvent.VK_UP: if
 * (stage.equals(Manager.Stage.MAP)) player.tryToMove(Character.Direction.UP);
 * break; case KeyEvent.VK_DOWN: if (stage.equals(Manager.Stage.MAP))
 * player.tryToMove(Character.Direction.DOWN); break; case KeyEvent.VK_ESCAPE:
 * if (stage.equals(Stage.MAP)) setStage(Stage.PAUSE); else if
 * (stage.equals(Stage.PAUSE)) setStage(Stage.MAP); default: break;
 */
