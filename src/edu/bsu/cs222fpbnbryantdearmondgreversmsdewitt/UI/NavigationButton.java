package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavigationButton extends ToggleButton {
	
	private final Image icon;
	private final String label;
	
	public NavigationButton(String label, Image icon) {
		super(label);
		this.label = label;
		this.icon = icon;
	}
	
}
