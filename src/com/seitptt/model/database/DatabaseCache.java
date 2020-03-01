package com.seitptt.model.database;

import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;

/**
 * stores the cached data from database
 * @author arnoldumakhihe 2445734u
 *
 */
public class DatabaseCache {
	/**
	 * stores the list of employees
	 */
	private static ListOfEmployees employeesCache;
	
	/**
	 * stores the list of semesters
	 */
	private static ListOfSemesters semestersCache;
	
	/**
	 * stores the list of classes
	 */
	private static ListOfClasses classesCache;

	/**
	 * get employees cache
	 * @return ListOfEmployees containing the list of employees cached
	 */
	public static ListOfEmployees getEmployeesCache() {
		return DatabaseCache.employeesCache;
	}

	/**
	 * set employees cache from database
	 * @param employeesCache ListOfEmployees containing the list of employees
	 */
	public static void setEmployeesCache(ListOfEmployees employeesCache) {
		DatabaseCache.employeesCache = employeesCache;
	}
	
	/**
	 * get semesters cache
	 * @return ListOfSemesters containing the list of semesters cached
	 */
	public static ListOfSemesters getSemestersCache() {
		return DatabaseCache.semestersCache;
	}

	/**
	 * set semesters cache from database
	 * @param semestersCache ListOfSemesters containing the list of semesters
	 */
	public static void setSemestersCache(ListOfSemesters semestersCache) {
		DatabaseCache.semestersCache = semestersCache;
	}
	
	/**
	 * get classes cache
	 * @return ListOfClasses containing the list of classes cached
	 */
	public static ListOfClasses getClassesCache() {
		return DatabaseCache.classesCache;
	}

	/**
	 * set classes cache from database
	 * @param classesCache ListOfClasses containing the list of classes
	 */
	public static void setClassesCache(ListOfClasses classesCache) {
		DatabaseCache.classesCache = classesCache;
	}
	
}
