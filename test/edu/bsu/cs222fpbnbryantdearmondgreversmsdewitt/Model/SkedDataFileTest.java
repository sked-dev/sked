package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import org.junit.*;
import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;

public class SkedDataFileTest {

	private final Assignment testAssignment = Assignment.Builder//
			.withName("Final Project")//
			.andDueDate(LocalDate.of(2015, 12, 12))//
			.andStartDate(LocalDate.of(2015, 8, 1))//
			.build();
	private final Assignment testAddableAssignment = Assignment.Builder//
			.withName("Speech")//
			.andDueDate(LocalDate.of(2015, 10, 31))//
			.andStartDate(LocalDate.of(2015, 10, 1))//
			.build();
	private final UserName testName = UserName.Builder//
			.withFirstName("Austin")//
			.andLastName("DeArmond")//
			.build();

	private final SkedDataFile temp_file = new SkedDataFile("res/user_TEMP.json");
	private final SkedDataFile unreadable_file = new SkedDataFile("res/user_UNREADABLE.json");
	private final SkedDataFile unwritable_file = new SkedDataFile("res/user_UNWRITEABLE.json");

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
		temp_file.delete();
	}

	@Test
	public void fileDoesNotExist() throws Exception {
		deleteTempFile();
		Assert.assertFalse(temp_file.exists());
	}

	@Test
	public void fileIsCreatedOnLoad() throws Exception {
		temp_file.load();
		Assert.assertTrue(temp_file.exists());
	}

	@Test
	public void unreadableFileThrowsSkedDataReadFailedException() throws Exception {
		Assume.assumeTrue(unreadable_file.exists());

		try {
			unreadable_file.load();
			Assert.fail("Expected exception " + SkedDataReadFailedException.class.getName() + " was not thrown.");
		} catch (Exception e) {
			Assert.assertEquals(SkedDataReadFailedException.class, e.getClass());
		}
	}

	@Test
	public void unwritableFileThrowsSkedDataWriteFailedException() throws Exception {
		Assume.assumeTrue(unwritable_file.exists());

		try {
			SkedData file = unwritable_file.load();
			unwritable_file.write(file);
			Assert.fail("Expected exception " + SkedDataWriteFailedException.class.getName() + " was not thrown.");
		} catch (Exception e) {
			Assert.assertEquals(SkedDataWriteFailedException.class, e.getClass());
		}
	}

	@Test
	public void dataFileWithAddedAssignmentHasSameContentAsTestFile() throws Exception {
		SkedDataFile oldFile = copySafeFile("user_one_assignment");
		SkedData data = oldFile.load();
		data.getAssignments().add(testAddableAssignment);
		oldFile.write(data);

		assertFilesEqual(oldFile.getFile(), new File("res/user_two_assignments.json.safe"));
	}

	private void assertFilesEqual(File file1, File file2) throws Exception {
		try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
				BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
			int character1;
			int character2;
			while ((character1 = reader1.read()) != -1) {
				character2 = reader2.read();
				Assert.assertEquals(character1, character2);
			}
			Assert.assertEquals(reader2.read(), -1);
		}
	}

}
