package edu.bsu.sked.view;

import javafx.scene.image.Image;

public final class Assets {
	
	public static final Image IC_FOLDER_OPEN = getImageFromAssetName("ic_folder_open_black_64px.png");
	public static final Image IC_NOTE_ADD = getImageFromAssetName("ic_note_add_black_64px.png");
	
	public static Image getImageFromAssetName(String name) {
		try {
			return new Image(Assets.class.getResource("/assets/" + name).toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
