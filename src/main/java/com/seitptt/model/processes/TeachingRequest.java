package main.java.com.seitptt.model.processes;

import main.java.com.seitptt.interfaces.Hostable;
import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.database.PrintToDatabaseVisitor;
import main.java.com.seitptt.model.personnel.Teacher;

/**
 * represents a teaching request
 * @author Hope Elumeziem (2500799E)
 *
 */
public class TeachingRequest  implements Hostable{

	/**
	 * id
	 */
	private int id;

	/**
	 * teacher requested
	 */
	private Teacher teacher;

	/**
	 * class of request
	 */
	private Classes classRef;

	/**
	 * connected teaching requirement
	 */
	private TeachingRequirement teachingRequirement;

	/**
	 * approval state
	 */
	private boolean approval;

	/**
	 * creates a new teaching request. Used by program to create new request.
	 * @param teacher teacher of request
	 * @param classRef class of request
	 * @param teachingRequirement connected requirement
	 */
	public TeachingRequest(Teacher teacher, Classes classRef, TeachingRequirement teachingRequirement) {
		this.setTeacher(teacher);
		this.setClassRef(classRef);
		this.setTeachingRequirement(teachingRequirement);
		this.approval = false;

		// set id automatically
		this.setId(Database.getLatestIdFromDB(this)+1);

		// print to database whenever a new requirement is created
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		this.accept(visitor);
	}

	/**
	 * creates a new teaching request. Used by database when loading requests from database.
	 * @param id id
	 * @param teacher teacher of request
	 * @param classRef class of request
	 * @param teachingRequirement connected requirement
	 * @param approval if approved
	 */
	public TeachingRequest(int id, Teacher teacher, Classes classRef, TeachingRequirement teachingRequirement, boolean approval) {
		this.setTeacher(teacher);
		this.setClassRef(classRef);
		this.setTeachingRequirement(teachingRequirement);
		this.approval = approval;
		this.setId(id);
	}

	/**
	 * is request approved
	 * @return true if approved, else false
	 */
	public boolean isApproved() {
		return this.approval;
	}

	/**
	 * approve the request if not already approved
	 */
	public void approve() {
		if(this.approval==true) {
			return;
		}
		this.approval = true;

		// approve in database and reduce associated requirement count
		Database.approveTeachingRequestOnDB(this);
		Database.reduceTeachingRequirementCountOnDB(this.getTeachingRequirement());
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
	 * @param id teaching request id
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * get teacher
	 * @return Teacher teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * set teacher
	 * @param teacher Teacher representing teaching request teacher
	 */
	private void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	 * @param classRef Classes object representing teaching request class
	 */
	private void setClassRef(Classes classRef) {
		this.classRef = classRef;
	}

	/**
	 * set requirement
	 * @return TeachingRequirement requirement
	 */
	public TeachingRequirement getTeachingRequirement() {
		return teachingRequirement;
	}

	/**
	 * set requirement
	 * @param teachingRequirement TeachingRequirement object representing teaching request requirement
	 */
	private void setTeachingRequirement(TeachingRequirement teachingRequirement) {
		this.teachingRequirement = teachingRequirement;
	}

	@Override
	public String toString() {
		return this.getTeacher() + " requested for " + this.getClassRef().getName();
	}

	@Override
	/**
	 * checks if 2 requests are equal. they are equal if 2 requests have same class and teacher
	 */
	public boolean equals(Object o) {
		if(o instanceof TeachingRequest) {
			TeachingRequest teachingRequest = (TeachingRequest) o;
			if(teachingRequest.getClassRef()==this.getClassRef() && teachingRequest.getTeacher()==this.getTeacher()) {
				return true;
			}
		}else {
			throw new RuntimeException("Cannot compare a teaching request with a non teaching request object");
		}
		return false;
	}

}
