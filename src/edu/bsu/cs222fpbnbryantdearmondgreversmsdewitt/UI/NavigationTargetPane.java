package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class NavigationTargetPane extends Pane {
	public abstract Image getIcon();
	public abstract String getLabel();
	
	protected Image getImageFromAssetName(String name) throws IOException {
		File file = new File("assets/" + name);
		return new Image(file.toURI().toURL().toString());
	}
}
