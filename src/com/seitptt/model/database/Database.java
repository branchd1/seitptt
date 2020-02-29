package com.seitptt.model.database;

import java.io.FileNotFoundException;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;

/**
 * Database class is used to access the database(i.e storage files) to CRUD information
 * @author arnoldumakhihe 2445734u
 */
public class Database {
	/**
	 * string representing the relative path to the storage files
	 */
	private final static String dbDir = "db/";
	
	/**
	 * retrieve employees information and cache throughout the program.
	 * NOTE: we do not need to update cache because the program does not alter the employees information in its current version.
	 * This enables us to run the program without hitting the database needlessly.
	 */
	public static void setEmployeesCacheFromDB() {
		// represents relative path to employee storage file
		final String dbFile = Database.dbDir + "employees.txt";
		
		// represents the list of employees
		final ListOfEmployees listOfEmployees = new ListOfEmployees();
		
		// used to read files
		FileReader fr = null;
		
		try {
			// create new file reader
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// create new scanner
		final Scanner s = new Scanner(fr);
		
		// skip first line
		s.nextLine();
		
		// while file has next line
		while(s.hasNextLine()) {
			// while line has next token, separated by whitespaces
			while(s.hasNext()) {
				// get attributes
				final String type = s.next();
				final String username = s.next();
				final String password = s.next();
				final String firstName = s.next();
				final String lastName = s.next();
				
				Employee employee = null;
				
				// check the type and determine what subclass of employee to create
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
					final boolean trained = s.nextBoolean();
					employee = new Teacher(firstName, lastName);
					employee.setUsername(username);
					employee.setPassword(password);
					if(trained) {
						((Teacher)employee).train();
					}
				}
				
				// add teacher to list
				listOfEmployees.add(employee);
				
			}
		}
		
		// close file reader and scanner
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// set the employee cache
		DatabaseCache.setEmployeesCache(listOfEmployees);
	}
	
	/**
	 * simulates getting the list of employees from the database by getting it from the employee cache initialized in the beginning.
	 * @return ListOfEmployees object containing a list of employees
	 */
	public static ListOfEmployees getEmployeesFromDB() {
		return DatabaseCache.getEmployeesCache();
	}
	
	/**
	 * sets the semester cache.
	 * NOTE: we do not need to update cache because the program does not alter the semester information in its current version.
	 * This enables us to run the program without hitting the database needlessly.
	 */
	public static void setSemesterCacheFromDB() {
		// represents path to storage file
		final String dbFile = Database.dbDir + "semesters.txt";
		
		// represents list of semesters
		final ListOfSemesters listOfSemesters = new ListOfSemesters();
		
		FileReader fr = null;
		
		// new file reader and scanner
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// skip first line
		s.nextLine();
		
		// while file has next line
		while(s.hasNextLine()) {
			// while line has next token, separeted by white space
			while(s.hasNext()) {
				// create new Semester objects from data
				final int id = s.nextInt();
				final int number = s.nextInt();
				final int year = s.nextInt();
				
				Semester semester = new Semester(id, number, year);
				
				// add each semester to the list
				listOfSemesters.add(semester);
				
			}
		}
		
		// close file reader and scanner
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// set the cache
		DatabaseCache.setSemestersCache(listOfSemesters);
	}
	
	/**
	 * simulates getting the list of semesters from the database by getting it from the semester cache initialized in the beginning.
	 * @return ListOfSemesters object containing the list of semesters
	 */
	public static ListOfSemesters getSemestersFromDB() {
		return DatabaseCache.getSemestersCache();
	}
	
	
	public static ListOfTeachingRequests getTeachingRequestsFromDB() {
		final String dbFile = Database.dbDir + "teaching_requests.txt";
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
				
				Classes classObj = (Classes)Database.getClassesFromDB().find(classCode);
				TeachingRequirement teachingRequirement = (TeachingRequirement)Database.getTeachingRequirementsFromDB().find(teachingRequirementId);
				
				TeachingRequest teachingRequest = new TeachingRequest(id, teacher, classObj, teachingRequirement);
				
				listOfTeachingRequests.add(teachingRequest);
				
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
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
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
				
				Classes classObj = (Classes)Database.getClassesFromDB().find(classCode);
				
				TeachingRequirement teachingRequirement = new TeachingRequirement(id, numberOfTeachers, classObj);
				
				listOfTeachingRequirements.add(teachingRequirement);
				
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
		final String dbFile = Database.dbDir + "classes.txt";
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
				final String name = s.next().replace('_', ' ');
				final int semesterId = s.nextInt();
				
				final Semester semester = Database.getSemestersFromDB().find(semesterId);
				
				final Classes classObj = new Classes(code, name, semester);
				
				listOfClasses.add(classObj);
				
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
	
	public static void removeTeachingRequirementFromDB(TeachingRequirement teachingRequirement) {
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
		String newDbString = "";
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		newDbString += s.nextLine();
		
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequirement.getId();
			
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else {
				s.nextLine();
			}
		}
		
		s.close();
		
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	public static void removeTeachingRequestFromDB(TeachingRequest teachingRequest) {
		final String dbFile = Database.dbDir + "teaching_requests.txt";
		String newDbString = "";
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		newDbString += s.nextLine();
		
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequest.getId();
			
			if (!(fileId==trId)){
				newDbString += s.nextLine();
			}
			
			s.nextLine();
		}
		
		s.close();
		
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	public static int getLatestIdFromDB(Object o) {
		int result = 1;
		String dbFile = "";
		if(o instanceof TeachingRequest) {
			dbFile = Database.dbDir + "teaching_requests.txt";
		} else if (o instanceof TeachingRequirement) {
			dbFile = Database.dbDir + "teaching_requirements.txt";
		}
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(!s.hasNextLine()) {
				try {
					result = Integer.parseInt(line.split(" ")[0]);
				} catch(NumberFormatException e) {
					
				}
			}
		}
		
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		return result;
	}
	
	public static void LoadCaches() {
		Database.setEmployeesCacheFromDB();
		Database.setSemesterCacheFromDB();
	}
}
