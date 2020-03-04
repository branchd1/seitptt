package main.java.com.seitptt.model.personnel;

import java.util.ArrayList;

import java.util.Iterator;

import main.java.com.seitptt.interfaces.Findable;

/**
 * heterogenous list of employees.
 * @author arnoldumakhihe 2445734U
 *
 */
public class ListOfEmployees implements Iterable<Employee>,Findable<Employee> {
	private final ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
	
	public void add(Employee employee) {
		listOfEmployees.add(employee);
	}
	
	@Override
	public Iterator<Employee> iterator() {
		return this.listOfEmployees.iterator();
	}
	
	public Employee find(String username) {
		for(Employee employee : listOfEmployees) {
			if (employee.getUsername().contentEquals(username)) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public Employee find(int identifier) {
		throw new RuntimeException("Please find a staff with a username, not an integer.");
	}
	
	public ListOfEmployees search(String query) {
		ListOfEmployees listOfEmployees = new ListOfEmployees();
		for (Employee e : this.listOfEmployees) {
			if(e.getFirstName().contains(query) || e.getLastName().contains(query)) {
				listOfEmployees.add(e);
			}
		}
		return listOfEmployees;
	}
	
	public ListOfEmployees getTeachers() {
		ListOfEmployees listOfTeachers = new ListOfEmployees();
		for (Employee e : this.listOfEmployees) {
			if(e instanceof Teacher) {
				listOfTeachers.add(e);
			}
		}
		return listOfTeachers;
	}
	
	public ListOfEmployees getUntrainedTeachers() {
		ListOfEmployees listOfTeachers = new ListOfEmployees();
		for (Employee e : this.listOfEmployees) {
			if(e instanceof Teacher) {
				if(((Teacher)e).isTrained()==false) {
					listOfTeachers.add(e);
				}
			}
		}
		return listOfTeachers;
	}
	
	public ListOfEmployees getTrainedTeachers() {
		ListOfEmployees listOfTeachers = new ListOfEmployees();
		for (Employee e : this.listOfEmployees) {
			if(e instanceof Teacher) {
				if(((Teacher)e).isTrained()==true) {
					listOfTeachers.add(e);
				}
			}
		}
		return listOfTeachers;
	}
}
