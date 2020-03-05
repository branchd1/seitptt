package main.java.com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.com.seitptt.model.Core;
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

/**
 * represents controller
 * @author Bokyung Lee (2431088L)
 *
 */
public class Controller implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;

	private Semester chosenSemester;
	private TeachingRequirement addTeachersInReq;

	private int selectedFilterIndexForAdmin;
	private String currUser;
	private int changedNumberOfTeachers, filterRequirementsIndex;

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


	private void setAddTeachersInReq(TeachingRequirement addTeachersInReq) {
		this.addTeachersInReq = addTeachersInReq;
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
			if(!e.getValueIsAdjusting() && e.getSource()==view.getRequirementsList()) {
				view.isClassDirListSelected();
			}else {
				view.isClassDirListSelected();
				return;
			}
		}
		//3.3. when teacher selected, call button enabling method
		else if(currUser=="Administrator") {					
			if(!e.getValueIsAdjusting() && e.getSource()==view.getTeacherList()) {
				if(view.getRequirementSelectedIndex()==0) {
					view.adminAddTeachersButton().setEnabled(false);
				}
				if(view.getRequirementSelectedIndex()!=0){
					view.adminAddTeachersButton().setEnabled(true);
				}
				if(view.getTrainingSelectedIndex()==1) {
					view.adminTrainTeachersButton().setEnabled(false);
				}
				if(view.getTrainingSelectedIndex()==2) {
					view.adminTrainTeachersButton().setEnabled(true);
				}
//				view.isTeacherListSelected();
			}
			else {
				view.isTeacherListSelected();
				return;
			}
		}
		//4.1
		else if(currUser=="PTTDirector") {
			if(!e.getValueIsAdjusting() && e.getSource()==view.pttDirRequirementsDisplay()) {
				view.isPttDirDisplaySelected();
			}else {
				view.isPttDirDisplaySelected();
				return;
			}
		}
	}


	private void createOtherController(String currUser, ActionEvent e) {
		String firstName, lastName;
		String[] selectedTeacherName;
		Classes findClass;

		if(e.getSource()==view.logoutButton) {
			view.createHomeScreen();
		}

		//2. Class Director Screen
		else if(currUser.equals("ClassDirector")) {
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
			else if(e.getSource()==view.getAddRequirementButton()) {
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
			else if(e.getSource()==view.getRemoveRequirementButton()) {
				int reqIndex=view.getRequirementsList().getSelectedIndex();//4
				ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
				int j=0;
				for(TeachingRequirement i : listOfRequirements) {
					if(j==reqIndex) {
						model.removeTeachingRequirement(i);
						JOptionPane.showMessageDialog(view, "Requirement Removed");

						view.updateClassDirScreen();
					}
					j++;
				}
			}
		}

		//3. Administrator Screen
		else if(currUser.equals("Administrator")) {
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
					if((j+1)==chosenReqIndex) {
						setAddTeachersInReq(i);
					}
					j++;
				}
			}

			//3.4. add teachers
			if(e.getSource()==view.adminAddTeachersButton()) {
				//add selected teachers can
				//sub number of teachers from current teaching requirement
				String s=(String)view.getTeacherList().getSelectedValue();				
				selectedTeacherName=s.split(" ");
				firstName=selectedTeacherName[0];
				lastName=selectedTeacherName[1];
				ListOfEmployees listOfTeachers=model.getListOfTeachers();	
				if(view.getRequirementSelectedIndex()==0) {
					view.adminAddTeachersButton().setEnabled(false);
				}else {
				for(Employee i : listOfTeachers) {
					if(i.getFirstName().equals(firstName)&&i.getLastName().equals(lastName)) {
						model.createAndAddTeachingRequest((Teacher) i, addTeachersInReq.getClassRef(), addTeachersInReq);
						changedNumberOfTeachers=addTeachersInReq.getNumOfTeachers()-1;
						addTeachersInReq.setNumOfTeachers(changedNumberOfTeachers);
						
						JOptionPane.showMessageDialog(view, "Added Successfully");
						view.updateAdminScreen();
					}
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
						JOptionPane.showMessageDialog(view, "Training Success");
						view.updateAdminScreen();
					}
				}
			}
		}

		//4. PTT Director Screen
		else if (currUser.equals("PTTDirector")){
			//4.1. (JComboBox<String>)filterRequirements
			if(e.getSource()==view.pttDirRequirementsFilter()) {
				filterRequirementsIndex=view.pttDirRequirementsFilterIndex();
				view.updatePTTDirScreen();
			}

			//4.2. approve button
			else if(e.getSource()==view.approveRequestButton() ) {//|| e.getSource()==view.denyRequestButton()
				String s=(String)view.pttDirRequirementsDisplay().getSelectedValue();
				String[] selectedRequest=s.split(" requested for ");
				String[] teacherName=selectedRequest[0].split(" ");
				firstName=teacherName[0];
				lastName=teacherName[1];
				String selectedClassName=selectedRequest[1];

				ListOfClasses listOfClasses=model.getListOfClasses();
				for(Classes loc: listOfClasses) {
					if(loc.getName().equals(selectedClassName)) {
						findClass=loc;

						ListOfTeachingRequests listOfRequests=model.getListOfTeachingRequests();

						for(TeachingRequest i : listOfRequests) {
							if(i.getTeacher().getFirstName().equals(firstName) &&
									i.getTeacher().getLastName().equals(lastName) &&
									i.getClassRef().getCode().equals(findClass.getCode())) {
								model.approveTeachingRequest(i);
								JOptionPane.showMessageDialog(view, "Approved Successfully");

								view.updatePTTDirScreen();
							}
						}
					}
				}
			}

			//4.3. deny button
			else if(e.getSource()==view.denyRequestButton()) {
				String s=(String)view.pttDirRequirementsDisplay().getSelectedValue();
				String[] selectedRequest=s.split("requested for ");
				String[] teacherName=selectedRequest[0].split(" ");
				firstName=teacherName[0];
				lastName=teacherName[1];
				String className=selectedRequest[1];

				ListOfTeachingRequests listOfRequests=model.getListOfTeachingRequests();

				for(TeachingRequest trequest : listOfRequests) {
					if(trequest.getTeacher().getFirstName().equals(firstName) &&
							trequest.getTeacher().getLastName().equals(lastName) &&
							trequest.getClassRef().getName().equals(className)) {
						model.denyTeachingRequest(trequest);	
						JOptionPane.showMessageDialog(view, "Request Denied");

						view.updatePTTDirScreen();
					}
				}	
			}
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
