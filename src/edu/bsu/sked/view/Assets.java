package edu.bsu.sked.view;

import java.io.IOException;

import javafx.scene.image.Image;

public final class Assets {
	public static Image getImageFromAssetName(String name) throws IOException {
		return new Image(Assets.class.getResource("/assets/" + name).toString());
	}
}
