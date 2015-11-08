package edu.bsu.sked.view;
import javafx.scene.Node;
import javafx.scene.image.Image;

public interface NavigationTarget {
	public Image getIcon();
	public String getLabel();
	public Node getNode();
}
