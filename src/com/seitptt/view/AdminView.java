package com.seitptt.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;

public class AdminView extends JPanel {
	//class attributes
	private Controller controller;
     
	
	
	
	
	public AdminView(Controller controller, int UNIT) {
		this.controller=controller;
		this.setLayout(new BorderLayout());
		Border adminBorder = BorderFactory.createEmptyBorder(2 * UNIT, 2 * UNIT, 3 * UNIT, 2 * UNIT);
		this.setBorder(adminBorder);
		

	}

}
