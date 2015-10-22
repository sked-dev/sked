package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

import java.time.LocalDate;

import org.junit.*;

import edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model.*;

public class AssignmentTest {
	private Assignment finalProject = Assignment.Builder//
			.withName("Final Project")//
			.andDueDate(LocalDate.of(2015, 12, 31))//
			.andStartDate(LocalDate.of(2014, 3, 29))//
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
	@Test
	public void AssignmentStartDateIs29Feb2160(){
		LocalDate testDate = LocalDate.of(2014, 3, 29);
		Assert.assertEquals(testDate, finalProject.getStartDate());
	}

}
