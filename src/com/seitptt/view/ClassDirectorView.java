package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.model.processes.Classes;
import com.seitptt.model.processes.ListOfClasses;
import com.seitptt.model.processes.ListOfSemesters;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.Semester;

public class ClassDirectorView extends JPanel {
	private JTextField enterNumTeachers;
	protected JList requirementsList;
	protected JComboBox classSelector;
	protected JComboBox semesterSelector;
	protected JButton removeRequirementButton;
	protected JButton addRequirementButton;
	private Controller controller;
	private Core model;
	
	
	public ClassDirectorView(Controller controller,Core model, int UNIT) {
		this.model=model;
		this.controller=controller;
		this.setLayout(new GridLayout(1,2));
		Border classDirBorder = BorderFactory.createEmptyBorder(2*UNIT,2*UNIT,3*UNIT,2*UNIT);
        this.setBorder(classDirBorder);
		//create and add teaching requirement addition panel
		JPanel addRequirementPanel=new JPanel();
		this.add(addRequirementPanel);
		addRequirementPanel.setLayout(new GridLayout(10,1));
		JLabel requirementsPanelHeader=new JLabel("Add Requirement", SwingConstants.CENTER); 
		requirementsPanelHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		addRequirementPanel.add(requirementsPanelHeader);
		
		JPanel selectSemesterPanel=new JPanel();
		addRequirementPanel.add(selectSemesterPanel);
		
		JLabel selectSemesterLabel=new JLabel("Select semester");
		selectSemesterPanel.add(selectSemesterLabel);
		ListOfSemesters semestersList=model.getListOfSemesters();
		ArrayList<String> selectorSemesters=new ArrayList();
		for(Semester i:semestersList) {
			selectorSemesters.add(i.toString());
		}
		semesterSelector=new JComboBox(selectorSemesters.toArray());
		semesterSelector.addActionListener(controller);
	
		selectSemesterPanel.add(semesterSelector);
		JPanel selectClassPanel=new JPanel();
		addRequirementPanel.add(selectClassPanel);
		JLabel selectClassLabel=new JLabel("Select a class");
		ListOfClasses classesList=model.getListOfClasses();
		ArrayList<String> selectorClasses=new ArrayList();
		for(Classes i:classesList) {
			selectorClasses.add(i.toString());
		}
		classSelector= new JComboBox(selectorClasses.toArray());
		classSelector.addActionListener(controller);
		
		selectClassPanel.add(selectClassLabel);
		selectClassPanel.add(classSelector);
		
		
		
		JPanel enterNumTeachersPanel=new JPanel();
		addRequirementPanel.add(enterNumTeachersPanel);
		JLabel enterNumTeachersLabel=new JLabel("# Teachers");
		//will be replaced by model call
		 enterNumTeachers= new JTextField();
		enterNumTeachers.setColumns(5);
		enterNumTeachersPanel.add(enterNumTeachersLabel);
		enterNumTeachersPanel.add(enterNumTeachers);
		
		
		JPanel addRequirementButtonPanel=new JPanel();
		addRequirementPanel.add(addRequirementButtonPanel);
	
		addRequirementButton= new JButton("Add");
		addRequirementButton.addActionListener(controller);
		addRequirementButtonPanel.add(addRequirementButton);
		
		//create and add requirements list
		JPanel requirementsListPanel = new JPanel();
		requirementsListPanel.setLayout(new BorderLayout());
		this.add(requirementsListPanel);
		
		
		//ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		//ArrayList<String> listRequirements = new ArrayList();
		//while(listOfRequirements.iterator().hasNext()) {
			//listRequirements.add(listOfRequirements.iterator().next().toString());
		//}
		requirementsList=new JList();
		requirementsList.addListSelectionListener(controller);
		requirementsListPanel.add(requirementsList,BorderLayout.CENTER);
		//create remove button
	    removeRequirementButton = new JButton("Remove");
		requirementsListPanel.add(removeRequirementButton,BorderLayout.SOUTH);
		
		
	
	}
	
	
	
}
