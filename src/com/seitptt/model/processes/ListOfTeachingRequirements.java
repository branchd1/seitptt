package com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import com.seitptt.interfaces.Findable;

public class ListOfTeachingRequirements implements Findable, Iterable<TeachingRequirement> {
	
	private ArrayList<TeachingRequirement> loR = new ArrayList<TeachingRequirement>();
	
	

	@Override
	public TeachingRequirement find(int identifier) {
		// TODO Auto-generated method stub
		
		for (TeachingRequirement tr: loR) {
			if (tr.getId() == identifier) {
				return tr;
			}
		}
		
		return null;
	}
	
	
	public void add(TeachingRequirement tr) {
		loR.add(tr);
	}

	@Override
	public Findable find(String identifier) {
		throw new RuntimeException("Error: Please search for TeachingRequirement using an ID");
	}
	
	public ListOfTeachingRequirements getAllRequirementsConnectedToARequest(TeachingRequest request) {
		ListOfTeachingRequirements listOfTeachingRequirements = new ListOfTeachingRequirements();
		int desiredId = request.getTeachingRequirement().getId();
		for(TeachingRequirement teachingRequirement : this.loR) {
			if(desiredId==teachingRequirement.getId()) {
				listOfTeachingRequirements.add(teachingRequirement);
			}
		}
		return listOfTeachingRequirements;
	}


	@Override
	public Iterator<TeachingRequirement> iterator() {
		return this.loR.iterator();
	}

}