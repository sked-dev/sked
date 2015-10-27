package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NavigationPane extends BorderPane {
	
	private NavigationButtonsBox navigationButtons;
	private HashMap<NavigationButton, Pane> availablePanes = new HashMap<NavigationButton, Pane>();
	
	private NavigationPane() {
		super();
		navigationButtons = new NavigationButtonsBox();
		
		navigationButtons.setOnNavigate(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				setCenter(availablePanes.get(navigationButtons.getCurrentButton()));
			}
		});
		setRight(navigationButtons);
	}
	
	public static NavigationPane configure() {
		NavigationPane navigation = new NavigationPane();
		navigation.addDefaults();
		return navigation;
	}
	
	private void addDefaults() {
		add(new TestNavigationPane("Button 1"));
		add(new TestNavigationPane("Button 2"));
		add(new TestNavigationPane("Button 3"));
	}
	
	public void add(NavigationTargetPane pane) {
		NavigationButton button = new NavigationButton(pane.getLabel(), pane.getIcon());
		navigationButtons.add(button);
		availablePanes.put(button, pane);
	}

	public void navigateToDefault() {
		navigationButtons.navigateByIndex(0);
	}
	
	
}
