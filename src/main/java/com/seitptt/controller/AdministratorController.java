package main.java.com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.personnel.Teacher;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.TeachingRequirement;
import main.java.com.seitptt.view.View;

public class AdministratorController implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
	protected int selectedFilterIndexForAdmin;
	private String selectedUserNameOfTeachers;
	private TeachingRequirement addTeachersInReq;
	
	public AdministratorController(Core model, View view) {
		this.model=model;
		this.view=view;
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
		if(e.getSource()==view.getRequirementSelector()) {
			selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();

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
		if(e.getSource()==view.adminAddTeachersButton()) {
			selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();

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
			
			ListOfEmployees listOfTeachers=model.getListOfTeachers();
			String selectedTeacherInString=view.getTeacherList().getSelectedValue().toString();
			String[] selectedTeacherName=selectedTeacherInString.split(" ");
			String firstName=selectedTeacherName[0];
			String lastName=selectedTeacherName[1];
						
			for(Employee i : listOfTeachers) {
				if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
					selectedUserNameOfTeachers=i.getUsername();	
				}
			}
			//add selected teachers can
			//sub number of teachers from current teaching requirement
//			ListOfEmployees listOfTeachers=model.getListOfTeachers();
			
				for(Employee i : listOfTeachers) {
					if(i.getUsername().equals(selectedUserNameOfTeachers) ) {
						//creates teaching request associated with a teacher, class, requirement
						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
						view.updateAdminScreen();
					}
				}
			
		}
		//3.5. train teachers
		
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
				selectedUserNameOfTeachers=i.getUsername();	
			}
		}
		
	}
	
}
