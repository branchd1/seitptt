package main.java.com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.database.Database;
import main.java.com.seitptt.model.personnel.ClassDirector;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.personnel.Teacher;
import main.java.com.seitptt.model.processes.Classes;
import main.java.com.seitptt.model.processes.ListOfClasses;
import main.java.com.seitptt.model.processes.ListOfSemesters;
import main.java.com.seitptt.model.processes.ListOfTeachingRequests;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.Semester;
import main.java.com.seitptt.model.processes.TeachingRequest;
import main.java.com.seitptt.model.processes.TeachingRequirement;
import main.java.com.seitptt.view.View;

public class Controller implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
	private Semester chosenSemester;
	private int removeReqID, selectedFilterIndexForAdmin;
	private String selectedUserNameOfTeachers;
	private TeachingRequirement addTeachersInReq;
	private String currUser;
	private int changedNumberOfTeachers;
	private String selectedTeacher, firstName, lastName;
	private String[] selectedTeacherName;
	
//	protected ClassDirectorController classDirController=new ClassDirectorController(model, view);
//	protected AdministratorController adminController=new AdministratorController(model, view);
//	protected PTTDirectorController pttController=new PTTDirectorController(model, view);

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

	/**
	 * value changed when view calls addListSelectionListener.
	 * @param ListSelectionEvent e
	 * */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//2.3. find id of remove requirements 
		//selects from list and find requested list's id in removeReqID
		if(currUser=="ClassDirector") {
//			classDirController=new ClassDirectorController(model, view);
//			this.classDirController.valueChanged(e);
			int reqIndex=view.getRequirementsList().getSelectedIndex();
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			int j=0;
			for(TeachingRequirement i : listOfRequirements) {
				if(j==reqIndex) {
					removeReqID=i.getId();
				}
				j++;
			}
		}else if(currUser=="Administrator" && e.getSource()==view.getTeacherList()) {
//			if(e.getSource()==view.getRequirementsList()) {
//			adminController=new AdministratorController(model, view);
//			this.adminController.valueChanged(e);
//			if(!e.getValueIsAdjusting()) {
//			if(e.getSource()==view.getTeacherList()) {
//				ListSelectionListener[] lsl=view.getListeners(ListSelectionListener.class);
				
				System.out.println("Event. isAdjusting is "+e.getValueIsAdjusting()+"; selected indexes: ");
				
				if(!e.getValueIsAdjusting()) {
//					ListOfEmployees listOfTeachers=model.getListOfTeachers();
//
//					JList list=(JList)e.getSource();
//					int selections[]=list.getSelectedIndices();
//					selectedTeacher=(String) list.getSelectedValue();
//					System.out.println("after casting list.getSelectedValue(), (String)list.getSelectedValue: "+(String) list.getSelectedValue());
//
//					System.out.println("before selectedTeacher.split, selectedTeacher:  "+selectedTeacher);
//					
//					selectedTeacherName=selectedTeacher.split(" ");
//					firstName=selectedTeacherName[0];
//					lastName=selectedTeacherName[1];
//								
//					for(Employee i : listOfTeachers) {
//						if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
//							selectedUserNameOfTeachers=i.getUsername();	
//						}
//					}
				}
				else {
					return;
				}
//			}
//			}
//			}
		}else if(currUser=="PTTDirector") {
//			pttController=new PTTDirectorController(model, view);
//			this.pttController.valueChanged(e);
		}
	}
	
	
	private void createOtherController(String currUser, ActionEvent e) {
		if(e.getSource()==view.logoutButton) {
			view.createHomeScreen();
		}
		
		else if(currUser=="ClassDirector") {
			//classDirController=new ClassDirectorController(model, view);
//			this.classDirController.actionPerformed(e);
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
//				ListOfSemesters listOfSemesters=model.getListOfSemesters();
//				int currSemesterIndex=view.getSemesterSelectedIndex();
//
//				for(Semester currSemester:listOfSemesters) {
//					if(currSemester.getId()-1==currSemesterIndex) {
//						chosenSemester=currSemester;
//					}
//				}
				
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
//				int reqIndex=view.getRequirementsList().getSelectedIndex();
//				ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
//				int j=0;
//				for(TeachingRequirement i : listOfRequirements) {
//					if(j==reqIndex) {
//						removeReqID=i.getId();
//					}
//					j++;
//				}
				ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
				for(TeachingRequirement selectedReq : listOfRequirements) {
					if(selectedReq.getId()==removeReqID) {
						model.removeTeachingRequirement(selectedReq);
					}
				}
				view.updateClassDirScreen();
			}
		}
		else if(currUser=="Administrator") {
			//			adminController=new AdministratorController(model, view);
//			this.adminController.actionPerformed(e);
			//3. Administrator Screen
			//3.1. (JComboBox) filter list of teachers
			if(e.getSource()==view.getTrainingSelector()) {
				selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
				view.trainingUpdate();
			}

			//3.2. (JComboBox) choose class requirements
			if(e.getSource()==view.getRequirementSelector()) {
//				selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();

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
//				selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
//
//				int chosenReqIndex=view.getRequirementSelectedIndex();
//				ListOfTeachingRequirements teachingReqirementList=model.getListOfTeachingRequirements();
//				int j=0;
//				for(TeachingRequirement i:teachingReqirementList) {
//					if(j==chosenReqIndex) {
//						addTeachersInReq=i;
//						//i.getNumOfTeachers()--;
//					}
//					j++;
//				}
//				
//				ListOfEmployees listOfTeachers=model.getListOfTeachers();
//				String selectedTeacherInString=view.getTeacherList().getSelectedValue().toString();
//				String[] selectedTeacherName=selectedTeacherInString.split(" ");
//				String firstName=selectedTeacherName[0];
//				String lastName=selectedTeacherName[1];
//							
//				for(Employee i : listOfTeachers) {
//					if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
//						selectedUserNameOfTeachers=i.getUsername();	
//					}
//				}
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
//						selectedUserNameOfTeachers=i.getUsername();	
						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
						int decrementNumOfTeachers=addTeachersInReq.getNumOfTeachers()-1;
						addTeachersInReq.setNumOfTeachers(decrementNumOfTeachers);
						
						changedNumberOfTeachers=addTeachersInReq.getNumOfTeachers();
						
						//for checking
						ListOfTeachingRequests listOfTeachingRequests = Database.getTeachingRequestsFromDB();
						for(TeachingRequest teachingRequest : listOfTeachingRequests) {
							System.out.println(teachingRequest.getId() + " " + 
									teachingRequest.getTeacher().getFirstName() + " " + 
									teachingRequest.getClassRef().getCode() + " " + 
									teachingRequest.getTeachingRequirement().getId() + " " + teachingRequest.isApproved());
						}
						
						view.updateAdminScreen();
					}
				}
				
				
