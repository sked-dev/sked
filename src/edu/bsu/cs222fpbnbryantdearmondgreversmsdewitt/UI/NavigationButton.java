package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class NavigationButton extends ToggleButton {
	
	public NavigationButton(String label, Image icon) {
		super();
		ImageView iconContainer = new ImageView(icon);
		this.setGraphic(iconContainer);
		iconContainer.setFitHeight(75);
		iconContainer.setPreserveRatio(true);
		
		this.setTooltip(new Tooltip(label));
	}
	
}
