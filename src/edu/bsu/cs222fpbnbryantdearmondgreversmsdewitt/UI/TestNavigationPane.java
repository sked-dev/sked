package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.UI;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class TestNavigationPane extends NavigationTargetPane {
	
	private final String label;
	private final Image icon = configureIcon();
	
	public TestNavigationPane(String label) {
		super();
		this.label = label;
		this.getChildren().add(new Label(label));
	}
	
	private Image configureIcon() {
		try {
			return getImageFromAssetName("assignmentIcon.png");
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
		return label;
	}

}
