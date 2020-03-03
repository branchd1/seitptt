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
	private int removeReqID, selectedFilterIndexForAdmin;

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
		//while(true) {
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

		//2.4. remove requirements
		if(e.getSource()==view.getRemoveRequirementButton()) {
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();
			System.out.print(removeReqID);
			for(TeachingRequirement selectedReq : listOfRequirements) {
				if(selectedReq.getId()==removeReqID) {
					model.removeTeachingRequirement(selectedReq);
					System.out.println("actionlistener: "+selectedReq);
				}
			}
			view.updateClassDirScreen();
		}	

		//3. Administrator Screen
		TeachingRequirement addTeachersInReq;

		//3.1. (JComboBox) filter list of teachers
		if(e.getSource()==view.getTrainingSelector()) {
			selectedFilterIndexForAdmin=view.getTrainingSelectedIndex();
			view.trainingUpdate();
		}

		//3.2. (JComboBox) choose class requirements
		if(e.getSource()==view.getRequirementSelector()) {
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

		//3.3. add teachers
		if(e.getSource()==view.adminAddTeachersButton()) {
			//add selected teachers can
			//sub number of teachers from current teaching requirement
			
		}
		//3.4. train teachers


		//4. PTT Director Screen
		//4.1. filter requirements (All, Pending)
		if(e.getSource()==view.pttDirRequirementsFilter()) {
			String filter=view.pttDirRequirementsFilter().toString();
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();

		}
		//}
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
					System.out.println(removeReqID);
				}
				j++;
				System.out.println("j: "+j);
			}
		}
	}
}
