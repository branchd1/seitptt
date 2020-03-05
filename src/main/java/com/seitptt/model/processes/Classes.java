package main.java.com.seitptt.model.processes;

/**
 * represents a class
 * @author Hope Elumeziem (2500799E)
 *
 */
public class Classes {

	/**
	 * string representing class code
	 */
	private String code;

	/**
	 * string representing name of class
	 */
	private String name;

	/**
	 * string representing semester
	 */
	private Semester semester;

	/**
	 * create a new class
	 * @param code class code
	 * @param name class name
	 * @param semester semester of class
	 */
	public Classes(String code, String name, Semester semester) {
		this.setCode(code);
		this.setName(name);
		this.setSemester(semester);
	}

	/**
	 * get class code
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * set class code
	 * @param code class code
	 */
	private void setCode(String code) {
		this.code = code;
	}


	/**
	 * get class name
	 * @return class name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set class name
	 * @param name class name
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * get semester
	 * @return semester of class
	 */
	public Semester getSemester() {
		return semester;
	}

	/**
	 * set semester
	 * @param semester class semester
	 */
	private void setSemester(Semester semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return this.getCode() + " " + this.getName();
	}

}
