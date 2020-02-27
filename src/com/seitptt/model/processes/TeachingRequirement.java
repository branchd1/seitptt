package com.seitptt.model.processes;

import com.seitptt.interfaces.Hostable;
import com.seitptt.visitors.PrintToDatabaseVisitor;

public class TeachingRequirement  implements Hostable{
	
	private int id;
	private int numOfTeachers;
	private Class classRef;
	

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


	public Class getClassRef() {
		return classRef;
	}


	public void setClassRef(Class classRef) {
		this.classRef = classRef;
	}

}
