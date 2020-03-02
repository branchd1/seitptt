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
import com.seitptt.visitors.PrintToDatabaseVisitor;

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
		System.out.println("1 2 AP49");
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
				"ADS24 Algorithms and data structures");
		System.out.println("-------------------\nEND\n-------------------");
		System.out.println();System.out.println();
		System.out.println("-------------------\nTEST LIST OF REQUESTS");
		System.out.println("-------------------\nASSERT\n-------------------");
		ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB();
		for(TeachingRequest teachingRequest : listOfTeachingRequests) {
			System.out.println(teachingRequest.getId() + " " + teachingRequest.getTeacher().getFirstName() + " " + teachingRequest.getClassRef().getCode() + " " + 
					teachingRequest.getTeachingRequirement().getId() + " " + teachingRequest.isApproved());
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
		core.setCurrentUser(new ClassDirector("John", "Grisham"));
		ListOfTeachingRequirements listOfTeachingRequirements2 = Database.getTeachingRequirementsFromDB();
		
		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements2) {
			System.out.println(teachingRequirement.getId() + " " + teachingRequirement.getNumOfTeachers() + " " + teachingRequirement.getClassRef().getCode());
		}
		core.removeTeachingRequirement(testReq);
		core.removeTeachingRequirement(testReq2);
		listOfTeachingRequirements2 = Database.getTeachingRequirementsFromDB();
		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements2) {
			System.out.println(teachingRequirement.getId() + " " + teachingRequirement.getNumOfTeachers() + " " + teachingRequirement.getClassRef().getCode());
		}
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("1 5 AP49\n" + 
				"2 5 ADS24\n" + 
				"3 5 ADS24\n" + 
				"1 5 AP49");
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
		
		TeachingRequirement tr = new TeachingRequirement(numberOfTeachersNeeded, classObj);
		
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		tr.accept(visitor);
	}
	
	public void approveTeachingRequest(TeachingRequest tr) {
		//this.checkPermission(PTTDirector.class);
		tr.approve();
	}

	public void removeTeachingRequirement(TeachingRequirement tr) {
		//this.checkPermission(ClassDirector.class);
		Database.removeTeachingRequirementFromDB(tr);
	}
	
	public void removeTeachingRequest(TeachingRequest tr) {
		//this.checkPermission(ClassDirector.class);
		ListOfTeachingRequirements listOfTeachingRequirements = Database.getTeachingRequirementsFromDB().getAllRequirementsConnectedToARequest(tr);
		for(TeachingRequirement teachingRequirement : listOfTeachingRequirements) {
			Database.removeTeachingRequirementFromDB(teachingRequirement);
		}
		Database.removeTeachingRequestFromDB(tr);
	}

	public Employee findStaff(String username) {
		//this.checkPermission(Administrator.class);
		ListOfEmployees loE = Database.getEmployeesFromDB();
		return loE.find(username);
	}
	
	public void organiseTraining(Teacher t) {
		//this.checkPermission(Administrator.class);
		t.train();
	}
	
	public void createAndAddTeachingRequest(Teacher t, Classes c, TeachingRequirement tr) {
		//this.checkPermission(Administrator.class);
		TeachingRequest teachingRequest = new TeachingRequest(t, c, tr);
			
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		teachingRequest.accept(visitor);
	}

	public ListOfTeachingRequirements getListOfTeachingRequirements() {
		//this.checkPermission(ClassDirector.class);
		return Database.getTeachingRequirementsFromDB();
	}

	public ListOfSemesters getListOfSemesters() {
		return Database.getSemestersFromDB();
	}
	
	public ListOfEmployees getListOfTeachers() {
		//this.checkPermission(Administrator.class);
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

	public void setCurrentUser(Employee currentUser) {
		this.currentUser = currentUser;
	}
	
	public ListOfClasses getListOfClasses() {
		return Database.getClassesFromDB();
	}


}
