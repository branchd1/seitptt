package com.seitptt.model.processes;


public class Classes {

	
	private String code;
	private String name;
	private Semester semester;
	
	
	public Classes(String code, String name, Semester semester) {
		this.setCode(code);
		this.setName(name);
		this.setSemester(semester);
	}
	



	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Semester getSemester() {
		return semester;
	}


	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	

}
