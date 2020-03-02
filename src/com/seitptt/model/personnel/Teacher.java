package com.seitptt.model.personnel;

/**
 * represents a teacher
 * @author arnoldumakhihe 2445734U
 *
 */
public class Teacher extends Employee{
	/**
	 * represents if the teacher is trained
	 */
	private boolean trained = false;
	
	/**
	 * teacher constructor 
	 * @param firstName teacher first name
	 * @param lastName teacher last name
	 */
	public Teacher(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	/**
	 * train a teacher
	 */
	public void train() {
		this.trained = true;
	}
	
	/**
	 * check if a teacher is trained
	 * @return true if teacher is trained, and false otherwise
	 */
	public boolean isTrained() {
		return this.trained;
	}
}
