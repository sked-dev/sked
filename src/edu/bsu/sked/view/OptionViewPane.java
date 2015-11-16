package edu.bsu.sked.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class OptionViewPane implements NavigationTarget {
	private final Image icon;
	private static final String OPTIONS = "Options";
	private final GridPane pane = new GridPane();
	TextField username = new TextField();
	Label usernameLabel = new Label();
	
	public OptionViewPane(){
		super();
		icon = configureIcon();
		setUpPane();
	}

	private Image configureIcon() {
		try {
			return Assets.getImageFromAssetName("options.png");
		} catch (Exception e) {
			return null; 
		}
	}
	
	private void setUpPane() {
		Button loginButton = new Button();
		loginButton.setText("Login");
		usernameLabel.setText("Username: ");
		pane.add(usernameLabel, 1, 0);
		pane.add(username, 2,0);
		pane.add(loginButton, 3, 0);
		loginButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Label user = new Label();
				Button logoutButton = new Button();
				pane.add(user, 0, 0);
				user.setText(getUsername());
				pane.getChildren().remove(username);
				pane.getChildren().remove(usernameLabel);
				pane.getChildren().remove(loginButton);
				logoutButton.setText("Logout");
				pane.add(logoutButton, 1, 0);
				logoutButton.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						pane.getChildren().remove(logoutButton);
						pane.getChildren().remove(user);
						setUpPane();
						
					}
					
				});
			
				
			}
			
		});
		
		
		
	}
	
	@Override
	public Image getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return OPTIONS;
	}
	
	@Override
	public GridPane getNode() {
		return pane;
	}

	public String getUsername(){
		if(username.getText() == ""){
			return "Not Logged in";
		}else{
			return ("Logged in as:" + username.getText());

		}
	}

}
