package edu.bsu.sked.view;

import java.util.HashMap;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NavigationPane extends BorderPane {

	private static final double BUTTON_SIZE = 100;
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
		navigation.setMinSize(120, 120);
		navigation.setPrefSize(500, 300);
		navigation.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		navigation.addDefaults();
		return navigation;
	}

	private void addDefaults() {
		add(new AssignmentViewPane());
		add(new OptionViewPane());
	}

	public void add(NavigationTargetPane pane) {
		NavigationButton button = new NavigationButton(pane.getLabel(), pane.getIcon());
		navigationButtons.add(button);
		navigationButtons.setMaxWidth(BUTTON_SIZE);
		availablePanes.put(button, pane);
	}

	public void navigateToDefault() {
		navigationButtons.navigateByIndex(0);
	}

}
