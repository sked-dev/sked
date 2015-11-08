package edu.bsu.sked.model;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;

import edu.bsu.sked.model.Subtask.Difficulty;

public class AssignmentTest {
	private Assignment finalProject;

	private LocalDate testToday = LocalDate.of(2015, 1, 1);

	@Before
	public void initializeFinalProject() {
		Subtask difficultSubtask = Subtask.Builder//
				.withDescription("Difficult subtask")//
				.andDifficulty(Difficulty.DIFFICULT)//
				.build();
		Subtask completeDifficultSubtask2 = Subtask.Builder//
				.withDescription("Another difficulty subtask")//
				.andDifficulty(Difficulty.DIFFICULT)//
				.andCompletion(true)//
				.build();
		Subtask easySubtask1 = Subtask.Builder//
				.withDescription("Easy task")//
				.andDifficulty(Difficulty.EASY)//
				.build();
		Subtask completeNormalSubtask = Subtask.Builder//
				.withDescription("Normal subtask")//
				.andDifficulty(Difficulty.NORMAL)//
				.andCompletion(true)//
				.build();
		Subtask easySubtask2 = Subtask.Builder//
				.withDescription("This is done.")//
				.andDifficulty(Difficulty.EASY)//
				.build();
		ArrayList<Subtask> subtasks = new ArrayList<Subtask>();
		subtasks.add(difficultSubtask);
		subtasks.add(completeDifficultSubtask2);
		subtasks.add(easySubtask1);
		subtasks.add(completeNormalSubtask);
		subtasks.add(easySubtask2);
		finalProject = Assignment.Builder//
				.withName("Final Project")//
				.andDueDate(LocalDate.of(2015, 12, 31))//
				.andStartDate(LocalDate.of(2014, 3, 29))//
				.andSubtasks(subtasks)//
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
				.withName("Due today").andDueDate(LocalDate.of(2015, 1, 1)).andStartDate(LocalDate.of(2014, 12, 1))
				.build();
		String dueDateString = dueToday.generateRelativeDateString(testToday);
		Assert.assertEquals("Due today!", dueDateString);
	}

	@Test
	public void testAssignmentOverdue() {
		Assignment overdue = Assignment.Builder//
				.withName("Overdue")//
				.andDueDate(LocalDate.of(2014, 12, 31)).andStartDate(LocalDate.of(2014, 12, 1)).build();
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
	public void testAssignmentDifficultyIsTwelve() {
		Assert.assertEquals(12, finalProject.getWeightedDifficulty());
	}

	@Test
	public void testAssignmentCompletionIsFive() {
		Assert.assertEquals(6, finalProject.getWeightedCompletion());
	}
	
	@Test
	public void testAssignmentIsHalfwayDone() {
		Assert.assertEquals(0.5, finalProject.getCompletionPercent(), 0.01);
	}
}
