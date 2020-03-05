package main.java.com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import main.java.com.seitptt.interfaces.Findable;

/**
 * represents a list of classes
 * @author Hope Elumeziem (2500799E)
 *
 */
public class ListOfClasses implements Findable<Classes>, Iterable<Classes> {

	/**
	 * list of classes
	 */
	private ArrayList<Classes> loc = new ArrayList<Classes>();

	@Override
	/**
	 * find a particular class given the id
	 * @param identifier the id
	 * @return Classes object representing the class
	 */
	public Classes find(int identifier) {
		throw new RuntimeException("Class does not have an ID. Use Class code for search.");
	}

	@Override
	/**
	 * find a particular class given the id
	 * @param identifier the class code
	 * @return Classes object representing the class
	 */
	public Classes find(String identifier) {
		// loop through and find the class
		for(Classes c: loc) {
			if (c.getCode().equals(identifier)) {
				return c;
			}
		}
		// else return null
		return null;
	}

	/**
	 * add a class to the list
	 * @param c Classes object to be added to list
	 */
	public void add (Classes c) {
		loc.add(c);
	}

	@Override
	public Iterator<Classes> iterator() {
		return this.loc.iterator();
	}

	/**
	 * filter the list of classes using a particular semester
	 * @param semester Semester object to use for filter
	 * @return ListOfClasses representing the list of classes
	 */
	public ListOfClasses filterBySemester(Semester semester) {
		ListOfClasses newListOfClasses = new ListOfClasses();
		// loop through the classes and only add classes with this semester
		for(Classes classes : loc) {
			if(classes.getSemester().equals(semester)) {
				newListOfClasses.add(classes);
			}
		}
		return newListOfClasses;
	}

}