package main.java.com.seitptt.model;

import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.personnel.Administrator;
import main.java.com.seitptt.model.personnel.ClassDirector;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.personnel.PTTDirector;
import main.java.com.seitptt.model.personnel.Teacher;
import main.java.com.seitptt.model.processes.Classes;
import main.java.com.seitptt.model.processes.ListOfClasses;
import main.java.com.seitptt.model.processes.ListOfSemesters;
import main.java.com.seitptt.model.processes.ListOfTeachingRequests;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.Semester;
import main.java.com.seitptt.model.processes.TeachingRequest;
import main.java.com.seitptt.model.processes.TeachingRequirement;

/**
 * The core model class to handle model logic
 * @author Hope Elumeziem (2500799E)
 *
 */
public class Core {

	/**
	 * current semester selected
	 */
	private Semester currentSemester;

	/**
	 * current user selected
	 */
	private Employee currentUser;

	/**
	 * creates a new core
	 */
	public Core() {
		Database.LoadCaches();
	}

	/**
	 * used to check permission
	 * @param <T> generic class extends Employee
	 * @param classWithPermission class with the permission needed
	 */
	private <T extends Employee> void checkPermission(Class<T> classWithPermission) {
		if(this.getCurrentUser().getClass().equals(classWithPermission)) {
			return;
		}

		// if current user is not of the appropriate class, throw exception
		throw new RuntimeException("Sorry current user is not a " + classWithPermission.getName());
	}

	/**
	 * create and add teaching request to database
	 * @param numberOfTeachersNeeded represents the number of teachers needed
	 * @param classObj represents the class object
	 */
	public void createAndAddTeachingRequirement(int numberOfTeachersNeeded, Classes classObj) {
		this.checkPermission(ClassDirector.class);
		new TeachingRequirement(numberOfTeachersNeeded, classObj);
	}

	/**
	 * approve a teaching request
	 * @param tr teaching request to be approved
	 */
	public void approveTeachingRequest(TeachingRequest tr) {
		this.checkPermission(PTTDirector.class);
		tr.approve();
	}

	/**
	 * deny a teaching request
	 * @param tr teaching request to be denied
	 */
	public void denyTeachingRequest(TeachingRequest tr) {
		try {
			this.checkPermission(PTTDirector.class);
		} catch(RuntimeException e) {
			this.checkPermission(ClassDirector.class);
		}
		this.removeTeachingRequest(tr);
	}

	/**
	 * remove a teaching requirement
	 * @param tr teaching requirement to be removed
	 */
	public void removeTeachingRequirement(TeachingRequirement tr) {
		this.checkPermission(ClassDirector.class);
		Database.removeTeachingRequirementFromDB(tr);
	}

	/**
	 * remove a teaching request
	 * @param tr teaching request to be removed
	 */
	public void removeTeachingRequest(TeachingRequest tr) {
		try {
			this.checkPermission(PTTDirector.class);
		} catch(RuntimeException e) {
			this.checkPermission(ClassDirector.class);
		}
		Database.removeTeachingRequestFromDB(tr);
	}

	/**
	 * organise training
	 * @param t teacher to be trained
	 */
	public void organiseTraining(Teacher t) {
		this.checkPermission(Administrator.class);
		t.train();
	}

	/**
	 * create and add a teaching request
	 * @param t represents the teacher
	 * @param c represents the class
	 * @param tr represents the associated teaching requirement
	 */
	public void createAndAddTeachingRequest(Teacher t, Classes c, TeachingRequirement tr) {
		this.checkPermission(Administrator.class);
		new TeachingRequest(t, c, tr);
	}

	/**
	 * get the list of teaching requirements
	 * @return ListOfTeachingRequirements object representing the list of teaching requirements
	 */
	public ListOfTeachingRequirements getListOfTeachingRequirements() {
		try {
			this.checkPermission(Administrator.class);
		} catch(RuntimeException e) {
			this.checkPermission(ClassDirector.class);
		}
		return Database.getTeachingRequirementsFromDB();
	}

	/**
	 * get the list of teaching requests
	 * @return ListOfTeachingRequests object representing the list of teaching requests
	 */
	public ListOfTeachingRequests getListOfTeachingRequests() {
		this.checkPermission(PTTDirector.class);
		return Database.getTeachingRequestsFromDB();
	}

	/**
	 * get the list of semesters
	 * @return ListOfSemesters object representing the list of semesters
	 */
	public ListOfSemesters getListOfSemesters() {
		return Database.getSemestersFromDB();
	}

	/**
	 * get the list of teachers 
	 * @return ListOfEmployees object representing the list of teachers
	 */
	public ListOfEmployees getListOfTeachers() {
		this.checkPermission(Administrator.class);
		return Database.getEmployeesFromDB().getTeachers();
	}

	/**
	 * get current semester
	 * @return Semester object representing the current semester
	 */
	public Semester getCurrentSemester() {
		return currentSemester;
	}

	/**
	 * set current semester
	 * @param currentSemester Semester object representing semester
	 */
	public void setCurrentSemester(Semester currentSemester) {
		this.currentSemester = currentSemester;
	}

	/**
	 * get current user
	 * @return Employee object representing current user
	 */
	public Employee getCurrentUser() {
		return currentUser;
	}

