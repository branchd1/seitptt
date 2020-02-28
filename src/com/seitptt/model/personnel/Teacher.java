package com.seitptt.model.personnel;

public class Teacher extends Employee{
	private boolean trained = false;
	
	public Teacher(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public void train() {
		this.trained = true;
	}
}
