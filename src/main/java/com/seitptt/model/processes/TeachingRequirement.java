package main.java.com.seitptt.model.processes;

import main.java.com.seitptt.interfaces.Hostable;
import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.database.PrintToDatabaseVisitor;

/**
 * represents requirement
 * @author Hope Elumeziem (2500799E)
 *
 */
public class TeachingRequirement  implements Hostable{
	/**
	 * id
	 */
	private int id;

	/**
	 * number of teachers
	 */
	private int numOfTeachers;

	/**
	 * class
	 */
	private Classes classRef;

	/**
	 * creates a requirement. Used by program to create new requirement.
	 * @param numOfTeachers number of teachers
	 * @param classRef class
	 */
	public TeachingRequirement(int numOfTeachers, Classes classRef) {
		this.setClassRef(classRef);
		this.setNumOfTeachers(numOfTeachers);
		this.setId(Database.getLatestIdFromDB(this)+1);

		// add to database whenever created
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		this.accept(visitor);
	}

	/**
	 * creates a requirement. Used by database when loading requests from database.
	 * @param id id
	 * @param numOfTeachers number of teachers
	 * @param classRef class
	 */
	public TeachingRequirement(int id, int numOfTeachers, Classes classRef) {
		this.setClassRef(classRef);
		this.setNumOfTeachers(numOfTeachers);
		this.setId(id);
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		visitor.visit(this);
	}

	/**
	 * get id
	 * @return int representing id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id
	 * @param id requirement id
	 */
	private void setId(int id) {
		this.id = id;
	}


	/**
	 * get number of teachers
	 * @return int representing requirement number of teachers
	 */
	public int getNumOfTeachers() {
		return numOfTeachers;
	}


	/**
	 * set number of teachers
	 * @param numOfTeachers requirement number of teachers
	 */
	public void setNumOfTeachers(int numOfTeachers) {
		this.numOfTeachers = numOfTeachers;
	}

	/**
	 * get class
	 * @return Classes class
	 */
	public Classes getClassRef() {
		return classRef;
	}


	/**
	 * set class
	 * @param classRef Classes representing requirement class
	 */
	private void setClassRef(Classes classRef) {
		this.classRef = classRef;
	}

	@Override
	public String toString() {
		return this.getNumOfTeachers() + " teachers required " + this.getClassRef();
	}

	@Override
	/**
	 * check equality. requirements are equal if the classes are the same.
	 */
	public boolean equals(Object o) {
		if(o instanceof TeachingRequirement) {
			TeachingRequirement teachingRequirement = (TeachingRequirement) o;
			if(teachingRequirement.getClassRef()==this.getClassRef()) {
				return true;
			}
		}else {
			throw new RuntimeException("Cannot compare a teaching requirement with a non teaching requirement object");
		}
		return false;
	}

}
