package main.java.com.seitptt.model.personnel;

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
	 * employee username. Used to uniquely identify an employee.
	 */
	private String username;
	
	/**
	 * employee constructor
	 * @param firstName employee first name
	 * @param lastName employee last name
	 */
	public Employee(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	/**
	 * get first name
	 * @return string representing first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set first name
	 * @param firstName employee first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get last name
	 * @return string representing last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set last name
	 * @param lastName employee last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get username
	 * @return employee username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * set username
	 * @param username employee username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
