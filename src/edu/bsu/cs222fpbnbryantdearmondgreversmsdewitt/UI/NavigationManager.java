package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NavigationManager extends BorderPane {
	
	private NavigationButtonsBox navigationButtons;
	
	public NavigationManager() {
		super();
		navigationButtons = NavigationButtonsBox.loadNavigationButtons();
		
		navigationButtons.setOnNavigate(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				setCenter(navigationButtons.getContentPane());
			}
		});
		setRight(navigationButtons);
	}
	
	public void navigateToDefault() {
		navigationButtons.navigateByIndex(0);
	}
	
	
}