	/**
	 * set current user. creates new user and sets as current user depending on the type of user needed.
	 * @param currentUserType string denoting the type of user
	 */
	public void setCurrentUser(String currentUserType) {
		if(currentUserType.equalsIgnoreCase("classdirector")) {
			this.currentUser = new ClassDirector("current", "user");
		}
		if(currentUserType.equalsIgnoreCase("pttdirector")) {
			this.currentUser = new PTTDirector("current", "user");
		}
		if(currentUserType.equalsIgnoreCase("administrator")) {
			this.currentUser = new Administrator("current", "user");
		}
	}

	/**
	 * get list of classes
	 * @return ListOfClasses object representing list of classes
	 */
	public ListOfClasses getListOfClasses() {
		return Database.getClassesFromDB();
	}

	public static void main(String[] args) {
		Database.LoadCaches();

		System.out.println("-------------------\nTEST LIST OF EMPLOYEES");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfEmployees listOfEmployees = Database.getEmployeesFromDB();
		for(Employee e : listOfEmployees) {
			System.out.print(e.getFirstName() + " ");
			System.out.print(e.getLastName() + "\n");
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("John Snow\n" + 
				"Cersei Lannister\n" + 
				"Ramsey Bolton\n" + 
				"Aria Stark\n" + 
				"Theon Greyjoy");
		System.out.println("-------------------\nEND\n-------------------");
		System.out.println();System.out.println();
		System.out.println("-------------------\nTEST LIST OF SEMESTERS");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfSemesters listOfSemesters = Database.getSemestersFromDB();
		for (Semester s : listOfSemesters) {
			System.out.println(s.getNumber() + " " + s.getYear());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("1 2020\n" + 
				"2 2020\n" + 
				"1 2021\n" + 
				"2 2021\n" + 
				"1 2022");
		System.out.println("-------------------\nEND\n-------------------");
		System.out.println();System.out.println();
		System.out.println("-------------------\nTEST LIST OF REQUIREMENTS");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfTeachingRequirements listOfTeachingRequirements = Database.getTeachingRequirementsFromDB();
		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements) {
			System.out.println(teachingRequirement.getId() + " " + teachingRequirement.getNumOfTeachers() + " " + teachingRequirement.getClassRef().getCode());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("1 5 AP49\n" + 
				"2 5 ADS24");
		System.out.println("-------------------\nEND\n-------------------");

		System.out.println();System.out.println();
		System.out.println("-------------------\nTEST LIST OF CLASSES");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfClasses listOfClasses = Database.getClassesFromDB();
		for(Classes classes : listOfClasses) {
			System.out.println(classes.getCode() + " " + classes.getName());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("AP49 Advanced programming\n" + 
				"SE12 Software engineering\n" + 
				"ADS24 Algorithms and data structures" + 
				"PG88 Programming 2" +
				"DB01 Database 2");
		System.out.println("-------------------\nEND\n-------------------");
		System.out.println();System.out.println();
		System.out.println("-------------------\nTEST LIST OF REQUESTS");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB();
		for(TeachingRequest teachingRequest : listOfTeachingRequests) {
			System.out.println(teachingRequest.getId() + " " + 
					teachingRequest.getTeacher().getFirstName() + " " + 
					teachingRequest.getClassRef().getCode() + " " + 
					teachingRequest.getTeachingRequirement().getId() + " " + 
					teachingRequest.isApproved());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("1 Aria AP49 1 false\n" + 
				"2 Theon SE12 1 false");
		System.out.println("-------------------\nEND\n-------------------");

		System.out.println();System.out.println();

		System.out.println("-------------------\nTEST ADD AND DELETE REQUIREMENTS");
		System.out.println("-------------------\nASSERT\n-------------------");
		TeachingRequirement testReq = new TeachingRequirement(5, new Classes("ADS24", "Algo_ds", new Semester(1, 2, 2020)));
		TeachingRequirement testReq2 = new TeachingRequirement(5, new Classes("ADS24", "Algo_ds", new Semester(1, 2, 2020)));
		Core core = new Core();
		core.setCurrentUser("classdirector");
		ListOfTeachingRequirements listOfTeachingRequirements2 = Database.getTeachingRequirementsFromDB();

		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements2) {
			System.out.println(teachingRequirement.getId() + " " + teachingRequirement.getNumOfTeachers() + " " + teachingRequirement.getClassRef().getCode());
		}
		core.removeTeachingRequirement(testReq);
		core.removeTeachingRequirement(testReq2);
		System.out.println("-------------------\nRESET TO NORMAL\n-------------------");
		listOfTeachingRequirements2 = Database.getTeachingRequirementsFromDB();
		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements2) {
			System.out.println(teachingRequirement.getId() + " " + teachingRequirement.getNumOfTeachers() + " " + teachingRequirement.getClassRef().getCode());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("4 3 SE12\n" + 
				"6 3 AP49\n" + 
				"8 1 ADS24\n" + 
				"9 1 SE12\n" + 
				"10 5 ADS24\n" + 
				"11 5 ADS24\n" + 
				"12 5 ADS24\n" + 
				"-------------------\n" + 
				"RESET TO NORMAL\n" + 
				"-------------------\n" + 
				"4 3 SE12\n" + 
				"6 3 AP49\n" + 
				"8 1 ADS24\n" + 
				"9 1 SE12\n" + 
				"10 5 ADS24");
		System.out.println("-------------------\nEND\n-------------------");

	}

}
