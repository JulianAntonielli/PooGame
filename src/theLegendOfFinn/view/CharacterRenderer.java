package theLegendOfFinn.view;

import java.awt.*;

import theLegendOfFinn.model.character.Character;

import javax.swing.*;

public class CharacterRenderer implements Renderer {
	private ImageData imageData;

	public CharacterRenderer() {

		this.imageData = new ImageData();
	}

	
	@Override
	public void render(Graphics g) {
		for(int i = 0; i < imageData.size(); i++){
			g.drawImage(imageData.getImage(i), imageData.getPosition(i).getX(), imageData.getPosition(i).getY(), imageData.getWidth(i), imageData.getHeight(i), null);
		}
		//Así no mas se puede agregar un gif:
		//Image icon = new ImageIcon("./Assets/finnDown.gif").getImage();
		//g.drawImage(icon, 20, 20, null);
	}
	
	public void draw(Character character){
			imageData.add(character);
	}
	
	public void dispose(){
		imageData = new ImageData();
	}
}
