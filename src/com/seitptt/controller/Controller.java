package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.model.database.Database;
import com.seitptt.model.personnel.ClassDirector;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequests;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
import com.seitptt.model.processes.TeachingRequest;
import com.seitptt.model.processes.TeachingRequirement;
import com.seitptt.view.View;

public class Controller implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
//	private Semester chosenSemester;
//	private int removeReqID, selectedFilterIndexForAdmin;
//	private ArrayList<String> selectedUserNameOfTeachers;
//	private TeachingRequirement addTeachersInReq;
	private String currUser;
	
	private ClassDirectorController classDirController;
	private AdministratorController adminController;
	private PTTDirectorController pttController;

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

	/**
	 * For Class Director screen when getSemesterSelector passes actionListener
	 * @return Semester chosenSemester
	 * */
	public Semester getChosenSemester() {
		return classDirController.chosenSemester;
	}
	

	/**
	 * For Administrator screen when getTrainingSelector passes actionListener
	 * @return int selextedFilterIndexForAdmin
	 * */
	public int getSelectedFilterIndexForAdmin() {
		return adminController.selectedFilterIndexForAdmin;
	}

	
	
	/**
	 * actionPerformed called from view (actionListener)
	 * */
	public void actionPerformed(ActionEvent e) {
		//1. login to each type of user
		if(e.getSource()==view.classDirLogin()) {
			currUser="ClassDirector";
			model.setCurrentUser(currUser);
			view.createClassDirScreen();
		}
		
		else if(e.getSource()==view.adminLogin()) {
			currUser="Administrator";
			model.setCurrentUser(currUser);
			view.createAdminScreen();
		}
		
		else if(e.getSource()==view.pttDirLogin()) {
			currUser="PTTDirector";
			model.setCurrentUser(currUser);
			view.createPTTDirScreen();
		}
		
			
		else if(currUser=="ClassDirector") {
				classDirController=new ClassDirectorController(model, view);
				classDirController.actionPerformed(e);
			}
		else if(currUser=="Administrator") {
				adminController=new AdministratorController(model, view);
				adminController.actionPerformed(e);
			}
		else if (currUser=="PTTDirector"){
				pttController=new PTTDirectorController(model, view);
				pttController.actionPerformed(e);
			}
	}

	/**
	 * value changed when view calls addListSelectionListener.
	 * @param ListSelectionEvent e
	 * */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//2.3. find id of remove requirements 
		//selects from list and find requested list's id in removeReqID
		if(currUser=="ClassDirector") {
			classDirController=new ClassDirectorController(model, view);
			classDirController.valueChanged(e);
		}else if(currUser=="Administrator") {
			adminController=new AdministratorController(model, view);
			adminController.valueChanged(e);
		}else if(currUser=="PTTDirector") {
			pttController=new PTTDirectorController(model, view);
			pttController.valueChanged(e);
		}
	}
}
