package com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.seitptt.model.Core;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.personnel.Teacher;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.TeachingRequirement;
import com.seitptt.view.View;

public class AdministratorController implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
	protected int selectedFilterIndexForAdmin;
	private ArrayList<String> selectedUserNameOfTeachers;
	private TeachingRequirement addTeachersInReq;
	
	public AdministratorController(Core model, View view) {
		this.model=model;
		this.view=view;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListOfEmployees listOfTeachers=model.getListOfTeachers();
		String selectedTeacherInString=view.getTeacherList().getSelectedValue().toString();
		String[] selectedTeacherName=selectedTeacherInString.split(" ");
		String firstName=selectedTeacherName[0];
		String lastName=selectedTeacherName[1];
					
		for(Employee i : listOfTeachers) {
			if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
				selectedUserNameOfTeachers.add(i.getUsername());
				
				//creates teaching request associated with a teacher, class, requirement
//				model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//3. Administrator Screen
		

		//3.1. (JComboBox) filter list of teachers
		if(e.getSource()==view.getTrainingSelector()) {
			selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
			view.trainingUpdate();
		}

		//3.2. (JComboBox) choose class requirements
		else if(e.getSource()==view.getRequirementSelector()) {
			int chosenReqIndex=view.getRequirementSelectedIndex();
			ListOfTeachingRequirements teachingReqirementList=model.getListOfTeachingRequirements();
			int j=0;
			for(TeachingRequirement i:teachingReqirementList) {
				if(j==chosenReqIndex) {
					addTeachersInReq=i;
					//i.getNumOfTeachers()--;
				}
				j++;
			}
		}

		//3.4. add teachers
		else if(e.getSource()==view.adminAddTeachersButton()) {
			//add selected teachers can
			//sub number of teachers from current teaching requirement
			ListOfEmployees listOfTeachers=model.getListOfTeachers();
			for(int j=0;j<selectedUserNameOfTeachers.size();j++) {
				for(Employee i : listOfTeachers) {
					if(i.getUsername().equals(selectedUserNameOfTeachers.get(j)) ) {
						//creates teaching request associated with a teacher, class, requirement
						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
						view.updateAdminScreen();
					}
				}
			}
		}
		//3.5. train teachers
		
	}
	
}
