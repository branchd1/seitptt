package com.seitptt.model.processes;

import com.seitptt.interfaces.Hostable;
import com.seitptt.visitors.PrintToDatabaseVisitor;

public class TeachingRequirement  implements Hostable{
	
	private int id;
	private int numOfTeachers;
	private Classes classRef;
	
	
	public TeachingRequirement(int numOfTeachers, Classes classRef) {
		this.setClassRef(classRef);
		this.setNumOfTeachers(numOfTeachers);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumOfTeachers() {
		return numOfTeachers;
	}


	public void setNumOfTeachers(int numOfTeachers) {
		this.numOfTeachers = numOfTeachers;
	}


	public Classes getClassRef() {
		return classRef;
	}


	public void setClassRef(Classes classRef) {
		this.classRef = classRef;
	}

}
