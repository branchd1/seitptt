package com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import com.seitptt.interfaces.Findable;

public class ListOfClasses implements Findable<Classes>, Iterable<Classes> {
	
	private ArrayList<Classes> loc = new ArrayList<Classes>();

	@Override
	public Classes find(int identifier) {
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

	@Override
	public Iterator<Classes> iterator() {
		return this.loc.iterator();
	}

}