package com.seitptt.view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
    //homescreen class attributes
	 private JTextField usernameEntry;
	 private JTextField passwordEntry;

	/**
	 * view constructor sets the model and controller and creates homescreen view
	 */
	public View() {
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
		//create and add homepage panel
				homePanel=new JPanel();
				homePanel.setLayout(new GridLayout(3,1));
				this.add(homePanel);
				
				//create and add app logo panel
				JLabel appLogo=new JLabel("TeacherAdmin App",SwingConstants.CENTER);
		        appLogo.setFont(new java.awt.Font("Arial", Font.BOLD, 75));
		        homePanel.add(appLogo);
		        
		        //create and add login panel
		        JPanel loginPanel= new JPanel();
		        loginPanel.setLayout(new GridLayout(4,1));
		        homePanel.add(loginPanel);
		        
		        //create and add username and password panels to login panel
		        JPanel usernamePanel=new JPanel();
		        JLabel usernameLabel=new JLabel("Username:");
		        usernameEntry= new JTextField();
		        usernameEntry.setColumns(20);
		        usernamePanel.add(usernameLabel);
		        usernamePanel.add(usernameEntry);
		        loginPanel.add(usernamePanel);
		        
		        JPanel passwordPanel=new JPanel();
		        JLabel passwordLabel=new JLabel("Password:");
		        passwordEntry= new JTextField();
		        passwordEntry.setColumns(20);
		        passwordPanel.add(passwordLabel);
		        passwordPanel.add(passwordEntry);
		        loginPanel.add(passwordPanel);
		     
		        //create and add login button panel to login panel
		        JPanel loginButtonPanel= new JPanel();
		        loginButtonPanel.setLayout(new GridBagLayout());
		        JButton loginButton = new JButton("Login");
		        //COMMENTED OUT CONTROLLER ACTION LISTENER
		        //loginButton.addActionListener(l);
		        loginButtonPanel.add(loginButton);
		        loginPanel.add(loginButtonPanel);
	}

	/**
	 * returns username from home screen
	 */
	public String getUsername() {
		return usernameEntry.getText();
	}
	
	public String getPassword() {
		return passwordEntry.getText();
	}

	/**
	 * creates class director screen
	 */
	public void createClassDirScreen() {
      
	}

	/**
	 * updates class director screen
	 */
	public void updateClassDirScreen() {

	}

	/**
	 * creates admin screen
	 */
	public void createAdminScreen() {

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

	}

	/**
	 * updates PTT director screen
	 */
	public void updatePTTDirScreen() {

	}

	// test main will remove
	public static void main(String[] args) {
			View gui=new View();
			gui.setVisible(true);
	}

}
