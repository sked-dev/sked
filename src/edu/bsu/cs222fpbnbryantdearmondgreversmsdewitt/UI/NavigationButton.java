package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class NavigationButton extends ToggleButton {
	
	private HBox pane = new HBox();
	private Label label;
	private String name;
	
	public NavigationButton(String name) {
		super(name);
		label = new Label(name + " pane");
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
