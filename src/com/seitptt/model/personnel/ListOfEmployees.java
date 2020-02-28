package com.seitptt.model.personnel;

import java.util.ArrayList;

import java.util.Iterator;

public class ListOfEmployees implements Iterable<Employee> {
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
}
