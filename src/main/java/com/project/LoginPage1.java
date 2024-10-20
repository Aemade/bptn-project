package com.project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Canvas;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage1 {

	private JFrame frmWelcomeToExpense;
	private JTextField textUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage1 window = new LoginPage1();
					window.frmWelcomeToExpense.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeToExpense = new JFrame();
		frmWelcomeToExpense.setResizable(false);
		frmWelcomeToExpense.setBackground(SystemColor.activeCaption);
		frmWelcomeToExpense.setFont(new Font("Calibri", Font.ITALIC, 18));
		frmWelcomeToExpense.setTitle("Welcome to Expense Manager");
		frmWelcomeToExpense.setForeground(new Color(153, 180, 209));
		frmWelcomeToExpense.setAlwaysOnTop(true);
		frmWelcomeToExpense.setBounds(100, 100, 878, 471);
		frmWelcomeToExpense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWelcomeToExpense.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(434, 141, 112, 27);
		frmWelcomeToExpense.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(434, 243, 122, 27);
		frmWelcomeToExpense.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textUsername.setBounds(556, 141, 171, 27);
		frmWelcomeToExpense.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(556, 241, 171, 27);
		frmWelcomeToExpense.getContentPane().add(passwordField);
		
		Canvas canvas = new Canvas();
		canvas.setForeground(SystemColor.info);
		canvas.setFont(null);
		canvas.setBackground(SystemColor.info);
		canvas.setBounds(0, 0, 381, 434);
		frmWelcomeToExpense.getContentPane().add(canvas);
		
		Label label = new Label("Welcome - Please Login");
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(525, 26, 263, 46);
		frmWelcomeToExpense.getContentPane().add(label);
		
		JButton btnlogin = new JButton("Log in");
		btnlogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				String username = textUsername.getText();
                char[] password = passwordField.getPassword(); // Use getPassword() to retrieve char[]
                
                // Convert the char[] password to a string for comparison
                String passwordStr = new String(password);}
			
		});
		btnlogin.setFont(new Font("Calibri", Font.BOLD, 14));
		btnlogin.setBounds(434, 329, 112, 37);
		frmWelcomeToExpense.getContentPane().add(btnlogin);
		
		JButton btnreset = new JButton("Cancel");
		btnreset.setFont(new Font("Calibri", Font.BOLD, 15));
		btnreset.setBounds(621, 326, 106, 37);
		frmWelcomeToExpense.getContentPane().add(btnreset);
		
	}
}
