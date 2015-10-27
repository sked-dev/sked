package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NavigationButtonsBox extends Pane {
	
	private VBox container = new VBox();
	private HashMap<String, Integer> names = new HashMap<>();
	private NavigationButton currentButton;

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
		String buttonName = button.getName();
		Integer buttonIndex = container.getChildren().indexOf(button);
		names.put(buttonName, buttonIndex);
		configureActions(button);
	}

	private void configureActions(NavigationButton button) {
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (((NavigationButton) event.getSource()).isSelected()) {
					navigate((NavigationButton) event.getSource());
				} else if (currentButton == (NavigationButton) event.getSource()) {
					((NavigationButton) event.getSource()).setSelected(true);
				}
			}
		});
		
	}

	private void navigate(NavigationButton button) {
		currentButton = button;
		untoggleOthers();
	}

	private void untoggleOthers() {
		for (Node child : container.getChildren()) {
			NavigationButton button = (NavigationButton) child;
			if (button != currentButton) {
				button.setSelected(false);
			}
		}
	}
	
	

}
