package edu.bsu.sked.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import edu.bsu.sked.model.Course;
import edu.bsu.sked.model.SkedDataWriteFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CourseViewPane extends BorderPane implements Initializable, NavigationTarget {
	
	@FXML private VBox courseOverviewBox;
	@FXML private Button addCourseButton;
	@FXML private TextField courseNameField;
	private Image icon = configureIcon();
	private Set<Course> courses;
	private List<CourseOverview> overviews = new ArrayList<>();
	
	public CourseViewPane() {
		super();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CoursePane.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Image configureIcon() {
		try {
			return Assets.getImageFromAssetName("ClassesIcon.png");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Image getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return "Courses";
	}

	@Override
	public Node getNode() {
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configure();
		refresh();
	}
	
	private void configure() {
		courseNameField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				addCourseButton.setDisable(courseNameField.getText().isEmpty());
			}
			
		});
	}

	public void refresh() {
		courses = SkedApplication.getSkedData().getCourses();
		overviews.clear();
		courseOverviewBox.getChildren().clear();
		for (Course course : courses) {
			overviews.add(new CourseOverview(course, this));
		}
		courseOverviewBox.getChildren().addAll(overviews);
	}
	
	@FXML
	private void addCourse() {
		Course course = new Course(courseNameField.getText());
		SkedApplication.getSkedData().getCourses().add(course);
		refresh();
		courseNameField.clear();
		save();
	}
	
	private void save() {
		try {
			SkedApplication.saveSkedData();
		} catch (SkedDataWriteFailedException e) {
			//TODO
			e.printStackTrace();
		}
	}
	
	

}
