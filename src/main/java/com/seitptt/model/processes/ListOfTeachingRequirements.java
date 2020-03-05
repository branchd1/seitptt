package main.java.com.seitptt.model.processes;

import java.util.ArrayList;
import java.util.Iterator;

import main.java.com.seitptt.interfaces.Findable;

/**
 * represents a list of teaching requirements
 * @author Hope Elumeziem (2500799E)
 *
 */
public class ListOfTeachingRequirements implements Findable<TeachingRequirement>, Iterable<TeachingRequirement> {

	/**
	 * list of teaching requirements
	 */
	private ArrayList<TeachingRequirement> loR = new ArrayList<TeachingRequirement>();

	@Override
	/**
	 * find a requirement
	 */
	public TeachingRequirement find(int identifier) {
		// TODO Auto-generated method stub

		for (TeachingRequirement tr: loR) {
			if (tr.getId() == identifier) {
				return tr;
			}
		}

		return null;
	}

	@Override
	/**
	 * find a requirement
	 */
	public TeachingRequirement find(String identifier) {
		throw new RuntimeException("Error: Please search for TeachingRequirement using an ID");
	}

	/**
	 * add a requirement to the list
	 * @param tr teaching requirement to be added
	 */
	public void add(TeachingRequirement tr) {
		loR.add(tr);
	}

	@Override
	public Iterator<TeachingRequirement> iterator() {
		return this.loR.iterator();
	}

	/**
	 * filter list by valid. if number of teachers needed is less than 1, it's not valid.
	 * @return ListOfTeachingRequirements representing list of teaching requirement
	 */
	public ListOfTeachingRequirements filterByValidation() {
		ListOfTeachingRequirements listOfTeachingRequirements = new ListOfTeachingRequirements();

		// loop through and add requirements with number of teachers greater than 0
		for(TeachingRequirement teachingRequirement : this.loR) {
			if(teachingRequirement.getNumOfTeachers()>0) {
				listOfTeachingRequirements.add(teachingRequirement);
			}
		}

		return listOfTeachingRequirements;
	}

}