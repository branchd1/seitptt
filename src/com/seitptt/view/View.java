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

/**
 * View - creates the GUI view for the teacher admin application
 */
public class View extends JFrame {

	// panels for each screen
	private JPanel homePanel;
	private JPanel classDirPanel;
	private JPanel adminPanel;
	private JPanel pttDirPanel;

	// view unit
	final int UNIT = 30;
	// homescreen class attributes
	private JTextField usernameEntry;
	private JTextField passwordEntry;
	//class director class attributes
	private JTextField enterNumTutors;
	private JTextField enterNumDemonstrators;
	private JList requirementsList;

	//PTT Director class attributes

	private JList requirementsDisplay;
	
	//admin screen class attributes
	private JComboBox teacherFilter;
	private JList teacherDisplay;
	
	private Controller controller;
	

	/**
	 * view constructor sets the model and controller and creates homescreen view
	 */
	public View(Controller controller) {
		this.controller=controller;
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
		// create and add homepage panel
		homePanel = new JPanel();
		homePanel.setLayout(new GridLayout(3, 1));
		this.add(homePanel);

		// create and add app logo panel
		JLabel appLogo = new JLabel("TeacherAdmin App", SwingConstants.CENTER);
		appLogo.setFont(new java.awt.Font("Arial", Font.BOLD, 75));
		homePanel.add(appLogo);

		// create and add login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(4, 1));
		homePanel.add(loginPanel);

		// create and add username and password panels to login panel
		JPanel usernamePanel = new JPanel();
		JLabel usernameLabel = new JLabel("Username:");
		usernameEntry = new JTextField();
		usernameEntry.setColumns(20);
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameEntry);
		loginPanel.add(usernamePanel);

		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password:");
		passwordEntry = new JTextField();
		passwordEntry.setColumns(20);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordEntry);
		loginPanel.add(passwordPanel);

		// create and add login button panel to login panel
		JPanel loginButtonPanel = new JPanel();
		loginButtonPanel.setLayout(new GridBagLayout());
		JButton loginButton = new JButton("Login");
		 loginButton.addActionListener(controller);
		loginButtonPanel.add(loginButton);
		loginPanel.add(loginButtonPanel);
	}
	
	
	/**
	 * alert box if wrong input on home screen 
	 */
	
	public void wrongInput() {
     JOptionPane errorMessage=new JOptionPane(JOptionPane.ERROR_MESSAGE);
     errorMessage.showMessageDialog(null,"You have entered an incorrect username or password");

	}
	
	public void noAccess() {
     JOptionPane errorMessage=new JOptionPane(JOptionPane.ERROR_MESSAGE);
     errorMessage.showMessageDialog(null,"You are not authorized to enter this app.");	
	}

	/**
	 * returns username from home screen
	 */
	public String getUsername() {
		return usernameEntry.getText();
	}

	/**
	 * returns password from home screen
	 */
	public String getPassword() {
		return passwordEntry.getText();
	}

	/**
	 * creates class director screen
	 */
	public void createClassDirScreen() {
		//remove homePanel
		this.remove(homePanel);
		//create and add ClassDirPanel
		classDirPanel=new JPanel();
		classDirPanel.setLayout(new GridLayout(1,2));
		Border classDirBorder = BorderFactory.createEmptyBorder(2*UNIT,2*UNIT,3*UNIT,2*UNIT);
        classDirPanel.setBorder(classDirBorder);
		this.add(classDirPanel);
		
		//create and add teaching requirement addition panel
		JPanel addRequirementPanel=new JPanel();
		classDirPanel.add(addRequirementPanel);
		addRequirementPanel.setLayout(new GridLayout(10,1));
		JLabel requirementsPanelHeader=new JLabel("Add Requirement", SwingConstants.CENTER); 
		requirementsPanelHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
		addRequirementPanel.add(requirementsPanelHeader);
		JPanel selectClassPanel=new JPanel();
		addRequirementPanel.add(selectClassPanel);
		JLabel selectClassLabel=new JLabel("Select a class");
		//will be replaced by model call
		String[] testClasses= {"Class1","Class2","Class3","Class4","Class5"};
		JComboBox classSelector= new JComboBox(testClasses);
		ACTION LISTENER FOR CONTROLLER
		classSelector.addActionListener(controller);
		selectClassPanel.add(selectClassLabel);
		selectClassPanel.add(classSelector);
		
		
		JPanel enterNumTutorsPanel=new JPanel();
		addRequirementPanel.add(enterNumTutorsPanel);
		JLabel enterNumTutorsLabel=new JLabel("# Tutors");
		//will be replaced by model call
		JTextField enterNumTutors= new JTextField();
		enterNumTutors.setColumns(5);
		enterNumTutorsPanel.add(enterNumTutorsLabel);
		enterNumTutorsPanel.add(enterNumTutors);
		
		
		JPanel enterNumDemonstratorsPanel=new JPanel();
		addRequirementPanel.add(enterNumDemonstratorsPanel);
		JLabel enterNumDemonstratorsLabel=new JLabel("# Demonstrators");
		enterNumDemonstrators= new JTextField();
		enterNumDemonstrators.setColumns(5);
		enterNumDemonstratorsPanel.add(enterNumDemonstratorsLabel);
		enterNumDemonstratorsPanel.add(enterNumDemonstrators);
		
		JPanel addRequirementButtonPanel=new JPanel();
		addRequirementPanel.add(addRequirementButtonPanel);
	
		JButton addRequirementButton= new JButton("Add");
		addRequirementButton.addActionListener(controller);
		addRequirementButtonPanel.add(addRequirementButton);
		
		
		//create and add requirements list
		JPanel requirementsListPanel = new JPanel();
		requirementsListPanel.setLayout(new BorderLayout());
		classDirPanel.add(requirementsListPanel);
		//will be replaced by model call
		String[] requirementsTestList= {"Class #Tutors #Demonstrators","History 1    2"};
		requirementsList=new JList(requirementsTestList);
		requirementsListPanel.add(requirementsList,BorderLayout.CENTER);
		//create remove button
		JButton removeRequirementButton = new JButton("Remove");
		requirementsListPanel.add(removeRequirementButton,BorderLayout.SOUTH);
	     
		
		
		//update screen
		 this.revalidate();
	     this.repaint();
	}

	/**
	 * updates class director screen
	 */
	public void updateClassDirScreen() {
		//MAKE MODEL CALL HERE 
		//requirementsList=new JList(REFERENCING MODEL CALL);
	}
	
	/**
	 * returns numbers of tutors from class director screen 
	 */
	public String getNumTutors() {
		
		return enterNumTutors.getText();
	}
	
	/**
	 * returns numbers of demonstrators from class director screen 
	 */
	public String getNumDemonstrators() {
		
		return enterNumDemonstrators.getText();
	}
	
	
	
	
	
	
	

	/**
	 * creates admin screen
	 */
	public void createAdminScreen() {
		//remove homePanel
		this.remove(homePanel);
		//create and add admin screen
		adminPanel=new JPanel();
		adminPanel.setLayout(new BorderLayout());
		this.add(adminPanel);
		//create and add class filter
		//WILL ADD CLASSES FROM MODEL LATER
		teacherFilter=new JComboBox();
		teacherFilter.addActionListener(controller);
		adminPanel.add(teacherFilter,BorderLayout.NORTH);
		
		
		
		
		
		//create and add teacher display list
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//update screen
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
		//remove homePanel
		this.remove(homePanel);
		//create and add PTTDirPanel
		pttDirPanel=new JPanel();
		pttDirPanel.setLayout(new BorderLayout());
        Border pttDirBorder = BorderFactory.createEmptyBorder(2*UNIT,2*UNIT,3*UNIT,2*UNIT);
        pttDirPanel.setBorder(pttDirBorder);
		this.add(pttDirPanel);
		
		//create and add headerPanel
		JPanel headerPanel=new JPanel();
		headerPanel.setLayout(new GridLayout(2,1));
		pttDirPanel.add(headerPanel,BorderLayout.NORTH);
		JLabel pttDirHeader=new JLabel("List of Requirements",SwingConstants.CENTER);
		pttDirHeader.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
		headerPanel.add(pttDirHeader);
		//create and add filter to list of requirements
		//will be replaced by model call
		String[] reqStatusOptions= {"All","Pending"}; 
		JComboBox filterRequirements= new JComboBox(reqStatusOptions);
		//filterRequirements.addActionListener(controller);
		headerPanel.add(filterRequirements);
		
		//create and add display of requirements list
		//will be replaced by model call
		String[] testReqs= {"Request1","Request2","Request3"};
		requirementsDisplay = new JList(testReqs);
		pttDirPanel.add(requirementsDisplay,BorderLayout.CENTER);
		
		//create and add action buttons
		JPanel buttonsPanel=new JPanel();
		buttonsPanel.setLayout(new GridLayout(1,2));
		pttDirPanel.add(buttonsPanel,BorderLayout.SOUTH);
		JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(controller);
		JButton denyButton= new JButton("Deny");
		denyButton.addActionListener(controller);
		buttonsPanel.add(approveButton);
		buttonsPanel.add(denyButton);
	
		//update screen
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
		View gui = new View();
		gui.setVisible(true);
		//gui.noAccess();
		//code to test the different screens
//		gui.createClassDirScreen();
	    //gui.createPTTDirScreen();
	
	  
	}

}
