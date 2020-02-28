package com.seitptt.controller;

import java.awt.event.ActionEvent;

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

public class Controller {
	
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
		//when e.getSource()==view.loginButton
		//Auth.login returns employee or null
//		if(e.getSource()==view.loginButton) {
			Database.LoadDatabase();
			ListOfEmployees listOfEmployees = Database.getEmployeesFromDB();

			Employee typeOfEmployee=Auth.login(view.getUsername(), view.getPassword());

			//first, check the user is authorized member
			if(typeOfEmployee==null) {//wrong user. should access again. 
				//view.resetToHome();
				//or
//				System.exit(0);
			}else if(typeOfEmployee instanceof ClassDirector) {//then, check the type of user: 1. ClassDirector
				view.createClassDirScreen();
			}
			

//		}
		
		
		
		
	}
	

	
}
