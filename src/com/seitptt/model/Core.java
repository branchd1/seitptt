package com.seitptt.model;

import java.util.ArrayList;

import com.seitptt.model.authentication.Auth;
import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
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
		
		
		
		System.out.println("-------------------\nASSERT\n-------------------");
		Employee employee = Auth.login("john_snow", "os3nwi332");
		System.out.println(employee.getFirstName() + " " + employee.getLastName());
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("John Snow");
		System.out.println("-------------------\nEND\n-------------------");
		
		
		
		System.out.println();System.out.println();
		
		
		
//		System.out.println("-------------------\nASSERT\n-------------------");
//		ArrayList<Semester> listOfSemesters = Database.getSemestersFromDB();
//		for (Semester s : listOfSemesters) {
//			System.out.println(s.getNumber() + " " + s.getYear());
//		}
//		System.out.println("-------------------\nEQUALS\n-------------------");
//		System.out.println("1 2020\n" + 
//				"2 2020\n" + 
//				"1 2021\n" + 
//				"2 2021\n" + 
//				"1 2022");
//		System.out.println("-------------------\nEND\n-------------------");
	}
	
	public void addTeachingRequirement(TeachingRequirement tr) {
		if (!(currentUser instanceof ClassDirector)) {
			throw new RuntimeException("Sorry current user is not a ClassDirector");
		}else {					
			PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
			tr.accept(visitor);			
		}
	}
	
	
	public ListOfTeachingRequirements getListOfTeachingRequirements() {
		if (!(currentUser instanceof ClassDirector)) {
			throw new RuntimeException("Sorry current user is not a ClassDirector");
		}else {
			return Database.getTeachingRequirementsFromDB();
		}
	}
	
	public ListOfSemesters getListOfSemesters() {
		return Database.getSemestersFromDB();
	}
	
	public Employee login(String username, String password) {
		return Auth.login(username, password);
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
}
