package com.seitptt.model.processes;

import com.seitptt.interfaces.Hostable;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.visitors.PrintToDatabaseVisitor;

public class TeachingRequest  implements Hostable{
	
	private int id;
	private Teacher teacher;
	private Class classRef;
	private TeachingRequirement teachingRequirement;
	

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		
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


	public Class getClassRef() {
		return classRef;
	}


	public void setClassRef(Class classRef) {
		this.classRef = classRef;
	}


	public TeachingRequirement getTeachingRequirement() {
		return teachingRequirement;
	}


	public void setTeachingRequirement(TeachingRequirement teachingRequirement) {
		this.teachingRequirement = teachingRequirement;
	}

}
