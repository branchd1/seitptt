package com.seitptt.visitors;

import java.io.FileWriter;
import java.io.IOException;

import com.seitptt.model.processes.TeachingRequest;
import com.seitptt.model.processes.TeachingRequirement;

public class PrintToDatabaseVisitor {
	/**
	 * string representing the relative path to the storage files
	 */
	private final String dbDir = "db/";
	private final String space = " ";
	private final String newLine = "\n";
	public void visit(TeachingRequirement teachingRequirement) {
		final String dbFile = this.dbDir + "teaching_requirements.txt";
		
		try {
			FileWriter fileWriter  = new FileWriter(dbFile, true);
			
			String teachingRequirementString = this.newLine;
			
			teachingRequirementString += teachingRequirement.getId();
			
			teachingRequirementString += this.space + teachingRequirement.getNumOfTeachers();
			
			teachingRequirementString += this.space + teachingRequirement.getClassRef().getCode();
			
			fileWriter.append(teachingRequirementString);
			
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void visit(TeachingRequest teachingRequest) {
		final String dbFile = this.dbDir + "teaching_requests.txt";
		try {
			FileWriter fileWriter  = new FileWriter(dbFile, true);
			
			String teachingRequestString = this.newLine;
			
			teachingRequestString += teachingRequest.getId();
			
			teachingRequestString += this.space + teachingRequest.getTeacher().getUsername();
			
			teachingRequestString += this.space + teachingRequest.getClassRef().getCode();
			
			teachingRequestString += this.space + teachingRequest.getTeachingRequirement().getId();
			
			fileWriter.append(teachingRequestString);
			
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
