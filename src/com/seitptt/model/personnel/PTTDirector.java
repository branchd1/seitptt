package com.seitptt.model.personnel;

import com.seitptt.visitors.PrintToDatabaseVisitor;

public class PTTDirector extends Employee{
	public PTTDirector(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
