package com.seitptt.model.processes;

public class Semester {
	private int number;
	private int year;
	
	public Semester(int number, int year) {
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
	
}
