package com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.seitptt.controller.Controller;
import com.seitptt.model.Core;

/**
 * View - creates the GUI view for the teacher admin application
 */
public class View extends JFrame {

	// panels for each screen

	private HomeScreen homeScreen;
	private ClassDirectorView classDirectorScreen;
	private PTTDirectorView pttDirectorScreen;

	// view unit
	final int UNIT = 30;

	private Controller controller;
	private Core model;

	/**
	 * view constructor sets the model and controller and creates homescreen view
	 */
	public View(Controller controller, Core model) {
		this.model = model;
		this.controller = controller;
		// set JFrame attributes
		this.setLocation(20, 20);
		this.setTitle("Teacher Admin");
		this.setSize(35 * UNIT, 20 * UNIT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1, 1));
		createHomeScreen();

	}

	/**
	 * creates home screen
	 */
	private void createHomeScreen() {
		homeScreen = new HomeScreen(controller, UNIT);
		this.add(homeScreen);
	}

	/**
	 * return class Dir Login Button
	 */
	public JButton classDirLogin() {
		return homeScreen.classDirLogin;
	}

	/**
	 * return admin login Button
	 */
	public JButton adminLogin() {
		return homeScreen.adminLogin;
	}

	/**
	 * return PTT Dir Login Button
	 */
	public JButton pttDirLogin() {
		return homeScreen.pttDirLogin;
	}

	/**
	 * creates class director screen
	 */

	public void createClassDirScreen() {
		// remove homePanel
		this.remove(homeScreen);

		// create class Director Screen
		classDirectorScreen = new ClassDirectorView(controller,model,UNIT);
		this.add(classDirectorScreen);

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * updates class director screen
	 */
	
	  public void updateClassDirScreen() {
	  classDirectorScreen.update();
	  this.revalidate();
	  this.repaint();
	  
	  }
	 

	/**
	 * returns numbers of tutors from class director screen
	 */
	
	  public String getNumTeachers() {
	  
	  return classDirectorScreen.getNumTeachers(); }
	 /**
		 * Add Requirement Button Access
		 */
	
	  
	  public JButton getAddRequirementButton() { return
	  classDirectorScreen.addRequirementButton; }
	 /**
		 * Remove Requirement Button Access
		 */
	
	  
	  public JButton getRemoveRequirementButton() { return
	  classDirectorScreen.removeRequirementButton; }
	 /**
		 * Class Selector Access
		 */
	
	  
	  public JComboBox getClassSelector() { return
	  classDirectorScreen.classSelector; }
	  
		 /**
			 * Class Selected Index
			 */
		
	  public int getClassSelectedIndex() { return
			  classDirectorScreen.classSelector.getSelectedIndex(); }
	  
	 /**
		 * Semester Selected Index
		 */
	
	  
	  public int getSemesterSelectedIndex() { return
	  classDirectorScreen.semesterSelector.getSelectedIndex(); }
	  /**
		 * Semester Selector Access
		 */
	
	  
	  public JComboBox getSemesterSelector() { return
	  classDirectorScreen.semesterSelector; }
	  
	  
	  public void enableClassList() {
		  classDirectorScreen.enableClassList();
		  
		// update screen
			this.revalidate();
			this.repaint();
	  }
	 /**
		 * Requirements List Access
		 */
			  public JList getRequirementsList() { return
			  classDirectorScreen.requirementsList; }
			 

	/**
	 * creates admin screen
	 */
	public void createAdminScreen() {
		// remove homePanel
		this.remove(homeScreen);
		// create and add admin screen

		AdminView adminScreen = new AdminView(controller, model, UNIT);
		this.add(adminScreen);

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * updates admin screen
	 */
	public void updateAdminScreen() {

	}

	/**
	 * creates PTT director screen
	 */
	public void createPTTDirScreen() {
		// remove homePanel
		this.remove(homeScreen);

		// create and add PTTDirView

		pttDirectorScreen = new PTTDirectorView(controller, model, UNIT);
		this.add(pttDirectorScreen);

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * updates PTT director screen
	 */
	public void updatePTTDirScreen() {

	}

	// test main will remove
	public static void main(String[] args) {
		Core model = new Core();
		Controller controller = new Controller(model);
		View gui = new View(controller, model);
		gui.setVisible(true);
		// gui.createClassDirScreen();
		// gui.createPTTDirScreen();
		//gui.createAdminScreen();

	}

}
