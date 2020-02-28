package com.seitptt.model.database;

import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.ListOfSemesters;

public class DatabaseCache {
	private static ListOfEmployees employeesCache;
	private static ListOfSemesters semestersCache;

	public static ListOfEmployees getEmployeesCache() {
		return DatabaseCache.employeesCache;
	}

	public static void setEmployeesCache(ListOfEmployees employeesCache) {
		DatabaseCache.employeesCache = employeesCache;
	}
	
	public static ListOfSemesters getSemestersCache() {
		return DatabaseCache.semestersCache;
	}

	public static void setSemestersCache(ListOfSemesters semestersCache) {
		DatabaseCache.semestersCache = semestersCache;
	}
	
}
