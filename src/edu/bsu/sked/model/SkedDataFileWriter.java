package edu.bsu.sked.model;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class SkedDataFileWriter {
	
	private final SkedDataFile file;
	
	public SkedDataFileWriter(SkedDataFile file) {
		this.file = file;
	}
	
	public void write(SkedDataContainer data) throws IOException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(data);
		writeToFile(jsonString);
	}
	
	private void writeToFile(String string) throws IOException {
		try (FileWriter writer = new FileWriter(file.getFile())) {
			writer.write(string);
		}
	}
}
