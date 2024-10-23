package com.project._04_personal_expense_manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Login {

	private JFrame frmWellcomeToPersonal;
	/**
	 *
	 */
	private final JPanel panel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmWellcomeToPersonal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWellcomeToPersonal = new JFrame();
		frmWellcomeToPersonal.setResizable(false);
		frmWellcomeToPersonal.setTitle("Welcome to Personal Expense Manager");
		frmWellcomeToPersonal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frmWellcomeToPersonal.setBounds(100, 100, 818, 489);
		frmWellcomeToPersonal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWellcomeToPersonal.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME - PLEASE LOG IN");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(511, 36, 225, 20);
		frmWellcomeToPersonal.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblUsername.setBounds(480, 118, 82, 32);
		frmWellcomeToPersonal.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(572, 120, 164, 32);
		frmWellcomeToPersonal.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPassword.setBounds(480, 198, 82, 32);
		frmWellcomeToPersonal.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(572, 200, 164, 32);
		frmWellcomeToPersonal.getContentPane().add(txtPassword);
		
		JButton btnlogin = new JButton("Log in");
		btnlogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = txtUsername.getText();
                char[] password = txtPassword.getPassword(); // Use getPassword() to retrieve char[]
                
                // Convert the char[] password to a string for comparison
                String passwordStr = new String(password);

                // Check if the credentials are correct
                if (passwordStr.equals("Ben") && username.equals("Emad")) {
                    txtPassword.setText(null);
                    txtUsername.setText(null);
                    
                    ExpenseManager0 info = new ExpenseManager0();
                    ExpenseManager0.main(null); // This should display the home screen
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username/Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                    txtPassword.setText(null);
                    txtUsername.setText(null);
                }
         			
			}
		});
		btnlogin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnlogin.setBounds(455, 356, 112, 41);
		frmWellcomeToPersonal.getContentPane().add(btnlogin);
		
		JButton btnreset = new JButton("Cancel");
		btnreset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText(null);
                txtPassword.setText(null);
			}
		});
		btnreset.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnreset.setBounds(624, 356, 112, 41);
		frmWellcomeToPersonal.getContentPane().add(btnreset);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Aemade\\Desktop\\Java_Programs\\final-project\\expensemanager-java\\images\\image 3.png"));
		lblNewLabel_1.setBounds(10, 10, 399, 497);
		frmWellcomeToPersonal.getContentPane().add(lblNewLabel_1);
	}
}
