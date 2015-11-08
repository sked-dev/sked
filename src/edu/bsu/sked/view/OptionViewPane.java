package edu.bsu.sked.view;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class OptionViewPane implements NavigationTarget {
	private final Image icon;
	private static final String OPTIONS = "Options";
	private final BorderPane pane = new BorderPane();
	
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
	public BorderPane getNode() {
		return pane;
	}

}
