package edu.bsu.sked.model;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonAssignmentSerializer implements JsonSerializer<Assignment> {
	
	private SkedDataContainer data;
	
	public GsonAssignmentSerializer(SkedDataContainer data) {
		this.data = data;
	}

	@Override
	public JsonElement serialize(Assignment src, Type typeOfSrc, JsonSerializationContext context) {
		Gson gson = new Gson();
		JsonObject assignment = (JsonObject) gson.toJsonTree(src);
		if (src.getCourse() != null) {
			assignment.addProperty("courseIndex", data.getCourses().indexOf(src.getCourse()));
		}
		return assignment;
	}

}
