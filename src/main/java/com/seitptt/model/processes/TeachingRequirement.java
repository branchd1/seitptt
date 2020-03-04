package main.java.com.seitptt.model.processes;

import main.java.com.seitptt.interfaces.Hostable;
import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.database.PrintToDatabaseVisitor;

public class TeachingRequirement  implements Hostable{
	
	private int id;
	private int numOfTeachers;
	private Classes classRef;
	
	
	public TeachingRequirement(int numOfTeachers, Classes classRef) {
		this.setClassRef(classRef);
		this.setNumOfTeachers(numOfTeachers);
		this.setId(Database.getLatestIdFromDB(this)+1);
		
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		this.accept(visitor);
	}
	
	public TeachingRequirement(int id, int numOfTeachers, Classes classRef) {
		this.setClassRef(classRef);
		this.setNumOfTeachers(numOfTeachers);
		this.setId(id);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
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
	
	@Override
	public String toString() {
		return this.getNumOfTeachers() + " teachers required " + this.getClassRef();
	}

}