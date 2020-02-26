package com.seitptt.model.authentication;

import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;

public class Auth {
	
	public static Employee login(String username, String password){
		Employee employee = null;
		
		final ListOfEmployees listOfEmployees = Database.getEmployeesFromDB();
		
		for(Employee tempEmployee : listOfEmployees) {
			final String tempUsername = tempEmployee.getUsername();
			final String tempPassword = tempEmployee.getPassword();
			
			if(tempUsername.contentEquals(username) && tempPassword.contentEquals(password)) {
				employee = tempEmployee;
			}
		}
		
		return employee;
	}
}
