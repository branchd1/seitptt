package com.seitptt.model.personnel;

/**
 * represents an employee
 * @author arnoldumakhihe 2445734u
 *
 */
public abstract class Employee {
	/**
	 * employee first name
	 */
	private String firstName;
	
	/**
	 * employee last name
	 */
	private String lastName;
	
	/**
	 * employee username
	 */
	private String username;
	
	public Employee(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
