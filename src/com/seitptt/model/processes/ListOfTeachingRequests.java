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
	
	public ListOfTeachingRequests filterRequestsConnectedToARequirement(TeachingRequirement requirement) {
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
}