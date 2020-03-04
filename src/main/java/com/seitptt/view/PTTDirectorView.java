package main.java.com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import main.java.com.seitptt.controller.Controller;
import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.personnel.Employee;
import main.java.com.seitptt.model.personnel.ListOfEmployees;
import main.java.com.seitptt.model.processes.ListOfTeachingRequests;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.model.processes.TeachingRequest;
import main.java.com.seitptt.model.processes.TeachingRequirement;

public class PTTDirectorView extends JPanel {

	// class attributes
	protected JComboBox filterRequirements;
	protected JList requirementsDisplay;
	private DefaultListModel listModel;
	protected JButton approveButton;
	protected JButton denyButton;
	private Controller controller;
	private Core model;
	
	

	/**
	 * View constructor creates the specialized PTT Director JPanel
	 */
	public PTTDirectorView(Controller controller, Core model, int UNIT) {
		this.model = model;
		this.controller = controller;
		this.setLayout(new BorderLayout());
		Border pttDirBorder = BorderFactory.createEmptyBorder(2 * UNIT, 2 * UNIT, 3 * UNIT, 2 * UNIT);
		this.setBorder(pttDirBorder);

		// create and add headerPanel
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(2, 1));
		this.add(headerPanel, BorderLayout.NORTH);
		JLabel pttDirHeader = new JLabel("List of Requirements", SwingConstants.CENTER);
		pttDirHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
		headerPanel.add(pttDirHeader);
		// create and add filter to list of requirements
		// will be replaced by model call
		String[] reqStatusOptions = { "All", "Pending" };
		filterRequirements = new JComboBox(reqStatusOptions);
		filterRequirements.addActionListener(controller);
		headerPanel.add(filterRequirements);

		// create and add display of requirements list
		listModel = new DefaultListModel();
		ListOfTeachingRequests teachingRequestList = model.getListOfTeachingRequests();
		for (TeachingRequest i : teachingRequestList) {
			listModel.addElement(i.toString());
		}
		requirementsDisplay = new JList(listModel);
		requirementsDisplay.addListSelectionListener(controller);
		this.add(requirementsDisplay, BorderLayout.CENTER);

		// create and add action buttons
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2));
		this.add(buttonsPanel, BorderLayout.SOUTH);
		approveButton = new JButton("Approve");
		approveButton.addActionListener(controller);
		denyButton = new JButton("Deny");
		denyButton.addActionListener(controller);
		buttonsPanel.add(approveButton);
		buttonsPanel.add(denyButton);
	}
	
	protected void update() {
		listModel.removeAllElements();
		ListOfTeachingRequests teachingRequestList =null;
		if(controller.getFilterRequirementsIndex()==0){
	       teachingRequestList = model.getListOfTeachingRequests();
	       approveButton.setEnabled(true);
	       denyButton.setEnabled(true);

		}
		if(controller.getFilterRequirementsIndex()==1) {
		       teachingRequestList = model.getListOfTeachingRequests().filterByApproval(true);
		       approveButton.setEnabled(false);
		       denyButton.setEnabled(true);
		}
		

		if(controller.getFilterRequirementsIndex()==2) {
		       teachingRequestList = model.getListOfTeachingRequests().filterByApproval(false);
		       approveButton.setEnabled(true);
		       denyButton.setEnabled(false);

		}
	
		for (TeachingRequest i : teachingRequestList) {
			listModel.addElement(i.toString());
		}
		
	}
}
