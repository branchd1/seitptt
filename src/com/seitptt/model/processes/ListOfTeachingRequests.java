package com.seitptt.model.processes;

import java.util.ArrayList;

import java.util.Iterator;

public class ListOfTeachingRequests implements Iterable<TeachingRequest> {
	
	ArrayList<TeachingRequest> loTR = new ArrayList<TeachingRequest>();
	
	public Iterator<TeachingRequest> iterator() {
		return this.loTR.iterator();
	}
	
	public void add(TeachingRequest teachingRequest) {
		this.loTR.add(teachingRequest);
	}
	
	public ListOfTeachingRequests filterByTeachingRequirement(TeachingRequirement requirement) {
		ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();
		int desiredId = requirement.getId();
		
		for(TeachingRequest teachingRequest : this.loTR) {
			int expectedId = teachingRequest.getTeachingRequirement().getId();
			if(desiredId==expectedId) {
				listOfTeachingRequests.add(teachingRequest);
			}
		}
		
		return listOfTeachingRequests;
	}
	
	public ListOfTeachingRequests filterByApproval(boolean approved) {
		ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();
		
		for(TeachingRequest teachingRequest : this.loTR) {
			if ((approved == true) && (teachingRequest.isApproved() == true)) {
				listOfTeachingRequests.add(teachingRequest);
			} else if ((approved == false) && (teachingRequest.isApproved() == false)) {
				listOfTeachingRequests.add(teachingRequest);
			}
		}
		
		return listOfTeachingRequests;
	}
}