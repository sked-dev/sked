package edu.bsu.sked.model;

import java.util.List;

import org.junit.*;

public class AssignmentListOrganizerTest {
	
	SkedDataContainer data;
	List<Assignment> oldList;
	AssignmentListOrganizer organizer;
	
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
}
