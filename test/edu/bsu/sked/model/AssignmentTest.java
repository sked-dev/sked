package edu.bsu.sked.model;

import java.time.LocalDate;

import org.junit.*;

public class AssignmentTest {
	private Assignment finalProject = Assignment.Builder//
			.withName("Final Project")//
			.andDueDate(LocalDate.of(2015, 12, 31))//
			.andStartDate(LocalDate.of(2014, 3, 29))//
			.build();
	private Assignment clasHomework;
	private Course clas101 = new Course("CLAS 101");
	private Course clas202 = new Course("CLAS 202");
	
	private LocalDate testToday = LocalDate.of(2015, 1, 1);
	
	@Before
	public void configure() {
		clasHomework = Assignment.Builder//
				.withName("CLAS homework")//
				.andDueDate(LocalDate.of(2015, 12, 31))//
				.andStartDate(LocalDate.of(2014, 3, 29))//
				.andCourse(clas101)//
				.build();
	}

	@Test
	public void testAssignmentNameIsFinalProject() {
		String name = finalProject.getName();
		Assert.assertEquals(name, "Final Project");
	}

	@Test
	public void testAssignmentDueDateIs31Dec2015() {
		LocalDate testDate = LocalDate.of(2015, 12, 31);
		Assert.assertEquals(testDate, finalProject.getDueDate());
	}

	@Test
	public void testAssignmentStartDateIs29Feb2160() {
		LocalDate testDate = LocalDate.of(2014, 3, 29);
		Assert.assertEquals(testDate, finalProject.getStartDate());
	}

	@Test(expected = IllegalStateException.class)
	public void testBuilderWithUnsetNameThrowsIllegalStateException() {
		Assignment.Builder b = new Assignment.Builder();
		b.andDueDate(LocalDate.of(2015, 12, 31))//
				.andStartDate(LocalDate.of(2015, 3, 29))//
				.build();
	}

	@Test(expected = IllegalStateException.class)
	public void testBuilderWithUnsetDueDateThrowsIllegalStateException() {
		Assignment.Builder//
				.withName("Final Project")//
				.andStartDate(LocalDate.of(2015, 3, 29)).build();
	}
	
	@Test
	public void testAssignmentDueToday() {
		Assignment dueToday = Assignment.Builder//
				.withName("Due today")
				.andDueDate(LocalDate.of(2015, 1, 1))
				.andStartDate(LocalDate.of(2014, 12, 1))
				.build();
		String dueDateString = dueToday.generateRelativeDateString(testToday);
		Assert.assertEquals("Due today!", dueDateString);
	}
	
	@Test
	public void testAssignmentOverdue() {
		Assignment overdue = Assignment.Builder//
				.withName("Overdue")//
				.andDueDate(LocalDate.of(2014, 12, 31))
				.andStartDate(LocalDate.of(2014, 12, 1))
				.build();
		String dueDateString = overdue.generateRelativeDateString(testToday);
		Assert.assertEquals("1 day overdue.", dueDateString);
	}
	
	@Test
	public void testUpcomingAssignment() {
		Assignment upcoming = Assignment.Builder//
				.withName("Upcoming")//
				.andDueDate(LocalDate.of(2015, 1, 2))//
				.andStartDate(LocalDate.of(2014, 12, 1))//
				.build();
		String dueDateString = upcoming.generateRelativeDateString(testToday);
		Assert.assertEquals("Due in 1 day.", dueDateString);
	}
	
	@Test
	public void testAssignmentHasNoCourse() {
		Assert.assertNull(finalProject.getCourse());
		Assert.assertFalse(finalProject.hasCourse());
	}
	
	@Test
	public void testCourseIsClas101() {
		Assert.assertTrue(clasHomework.hasCourse());
		Assert.assertEquals(clasHomework.getCourse(), clas101);
	}
	
	@Test
	public void testCourseBecomesClas202() {
		clasHomework.setCourse(clas202);
		Assert.assertEquals(clasHomework.getCourse(), clas202);
	}

}
