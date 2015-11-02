package edu.bsu.sked.view;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class NavigationTargetPane extends Pane {
	public abstract Image getIcon();
	public abstract String getLabel();
	
	protected Image getImageFromAssetName(String name) throws IOException {
		return new Image(getClass().getResource("/assets/" + name).toString());
	}
}
