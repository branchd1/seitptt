package com.seitptt.visitors;

import java.io.FileWriter;
import java.io.IOException;

import com.seitptt.model.processes.TeachingRequest;
import com.seitptt.model.processes.TeachingRequirement;

public class PrintToDatabaseVisitor {
	public void visit(TeachingRequirement teachingRequirement) {
		final String dbFile = "teaching_requirements.txt";
		try {
			FileWriter fileWriter  = new FileWriter(dbFile);
			
			String teachingRequirementString = "";
			
			teachingRequirementString += teachingRequirement.getId();
			
			teachingRequirementString += teachingRequirement.getNumOfTeachers();
			
			teachingRequirementString += teachingRequirement.getClassRef().getCode();
			
			fileWriter.append(teachingRequirementString);
			
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void visit(TeachingRequest teachingRequest) {
		final String dbFile = "teaching_requests.txt";
		try {
			FileWriter fileWriter  = new FileWriter(dbFile);
			
			String teachingRequestString = "";
			
			teachingRequestString += teachingRequest.getId();
			
			teachingRequestString += teachingRequest.getTeacher().getUsername();
			
			teachingRequestString += teachingRequest.getClassRef().getCode();
			
			teachingRequestString += teachingRequest.getTeachingRequirement().getId();
			
			fileWriter.append(teachingRequestString);
			
			fileWriter.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
