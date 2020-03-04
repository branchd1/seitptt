package com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import com.seitptt.interfaces.Findable;

public class ListOfTeachingRequirements implements Findable<TeachingRequirement>, Iterable<TeachingRequirement> {
	
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
	public TeachingRequirement find(String identifier) {
		throw new RuntimeException("Error: Please search for TeachingRequirement using an ID");
	}

	@Override
	public Iterator<TeachingRequirement> iterator() {
		return this.loR.iterator();
	}

}