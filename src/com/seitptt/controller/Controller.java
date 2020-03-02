package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.seitptt.model.Core;
import com.seitptt.model.authentication.Auth;
import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.Administrator;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.PTTDirector;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.view.View;

public class Controller implements ActionListener{
	
	private Core model;
	private View view;
	private ClassDirector classDirector;
	private PTTDirector pttDirector;
	private Administrator admin;
	private Teacher teacher;
	
	/**
	 * @param Core model
	 * @param View view
	 * */
	public Controller(Core model) {
		this.model=model;
	}
	
	public void setView(View view) {
		this.view=view;
	}
	
	public void actionPerformed(ActionEvent e) {
		//1. login to each type of user
//		if(e.getSource()==view.createClassDirScreen())
		
		//ClassDirector
		//if user selects certain class, 
		//should pass number of teachers that entered by class director and class name to model
		//then model will add to the list
		//I'll call the updated list screen
		
//		if(e.getSource()==view.createClassDirScreen()) {
//			
//			view.getNumTutors()
//		}
		
		
		
		
	}
	

	
}
