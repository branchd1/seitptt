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
	private Semester chosenSemester;
	private int removeReqID, selectedFilterIndexForAdmin;
	private ArrayList<String> selectedUserNameOfTeachers;
	private TeachingRequirement addTeachersInReq;
	private ClassDirectorController classDirController;
	private AdministratorController adminController;
	private PTTDirectorController pttController;
	private String currUser;

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

		//2. ClassDirectorView
		//2.1. selecting semester
		else if(e.getSource()==view.getSemesterSelector()) {
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
		else if(e.getSource()==view.getAddRequirementButton()) {
			Classes chosenClass = null;
			ListOfClasses listOfClasses=model.getListOfClasses().filterBySemester(chosenSemester);
			String currClassString=(String)view.getClassSelector().getSelectedItem();
			String[] splitCurrClass=currClassString.split(" ");
			String classCode=splitCurrClass[0];

			for(Classes currClass:listOfClasses) {
				if(currClass.getCode().equals(classCode)) {//listOfClasses.find(currClassIndex)
					chosenClass=currClass;					
					int numberOfTeachers=Integer.parseInt(view.getNumTeachers());
					model.createAndAddTeachingRequirement(numberOfTeachers, chosenClass);
//					TeachingRequirement addReq = new TeachingRequirement(numberOfTeachers, chosenClass);
					
					view.updateClassDirScreen();
				}
			}
		}

		//2.4. remove requirements
		else if(e.getSource()==view.getRemoveRequirementButton()) {
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			for(TeachingRequirement selectedReq : listOfRequirements) {
				if(selectedReq.getId()==removeReqID) {
					model.removeTeachingRequirement(selectedReq);
				}
			}
			view.updateClassDirScreen();
		}	

//		//3. Administrator Screen
//		
//
//		//3.1. (JComboBox) filter list of teachers
//		else if(e.getSource()==view.getTrainingSelector()) {
//			selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
//			view.trainingUpdate();
//		}
//
//		//3.2. (JComboBox) choose class requirements
//		else if(e.getSource()==view.getRequirementSelector()) {
//			int chosenReqIndex=view.getRequirementSelectedIndex();
//			ListOfTeachingRequirements teachingReqirementList=model.getListOfTeachingRequirements();
//			int j=0;
//			for(TeachingRequirement i:teachingReqirementList) {
//				if(j==chosenReqIndex) {
//					addTeachersInReq=i;
//					//i.getNumOfTeachers()--;
//				}
//				j++;
//			}
//		}
//
//		//3.4. add teachers
//		else if(e.getSource()==view.adminAddTeachersButton()) {
//			//add selected teachers can
//			//sub number of teachers from current teaching requirement
//			ListOfEmployees listOfTeachers=model.getListOfTeachers();
//			for(int j=0;j<selectedUserNameOfTeachers.size();j++) {
//				for(Employee i : listOfTeachers) {
//					if(i.getUsername().equals(selectedUserNameOfTeachers.get(j)) ) {
//						//creates teaching request associated with a teacher, class, requirement
//						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
//						view.updateAdminScreen();
//					}
//				}
//			}
//		}
//		//3.5. train teachers
//
//
//		//4. PTT Director Screen
//		//4.1. filter requirements (All, Pending)
//		else if(e.getSource()==view.pttDirRequirementsFilter()) {
//			String filter=view.pttDirRequirementsFilter().toString();
//			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
//
//		}
		
	}

	/**
	 * value changed when view calls addListSelectionListener.
	 * @param ListSelectionEvent e
	 * */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//2.3. find id of remove requirements 
		//selects from list and find requested list's id in removeReqID
		if(e.getSource()==view.getRequirementsList()) {
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
		
		//3.3. find last name of selected teachers
		if(e.getSource()==view.getTeacherList()) {
			
			ListOfEmployees listOfTeachers=model.getListOfTeachers();
			String selectedTeacherInString=view.getTeacherList().getSelectedValue().toString();
			String[] selectedTeacherName=selectedTeacherInString.split(" ");
			String firstName=selectedTeacherName[0];
			String lastName=selectedTeacherName[1];
						
			for(Employee i : listOfTeachers) {
				if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
					selectedUserNameOfTeachers.add(i.getUsername());
					
					//creates teaching request associated with a teacher, class, requirement
//					model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
				}
			}
		}
	}
}
