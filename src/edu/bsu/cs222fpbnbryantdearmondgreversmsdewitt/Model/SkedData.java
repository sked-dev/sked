package edu.bsu.cs222fpbnbryantdearmondgreversmsdewitt.Model;

public class SkedData {

		private final AssignmentList assignments;
		
		private SkedData(AssignmentList a) {
			assignments = a;
		}
		
		public static SkedData loadSavedUser() {
			AssignmentList assignments = new AssignmentList();
			return new SkedData(assignments);
		}
		
		public static boolean savedUserExists() {
			return false;
		}
		
		public void saveUser() {
			return;
		}
		
}
