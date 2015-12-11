package edu.bsu.sked.model;

import java.io.File;
import java.io.IOException;

public class SkedDataFile {

	public final String path;
	
	public SkedDataFile(String path) {
		this.path = path;
	}
	
	public SkedDataFile() {
		this("sked.json");
	}

	public SkedDataContainer load() throws SkedDataReadFailedException {
		if (!exists()) {
			return safelyInitialize();
		}
		SkedDataFileReader reader = createReader();
		try {
			return reader.read();
		} catch (IOException e) {
			throw new SkedDataReadFailedException("The file exists but could not be read.");
		}
	}

	public boolean exists() {
		File skedFile = getFile();
		return skedFile.exists();
	}
	
	public File getFile() {
		return new File(path);
	}
	
	private SkedDataContainer safelyInitialize() throws SkedDataReadFailedException {
		try {
			return initialize();
		} catch (SkedDataWriteFailedException e) {
			throw new SkedDataReadFailedException("Could not create a new SKED data file.  " + e.getMessage());
		}
	}

	private SkedDataContainer initialize() throws SkedDataWriteFailedException {
		SkedDataContainer data = SkedDataContainer.initialize();
		write(data);
		return data;
	}
	
	public void write(SkedDataContainer data) throws SkedDataWriteFailedException {
		try {
			new SkedDataFileWriter(this).write(data);
		} catch (IOException e) {
			throw new SkedDataWriteFailedException(e.getMessage());
		}
	}
	
	private SkedDataFileReader createReader() throws SkedDataReadFailedException {
		try {
			SkedDataFileReader reader = new SkedDataFileReader(getFile());
			return reader;
		} catch (IOException e) {
			throw new SkedDataReadFailedException(e.getMessage());
		}
	}
	
	public void delete() {
		if (exists()) {
			getFile().delete();
		}
	}
	

}
