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
	Rectangle progressBar = new Rectangle(50,20);
	BorderPane pane = new BorderPane();
	Label percentLabel = new Label();
	double actualProgress;
	double expectedProgress;
	


	public  ProgressBar(Assignment assignment){
	
		this.assignment = assignment;
		pane.setBottom(percentLabel);
		
		AnchorPane progressBarFrame = new AnchorPane(progressBarContainer);
		pane.setCenter(progressBarFrame);

		
		progressBarContainer.setFill(Color.CADETBLUE);
		progressBarContainer.setArcHeight(RECTANGLE_ARC);
		progressBarContainer.setArcWidth(RECTANGLE_ARC);
		AnchorPane.setTopAnchor(progressBarContainer, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(progressBarContainer, RECTANGLE_ANCHOR);


		pane.setCenter(new AnchorPane(progressBar));
		progressBar.setFill(Color.GREEN);
		progressBar.setArcHeight(RECTANGLE_ARC);
		progressBar.setArcWidth(RECTANGLE_ARC);
		AnchorPane.setTopAnchor(progressBar, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(progressBar, RECTANGLE_ANCHOR);
		progressCalculator();
		
		

	}
	private void progressCalculator(){
			actualProgress = assignment.getCompletionPercent() * progressBarContainer.getWidth();
			expectedProgress = (assignment.getDaysPassedPercent()) * progressBarContainer.getWidth();
			progressBar.setWidth(actualProgress);
			percentLabel.setText(widthCheck());

	}
	private String widthCheck() {
		while(actualProgress >= progressBarContainer.getWidth()){
			progressBar.setFill(Color.GRAY);
			return "Assignment Complete!";
		}
		while(expectedProgress > actualProgress){
			progressBar.setFill(Color.RED);
			return "You are behind!";
		}
		while(expectedProgress < actualProgress){
			progressBar.setFill(Color.GREEN);
			return "You are ahead!";
		}
		return "You are on track!";

	
	}
	public BorderPane getPaneasNode(){
		return pane;
	}
}
