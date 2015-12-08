package edu.bsu.sked.view;

import java.util.HashMap;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class NavigationPane extends BorderPane {

	private NavigationButtonsBox navigationButtons;
	Label username = new Label();
	private HashMap<NavigationButton, Node> availablePanes = new HashMap<NavigationButton, Node>();

	private NavigationPane() {
		super();
		setPadding(new Insets(10));
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
		add(new CourseViewPane());
		add(new OptionViewPane());
	}

	public void add(NavigationTarget pane) {
		NavigationButton button = new NavigationButton(pane.getLabel(), pane.getIcon());
		navigationButtons.add(button);
		availablePanes.put(button, pane.getNode());
	}

	public void navigateToDefault() {
		navigationButtons.navigateByIndex(0);
	}

}
