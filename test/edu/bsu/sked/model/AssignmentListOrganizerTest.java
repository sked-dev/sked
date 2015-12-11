package edu.bsu.sked.model;

import java.util.List;

import org.junit.*;

public class AssignmentListOrganizerTest {
	
	private SkedDataContainer data;
	private List<Assignment> oldList;
	private AssignmentListOrganizer organizer;
	
	@Before
	public void initializeSkedData() throws Exception {
		SkedDataFile file = new SkedDataFile(getClass().getResource("TestSkedFile.sked").getFile());
		data = file.load();
		oldList = data.getAssignments();
		organizer = new AssignmentListOrganizer(oldList);
	}
	
	@Test
	public void testNewOrganizerReturnsUnmodifiedList() {
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(oldList, newList);
	}
	
	@Test
	public void testNewOrganizerDoesNotModifyOldList() {
		List<Assignment> newList = organizer.organize();
		oldList.remove(2);
		oldList.add(2, oldList.remove(0));
		Assert.assertNotEquals(oldList, newList);
	}
	
	@Test
	public void testPriorityFilterReturnsOnlyPrioritizedAssignments() {
		organizer.setFilterByCoursePriority(true);
		List<Assignment> newList = organizer.organize();
		for (Assignment assignment : newList) {
			Assert.assertTrue(assignment.getCourse().isPrioritized());
		}
	}
	
	@Test
	public void testDefaultSortingMethodDoesNotModifyList() {
		organizer.setSortingMethod(AssignmentSortingMethod.DEFAULT);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Mini assignment");
		Assert.assertEquals(newList.get(1).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(2).getName(), "Presentation");
		Assert.assertEquals(newList.get(3).getName(), "On track project");
		Assert.assertEquals(newList.get(4).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(5).getName(), "Medium assignment (difficult done)");
	}
	
	@Test
	public void testNameSortingMethodSortsByName() {
		organizer.setSortingMethod(AssignmentSortingMethod.NAME);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(1).getName(), "Medium assignment (difficult done)");
		Assert.assertEquals(newList.get(2).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(3).getName(), "Mini assignment");
		Assert.assertEquals(newList.get(4).getName(), "On track project");
		Assert.assertEquals(newList.get(5).getName(), "Presentation");
	}
	
	@Test
	public void testDueDateSortingMethodSortsByDueDate() {
		organizer.setSortingMethod(AssignmentSortingMethod.DUE_DATE);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Presentation");
		Assert.assertEquals(newList.get(1).getName(), "Mini assignment");
		Assert.assertEquals(newList.get(2).getName(), "Medium assignment (difficult done)");
		Assert.assertEquals(newList.get(3).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(4).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(5).getName(), "On track project");
	}
	
	@Test
	public void testStartDateSortingMethodSortsByStartDate() {
		organizer.setSortingMethod(AssignmentSortingMethod.START_DATE);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(1).getName(), "On track project");
		Assert.assertEquals(newList.get(2).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(3).getName(), "Presentation");
		Assert.assertEquals(newList.get(4).getName(), "Medium assignment (difficult done)");
		Assert.assertEquals(newList.get(5).getName(), "Mini assignment");
	}
	
	@Test
	public void testCourseSortingMethodSortsByCourseName() {
		organizer.setSortingMethod(AssignmentSortingMethod.COURSE);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Mini assignment");
		Assert.assertEquals(newList.get(1).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(2).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(3).getName(), "Medium assignment (difficult done)");
		Assert.assertEquals(newList.get(4).getName(), "Presentation");
		Assert.assertEquals(newList.get(5).getName(), "On track project");
	}
	
	@Test
	public void testCompletionSortingMethodSortsByCompletionPercent() {
		organizer.setSortingMethod(AssignmentSortingMethod.COMPLETION);
		List<Assignment> newList = organizer.organize();
		Assert.assertEquals(newList.get(0).getName(), "Mini assignment");
		Assert.assertEquals(newList.get(1).getName(), "Presentation");
		Assert.assertEquals(newList.get(2).getName(), "Big procrastination project");
		Assert.assertEquals(newList.get(3).getName(), "Medium assignment (easy done)");
		Assert.assertEquals(newList.get(4).getName(), "On track project");
		Assert.assertEquals(newList.get(5).getName(), "Medium assignment (difficult done)");
	}
}
