package edu.bsu.sked.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

public class SkedDataFileReader {

	private final File file;

	public SkedDataFileReader(String path) throws FileNotFoundException {
		this(new File(path));
	}

	public SkedDataFileReader(File skedFile) throws FileNotFoundException {
		if (!skedFile.exists()) {
			throw new FileNotFoundException();
		}
		file = skedFile;
	}

	public SkedDataContainer read() throws FileNotFoundException {
		FileReader jsonFileReader = makeFileReader();
		Gson gson = new Gson();
		return gson.fromJson(jsonFileReader, SkedDataContainer.class);
	}

	private FileReader makeFileReader() throws FileNotFoundException {
		return new FileReader(file);
	}

}
