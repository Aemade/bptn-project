package com.project._02_expense;

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
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 883, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new CompoundBorder(new CompoundBorder(), new LineBorder(new Color(0, 0, 0))));
		panel.setBounds(10, 10, 391, 513);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 78, 45, 25);
		panel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(107, 78, 189, 31);
		panel.add(dateChooser);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblItem.setBounds(38, 148, 45, 25);
		panel.add(lblItem);
		
		Item = new JTextField();
		Item.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Item.setBounds(107, 146, 189, 31);
		panel.add(Item);
		Item.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblCategory.setBounds(34, 220, 63, 25);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Clothing", "Education", "Food", "Housing", "Healthcare", "Personal", "Transportation", "Utilities"}));
		comboBox.setBounds(107, 218, 189, 31);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Amount:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(38, 295, 72, 25);
		panel.add(lblNewLabel_1_1_1_1);
		
		Amount = new JTextField();
		Amount.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		Amount.setColumns(10);
		Amount.setBounds(107, 293, 189, 31);
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
		btnNewButton.setBounds(46, 375, 105, 31);
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
		btnReset.setBounds(186, 375, 110, 31);
		panel.add(btnReset);
		
		JButton btnViewExpense = new JButton("View Expense");
		btnViewExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewExpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewExpense Exp = new ViewExpense();
				Exp.setVisible(true);
			}
		});
		btnViewExpense.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewExpense.setBounds(107, 452, 165, 31);
		panel.add(btnViewExpense);
		
		JLabel lblNewLabel = new JLabel("Please Record Your Expense Here");
		lblNewLabel.setBounds(75, 10, 244, 31);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Aemade\\Desktop\\Java_Programs\\final-project\\expensemanager-java\\images\\images 4.jpg"));
		lblNewLabel_2.setBounds(313, 10, 546, 513);
		contentPane.add(lblNewLabel_2);
	}
}
