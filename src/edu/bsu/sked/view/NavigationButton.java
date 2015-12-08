package edu.bsu.sked.view;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class NavigationButton extends ToggleButton {
	
	public NavigationButton(String label, Image icon) {
		super();
		setButtonIcon(icon);
		this.setText(label);
		this.setMaxWidth(Double.MAX_VALUE);
	}

	private void setButtonIcon(Image icon) {
		ImageView iconContainer = new ImageView(icon);
		this.setGraphic(iconContainer);
		iconContainer.setFitHeight(32);
		iconContainer.setFitWidth(32);
		iconContainer.setPreserveRatio(true);
	}
	
}
