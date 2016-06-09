package theLegendOfFinn.view;

public class PauseRenderer extends MenuRenderer implements Renderer {
	public static final MenuOption RESUME = new MenuOption(224, 160, 193, 50);
	public static final MenuOption SAVE = new MenuOption(224, 231, 193, 50);
	public static final MenuOption EXIT = new MenuOption(224, 302, 193, 50);

	private static final String backGroundImagePath = "./Assets/menus/pause.png";

	public PauseRenderer() {
		super(PauseRenderer.backGroundImagePath);
		addOption(PauseRenderer.RESUME);
		addOption(PauseRenderer.SAVE);
		addOption(PauseRenderer.EXIT);
	}
}