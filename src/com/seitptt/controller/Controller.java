package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.Semester;
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
		if(e.getSource()==view.classDirLogin()) {
			view.createClassDirScreen();
		}else if(e.getSource()==view.adminLogin()) {
			view.createAdminScreen();
		}else if(e.getSource()==view.pttDirLogin()) {
			view.createPTTDirScreen();
		}
		
		//2. ClassDirectorView
		//selecting semester
		if(e.getSource()==view.getSemesterSelector()) {
			String currentSemester=view.getSemesterSelector().toString();
			String[] splitSemester=currentSemester.split(" ");
			int semesterNumber=Integer.parseInt(splitSemester[1]);
			int semesterYear=Integer.parseInt(splitSemester[2]);
			
			model.setCurrentSemester(semesterNumber, semesterYear);
		}
		
		//selecting class
		if(e.getSource()==view.getClassSelector()) {
			Classes selectedClass=view.getClassSelector();
			model.createAndAddTeachingRequirement(Integer.parseInt(view.getNumTeachers()), selectedClass);
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
