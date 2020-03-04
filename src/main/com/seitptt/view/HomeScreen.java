package main.com.seitptt.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import main.com.seitptt.controller.Controller;

public class HomeScreen extends JPanel {

	// class attributes
	protected JButton classDirLogin;
	protected JButton adminLogin;
	protected JButton pttDirLogin;

	/**
	 * constructor creates a home screen panel
	 */

	public HomeScreen(Controller controller, int UNIT) {
		this.setLayout(new GridLayout(4, 1));
        Border loginPanelsBorder=BorderFactory.createEmptyBorder(UNIT,UNIT*6,UNIT,UNIT*6);
       
		
		//create and add home message panel
        
        JPanel homeMessagePanel=new JPanel();
        homeMessagePanel.setBorder(loginPanelsBorder);
        homeMessagePanel.setLayout(new GridLayout(1,1));
        JLabel loginMessage= new JLabel("Please sign in to your role",SwingConstants.CENTER);
        loginMessage.setFont(new java.awt.Font("Arial", Font.BOLD, 25));
        homeMessagePanel.add(loginMessage);
        this.add(homeMessagePanel);
        
        //create and add class dir login panel
        classDirLogin=new JButton("Class Director");
        classDirLogin.addActionListener(controller);
        JPanel classDirLoginPanel=new JPanel();
        classDirLoginPanel.setLayout(new BorderLayout());
        classDirLoginPanel.add(classDirLogin,BorderLayout.CENTER);
        classDirLoginPanel.setBorder(loginPanelsBorder);
        this.add(classDirLoginPanel);
        
        //create and add admin login panel
        adminLogin=new JButton("Administrator");
        adminLogin.addActionListener(controller);
        JPanel  adminLoginPanel = new JPanel();
        adminLoginPanel.setLayout(new BorderLayout());
        adminLoginPanel.add(adminLogin,BorderLayout.CENTER);
        adminLoginPanel.setBorder(loginPanelsBorder);
        this.add(adminLoginPanel);
        
        //create and add pttDir Panel
        pttDirLogin=new JButton("PTT Director");
        pttDirLogin.addActionListener(controller);
        JPanel  pttDirLoginPanel = new JPanel();
        pttDirLoginPanel.setLayout(new BorderLayout());
        pttDirLoginPanel.add(pttDirLogin,BorderLayout.CENTER);
        pttDirLoginPanel.setBorder(loginPanelsBorder);
        this.add(pttDirLoginPanel);
        
		
	}



	

}
