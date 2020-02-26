package com.seitptt.model.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.seitptt.model.personnel.Administrator;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.PTTDirector;
import com.seitptt.model.personnel.Teacher;

public class Database {
	public static void setEmployeesCacheFromDB() {
		final String dbFile = "employees.txt";
		final ListOfEmployees listOfEmployees = new ListOfEmployees();
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		s.nextLine();
		
		while(s.hasNextLine()) {
			while(s.hasNext()) {
				final String type = s.next();
				final String username = s.next();
				final String password = s.next();
				final String firstName = s.next();
				final String lastName = s.next();
				
				Employee employee = null;
				
				if (type.contains("ptt")) {
					employee = new PTTDirector(firstName, lastName);
					employee.setUsername(username);
					employee.setPassword(password);
				}
				
				if (type.contains("class")) {
					employee = new ClassDirector(firstName, lastName);
					employee.setUsername(username);
					employee.setPassword(password);
				}
				
				if (type.contains("admin")) {
					employee = new Administrator(firstName, lastName);
					employee.setUsername(username);
					employee.setPassword(password);
				}
				
				if (type.contains("teacher")) {
					employee = new Teacher(firstName, lastName);
					employee.setUsername(username);
					employee.setPassword(password);
				}
				
				listOfEmployees.add(employee);
				
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		DatabaseCache.setEmployeesCache(listOfEmployees);
	}
	
	public static ListOfEmployees getEmployeesFromDB() {
		return DatabaseCache.getEmployeesCache();
	}
	
	public static void LoadDatabase() {
		Database.setEmployeesCacheFromDB();
	}
}
