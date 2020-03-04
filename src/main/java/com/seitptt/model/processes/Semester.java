package main.java.com.seitptt.model.processes;

public class Semester {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	@Override
	public String toString() {
		return this.getNumber() + " " + this.getYear();
	}
	
}
