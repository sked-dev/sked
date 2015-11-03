package edu.bsu.sked.model;

import java.time.LocalDate;

import org.junit.*;

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
	public void AssignmentStartDateIs29Feb2160() {
		LocalDate testDate = LocalDate.of(2014, 3, 29);
		Assert.assertEquals(testDate, finalProject.getStartDate());
	}

	@Test(expected = IllegalStateException.class)
	public void builderWithUnsetNameThrowsIllegalStateException() {
		Assignment.Builder b = new Assignment.Builder();
		b.andDueDate(LocalDate.of(2015, 12, 31))//
		.andStartDate(LocalDate.of(2015, 3, 29))//
		.build();
	}
	
	@Test(expected = IllegalStateException.class)
	public void builderWithUnsetDueDateThrowsIllegalStateException() {
		Assignment.Builder//
		.withName("Final Project")//
		.andStartDate(LocalDate.of(2015, 3, 29))
		.build();
	}

}
