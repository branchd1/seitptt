package com.seitptt.model.database;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.seitptt.model.personnel.Administrator;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.PTTDirector;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.model.processes.ListOfTeachingRequests;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
import com.seitptt.model.processes.TeachingRequest;
import com.seitptt.model.processes.TeachingRequirement;
import com.seitptt.model.processes.Class;
import com.seitptt.model.processes.ListOfClasses;

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
	
	public static void setSemesterCacheFromDB() {
		final String dbFile = "semesters.txt";
		final ArrayList<Semester> listOfSemesters = new ArrayList<Semester>();
		
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
				final int id = s.nextInt();
				final int number = s.nextInt();
				final int year = s.nextInt();
				
				Semester semester = new Semester(id, number, year);
				
				listOfSemesters.add(semester);
				
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		DatabaseCache.setSemestersCache(listOfSemesters);
	}
	
	public static ArrayList<Semester> getSemestersFromDB() {
		return DatabaseCache.getSemestersCache();
	}
	
	public static ListOfTeachingRequests getTeachingRequestsFromDB() {
		final String dbFile = "teaching_requests.txt";
		final ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();
		
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
				final int id = s.nextInt();
				final String teacherUsername = s.next();
				final String classCode = s.next();
				final int teachingRequirementId = s.nextInt();
				
				Teacher teacher = (Teacher)Database.getEmployeesFromDB().find(teacherUsername);
				
				// CHANGE THESE TO USE FIND
				Class classObj = new Class();
				TeachingRequirement teachingRequirement = new TeachingRequirement();
				
//				TeachingRequest teachingRequest = new TeachingRequest(teacher, classObj, teachingRequirement);
				TeachingRequest teachingRequest = new TeachingRequest();
				
//				listOfTeachingRequests.add(teachingRequest);
				
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		return listOfTeachingRequests;
	}
	
	public static ListOfTeachingRequirements getTeachingRequirementsFromDB() {
		final String dbFile = "teaching_requirements.txt";
		final ListOfTeachingRequirements listOfTeachingRequirements= new ListOfTeachingRequirements();
		
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
				final int id = s.nextInt();
				final int numberOfTeachers = s.nextInt();
				final String classCode = s.next();
				
				Class classObj = new Class();
				
//				TeachingRequirement teachingRequirement = new TeachingRequirement(id, numberOfTeachers, classObj);
				TeachingRequirement teachingRequirement = new TeachingRequirement();
				
//				ListOfTeachingRequirements.add(teachingRequirement);
				
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		return listOfTeachingRequirements;
	}
	
	public static ListOfClasses getClassesFromDB() {
		final String dbFile = "classes.txt";
		final ListOfClasses listOfClasses= new ListOfClasses();
		
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
				final String code = s.next();
				final String name = s.next();
				final int semesterId = s.nextInt();
				
				final Semester semester = Database.getSemestersFromDB().find(semesterId);
				
				final Class classObj = new Class(code, name, semester);
				
				ListOfClasses.add(classObj);
				
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		return listOfClasses;
	}
	
	public static void LoadCaches() {
		Database.setEmployeesCacheFromDB();
		Database.setSemesterCacheFromDB();
	}
}
