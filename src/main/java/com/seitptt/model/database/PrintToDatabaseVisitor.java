package main.java.com.seitptt.model.database;

import java.io.FileWriter;
import java.io.IOException;

import main.java.com.seitptt.model.processes.ListOfTeachingRequests;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.TeachingRequest;
import main.java.com.seitptt.model.processes.TeachingRequirement;

/**
 * a visitor that holds methods to print an object to database
 * @author arnoldumakhihe 2445734u
 *
 */
public class PrintToDatabaseVisitor {
	/**
	 * string representing the relative path to the storage files
	 */
	private final String dbDir = "db/";
	
	/**
	 * string representing a space
	 */
	private final String space = " ";
	
	/**
	 * string representing a new line
	 */
	private final String newLine = "\n";
	
	/**
	 * print a teaching requirement to database
	 * @param teachingRequirement TeachingRequirement object to be printed to database
	 */
	public void visit(TeachingRequirement teachingRequirement) {
		
		// don't add requirement if it exists, increment count of teachers instead
		ListOfTeachingRequirements listOfTeachingRequirements = Database.getTeachingRequirementsFromDB();
		for(TeachingRequirement teachingRequirementsForLoop : listOfTeachingRequirements) {
			if(teachingRequirementsForLoop.equals(teachingRequirement)) {
				Database.increaseTeachingRequirementCountOnDB(teachingRequirementsForLoop, teachingRequirement.getNumOfTeachers());
				return;
			}
		}
		
		// database file
		final String dbFile = this.dbDir + "teaching_requirements.txt";
		
		try {
			// init file writer
			FileWriter fileWriter  = new FileWriter(dbFile, true);
			
			// init string containing line
			String teachingRequirementString = this.newLine;
			
			// get data and add to string
			teachingRequirementString += teachingRequirement.getId();
			
			teachingRequirementString += this.space + teachingRequirement.getNumOfTeachers();
			
			teachingRequirementString += this.space + teachingRequirement.getClassRef().getCode();
			
			// append line to file
			fileWriter.append(teachingRequirementString);
			
			// close file writer
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * print a teaching request to database
	 * @param teachingRequest TeachingRequest object to be printed to database
	 */
	public void visit(TeachingRequest teachingRequest) {
		
		// don't add request if an identical request exists
		ListOfTeachingRequests listOfTeachingRequest = Database.getTeachingRequestsFromDB();
		for(TeachingRequest teachingRequestForLoop : listOfTeachingRequest) {
			if(teachingRequestForLoop.equals(teachingRequest)) {
				return;
			}
		}
		
		// database file
		final String dbFile = this.dbDir + "teaching_requests.txt";
		
		try {
			// init file writer
			FileWriter fileWriter  = new FileWriter(dbFile, true);
			
			// init string containing line
			String teachingRequestString = this.newLine;
			
			// get data and add to string
			teachingRequestString += teachingRequest.getId();
			
			teachingRequestString += this.space + teachingRequest.getTeacher().getUsername();
			
			teachingRequestString += this.space + teachingRequest.getClassRef().getCode();
			
			teachingRequestString += this.space + teachingRequest.getTeachingRequirement().getId();
			
			teachingRequestString += this.space + teachingRequest.isApproved();
			
			// append to file and close file writer
			fileWriter.append(teachingRequestString);
			
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
