package main.java.com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.personnel.Teacher;
import main.java.com.seitptt.model.processes.Classes;
import main.java.com.seitptt.model.processes.ListOfClasses;
import main.java.com.seitptt.model.processes.ListOfSemesters;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.Semester;
import main.java.com.seitptt.model.processes.TeachingRequirement;
import main.java.com.seitptt.view.View;

public class Controller implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
	private Semester chosenSemester;
	private int removeReqID, selectedFilterIndexForAdmin;
	private TeachingRequirement addTeachersInReq;
	private String currUser;
	private int changedNumberOfTeachers, filterRequirementsIndex;
	private String firstName, lastName;
	private String[] selectedTeacherName;
	
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
		return chosenSemester;
	}
	

	/**
	 * For Administrator screen when getTrainingSelector passes actionListener
	 * @return int selextedFilterIndexForAdmin
	 * */
	public int getSelectedFilterIndexForAdmin() {
		return selectedFilterIndexForAdmin;
	}

	
	
	public int getChangedNumberOfTeachers() {
		return changedNumberOfTeachers;
	}
	
	

	public int getFilterRequirementsIndex() {
		return filterRequirementsIndex;
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
		//3.3. when teacher selected, call button enabling method
		else if(currUser=="Administrator") {					
				if(!e.getValueIsAdjusting() && e.getSource()==view.getTeacherList()) {
					view.isTeacherListSelected();
				}
				else {
					view.isTeacherListSelected();
					return;
				}
		}
		//4.1
		else if(currUser=="PTTDirector") {

		}
	}
	
	
	private void createOtherController(String currUser, ActionEvent e) {
		if(e.getSource()==view.logoutButton) {
			view.createHomeScreen();
		}
		
		//2. Class Director Screen
		else if(currUser=="ClassDirector") {
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
				ListOfClasses listOfClasses=model.getListOfClasses().filterBySemester(chosenSemester);
				String currClassString=(String)view.getClassSelector().getSelectedItem();
				String[] splitCurrClass=currClassString.split(" ");
				String classCode=splitCurrClass[0];

				for(Classes currClass:listOfClasses) {
					if(currClass.getCode().equals(classCode)) {
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
		
		//3. Administrator Screen
		else if(currUser=="Administrator") {
			//3.1. (JComboBox) filter list of teachers
			if(e.getSource()==view.getTrainingSelector()) {
				selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
				view.updateAdminScreen();
			}

			//3.2. (JComboBox) choose class requirements
			if(e.getSource()==view.getRequirementSelector()) {
				int chosenReqIndex=view.getRequirementSelectedIndex();
				ListOfTeachingRequirements teachingReqirementList=model.getListOfTeachingRequirements();
				int j=0;
				for(TeachingRequirement i:teachingReqirementList) {
					if(j==chosenReqIndex) {
						addTeachersInReq=i;
					}
					j++;
				}
			}

			//3.4. add teachers
			if(e.getSource()==view.adminAddTeachersButton()) {
				//add selected teachers can
				//sub number of teachers from current teaching requirement
				int index=view.getTeacherList().getSelectedIndex();
				System.out.println("Index selected: "+ index);
				String s=(String)view.getTeacherList().getSelectedValue();
				System.out.println("Value selected: "+ s);
				
				selectedTeacherName=s.split(" ");
				firstName=selectedTeacherName[0];
				lastName=selectedTeacherName[1];
				ListOfEmployees listOfTeachers=model.getListOfTeachers();	
				
				for(Employee i : listOfTeachers) {
					if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
						int decrementNumOfTeachers=addTeachersInReq.getNumOfTeachers()-1;
						addTeachersInReq.setNumOfTeachers(decrementNumOfTeachers);

						view.updateAdminScreen();
					}
				}				
			}
			//3.5. train teachers
			if(e.getSource()==view.adminTrainTeachersButton()) {
				String s=(String)view.getTeacherList().getSelectedValue();
				selectedTeacherName=s.split(" ");
				firstName=selectedTeacherName[0];
				lastName=selectedTeacherName[1];
				
				ListOfEmployees listOfTeachers=model.getListOfTeachers();	
				for(Employee i: listOfTeachers) {
					if(i.getFirstName().equals(firstName) && i.getLastName().equals(lastName)) {
						model.organiseTraining((Teacher)i);
						view.updateAdminScreen();
					}
				}
			}
		}
		
		//4. PTT Director Screen
		else if (currUser=="PTTDirector"){
			//4.1. (JComboBox<String>)filterRequirements
			if(e.getSource()==view.pttDirRequirementsFilter()) {
				filterRequirementsIndex=view.pttDirRequirementsFilterIndex();
				view.updatePTTDirScreen();
			}
			
			//4.2. approve button
			
			//4.3. deny button
			
		}
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
		
			
		else {
			createOtherController(currUser, e);
		}
	}


}
