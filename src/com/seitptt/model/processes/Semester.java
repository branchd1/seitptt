package com.seitptt.model.processes;

import com.seitptt.interfaces.Hostable;
import com.seitptt.visitors.PrintToDatabaseVisitor;

public class Semester implements Hostable {
	private int id;
	private int number;
	private int year;
	
	public Semester(int id, int number, int year) {
		this.setId(id);
		this.setNumber(number);
		this.setYear(year);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Semester) {
			final Semester semester = (Semester) o;
			
			if(semester.getNumber()==this.getNumber() && semester.getYear()==this.getYear()) {
				return true;
			}
			
		} else {
			throw new RuntimeException("Cannot compare a semester with a non-semester object");
		}
		
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void accept(PrintToDatabaseVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	
}
