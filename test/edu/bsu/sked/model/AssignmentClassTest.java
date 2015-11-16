package edu.bsu.sked.model;

import org.junit.*;

public class AssignmentClassTest {
	
	@Test
	public void testClassIsClas101() {
		AssignmentClass assignmentClass = new AssignmentClass("CLAS 101");
		Assert.assertEquals(assignmentClass.getName(), "CLAS 101");
	}
	
	@Test
	public void testClassIsEd200() {
		AssignmentClass assignmentClass = new AssignmentClass("ED 200");
		Assert.assertEquals(assignmentClass.getName(), "ED 200");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyClassNameThrowsIllegalArgumentException() {
		new AssignmentClass("");
	}
	
	@Test
	public void testHardClassIsPrioritized() {
		AssignmentClass hardClass = new AssignmentClass("HARD 999");
		Assert.assertFalse(hardClass.isPrioritized());
		hardClass.setPrioritized(true);
		Assert.assertTrue(hardClass.isPrioritized());
	}
	
}
