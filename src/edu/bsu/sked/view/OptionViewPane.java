package edu.bsu.sked.view;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class OptionViewPane extends NavigationTargetPane {
	private final Image icon;
	private static final String OPTIONS = "Options";
	private final BorderPane pane = new BorderPane();
	
	public  OptionViewPane(){
		super();
		icon = configureIcon();
		this.getChildren().add(pane);
		setUpPane();
	}
	
	private void setUpPane() {
		
	}

	private Image configureIcon() {
		try {
			return getImageFromAssetName("options.png");
		} catch (Exception e) {
			return null; 
		}
	}
	
	@Override
	public Image getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return OPTIONS;
	}

}
