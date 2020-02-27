package com.seitptt.model.personnel;

import com.seitptt.visitors.PrintToDatabaseVisitor;

public class Administrator extends Employee {
	public Administrator(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
