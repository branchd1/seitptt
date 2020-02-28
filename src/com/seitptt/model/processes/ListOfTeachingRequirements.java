package com.seitptt.model.processes;

import java.util.ArrayList;

import com.seitptt.interfaces.Findable;

public class ListOfTeachingRequirements implements Findable {
	
	private ArrayList<TeachingRequirement> loR;
	
	

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
	
	public ArrayList<TeachingRequirement> getList() {
		return this.loR;
	}

}