package main.java.com.seitptt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.processes.ListOfTeachingRequirements;
import main.java.com.seitptt.view.View;

public class PTTDirectorController implements ActionListener, ListSelectionListener{

	private Core model;
	private View view;
	
	public PTTDirectorController(Core model, View view) {
		this.model=model;
		this.view=view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//4. PTT Director Screen
		//4.1. filter requirements (All, Pending)
		if(e.getSource()==view.pttDirRequirementsFilter()) {
			String filter=view.pttDirRequirementsFilter().toString();
			ListOfTeachingRequirements listOfRequirements=model.getListOfTeachingRequirements();

		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
