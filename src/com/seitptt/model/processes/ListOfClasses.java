package com.seitptt.model.processes;

import java.util.ArrayList;

import com.seitptt.interfaces.Findable;

public class ListOfClasses implements Findable {
	
	private ArrayList<Classes> loc;

	@Override
	public Object find(int identifier) {
		throw new RuntimeException("Class does not have an ID. Use Class code for search.");
	}

	@Override
	public Classes find(String identifier) {
		// TODO Auto-generated method stub
		for(Classes c: loc) {
			if (c.getCode().equals(identifier)) {
				return c;
			}
		}
		
		return null;
	}
	
	public void add (Classes c) {
		loc.add(c);
	}

}