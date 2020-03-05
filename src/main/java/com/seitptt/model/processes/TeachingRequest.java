package main.java.com.seitptt.model.processes;

import main.java.com.seitptt.interfaces.Hostable;
import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.database.PrintToDatabaseVisitor;
import main.java.com.seitptt.model.personnel.Teacher;

public class TeachingRequest  implements Hostable{
	
	private int id;
	private Teacher teacher;
	private Classes classRef;
	private TeachingRequirement teachingRequirement;
	private boolean approval;
	
	
	public TeachingRequest(Teacher teacher, Classes classRef, TeachingRequirement teachingRequirement) {
		this.setTeacher(teacher);
		this.setClassRef(classRef);
		this.setTeachingRequirement(teachingRequirement);
		this.approval = false;
		this.setId(Database.getLatestIdFromDB(this)+1);
		
		PrintToDatabaseVisitor visitor = new PrintToDatabaseVisitor();
		this.accept(visitor);
	}
	
	public TeachingRequest(int id, Teacher teacher, Classes classRef, TeachingRequirement teachingRequirement, boolean approval) {
		this.setTeacher(teacher);
		this.setClassRef(classRef);
		this.setTeachingRequirement(teachingRequirement);
		this.approval = approval;
		this.setId(id);
	}
	
	public boolean isApproved() {
		return this.approval;
	}
	
	public void approve() {
		if(this.approval==true) {
			return;
		}
		this.approval = true;
		Database.approveTeachingRequestOnDB(this);
		Database.reduceTeachingRequirementCountOnDB(this.getTeachingRequirement());
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


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	public Classes getClassRef() {
		return classRef;
	}


	public void setClassRef(Classes classRef) {
		this.classRef = classRef;
	}


	public TeachingRequirement getTeachingRequirement() {
		return teachingRequirement;
	}


	public void setTeachingRequirement(TeachingRequirement teachingRequirement) {
		this.teachingRequirement = teachingRequirement;
	}
	
	public String toString() {
		return this.getTeacher() + " requested for " + this.getClassRef().getName();
	}

}
