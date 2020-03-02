package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.model.processes.ListOfTeachingRequirements;

public class AdminView extends JPanel {
	//class attributes
	private Controller controller;
	private Core model;
    protected JComboBox trainingFilter;
    protected JComboBox requirementFilter;
    protected JList teacherList;
	
	
	
	
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
		ListOfTeachingRequirements listOfRequirements = model.getListOfTeachingRequirements();
		ArrayList<String> listRequirements = new ArrayList();
		while(listOfRequirements.iterator().hasNext()) {
			listRequirements.add(listOfRequirements.iterator().next().toString());
		}
		requirementFilter= new JComboBox(listRequirements.toArray());
		filterPanel.add(requirementFilter);
		
		

	}

}
