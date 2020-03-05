package main.java.com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import main.java.com.seitptt.interfaces.Findable;

/**
 * represents a list of semester
 * @author Hope Elumeziem (2500799E)
 *
 */
public class ListOfSemesters implements Iterable<Semester>,Findable<Semester>{

	/**
	 * list of semesters
	 */
	private ArrayList<Semester> loS = new ArrayList<Semester>();

	@Override
	/**
	 * find a semester given the id
	 */
	public Semester find(int identifier) {

		for (Semester sm: loS) {
			if (sm.getId() == identifier) {
				return sm;
			}
		}

		return null;
	}

	/**
	 * find a semester given the id
	 */
	@Override
	public Semester find(String identifier) {
		throw new RuntimeException("Error: Please search for Semesters using an ID");
	}

	@Override
	public Iterator<Semester> iterator() {
		return loS.iterator();
	}

	/**
	 * add a semester to the list
	 * @param sm the semester
	 */
	public void add(Semester sm) {
		loS.add(sm);
	}

}
