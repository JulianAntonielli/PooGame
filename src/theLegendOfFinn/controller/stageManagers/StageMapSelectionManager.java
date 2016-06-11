package theLegendOfFinn.controller.stageManagers;

import java.awt.event.KeyEvent;

import theLegendOfFinn.controller.Manager;
import theLegendOfFinn.controller.RenderManager.Stage;
import theLegendOfFinn.controller.exceptions.TickerMissingException;
import theLegendOfFinn.model.Ticker;
import theLegendOfFinn.view.menu.MapSelectionRenderer;

public class StageMapSelectionManager extends StageManager{

	public StageMapSelectionManager(Manager manager) {
		super(manager);
	}

	public Stage handleStage(int key) {
		Stage stage = Stage.MAPSELECTION;
		MapSelectionRenderer menuMapSelection = masterRenderer.getMapSelectionRenderer();
		
		switch (key) {
		case KeyEvent.VK_RIGHT:
			menuMapSelection.nextOption();
			break;
		case KeyEvent.VK_LEFT:
			menuMapSelection.previousOption();
			break;
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_A:
			manager.loadTicker(new Ticker(manager.getNotifier()));
			//implementar dsp
			/*if (menuMapSelection.getOption().equals(MapSelectionRenderer.GRASS))
				
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.ICE))
				
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.MOUNTAIN))
				
			else if(menuMapSelection.getOption().equals(MapSelectionRenderer.LAVA))*/
			try {
				manager.initialize();
			} catch (TickerMissingException e) {
				e.printStackTrace();
			}
			stage = Stage.MAP;
			break;
		}
		return stage;
	}

}
