package edu.bsu.sked.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class NavigationButtonsBox extends VBox {
	
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

	//private VBox container = new VBox(15);
	private NavigationButton currentButton;

	public static NavigationButtonsBox loadNavigationButtons() {
		NavigationButtonsBox box = new NavigationButtonsBox();
		
		return box;
	}
	
	public NavigationButtonsBox() {
		super();
		this.setPrefWidth(USE_COMPUTED_SIZE);
		this.setMaxWidth(350);
		this.setSpacing(15);
		this.setFillWidth(true);
	}
	
	public void add(NavigationButton button) {
		getChildren().add(button);
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
		for (Node child : getChildren()) {
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
		NavigationButton button = (NavigationButton) getChildren().get(index);
		navigate(button);
	}
	
	public NavigationButton getCurrentButton() {
		return currentButton;
	}
	
	

}
