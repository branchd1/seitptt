package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.view.View;

public class Controller implements ActionListener, ListSelectionListener{
	
	private Core model;
	private View view;
	
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
		if(e.getSource()==view.getLoginButton()) {
			view.createClassDirScreen();
			view.createAdminScreen();
			view.createPTTDirScreen();
		}
		
		//2. ClassDirector Screen
		//selecting semester
		if(e.getSource()==view.getSemesterSelector()) {
			view.getSemesterSelector().
			model.setCurrentSemester(view.getSemesterSelector());
		}
		
		
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//Class director screen
		//ClassDirectorView/requirementsList
		
		//updateClassDirecScreen/requirementsList
		
	}
	

	
}
