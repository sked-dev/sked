package edu.bsu.sked.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.*;

public class SkedDataFileTest {

	private final SkedDataFile tempFile = new SkedDataFile("res/user_TEMP.json");
	private final SkedDataFile unreadableFile = new SkedDataFile("res/user_UNREADABLE.json");
	private final SkedDataFile unwritableFile = new SkedDataFile("res/user_UNWRITEABLE.json");

	public SkedDataFile copySafeFile(String name) {
		File source = new File("res/" + name + ".json.safe");
		File destination = new File("res/" + name + ".json");
		try {
			if (destination.exists())
				destination.delete();
			Files.copy(source.toPath(), destination.toPath());
		} catch (IOException e) {
			Assume.assumeNoException("Could not create a temporary user file; skipping test.", e);
		}
		return new SkedDataFile(destination.getPath());
	}

	@Before
	public void deleteTempFile() {
		tempFile.delete();
	}

	@Test
	public void fileDoesNotExist() throws Exception {
		deleteTempFile();
		Assert.assertFalse(tempFile.exists());
	}

	@Test
	public void fileIsCreatedOnLoad() throws Exception {
		tempFile.load();
		Assert.assertTrue(tempFile.exists());
	}

	@Test
	public void unreadableFileThrowsSkedDataReadFailedException() throws Exception {
		Assume.assumeTrue(unreadableFile.exists());

		try {
			unreadableFile.load();
			Assert.fail("Expected exception " + SkedDataReadFailedException.class.getName() + " was not thrown.");
		} catch (Exception e) {
			Assert.assertEquals(SkedDataReadFailedException.class, e.getClass());
		}
	}

	@Test
	public void unwritableFileThrowsSkedDataWriteFailedException() throws Exception {
		Assume.assumeTrue(unwritableFile.exists());

		try {
			SkedData file = unwritableFile.load();
			unwritableFile.write(file);
			Assert.fail("Expected exception " + SkedDataWriteFailedException.class.getName() + " was not thrown.");
		} catch (Exception e) {
			Assert.assertEquals(SkedDataWriteFailedException.class, e.getClass());
		}
	}


}
