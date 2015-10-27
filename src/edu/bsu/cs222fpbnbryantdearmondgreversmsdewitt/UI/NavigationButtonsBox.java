package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.util.HashMap;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI.NavigationButtonsBox.NavigateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NavigationButtonsBox extends Pane {
	
	public static class NavigateEvent extends Event {
		
		private static final long serialVersionUID = 1L;
		public static final EventType<NavigateEvent> NAVIGATED = new EventType<>(Event.ANY, "NAVIGATED");
		
		public NavigateEvent() {
			this(NAVIGATED);
		}

		public NavigateEvent(EventType<? extends Event> eventType) {
			super(eventType);
		}

	}

	private VBox container = new VBox();
	private NavigationButton currentButton;

	public static NavigationButtonsBox loadNavigationButtons() {
		NavigationButtonsBox box = new NavigationButtonsBox();
		
		return box;
	}
	
	public NavigationButtonsBox() {
		super();
		getChildren().add(container);
	}
	
	public void add(NavigationButton button) {
		container.getChildren().add(button);
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
		button.setSelected(true);
		untoggleOthers();
		this.fireEvent(new NavigateEvent());
	}

	private void untoggleOthers() {
		for (Node child : container.getChildren()) {
			NavigationButton button = (NavigationButton) child;
			if (button != currentButton) {
				button.setSelected(false);
			}
		}
	}
	
	public void setOnNavigate(EventHandler<Event> handler) {
		this.setEventHandler(NavigateEvent.NAVIGATED, handler);
	}


	public void navigateByIndex(int index) {
		NavigationButton button = (NavigationButton) container.getChildren().get(index);
		navigate(button);
	}
	
	public NavigationButton getCurrentButton() {
		return currentButton;
	}
	
	

}
