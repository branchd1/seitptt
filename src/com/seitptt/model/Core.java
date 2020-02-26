package com.seitptt.model;

import java.util.ArrayList;

import com.seitptt.model.authentication.Auth;
import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.Semester;

public class Core {
	
	private Semester currentSemester;
	
	public Core() {
		Database.LoadDatabase();
	}
	
	public Employee login(String username, String password) {
		final Employee employee = Auth.login(username, password);
		return employee;
	}
	
	public static void main(String[] args) {
		Database.LoadDatabase();
		
		
		
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
		
		
		
		System.out.println("-------------------\nASSERT\n-------------------");
		ArrayList<Semester> listOfSemesters = Database.getSemestersFromDB();
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
	}

	public Semester getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(Semester currentSemester) {
		this.currentSemester = currentSemester;
	}
}
