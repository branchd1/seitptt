package com.seitptt.model;

import com.seitptt.model.database.Database;

import com.seitptt.model.personnel.Administrator;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.PTTDirector;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequests;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
import com.seitptt.model.processes.TeachingRequest;
import com.seitptt.model.processes.TeachingRequirement;

public class Core {

	private Semester currentSemester;
	private Employee currentUser;


	public Core() {
		Database.LoadCaches();
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

	private <T extends Employee> void checkPermission(Class<T> classWithPermission) {
		if(this.getCurrentUser().getClass().equals(classWithPermission)) {
			return;
		}
		throw new RuntimeException("Sorry current user is not a " + classWithPermission.getName());
	}


	public void createAndAddTeachingRequirement(int numberOfTeachersNeeded, Classes classObj) {
		this.checkPermission(ClassDirector.class);
		new TeachingRequirement(numberOfTeachersNeeded, classObj);
	}

	public void approveTeachingRequest(TeachingRequest tr) {
		this.checkPermission(PTTDirector.class);
		tr.approve();
	}

	public void removeTeachingRequirement(TeachingRequirement tr) {
		this.checkPermission(ClassDirector.class);
		Database.removeTeachingRequirementFromDB(tr);
	}

	public void removeTeachingRequest(TeachingRequest tr) {
		this.checkPermission(ClassDirector.class);
		Database.removeTeachingRequestFromDB(tr);
	}

	public void organiseTraining(Teacher t) {
		this.checkPermission(Administrator.class);
		t.train();
	}

	public void createAndAddTeachingRequest(Teacher t, Classes c, TeachingRequirement tr) {
		this.checkPermission(Administrator.class);
		new TeachingRequest(t, c, tr);
	}

	public ListOfTeachingRequirements getListOfTeachingRequirements() {
		this.checkPermission(ClassDirector.class);
		return Database.getTeachingRequirementsFromDB();
	}

	public ListOfSemesters getListOfSemesters() {
		return Database.getSemestersFromDB();
	}

	public ListOfEmployees getListOfTeachers() {
		this.checkPermission(Administrator.class);
		return Database.getEmployeesFromDB().getTeachers();
	}

	public Semester getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(Semester currentSemester) {
		this.currentSemester = currentSemester;
	}

	public Employee getCurrentUser() {
		return currentUser;
	}

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

	public ListOfClasses getListOfClasses() {
		return Database.getClassesFromDB();
	}


}
