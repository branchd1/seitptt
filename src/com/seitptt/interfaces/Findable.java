package com.seitptt.interfaces;

/**
 * Interface for list of classes to filter objects using an identifier
 * @author arnoldumakhihe 2445734u
 *
 */
public interface Findable<T> {
	/**
	 * find an object
	 * @param identifier to find object
	 * @return the found object
	 */
	public T find(int identifier);
	/**
	 * find an object
	 * @param identifier to find object
	 * @return the found object
	 */
	public T find(String identifier);
}
