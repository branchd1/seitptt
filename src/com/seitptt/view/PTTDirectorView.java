package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;

public class PTTDirectorView extends JPanel {

	// class attributes
	protected JComboBox filterRequirements;
	protected JList requirementsDisplay;
	protected JButton approveButton;
	protected JButton denyButton;
	private Controller controller;

	public PTTDirectorView(Controller controller, int UNIT) {
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
		// filterRequirements.addActionListener(controller);
		headerPanel.add(filterRequirements);

		// create and add display of requirements list
		// will be replaced by model call
		String[] testReqs = { "Request1", "Request2", "Request3" };
		requirementsDisplay = new JList(testReqs);
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
