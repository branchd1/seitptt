package main.java.com.seitptt.model.database;

import java.io.FileNotFoundException;



import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
		
		// skip first line that holds meta data
		s.nextLine();
		
		// while file has next line
		while(s.hasNextLine()) {
			// while line has next token, separated by whitespaces
			while(s.hasNext()) {
				// get attributes
				final String type = s.next();
				final String username = s.next();
				final String firstName = s.next();
				final String lastName = s.next();
				
				Employee employee = null;
				
				// check the type and determine what subclass of employee to create
				if (type.contains("ptt")) {
					employee = new PTTDirector(firstName, lastName);
					employee.setUsername(username);
				}
				
				if (type.contains("class")) {
					employee = new ClassDirector(firstName, lastName);
					employee.setUsername(username);
				}
				
				if (type.contains("admin")) {
					employee = new Administrator(firstName, lastName);
					employee.setUsername(username);
				}
				
				if (type.contains("teacher")) {
					final boolean trained = s.nextBoolean();
					employee = new Teacher(firstName, lastName);
					employee.setUsername(username);
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
		
		// skip first line that holds meta data
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
	
	/**
	 * gets the list of teaching requests from the database
	 * @return ListOfTeachingRequests object containing the list of teaching requests
	 */
	public static ListOfTeachingRequests getTeachingRequestsFromDB() {
		// db file
		final String dbFile = Database.dbDir + "teaching_requests.txt";
		// stores the list
		final ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();
		
		// init file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// skip first line that holds meta data
		s.nextLine();
		
		// while file has next line
		while(s.hasNextLine()) {
			// while line has next token separated by space
			while(s.hasNext()) {
				// use data to create new teaching request object with correct attributes
				final int id = s.nextInt();
				final String teacherUsername = s.next();
				final String classCode = s.next();
				final int teachingRequirementId = s.nextInt();
				final boolean isApproved = s.nextBoolean();
				
				// create the necessary objects
				Teacher teacher = (Teacher)Database.getEmployeesFromDB().find(teacherUsername);
				
				Classes classObj = (Classes)Database.getClassesFromDB().find(classCode);
				TeachingRequirement teachingRequirement = (TeachingRequirement)Database.getTeachingRequirementsFromDB().find(teachingRequirementId);
				
				TeachingRequest teachingRequest = new TeachingRequest(id, teacher, classObj, teachingRequirement);
				
				if(isApproved) {
					teachingRequest.approve();
				}
				
				// add object to the list
				listOfTeachingRequests.add(teachingRequest);
				
			}
		}
		
		// close file reader and scanner
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// return the list
		return listOfTeachingRequests;
	}
	
	/**
	 * gets the list of teaching requirements from the database
	 * @return ListOfTeachingRequirements object containing list of teaching requirements
	 */
	public static ListOfTeachingRequirements getTeachingRequirementsFromDB() {
		// database file
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
		
		// list of teaching requirements
		final ListOfTeachingRequirements listOfTeachingRequirements= new ListOfTeachingRequirements();
		
		// initialize file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// skip first line that holds meta data
		s.nextLine();
		
		// while file has next line
		while(s.hasNextLine()) {
			// while line has next token separated by space
			while(s.hasNext()) {
				// use data to create new teaching requirement object
				final int id = s.nextInt();
				final int numberOfTeachers = s.nextInt();
				final String classCode = s.next();
				
				Classes classObj = (Classes)Database.getClassesFromDB().find(classCode);
				
				TeachingRequirement teachingRequirement = new TeachingRequirement(id, numberOfTeachers, classObj);
				
				// add to the list
				listOfTeachingRequirements.add(teachingRequirement);
				
			}
		}
		
		// close scanner and file reader
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// return the list
		return listOfTeachingRequirements;
	}
	
	/**
	 * sets the classes cache.
	 * NOTE: we do not need to update cache because the program does not alter the classes information in its current version.
	 * This enables us to run the program without hitting the database needlessly.
	 */
	public static void setClassesCacheFromDB() {
		// database file
		final String dbFile = Database.dbDir + "classes.txt";
		final ListOfClasses listOfClasses= new ListOfClasses(); // list of classes
		
		// initialize file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// skip first line that holds meta data
		s.nextLine();
		
		// loop through the file and create new classes objects
		while(s.hasNextLine()) {
			while(s.hasNext()) {
				final String code = s.next();
				final String name = s.next().replace('_', ' ');
				final int semesterId = s.nextInt();
				
				final Semester semester = Database.getSemestersFromDB().find(semesterId);
				
				final Classes classObj = new Classes(code, name, semester);
				
				// add it to the list
				listOfClasses.add(classObj);
				
			}
		}
		
		// close filereader and scanner
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// set the cache
		DatabaseCache.setClassesCache(listOfClasses);
	}
	
	/**
	 * simulates getting the list of classes from the database by getting it from the classes cache initialized in the beginning.
	 * @return ListOfClasses object containing the list of classes
	 */
	public static ListOfClasses getClassesFromDB() {
		return DatabaseCache.getClassesCache();
	}
	
	/**
	 * delete a teaching requirement from the database
	 * @param teachingRequirement the TeachingRequirement object to be deleted
	 */
	public static void removeTeachingRequirementFromDB(TeachingRequirement teachingRequirement) {
		
		// first delete all teaching requests with foreign key to this requirement i.e CASCADE
		ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB().filterByTeachingRequirement(teachingRequirement);
		for(TeachingRequest teachingRequest : listOfTeachingRequests) {
			Database.removeTeachingRequestFromDB(teachingRequest);
		}
		
		//database file
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
		
		// string to contain contents of the new file
		String newDbString = "";
		
		// initialize file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// add first line to new file
		newDbString += s.nextLine();
		
		// loop through the lines and add them to new file
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequirement.getId();
			
			// except the line where the id is the same with the teaching requirement id to be deleted
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else {
				s.nextLine();
			}
		}
		
		// close scanner
		s.close();
		
		// overwrite old file with new file and close file writer
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	/**
	 * delete a teaching request from the database
	 * @param teachingRequest the TeachingRequest object to be deleted
	 */
	public static void removeTeachingRequestFromDB(TeachingRequest teachingRequest) {
		// database file
		final String dbFile = Database.dbDir + "teaching_requests.txt";
		
		// string to contain content of new file
		String newDbString = "";
		
		// init file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// add file line to new file
		newDbString += s.nextLine();
		
		// loop through the file and add the lines to new file
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequest.getId();
			
			// except for the line where id is the same with the TeachingRequest id to be deleted
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else{
				s.nextLine();
			}
		}
		
		// close scanner
		s.close();
		
		// overwrite old file with new file and close filewriter
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	/**
	 * NOT WORKING - UNTESTED
	 * approve a teaching request on the database
	 * @param teachingRequest the TeachingRequest object to be updated
	 */
	public static void approveTeachingRequestOnDB(TeachingRequest teachingRequest) {
		// database file
		final String dbFile = Database.dbDir + "teaching_requests.txt";
		
		// string to contain content of new file
		String newDbString = "";
		
		// init file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// add file line to new file
		newDbString += s.nextLine();
		
		// loop through the file and add the lines to new file
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequest.getId();
			
			// except for the line where id is the same with the TeachingRequest id to be updated
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else{
				newDbString += "\n";
				newDbString += fileId + " ";
				newDbString += s.next() + " ";
				newDbString += s.next() + " ";
				newDbString += s.nextInt() + " ";
				newDbString += true;
			}
		}
		
		// close scanner
		s.close();
		
		// overwrite old file with new file and close filewriter
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	/**
	 * NOT WORKING - UNTESTED
	 * reduce a teaching requirement count for number of teachers needed from the database
	 * @param teachingRequirement the TeachingRequirement object to be updated
	 */
	public static void reduceTeachingRequirementCountOnDB(TeachingRequirement teachingRequirement) {
		
		// first delete all teaching requests with foreign key to this requirement i.e CASCADE
		ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB().filterByTeachingRequirement(teachingRequirement);
		for(TeachingRequest teachingRequest : listOfTeachingRequests) {
			Database.removeTeachingRequestFromDB(teachingRequest);
		}
		
		//database file
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
		
		// string to contain contents of the new file
		String newDbString = "";
		
		// initialize file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// add first line to new file
		newDbString += s.nextLine();
		
		// loop through the lines and add them to new file
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequirement.getId();
			
			// except the line where the id is the same with the teaching requirement id to be updated
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else {
				newDbString += "\n";
				newDbString += fileId + " ";
				int newNumber = s.nextInt() - 1;
				newDbString += (newNumber) + " ";
				newDbString += s.next();
			}
		}
		
		// close scanner
		s.close();
		
		// overwrite old file with new file and close file writer
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	/**
	 * NOT WORKING
	 * train a teacher on the database
	 * @param teacher the teacher object to be updated
	 */
	public static void trainTeacherOnDB(Teacher teacher) {
		
		// first delete all teaching requests with foreign key to this requirement i.e CASCADE
		ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB().filterByTeachingRequirement(teachingRequirement);
		for(TeachingRequest teachingRequest : listOfTeachingRequests) {
			Database.removeTeachingRequestFromDB(teachingRequest);
		}
		
		//database file
		final String dbFile = Database.dbDir + "teaching_requirements.txt";
		
		// string to contain contents of the new file
		String newDbString = "";
		
		// initialize file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// add first line to new file
		newDbString += s.nextLine();
		
		// loop through the lines and add them to new file
		while(s.hasNextLine()) {
			int fileId = s.nextInt();
			
			int trId = teachingRequirement.getId();
			
			// except the line where the id is the same with the teaching requirement id to be deleted
			if (!(fileId==trId)){
				newDbString += "\n";
				newDbString += fileId;
				newDbString += s.nextLine();
			} else {
				s.nextLine();
			}
		}
		
		// close scanner
		s.close();
		
		// overwrite old file with new file and close file writer
		try {
			FileWriter fw = null;
			fw = new FileWriter(dbFile);
			fw.write(newDbString);
			fw.close();
		} catch(IOException e) {
			
		}
		
	}
	
	/**
	 * get the last id used in the database
	 * @param o generic object that determines the database of objects we want to search
	 * @return integer that represents the last id used in the database
	 */
	public static <T> int getLatestIdFromDB(T o) {
		// init result as 1, if no results are found, start with 1
		int result = 1;
		
		// set database file based on the instance of the object
		String dbFile = "";
		if(o instanceof TeachingRequest) {
			dbFile = Database.dbDir + "teaching_requests.txt";
		} else if (o instanceof TeachingRequirement) {
			dbFile = Database.dbDir + "teaching_requirements.txt";
		}
		
		// init file reader and scanner
		FileReader fr = null;
		
		try {
			fr = new FileReader(dbFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		final Scanner s = new Scanner(fr);
		
		// loop through file
		while(s.hasNextLine()) {
			String line = s.nextLine();
			// if last line
			if(!s.hasNextLine()) {
				// get the first token on the line and store in result
				try {
					result = Integer.parseInt(line.split(" ")[0]);
				} catch(NumberFormatException e) {
					
				}
			}
		}
		
		// close file reader and scanner
		try{
			fr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		s.close();
		
		// return result
		return result;
	}
	
	/**
	 * load constant data from database - employee list, semester list, classes list
	 */
	public static void LoadCaches() {
		Database.setEmployeesCacheFromDB();
		Database.setSemesterCacheFromDB();
		Database.setClassesCacheFromDB();
	}
}
