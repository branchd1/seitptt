package main.java.com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import main.java.com.seitptt.interfaces.Findable;

public class ListOfSemesters implements Iterable<Semester>,Findable<Semester>{

	private ArrayList<Semester> loS = new ArrayList<Semester>();

	@Override
	public Semester find(int identifier) {
		
		for (Semester sm: loS) {
			if (sm.getId() == identifier) {
				return sm;
			}
		}
		
		return null;
	}

	@Override
	public Semester find(String identifier) {
		throw new RuntimeException("Error: Please search for Semesters using an ID");
	}

	@Override
	public Iterator<Semester> iterator() {
		return loS.iterator();
	}
	
	public void add(Semester sm) {
		loS.add(sm);
	}
	
}
