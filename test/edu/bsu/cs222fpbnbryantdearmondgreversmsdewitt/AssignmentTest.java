package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt;

import java.time.LocalDate;

import org.junit.*;
import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.*;

public class AssignmentTest {
	private Assignment finalProject = Assignment.Builder//
			.withName("Final Project")//
			.andDueDate(LocalDate.of(2015, 12, 31))//
			.build();

	@Test
	public void AssignmentNameIsFinalProject() {
		String name = finalProject.getName();
		Assert.assertEquals(name, "Final Project");
	}

	@Test
	public void AssignmentDueDateIs31Dec2015() {
		LocalDate testDate = LocalDate.of(2015, 12, 31);
		Assert.assertEquals(testDate, finalProject.getDueDate());
	}

}
