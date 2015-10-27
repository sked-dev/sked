package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class NavigationButton extends ToggleButton {
	
	private Pane pane = new Pane();
	private Label label;
	private String name;
	
	public NavigationButton(String name) {
		super(name);
		label = new Label(name);
		pane.getChildren().add(label);
		this.name = name;
	}
	
	public Pane getContentPane() {
		return pane;
	}

	public String getName() {
		return name;
	}
	
}
