package com.seitptt.view;

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

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.model.processes.ListOfTeachingRequirements;
import com.seitptt.model.processes.TeachingRequirement;

public class PTTDirectorView extends JPanel {

	// class attributes
	protected JComboBox filterRequirements;
	protected JList requirementsDisplay;
	private DefaultListModel listModel;
	protected JButton approveButton;
	protected JButton denyButton;
	private Controller controller;
	private Core model;

	public PTTDirectorView(Controller controller,Core model, int UNIT) {
		this.model=model;
        this.controller=controller;
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
        listModel=new DefaultListModel();
        ListOfTeachingRequirements teachingRequirementsList = new ListOfTeachingRequirements();
        for(TeachingRequirement i : teachingRequirementsList) {
        	listModel.addElement(i.toString());
        }
		requirementsDisplay = new JList(listModel);
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
}
