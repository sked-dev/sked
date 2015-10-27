package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NavigationManager extends BorderPane {
	
	private NavigationButtonsBox navigationButtons;
	
	public NavigationManager() {
		super();
		navigationButtons = NavigationButtonsBox.loadNavigationButtons();
		setRight(navigationButtons);
		setCenter(new Pane());
	}
	
	
}
