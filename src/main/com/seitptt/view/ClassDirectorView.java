package main.com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import main.com.seitptt.controller.Controller;
import main.com.seitptt.model.Core;
import main.com.seitptt.model.processes.Classes;
import main.com.seitptt.model.processes.ListOfClasses;
import main.com.seitptt.model.processes.ListOfSemesters;
import main.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.com.seitptt.model.processes.Semester;
import main.com.seitptt.model.processes.TeachingRequirement;

public class ClassDirectorView extends JPanel {

	// class attributes
	private JTextField enterNumTeachers;
	protected JList requirementsList;
	protected JComboBox classSelector;
	protected JComboBox semesterSelector;
	protected JButton removeRequirementButton;
	protected JButton addRequirementButton;
	private DefaultListModel listModel;
	private Controller controller;
	private Core model;

	/**
	 * View constructor creates the specialized Class Director JPanel
	 */
	public ClassDirectorView(Controller controller, Core model, int UNIT) {
		
		//set the model and controller
		this.model = model;
		this.controller = controller;
		this.setLayout(new GridLayout(1, 2));
		
		//create the border for the JPanel
		Border classDirBorder = BorderFactory.createEmptyBorder(2 * UNIT, 2 * UNIT, 3 * UNIT, 2 * UNIT);
		this.setBorder(classDirBorder);
		// create and add teaching requirement addition panel
		JPanel addRequirementPanel = new JPanel();
		this.add(addRequirementPanel);
		addRequirementPanel.setLayout(new GridLayout(10, 1));
		JLabel requirementsPanelHeader = new JLabel("Add Requirement", SwingConstants.CENTER);
		requirementsPanelHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		addRequirementPanel.add(requirementsPanelHeader);

		JPanel selectSemesterPanel = new JPanel();
		addRequirementPanel.add(selectSemesterPanel);

		JLabel selectSemesterLabel = new JLabel("Select semester");
		selectSemesterPanel.add(selectSemesterLabel);
		ListOfSemesters semestersList = model.getListOfSemesters();
		ArrayList<String> selectorSemesters = new ArrayList();
		for (Semester i : semestersList) {
			selectorSemesters.add(i.toString());
		}
		semesterSelector = new JComboBox(selectorSemesters.toArray());
		semesterSelector.addActionListener(controller);

		selectSemesterPanel.add(semesterSelector);
		JPanel selectClassPanel = new JPanel();
		addRequirementPanel.add(selectClassPanel);
		JLabel selectClassLabel = new JLabel("Select a class");

		classSelector = new JComboBox();
		classSelector.addActionListener(controller);
		classSelector.setEnabled(false);
		selectClassPanel.add(selectClassLabel);
		selectClassPanel.add(classSelector);

		JPanel enterNumTeachersPanel = new JPanel();
		addRequirementPanel.add(enterNumTeachersPanel);
		JLabel enterNumTeachersLabel = new JLabel("# Teachers");
		// will be replaced by model call
		enterNumTeachers = new JTextField();
		enterNumTeachers.setColumns(5);
		enterNumTeachers.setEnabled(false);

		enterNumTeachersPanel.add(enterNumTeachersLabel);
		enterNumTeachersPanel.add(enterNumTeachers);

		JPanel addRequirementButtonPanel = new JPanel();
		addRequirementPanel.add(addRequirementButtonPanel);

		addRequirementButton = new JButton("Add");
		addRequirementButton.setEnabled(false);
		addRequirementButton.addActionListener(controller);
		addRequirementButtonPanel.add(addRequirementButton);

		// create and add requirements list
		JPanel requirementsListPanel = new JPanel();
		requirementsListPanel.setLayout(new BorderLayout());
		this.add(requirementsListPanel);

		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();

		listModel = new DefaultListModel();
		for (TeachingRequirement i : listOfRequirements) {
			listModel.addElement(i.toString());

		}
		requirementsList = new JList(listModel);
		requirementsList.addListSelectionListener(controller);
		requirementsListPanel.add(requirementsList, BorderLayout.CENTER);
		// create remove button
		removeRequirementButton = new JButton("Remove");
		removeRequirementButton.addActionListener(controller);
		requirementsListPanel.add(removeRequirementButton, BorderLayout.SOUTH);

	}

	/**
	 * returns numbers of teachers from class director panel
	 */
	protected String getNumTeachers() {

		return enterNumTeachers.getText();
	}

	/**
	 * enables rest of form after semester selected.
	 */
	protected void enableClassList() {
		ListOfClasses classesList = model.getListOfClasses().filterBySemester(controller.getChosenSemester());
		classSelector.removeAllItems();

		for (Classes i : classesList) {
			ArrayList<String> selectorClasses = new ArrayList();
			selectorClasses.add(i.toString());
			classSelector.addItem(i.toString());
		}

		classSelector.setEnabled(true);
		enterNumTeachers.setEnabled(true);
		addRequirementButton.setEnabled(true);

	}

	/**
	 * Updates the screen after a change is made
	 */
	protected void update() {
		listModel.removeAllElements();
		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		for (TeachingRequirement i : listOfRequirements) {
			listModel.addElement(i.toString());
		}

	}

}
