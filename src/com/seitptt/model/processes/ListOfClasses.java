package com.seitptt.model.processes;

import java.util.ArrayList;

import com.seitptt.interfaces.Findable;

public class ListOfClasses implements Findable {
	
	private ArrayList<Class> loc;

	@Override
	public Object find(int identifier) {
		throw new RuntimeException("Class does not have an ID. Use Class code for search.");
	}

	@Override
	public Class find(String identifier) {
		// TODO Auto-generated method stub
		for(Class c: loc) {
			if (c.getCode().equals(identifier)) {
				return c;
			}
		}
		
		return null;
	}
	
	public void add (Class c) {
		loc.add(c);
	}

}