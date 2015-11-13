package edu.bsu.sked.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ProgressBar{
	
	private static final double RECTANGLE_ARC = 10.0;
	private static final double RECTANGLE_ANCHOR = 20.0;
	private static final double INCREMENT = 10.0;
	
	GridPane primaryPane = new GridPane();
	Rectangle rectangle = new Rectangle(100,20);
	Rectangle progressBar = new Rectangle(0,20);
	Button button = new Button();
	Label percentLabel = new Label();
	


	public GridPane buildProgressBar() {
		percentLabel.setText("0.0%");
		AnchorPane progressBarFrame = new AnchorPane(rectangle);
		button.setText("Make Progress!");
		button.setTextFill(Color.GREEN);
		button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
			refreshProgress();
				
			}
		});
		
		
		
		primaryPane.add(button, 1,15);
		primaryPane.add(percentLabel, 1,3);
		primaryPane.add(progressBarFrame, 0, 1);
		AnchorPane.setTopAnchor(rectangle, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(rectangle, RECTANGLE_ANCHOR);
		rectangle.setFill(Color.ANTIQUEWHITE);
		rectangle.setArcHeight(RECTANGLE_ARC);
		rectangle.setArcWidth(RECTANGLE_ARC);
		progressBar.setFill(Color.GREEN);
		progressBar.setArcHeight(RECTANGLE_ARC);
		progressBar.setArcWidth(RECTANGLE_ARC);
		progressBarFrame.getChildren().add(new AnchorPane(progressBar));
		AnchorPane.setTopAnchor(progressBar, RECTANGLE_ANCHOR);
		AnchorPane.setLeftAnchor(progressBar, RECTANGLE_ANCHOR);
		return primaryPane;
		
	}
	private void refreshProgress(){
		if(maxWidthCheck()==false){
			Double width = progressBar.getWidth(); 
			width = getNewWidth();
			width = width + INCREMENT;
			progressBar.setWidth(width);
			percentLabel.setText(getNewWidth() + "%");
			System.out.println(getNewWidth() + "%");
		}if(maxWidthCheck()==true){
			percentLabel.setText("Task Completed!");
			System.out.println("Task Completed!");
		}

	}
	private boolean maxWidthCheck() {
		while(progressBar.getWidth() < rectangle.getWidth()){
		return false;
		}
		progressBar.setFill(Color.GRAY);
		return true;
	
	}
	private double getNewWidth(){
		return progressBar.getWidth();
	}
	
}
