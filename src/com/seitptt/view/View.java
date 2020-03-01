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
	public View(Controller controller) {
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
		homeScreen = new HomeScreen(controller);
		this.add(homeScreen);
	}

	public String getUsername() {
		return homeScreen.getUsername();
	}

	/**
	 * returns password from home screen
	 */
	public String getPassword() {
		return homeScreen.getPassword();
	}

	/**
	 * alert box if wrong input on home screen
	 */

	public void wrongInput() {
		homeScreen.wrongInput();
	}

	/**
	 * alert box if user is not allowed access
	 */

	public void noAccess() {
		homeScreen.noAccess();
	}

	/**
	 * Login Button Access
	 */

	public JButton getLoginButton() {
		return homeScreen.loginButton;
	}

	/**
	 * creates class director screen
	 */
	public void createClassDirScreen() {
		// remove homePanel
		this.remove(homeScreen);

		// create class Director Screen
		classDirectorScreen = new ClassDirectorView(controller,model, UNIT);
		this.add(classDirectorScreen);

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * updates class director screen
	 */
	public void updateClassDirScreen() {
		// MAKE MODEL CALL HERE
		// requirementsList=new JList(REFERENCING MODEL CALL);
	}
    
	/**
	 * returns numbers of tutors from class director screen
	 */
	public String getNumTeachers() {

		return classDirectorScreen.getNumTeachers();
	}
	/**
	 *Add Requirement Button Access
	 */

	public JButton getAddRequirementButton() {
		return classDirectorScreen.addRequirementButton;
	}
	/**
	 * Remove Requirement Button Access
	 */

	public JButton getRemoveRequirementButton() {
		return classDirectorScreen.removeRequirementButton;
	}
	/**
	 * Class Selector Access
	 */

	public JComboBox getClassSelector() {
		return classDirectorScreen.classSelector;
	}
	/**
	 * Semester Selector Access
	 */

	public JComboBox getSemesterSelector() {
		return classDirectorScreen.classSelector;
	}
	
	public JList getRequirementsList() {
		return classDirectorScreen.requirementsList;
	}


	/**
	 * creates admin screen
	 */
	public void createAdminScreen() {
		// remove homePanel
		this.remove(homeScreen);
		// create and add admin screen
		

		// create and add teacher display list

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
		
		//create and add PTTDirView
		
		pttDirectorScreen= new PTTDirectorView(controller,UNIT);
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
		View gui = new View(controller);
		gui.setVisible(true);
		// gui.noAccess();
		// code to test the different screens
		gui.createClassDirScreen();
		// gui.createPTTDirScreen();

	}

}
