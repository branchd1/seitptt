package com.seitptt.model.personnel;

import com.seitptt.visitors.PrintToDatabaseVisitor;

public class ClassDirector extends Employee{
	public ClassDirector(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
