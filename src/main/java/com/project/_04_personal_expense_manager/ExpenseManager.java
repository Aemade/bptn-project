package com.project._04_personal_expense_manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Item;
	private JTextField Amount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseManager frame = new ExpenseManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExpenseManager() {
		setFont(new Font("Times New Roman", Font.BOLD, 18));
		setTitle("Expense Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1078, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 1048, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please Record Your Expense Here");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(409, 0, 244, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(54, 52, 45, 25);
		panel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(93, 52, 119, 25);
		panel.add(dateChooser);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItem.setBounds(262, 52, 45, 25);
		panel.add(lblItem);
		
		Item = new JTextField();
		Item.setBounds(303, 50, 189, 31);
		panel.add(Item);
		Item.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCategory.setBounds(538, 52, 63, 25);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Clothing", "Education", "Food", "Housing", "Healthcare", "Personal", "Transportation", "Utilities"}));
		comboBox.setBounds(599, 50, 139, 31);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Amount:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(797, 52, 72, 25);
		panel.add(lblNewLabel_1_1_1_1);
		
		Amount = new JTextField();
		Amount.setColumns(10);
		Amount.setBounds(859, 50, 96, 31);
		panel.add(Amount);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Get the selected date from JDateChooser
				Date selectedDate = dateChooser.getDate();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			    
			    // Check if the date is selected
		        if (selectedDate != null) {
                    String formattedDate = dateFormat.format(selectedDate);
                    String item = Item.getText();
                    String category = (String) comboBox.getSelectedItem();
                    String amountText = Amount.getText();

                    // Validate if the amount is a valid double
                    try {
                        double amount = Double.parseDouble(amountText); // Check for valid double
			                			        
			        try {
			        	// Database connection and insertion
		                String url = "jdbc:mysql://localhost:3306/expensedb";
		                String user = "root";
		                String password = "Benu";
		                Class.forName("com.mysql.cj.jdbc.Driver");
		                Connection connection = DriverManager.getConnection(url, user, password);
		                System.out.println("Connection is successful to the database " + url);
		                
		                // Construct the SQL query
		                String query = "INSERT INTO expense(Date, Item, Category, Amount) VALUES ('" + formattedDate + "','" + Item.getText() + "', '" + comboBox.getSelectedItem() + "', " + Amount.getText() + ")";
		                
		                // Execute the query
		                Statement statement = connection.createStatement();
		                statement.executeUpdate(query);
		                JOptionPane.showMessageDialog(null, "Expense Saved Successfully!");

		                // Close the connection
		                connection.close();

			        } catch (ClassNotFoundException | SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }

                } catch (NumberFormatException ex) {
                    // Show error message for invalid amount input
                    JOptionPane.showMessageDialog(null, 
                        "Invalid input for amount! Please enter a numeric value.", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a date!");
            }
        }
    });
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(192, 103, 105, 31);
		panel.add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dateChooser.setDate(null);
				Item.setText("");
				comboBox.setSelectedIndex(0);
				Amount.setText("");
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnReset.setBounds(409, 103, 110, 31);
		panel.add(btnReset);
		
		JButton btnViewExpense = new JButton("View Expense");
		btnViewExpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewExpense Exp = new ViewExpense();
				Exp.setVisible(true);
			}
		});
		btnViewExpense.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewExpense.setBounds(630, 103, 165, 31);
		panel.add(btnViewExpense);
	}
}
