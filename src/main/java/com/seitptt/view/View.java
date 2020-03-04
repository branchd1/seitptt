package main.java.com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import main.java.com.seitptt.controller.Controller;
import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.model.personnel.PTTDirector;

/**
 * View - creates the GUI view for the teacher admin application
 */
public class View extends JFrame {

	// panels for each screen

	private HomeScreen homeScreen;
	private ClassDirectorView classDirectorScreen;
	private PTTDirectorView pttDirectorScreen;
	private AdminView adminScreen;

	// view unit
	final int UNIT = 30;

	private Controller controller;
	private Core model;
	public JButton logoutButton;
	private JPanel innerPanel;
	private boolean hasInnerPanel;
	

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
		
		//create back button inner panel for use in non home screen pages
		innerPanel= new JPanel();
		innerPanel.setLayout(new BorderLayout());
		logoutButton=new JButton("LOGOUT");
		logoutButton.addActionListener(controller);
		innerPanel.add(logoutButton,BorderLayout.SOUTH);
		
	}

	/**
	 * creates home screen
	 */
	public void createHomeScreen() {
		if(hasInnerPanel) {
			this.remove(innerPanel);
			innerPanel=new JPanel();
			innerPanel.setLayout(new BorderLayout());
			logoutButton=new JButton("LOGOUT");
			logoutButton.addActionListener(controller);
			innerPanel.add(logoutButton,BorderLayout.SOUTH);
			hasInnerPanel=false;
		}
	     
		homeScreen = new HomeScreen(controller, UNIT);
		this.add(homeScreen);
		
		//update screen when used after logout
		this.revalidate();
		this.repaint();
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
		classDirectorScreen = new ClassDirectorView(controller, model, UNIT);
		this.add(innerPanel);
		hasInnerPanel=true;
		innerPanel.add(classDirectorScreen,BorderLayout.CENTER);

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

		return classDirectorScreen.getNumTeachers();
	}

	/**
	 * Add Requirement Button Access
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
	 * Class Selected Index
	 */

	public int getClassSelectedIndex() {
		return classDirectorScreen.classSelector.getSelectedIndex();
	}

	/**
	 * Semester Selected Index
	 */

	public int getSemesterSelectedIndex() {
		return classDirectorScreen.semesterSelector.getSelectedIndex();
	}

	/**
	 * Semester Selector Access
	 */

	public JComboBox getSemesterSelector() {
		return classDirectorScreen.semesterSelector;
	}

	public void enableClassList() {
		classDirectorScreen.enableClassList();

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * Requirements List Access
	 */
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

		adminScreen = new AdminView(controller, model, UNIT);
		this.add(innerPanel);
		hasInnerPanel=true;
		innerPanel.add(adminScreen,BorderLayout.CENTER);
		

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * updates admin screen
	 */
	public void updateAdminScreen() {
         adminScreen.update();
         
     	// update screen
 		this.revalidate();
 		this.repaint();
	}


	/**
	 * returns the add Teachers Button
	 */
	public JButton adminAddTeachersButton() {
		return adminScreen.addTeachers;
	}

	/**
	 * returns the train Teachers Button
	 */
	public JButton adminTrainTeachersButton() {
		return adminScreen.trainTeachers;
	}

	/**
	 * Training JComboBox Access
	 */
	public JComboBox getTrainingSelector() {
		return adminScreen.trainingFilter;
	}

	/**
	 * Training Selected Index
	 */

	public int getTrainingSelectedIndex() {
		return adminScreen.trainingFilter.getSelectedIndex();
	}

	/**
	 * Requirements Selected Index
	 */

	public int getRequirementSelectedIndex() {
		return adminScreen.requirementFilter.getSelectedIndex();
	}

	/**
	 * Requirement JComboBox Access
	 */
	public JComboBox getRequirementSelector() {
		return adminScreen.requirementFilter;
	}

	/**
	 * Requirements List Access
	 */
	public JList getTeacherList() {
		return adminScreen.teacherList;
	}
	
	/**
	 * Disables add button if teacher list is empty
	 */
	public void isTeacherListSelected() {
	 adminScreen.isTeacherListSelected();
	}

	/**
	 * creates PTT director screen
	 */
	public void createPTTDirScreen() {
		// remove homePanel
		this.remove(homeScreen);

		// create and add PTTDirView

		pttDirectorScreen = new PTTDirectorView(controller, model, UNIT);
		this.add(innerPanel);
		hasInnerPanel=true;
		innerPanel.add(pttDirectorScreen,BorderLayout.CENTER);
		

		// update screen
		this.revalidate();
		this.repaint();
	}

	/**
	 * returns approve request button
	 */
	public JButton approveRequestButton() {
		return pttDirectorScreen.approveButton;
	}

	/**
	 * returns deny request button
	 */
	public JButton denyRequestButton() {
		return pttDirectorScreen.denyButton;
	}

	/**
	 * returns pttDir Requirements Display
	 */
	public JList pttDirRequirementsDisplay() {
		return pttDirectorScreen.requirementsDisplay;
	}

	/**
	 * returns pttdir requirements filter
	 */
	public JComboBox pttDirRequirementsFilter() {
		return pttDirectorScreen.filterRequirements;
	}

	/**
	 * returns pttdir requirements filter index
	 */
	public int pttDirRequirementsFilterIndex() {
		return pttDirectorScreen.filterRequirements.getSelectedIndex();
	}

	/**
	 * updates PTT director screen
	 */
	public void updatePTTDirScreen() {
          pttDirectorScreen.update();
	}
    
	
	public void isPttDirDisplaySelected() {
		pttDirectorScreen.isRequirementsDisplaySelected();
	}
	// test main will remove
	public static void main(String[] args) {
		Core model = new Core();
		Controller controller = new Controller(model);
		View gui = new View(controller, model);
		gui.setVisible(true);
		 //gui.createClassDirScreen();
		 gui.createPTTDirScreen();
	//gui.createAdminScreen();

	}

}