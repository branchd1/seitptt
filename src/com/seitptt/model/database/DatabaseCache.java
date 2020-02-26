package com.seitptt.model.database;

import com.seitptt.model.personnel.ListOfEmployees;

public class DatabaseCache {
	private static ListOfEmployees employeesCache;

	public static ListOfEmployees getEmployeesCache() {
		return employeesCache;
	}

	public static void setEmployeesCache(ListOfEmployees employeesCache) {
		DatabaseCache.employeesCache = employeesCache;
	}
	
}
