package main.java.com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import main.java.com.seitptt.controller.Controller;
import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.TeachingRequirement;

public class AdminView extends JPanel {
	
	
	// class attributes
	private Controller controller;
	private Core model;
	
	protected JComboBox trainingFilter;
	protected JComboBox requirementFilter;
	
	protected JList teacherList;
	private DefaultListModel listModel;
	private DefaultComboBoxModel comboModel;
	
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
		String[] trainingStatus = { "All","Trained", "Untrained" };
		trainingFilter = new JComboBox(trainingStatus);
		trainingFilter.addActionListener(controller);
		filterPanel.add(trainingFilter);
		
		//create and add list of teaching requirements filter
		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements().filterByValidation();
		ArrayList<String> listRequirements = new ArrayList();
		listRequirements.add("Select Requirement");
		for (TeachingRequirement i : listOfRequirements) {
			listRequirements.add(i.toString());
		}
		comboModel=new DefaultComboBoxModel(listRequirements.toArray());
	
		requirementFilter = new JComboBox();
		requirementFilter.setModel(comboModel);
		requirementFilter.addActionListener(controller);
		filterPanel.add(requirementFilter);
		this.add(filterPanel, BorderLayout.NORTH);
		listModel = new DefaultListModel();
		
		
		// create and add teacher list display
		ListOfEmployees listOfTeachers = model.getListOfTeachers();
		for (Employee i : listOfTeachers) {

			listModel.addElement(i.toString());
		}
		teacherList = new JList(listModel);
		teacherList.addListSelectionListener(controller);
		this.add(teacherList, BorderLayout.CENTER);

		// create and add action Buttons
		JPanel buttonPanel = new JPanel();
		addTeachers = new JButton("Add Teachers");
		addTeachers.addActionListener(controller);
		trainTeachers = new JButton("Train Teachers");
		trainTeachers.addActionListener(controller);
		
		addTeachers.setEnabled(false);
		trainTeachers.setEnabled(false);
		buttonPanel.add(addTeachers);
		buttonPanel.add(trainTeachers);
		this.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	protected void update() {
		requirementFilter.removeAllItems();
		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		ArrayList<String> listRequirements = new ArrayList();
		listRequirements.add("Select Requirement");
		for (TeachingRequirement i : listOfRequirements) {
			if(i.getNumOfTeachers()>0) {
				listRequirements.add(i.toString());
			}
			
		}
		comboModel=new DefaultComboBoxModel(listRequirements.toArray());
		requirementFilter.setModel(comboModel);
        
		listModel.removeAllElements();
		ListOfEmployees listOfTeachers=null;
		if(controller.getSelectedFilterIndexForAdmin()==0){
			listOfTeachers = model.getListOfTeachers();
//			trainTeachers.setEnabled(false);

		}
		if(controller.getSelectedFilterIndexForAdmin()==1) {
	        listOfTeachers = model.getListOfTeachers().getTrainedTeachers();
//			trainTeachers.setEnabled(false);
		}
		

		if(controller.getSelectedFilterIndexForAdmin()==2) {
			   listOfTeachers = model.getListOfTeachers().getUntrainedTeachers();	
//				trainTeachers.setEnabled(true);

		}
	
		for (Employee i : listOfTeachers) {

			listModel.addElement(i.toString());
		}
		
	}
	
	
	
	protected void isTeacherListSelected() {
		if(teacherList.isSelectionEmpty()) {
			addTeachers.setEnabled(false);
			trainTeachers.setEnabled(false);
		}
//		if(!teacherList.isSelectionEmpty()) {
//			trainTeachers.setEnabled(true);
//		}
	}

}
