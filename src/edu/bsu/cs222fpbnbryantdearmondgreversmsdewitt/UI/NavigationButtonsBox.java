package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NavigationButtonsBox extends Pane {
	
	private VBox container = new VBox();

	public static NavigationButtonsBox loadNavigationButtons() {
		NavigationButtonsBox box = new NavigationButtonsBox();
		box.add(new NavigationButton("Home"));
		box.add(new NavigationButton("Classes"));
		box.add(new NavigationButton("Assignments"));
		box.add(new NavigationButton("Settings"));
		return box;
	}
	
	public NavigationButtonsBox() {
		super();
		getChildren().add(container);
	}
	
	public void add(NavigationButton button) {
		container.getChildren().add(button);
	}

}
