package com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import com.seitptt.interfaces.Findable;

public class ListOfTeachingRequests implements Findable, Iterable<TeachingRequest> {

	
	ArrayList<TeachingRequest> loTR = new ArrayList<TeachingRequest>();

	@Override
	public Findable find(int identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Findable find(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getID() {
		return loTR.get(loTR.size()-1).getId();
	}
	
	
	public Iterator<TeachingRequest> iterator() {
		return this.loTR.iterator();
	}
	
	public void add(TeachingRequest teachingRequest) {
		this.loTR.add(teachingRequest);
	}
}