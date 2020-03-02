package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.ListOfTeachingRequirements;

public class AdminView extends JPanel {
	//class attributes
	private Controller controller;
	private Core model;
    protected JComboBox trainingFilter;
    protected JComboBox requirementFilter;
    protected JList teacherList;
    protected JButton addTeachers;
    protected JButton trainTeachers;
	
	
	
	
	public AdminView(Controller controller,Core model, int UNIT) {
		this.model=model;
		this.controller=controller;
		this.setLayout(new BorderLayout());
		Border adminBorder = BorderFactory.createEmptyBorder(2 * UNIT, 2 * UNIT, 3 * UNIT, 2 * UNIT);
		this.setBorder(adminBorder);
		//create and add filters
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new GridLayout(1,2));
	
		String[] trainingStatus= {"Trained","Untrained"};
		trainingFilter=new JComboBox(trainingStatus);
		filterPanel.add(trainingFilter);
		//ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		//ArrayList<String> listRequirements = new ArrayList();
		//while(listOfRequirements.iterator().hasNext()) {
			//listRequirements.add(listOfRequirements.iterator().next().toString());
		//}
		requirementFilter= new JComboBox();
		filterPanel.add(requirementFilter);
		this.add(filterPanel, BorderLayout.NORTH);
		
		//create and add teacher list display
		//ListOfEmployees listOfTeachers = model.getListOfTeachers();
		//ArrayList<String> teacherArrayList = new ArrayList();
		//while(listOfTeachers.iterator().hasNext()) {
			//teacherArrayList.add(listOfTeachers.iterator().next().toString());
		//}
		teacherList=new JList();
		this.add(teacherList,BorderLayout.CENTER);
		
		//create and add action Buttons
		JPanel buttonPanel=new JPanel();
		addTeachers=new JButton("Add Teachers");
		trainTeachers=new JButton("Train Teachers");
		buttonPanel.add(addTeachers);
		buttonPanel.add(trainTeachers);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		
		
		

	}

}