//					for(Employee i : listOfTeachers) {
//						if(i.getUsername().equals(selectedUserNameOfTeachers) ) {
							//creates teaching request associated with a teacher, class, requirement
							
//							model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
//							int decrementNumOfTeachers=addTeachersInReq.getNumOfTeachers()-1;
//							addTeachersInReq.setNumOfTeachers(decrementNumOfTeachers);
//							
//							changedNumberOfTeachers=addTeachersInReq.getNumOfTeachers();
//							System.out.println("5. "+changedNumberOfTeachers);
							
							
//							//for checking
//							ListOfTeachingRequests lTRequest=Database.getTeachingRequestsFromDB();
//							for(TeachingRequest request : lTRequest) {
//								System.out.println("6. "+request.getId());
//							}
//							
//							view.updateAdminScreen();
//						}
//					}
				
			}
			//3.5. train teachers
		}
		else if (currUser=="PTTDirector"){
			//			pttController=new PTTDirectorController(model, view);
//			this.pttController.actionPerformed(e);
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
		
//		else if(currUser=="ClassDirector") {
////				classDirController=new ClassDirectorController(model, view);
//				this.classDirController.actionPerformed(e);
//			}
//		else if(currUser=="Administrator") {
////				adminController=new AdministratorController(model, view);
//				this.adminController.actionPerformed(e);
//			}
//		else if (currUser=="PTTDirector"){
////				pttController=new PTTDirectorController(model, view);
//				this.pttController.actionPerformed(e);
//			}
	}


}
