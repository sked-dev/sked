package edu.bsu.sked.model;

import org.junit.*;

public class CourseTest {
	
	@Test
	public void testClassIsClas101() {
		Course course = new Course("CLAS 101");
		Assert.assertEquals(course.getName(), "CLAS 101");
	}
	
	@Test
	public void testClassIsEd200() {
		Course course = new Course("ED 200");
		Assert.assertEquals(course.getName(), "ED 200");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyClassNameThrowsIllegalArgumentException() {
		new Course("");
	}
	
	@Test
	public void testHardClassIsPrioritized() {
		Course hardClass = new Course("HARD 999");
		Assert.assertFalse(hardClass.isPrioritized());
		hardClass.setPrioritized(true);
		Assert.assertTrue(hardClass.isPrioritized());
	}
	
	@Test
	public void testClassBecomesEd299() {
		Course course = new Course("ED 200");
		course.setName("ED 299X");
		String courseName = course.getName();
		Assert.assertEquals("ED 299X", courseName);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyRenameThrowsIllegalArgumentException() {
		Course course = new Course("CLAS 101");
		course.setName("");
	}
	
}
