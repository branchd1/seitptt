package com.seitptt.model;

import com.seitptt.model.authentication.Auth;
import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;

public class Core {
	public static void main(String[] args) {
		Database.LoadDatabase();
		
		ListOfEmployees listOfEmployees = Database.getEmployeesFromDB();
		
		System.out.println("-------------------\nASSERT\n-------------------");
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
		System.out.println();
		System.out.println("-------------------\nASSERT\n-------------------");
		Employee employee = Auth.login("john_snow", "os3nwi332");
		System.out.println(employee.getFirstName() + " " + employee.getLastName());
		System.out.println("-------------------\nEQUALS\n-------------------");
		System.out.println("John Snow");
		System.out.println("-------------------\nEND\n-------------------");
	}
}
