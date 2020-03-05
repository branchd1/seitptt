package main.java.com.seitptt.model.processes;

import java.util.ArrayList;

import java.util.Iterator;

/**
 * represents a list of teaching requests
 * @author Hope Elumeziem (2500799E)
 *
 */
public class ListOfTeachingRequests implements Iterable<TeachingRequest> {

	/**
	 * list of teaching requests
	 */
	ArrayList<TeachingRequest> loTR = new ArrayList<TeachingRequest>();

	@Override
	public Iterator<TeachingRequest> iterator() {
		return this.loTR.iterator();
	}

	/**
	 * add a teaching request to the list
	 * @param teachingRequest teaching request to be added
	 */
	public void add(TeachingRequest teachingRequest) {
		this.loTR.add(teachingRequest);
	}

	/**
	 * filter list by teaching requirement
	 * @param requirement teaching requirement to use for filter
	 * @return ListOfTeachingRequests objects containing teaching requests associated with the requirement
	 */
	public ListOfTeachingRequests filterByTeachingRequirement(TeachingRequirement requirement) {
		ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();
		int desiredId = requirement.getId();

		// loop through requests and add requests related to the teaching requirement
		for(TeachingRequest teachingRequest : this.loTR) {
			int expectedId = teachingRequest.getTeachingRequirement().getId();
			if(desiredId==expectedId) {
				listOfTeachingRequests.add(teachingRequest);
			}
		}

		return listOfTeachingRequests;
	}

	/**
	 * filter list by approval
	 * @param approved boolean true or false
	 * @return ListOfTeachingRequests with approval value equal to param approval
	 */
	public ListOfTeachingRequests filterByApproval(boolean approved) {
		ListOfTeachingRequests listOfTeachingRequests = new ListOfTeachingRequests();

		// loop through requests and add requests that meet criteria
		for(TeachingRequest teachingRequest : this.loTR) {
			if ((approved == true) && (teachingRequest.isApproved() == true)) {
				listOfTeachingRequests.add(teachingRequest);
			} else if ((approved == false) && (teachingRequest.isApproved() == false)) {
				listOfTeachingRequests.add(teachingRequest);
			}
		}

		return listOfTeachingRequests;
	}
}