package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.model.database.Database;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
import com.seitptt.model.processes.TeachingRequirement;
import com.seitptt.view.View;

public class Controller implements ActionListener, ListSelectionListener{
	
	private Core model;
	private View view;
	private Semester chosenSemester;
	
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
	
	public Semester getChosenSemester() {
		return chosenSemester;
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
		//2.1. selecting semester
		if(e.getSource()==view.getSemesterSelector()) {
			ListOfSemesters listOfSemesters=model.getListOfSemesters();
			int currSemesterIndex=view.getSemesterSelectedIndex();

			for(Semester currSemester:listOfSemesters) {
				if(currSemester.getId()-1==currSemesterIndex) {
					chosenSemester=currSemester;
				}
			}
			
			view.enableClassList();
		}
		
		//2.2. add requirements
		if(e.getSource()==view.getAddRequirementButton()) {
			Classes chosenClass = null;
			ListOfClasses listOfClasses=model.getListOfClasses().filterBySemester(chosenSemester);
			String currClassString=(String)view.getClassSelector().getSelectedItem();
			String[] splitCurrClass=currClassString.split(" ");
			String classCode=splitCurrClass[0];
			
			for(Classes currClass:listOfClasses) {
				if(currClass.getCode().equals(classCode)) {//listOfClasses.find(currClassIndex)
					chosenClass=currClass;					
					int numberOfTeachers=Integer.parseInt(view.getNumTeachers());
					TeachingRequirement addReq = new TeachingRequirement(numberOfTeachers, chosenClass);

					view.updateClassDirScreen();
				}
			}
		}
		
//		if(e.getSource()==view.getRemoveRequirementButton()) {
//			valueChanged(ListSelectionEvent e){
//				TeachingRequirement removeReq;
//				int reqIndex=view.getRequirementsList().getSelectedIndex();
//				ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
//				for(TeachingRequirement selectedReq : listOfRequirements) {
//					if(selectedReq.getId()-1==reqIndex) {
//						removeReq=selectedReq;
//						model.removeTeachingRequirement(removeReq);
//					}
//				}
//				view.updateClassDirScreen();
//				
//			}
//		}
		
		
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//Class director screen
		//requirementsListPanel-for updating list to the list panel.
		if(e.getSource()==view.getRemoveRequirementButton()) {

			TeachingRequirement removeReq;
			int reqIndex=view.getRequirementsList().getSelectedIndex();
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			for(TeachingRequirement selectedReq : listOfRequirements) {
				if(selectedReq.getId()-1==reqIndex) {
					removeReq=selectedReq;
					model.removeTeachingRequirement(removeReq);
				}
			}
			view.updateClassDirScreen();
		}
		
	}
	

	
}
