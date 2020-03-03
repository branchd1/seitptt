package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.model.personnel.Employee;
import com.seitptt.model.personnel.ListOfEmployees;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.TeachingRequirement;

public class AdminView extends JPanel {
	
	
	// class attributes
	private Controller controller;
	private Core model;
	
	protected JComboBox trainingFilter;
	protected JComboBox requirementFilter;
	
	protected JList teacherList;
	private DefaultListModel listModel;
	
	protected JButton addTeachers;
	protected JButton trainTeachers;
	

	

	/**
	 * View constructor creates the specialized Admin JPanel
	 */
	public AdminView(Controller controller, Core model, int UNIT) {
		//set the model and controller
		this.model = model;
		this.controller = controller;
		this.setLayout(new BorderLayout());
		
		
		//create and set panel border
		Border adminBorder = BorderFactory.createEmptyBorder(2 * UNIT, 2 * UNIT, 3 * UNIT, 2 * UNIT);
		this.setBorder(adminBorder);
		
		
		// create and add training filter
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new GridLayout(1, 2));
		String[] trainingStatus = { "Trained", "Untrained" };
		trainingFilter = new JComboBox(trainingStatus);
		filterPanel.add(trainingFilter);
		
		//create and add list of teaching requirements filter
		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		ArrayList<String> listRequirements = new ArrayList();
		
		for (TeachingRequirement i : listOfRequirements) {
			listRequirements.add(i.toString());
		}
		
		requirementFilter = new JComboBox(listRequirements.toArray());
		filterPanel.add(requirementFilter);
		this.add(filterPanel, BorderLayout.NORTH);
		listModel = new DefaultListModel();
		
		
		// create and add teacher list display
		ListOfEmployees listOfTeachers = model.getListOfTeachers();
		for (Employee i : listOfTeachers) {

			listModel.addElement(i.toString());
		}
		teacherList = new JList(listModel);
		this.add(teacherList, BorderLayout.CENTER);

		// create and add action Buttons
		JPanel buttonPanel = new JPanel();
		addTeachers = new JButton("Add Teachers");
		addTeachers.addActionListener(controller);
		trainTeachers = new JButton("Train Teachers");
		trainTeachers.addActionListener(controller);
		
		buttonPanel.add(addTeachers);
		buttonPanel.add(trainTeachers);
		this.add(buttonPanel, BorderLayout.SOUTH);

	}

}
