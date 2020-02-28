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
		//when e.getSource()==view.loginButton
		//Auth.login returns employee or null
		if(e.getSource()==view.loginButton) {
			Employee typeOfEmployee=model.login(view.getUsername(), view.getPassword());

			//first, check the user is authorized member
			if(typeOfEmployee==null) {//wrong user. should access again. 
				view.wrongInput();
			}else if(typeOfEmployee instanceof ClassDirector) {//then, check the type of user: 1. ClassDirector
				view.createClassDirScreen();
				model.setCurrentUser(typeOfEmployee);
			}else if(typeOfEmployee instanceof Administrator) {//2. create administrator screen
				view.createAdminScreen();
				model.setCurrentUser(typeOfEmployee);
			}else if(typeOfEmployee instanceof PTTDirector) {//3. create PTTDirector screen
				view.createPTTDirScreen();
				model.setCurrentUser(typeOfEmployee);
			}else {//4. when Teacher logged in
				view.noAccess();
			}
		}
		
		//ClassDirector
		if(e.getSource()==view.classSelector) {
			model.addTeachingRequirement(id, view.getNumTutors(), view.classSelector);
			
		}
		
		
		
		
	}
	

	
}
