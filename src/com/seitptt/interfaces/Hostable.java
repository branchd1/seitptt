package com.seitptt.interfaces;

import com.seitptt.visitors.PrintToDatabaseVisitor;

public interface Hostable {
	public void accept(PrintToDatabaseVisitor visitor);
}
