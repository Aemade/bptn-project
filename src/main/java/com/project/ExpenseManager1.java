package com.project;

public class ExpenseManager1 extends JFrame {

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



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
	public ExpenseManager1() {
		setFont(new Font("Calibri", Font.ITALIC, 20));
		setTitle("Expense Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1165, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1146, 141);
		panel.setBackground(new Color(198, 198, 198));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Your Expence Here");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri Light", Font.BOLD, 20));
		lblNewLabel.setBounds(389, 10, 294, 25);
		panel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDate.setBounds(22, 52, 51, 25);
		panel.add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(69, 52, 106, 25);
		panel.add(dateChooser);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblItem.setBounds(225, 54, 51, 20);
		panel.add(lblItem);
		
		Item = new JTextField();
		Item.setBounds(274, 52, 223, 25);
		panel.add(Item);
		Item.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCategory.setBounds(566, 52, 63, 25);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Clothing", "Education", "Food", "Housing", "Healthcare", "Personal", "Transportation", "Utilities"}));
		comboBox.setBounds(639, 53, 143, 24);
		panel.add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblAmount.setBounds(834, 52, 63, 25);
		panel.add(lblAmount);
		
		Amount = new JTextField();
		Amount.setBounds(912, 52, 128, 25);
		panel.add(Amount);
		Amount.setColumns(10);
		
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

			        // Now create the content string with the formatted date
			        String content = formattedDate + "," + Item.getText() + "," + comboBox.getSelectedItem() + "," + Amount.getText();
			        
				/*try {
					FileWriter writer = new FileWriter ("C:\\Users\\Aemade\\Desktop\\Java Project\\expense.txt", true);
					
					BufferedWriter bufferedWriter = new BufferedWriter(writer);
					bufferedWriter.write(content);
					bufferedWriter.newLine();
					bufferedWriter.close();
					
					JOptionPane.showMessageDialog(null, "Expense Saved Successfully!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    } else {
			        JOptionPane.showMessageDialog(null, "Please select a date!");
			    }	
					}
				});
				*/
			        
			        
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

				        } catch (ClassNotFoundException e1) {
				            e1.printStackTrace();
				        } catch (SQLException throwables) {
				            throwables.printStackTrace();
				        }
				    }
			}
				});

						
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnNewButton.setBounds(274, 104, 85, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dateChooser.setDate(null);
				Item.setText("");
				comboBox.setSelectedIndex(0);
				Amount.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnNewButton_1.setBounds(463, 105, 97, 27);
		panel.add(btnNewButton_1);
		
		JButton btnviewexpense = new JButton("View Expense");
		btnviewexpense.setBounds(674, 101, 146, 34);
		panel.add(btnviewexpense);
		btnviewexpense.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewExp Exp = new ViewExp();
				contentPane.add(Exp);
				Exp.setVisible(true);
			}
		});
		btnviewexpense.setFont(new Font("Calibri", Font.ITALIC, 16));
	}
}
