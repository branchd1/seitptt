package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;
import com.seitptt.model.processes.TeachingRequirement;
import com.seitptt.view.View;

public class ClassDirectorController implements ActionListener, ListSelectionListener{
	
	private Core model;
	private View view;
	
	protected Semester chosenSemester;
	private int removeReqID;
	
	public ClassDirectorController(Core model, View view) {
		this.model=model;
		this.view=view;
	}
	
	
	public void actionPerformed(ActionEvent e) {
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
			System.out.println(chosenSemester.toString());
			
			ListOfClasses listOfClasses=model.getListOfClasses().filterBySemester(chosenSemester);
			String currClassString=(String)view.getClassSelector().getSelectedItem();
			String[] splitCurrClass=currClassString.split(" ");
			String classCode=splitCurrClass[0];

			
			
			for(Classes currClass:listOfClasses) {
				if(currClass.getCode().equals(classCode)) {
					System.out.println(currClass.toString());
					int numberOfTeachers=Integer.parseInt(view.getNumTeachers());
					model.createAndAddTeachingRequirement(numberOfTeachers, currClass);
					view.updateClassDirScreen();
				}
			}
		}

		//2.4. remove requirements
		if(e.getSource()==view.getRemoveRequirementButton()) {
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			for(TeachingRequirement selectedReq : listOfRequirements) {
				if(selectedReq.getId()==removeReqID) {
					model.removeTeachingRequirement(selectedReq);
				}
			}
			view.updateClassDirScreen();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		//2.3. find id of remove requirements 
		//selects from list and find requested list's id in removeReqID
			int removeReqIndex;
			int reqIndex=view.getRequirementsList().getSelectedIndex();
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			int j=0;
			for(TeachingRequirement i : listOfRequirements) {
				if(j==reqIndex) {
					removeReqID=i.getId();
				}
				j++;
			}
		
	}	
}
