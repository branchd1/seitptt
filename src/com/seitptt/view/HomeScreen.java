package com.seitptt.view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.seitptt.controller.Controller;

public class HomeScreen extends JPanel {

	// class attributes
	private JTextField usernameEntry;
	private JTextField passwordEntry;
	protected JButton loginButton;

	/**
	 * constructor creates a home screen panel
	 */

	public HomeScreen(Controller controller) {
		this.setLayout(new GridLayout(3, 1));

		// create and add app logo panel
		JLabel appLogo = new JLabel("TeacherAdmin App", SwingConstants.CENTER);
		appLogo.setFont(new java.awt.Font("Arial", Font.BOLD, 75));
		this.add(appLogo);

		// create and add login panel
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(4, 1));
		this.add(loginPanel);

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
		loginButton = new JButton("Login");
		loginButton.addActionListener(controller);
		loginButtonPanel.add(loginButton);
		loginPanel.add(loginButtonPanel);
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
	 * alert box if wrong input on home screen
	 */

	public void wrongInput() {
		JOptionPane errorMessage = new JOptionPane(JOptionPane.ERROR_MESSAGE);
		errorMessage.showMessageDialog(null, "You have entered an incorrect username or password");

	}

	/**
	 * alert box if user is not allowed access
	 */

	public void noAccess() {
		JOptionPane errorMessage = new JOptionPane(JOptionPane.ERROR_MESSAGE);
		errorMessage.showMessageDialog(null, "You are not authorized to enter this app.");
	}

}
