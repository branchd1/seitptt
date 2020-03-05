package main.java.com.seitptt.model.processes;

/**
 * represents a semester
 * @author Hope Elumeziem (2500799E)
 *
 */
public class Semester {
	/**
	 * semester id
	 */
	private int id;

	/**
	 * semester number
	 */
	private int number;

	/**
	 * semester year
	 */
	private int year;

	/**
	 * create new semester
	 * @param id semester id
	 * @param number semester number
	 * @param year semester year
	 */
	public Semester(int id, int number, int year) {
		this.setId(id);
		this.setNumber(number);
		this.setYear(year);
	}

	/**
	 * get semester number
	 * @return int representing semester number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * set semester number
	 * @param number semester number
	 */
	private void setNumber(int number) {
		this.number = number;
	}

	/**
	 * get semester year
	 * @return int representing semester year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * set semester year
	 * @param year semester year
	 */
	private void setYear(int year) {
		this.year = year;
	}

	/**
	 * get semester id
	 * @return int representing semester id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id
	 * @param id semester id
	 */
	private void setId(int id) {
		this.id = id;
	}

	@Override
	/**
	 * check if a semester is equal to another object
	 */
	public boolean equals(Object o) {
		if(o instanceof Semester) {
			final Semester semester = (Semester) o;

			// semester are equal if they have the same number and year
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
