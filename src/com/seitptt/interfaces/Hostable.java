package com.seitptt.interfaces;

import com.seitptt.model.database.PrintToDatabaseVisitor;

/**
 * interface for a class that accepts visitor to print to database
 * @author arnoldumakhihe 2445734u
 *
 */
public interface Hostable {
	/**
	 * accept a visitor
	 * @param visitor visitor to accept
	 */
	public void accept(PrintToDatabaseVisitor visitor);
}
