package com.seitptt.model.processes;

import java.util.ArrayList;

import com.seitptt.interfaces.Findable;

public class listOfSemesters implements Findable{

	private ArrayList<Semester> loS;

	@Override
	public Semester find(int identifier) {
		// TODO Auto-generated method stub
		
		for (Semester sm: loS) {
			if (sm.getId() == identifier) {
				return sm;
			}
		}
		
		return null;
	}

	@Override
	public Findable find(String identifier) {
		throw new RuntimeException("Error: Please search for Semesters using an ID");
	}

	
	
	public void add(Semester sm) {
		loS.add(sm);
	}
	
}
