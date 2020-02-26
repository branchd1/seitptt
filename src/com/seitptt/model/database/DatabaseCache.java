package com.seitptt.model.database;

import java.util.ArrayList;

import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.Semester;

public class DatabaseCache {
	private static ListOfEmployees employeesCache;
	private static ArrayList<Semester> semestersCache;

	public static ListOfEmployees getEmployeesCache() {
		return DatabaseCache.employeesCache;
	}

	public static void setEmployeesCache(ListOfEmployees employeesCache) {
		DatabaseCache.employeesCache = employeesCache;
	}
	
	public static ArrayList<Semester> getSemestersCache() {
		return DatabaseCache.semestersCache;
	}

	public static void setSemestersCache(ArrayList<Semester> semestersCache) {
		DatabaseCache.semestersCache = semestersCache;
	}
	
}
