package edu.bsu.sked.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class SkedDataFileReader {

	private final File file;
	private final Gson gson = new Gson();
	private List<Course> courses;
	private List<Assignment> assignments;

	public SkedDataFileReader(String path) throws FileNotFoundException {
		this(new File(path));
	}

	public SkedDataFileReader(File skedFile) throws FileNotFoundException {
		if (!skedFile.exists()) {
			throw new FileNotFoundException();
		}
		file = skedFile;
	}

	public SkedDataContainer read() throws IOException {
		JsonObject dataJson = getJsonObject();
		courses = getCourses(dataJson);
		assignments = getAssignments(dataJson);
		SkedDataContainer.Builder dataBuilder = gson.fromJson(dataJson, SkedDataContainer.Builder.class);
		dataBuilder.withCourses(courses);
		dataBuilder.withAssignmentList(assignments);
		return dataBuilder.build();
	}

	private List<Course> getCourses(JsonObject json) {
		JsonArray courses = json.remove("courses").getAsJsonArray();
		return gson.fromJson(courses, new TypeToken<ArrayList<Course>>(){}.getType());
		
	}
	
	private List<Assignment> getAssignments(JsonObject json) {
		List<Assignment> assignmentList = new ArrayList<>();
		Assignment newAssignment;
		JsonObject conversion;
		Course course;
		int courseIndex;
		JsonArray assignments = json.remove("assignments").getAsJsonArray();
		for (JsonElement assignmentJson : assignments) {
				conversion = assignmentJson.getAsJsonObject();
				if (conversion.has("courseIndex")) {
					courseIndex = conversion.remove("courseIndex").getAsInt();
					course = courses.get(courseIndex);
				} else
					course = null;
				newAssignment = gson.fromJson(assignmentJson, Assignment.class);
				newAssignment.setCourse(course);
				assignmentList.add(newAssignment);
		}
		return assignmentList;
	}

	private JsonObject getJsonObject() throws FileNotFoundException, IOException {
		FileReader jsonFileReader = makeFileReader();
		String json = readFile(jsonFileReader);
		JsonObject dataJson = new JsonParser().parse(json).getAsJsonObject();
		return dataJson;
	}

	private FileReader makeFileReader() throws FileNotFoundException {
		return new FileReader(file);
	}
	
	private String readFile(FileReader reader) throws IOException {
		int buffer = reader.read();
		String string = "";
		while (buffer != -1) {
			string += (char) buffer;
			buffer = reader.read();
		}
		return string;
	}
}
