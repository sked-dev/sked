package edu.bsu.sked.view;



import edu.bsu.sked.model.Assignment;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ProgressBar {

	Assignment assignment;
	private static final double RECTANGLE_ARC = 10.0;
	private static final double RECTANGLE_ANCHOR = 20.0;
	private double CONTAINER_WIDTH = 100.0;
	

	Rectangle progressBarContainer = new Rectangle(CONTAINER_WIDTH,20);
	Rectangle actualProgressBar = new Rectangle(CONTAINER_WIDTH,20);
	BorderPane pane = new BorderPane();
	Label percentLabel = new Label();
	double actualProgress = CONTAINER_WIDTH/2;
	double expectedProgress = CONTAINER_WIDTH;
	


	public  ProgressBar(Assignment assignment){
		this.assignment = assignment;
		pane.setBottom(percentLabel);
		
		AnchorPane progressBarFrame = new AnchorPane(progressBarContainer);
		pane.setCenter(progressBarFrame);

		
		progressBarContainer.setFill(Color.ANTIQUEWHITE);
		progressBarContainer.setArcHeight(RECTANGLE_ARC);
		progressBarContainer.setArcWidth(RECTANGLE_ARC);
		AnchorPane.setTopAnchor(progressBarContainer, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(progressBarContainer, RECTANGLE_ANCHOR);


		pane.setCenter(new AnchorPane(actualProgressBar));
		actualProgressBar.setFill(Color.GREEN);
		actualProgressBar.setArcHeight(RECTANGLE_ARC);
		actualProgressBar.setArcWidth(RECTANGLE_ARC);
		AnchorPane.setTopAnchor(actualProgressBar, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(actualProgressBar, RECTANGLE_ANCHOR);
		progressCalculator();
		
		

	}
	private void progressCalculator(){
			actualProgress = assignment.getCompletionPercent() * actualProgressBar.getWidth();
		//	expectedProgress = assignment.getRelativeDateCompletionPercent();
			actualProgressBar.setWidth(expectedProgress);
			percentLabel.setText(widthCheck());

	}
	private String widthCheck() {
		if(actualProgress >= progressBarContainer.getWidth()){
			actualProgressBar.setFill(Color.GRAY);
			return "Assignment Complete!";
		}
		if(expectedProgress > actualProgress){
			actualProgressBar.setFill(Color.RED);
			return "You are behind!";
		}
		if(expectedProgress < actualProgress){
			actualProgressBar.setFill(Color.GREEN);
			return "You are ahead!";
		}
		return "";

	
	}
	public BorderPane getPaneasNode(){
		return pane;
	}
}
