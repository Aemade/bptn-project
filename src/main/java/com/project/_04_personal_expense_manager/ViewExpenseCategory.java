package com.project._04_personal_expense_manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ViewExpenseCategory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField totalamount2;
	private JTable table2;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewExpenseCategory frame = new ViewExpenseCategory();
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
	public ViewExpenseCategory() {
		setFont(new Font("Times New Roman", Font.BOLD, 20));
		setTitle("View Your Expense by Category");
		setBounds(100, 100, 937, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelsearchbydate_1 = new JPanel();
		panelsearchbydate_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SystemColor.inactiveCaption, null));
		panelsearchbydate_1.setLayout(null);
		panelsearchbydate_1.setBounds(427, 10, 496, 587);
		contentPane.add(panelsearchbydate_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date From:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 20, 78, 20);
		panelsearchbydate_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Date To:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 65, 67, 20);
		panelsearchbydate_1.add(lblNewLabel_1_1_1);
		
		JDateChooser date21 = new JDateChooser();
		date21.setBounds(81, 20, 111, 25);
		panelsearchbydate_1.add(date21);
		
		JDateChooser date22 = new JDateChooser();
		date22.setBounds(81, 65, 111, 25);
		panelsearchbydate_1.add(date22);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Clothing", "Education", "Food", "Housing", "Healthcare", "Personal", "Transportation", "Utilities"}));
		comboBox.setBounds(352, 20, 134, 23);
		panelsearchbydate_1.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
		            javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table2.getModel();
		            int rc = dtm.getRowCount();
		            while (rc-- != 0) {
		                dtm.removeRow(0);
		            }

		            String c = (String) comboBox.getSelectedItem(); // Use the global comboBox
		            java.sql.Date sqlDate21 = new java.sql.Date(date21.getDate().getTime());
		            java.sql.Date sqlDate22 = new java.sql.Date(date22.getDate().getTime());

		            // Replace with your actual database connection details
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensedb", "root", "Benu");
		            Statement st = conn.createStatement();
		            ResultSet rs = st.executeQuery("SELECT * FROM expense WHERE Date >= '" + sqlDate21 + "' AND Date <= '" + sqlDate22 + "' AND Category = '" + c + "' ORDER BY Date ASC");

		            double total = 0; // Use double to hold the total amount

		            while (rs.next()) {
		                double t = rs.getDouble("Amount"); // Use double to store the amount
		                total += t;
		                dtm.addRow(new Object[]{rs.getDate("Date"), rs.getString("Item"), rs.getString("Category"), t});
		            }
		            totalamount2.setText(total + "");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.setBounds(352, 60, 134, 30);
		panelsearchbydate_1.add(btnNewButton_1);
		
		JLabel lbltotalamount1_1 = new JLabel("Total Amount:");
		lbltotalamount1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltotalamount1_1.setBounds(294, 504, 94, 30);
		panelsearchbydate_1.add(lbltotalamount1_1);
		
		totalamount2 = new JTextField();
		totalamount2.setColumns(10);
		totalamount2.setBounds(381, 508, 105, 26);
		panelsearchbydate_1.add(totalamount2);
		
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Category:");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(283, 20, 78, 20);
		panelsearchbydate_1.add(lblNewLabel_1_2_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 113, 476, 390);
		panelsearchbydate_1.add(scrollPane_3);
		
		table2 = new JTable();
		scrollPane_3.setViewportView(table2);
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Item", "Category", "Amount"
			}
		));
		table2.getColumnModel().getColumn(3).setPreferredWidth(62);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 149, 447, 282);
		panelsearchbydate_1.add(scrollPane_2);
		
		JButton ExportToCSV_1 = new JButton("Export To CSV");
		ExportToCSV_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Get the table model from table1
		    	  // Ask for user confirmation
              int response = JOptionPane.showConfirmDialog(null, 
                      "Do you want to export the data to a CSV file?", 
                      "Export Confirmation", 
                      JOptionPane.YES_NO_OPTION);

              if (response == JOptionPane.YES_OPTION) {
                  // Open file chooser for location and file name
                  JFileChooser fileChooser = new JFileChooser();
                  fileChooser.setDialogTitle("Specify a file to save");

                  // Set the default file extension to .csv
                  fileChooser.setSelectedFile(new java.io.File("expenses.csv"));

                  int userSelection = fileChooser.showSaveDialog(null);

                  if (userSelection == JFileChooser.APPROVE_OPTION) {
                      // Get the selected file path
                      java.io.File fileToSave = fileChooser.getSelectedFile();

                      // Get the table model from table1
                      DefaultTableModel model = (DefaultTableModel) table2.getModel();

                      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                          // Write the header
                          writer.write("Date,Item,Amount");
                          writer.newLine();

                          // Write the data rows
                          for (int i = 0; i < model.getRowCount(); i++) {
                              String date = model.getValueAt(i, 0).toString();
                              String item = model.getValueAt(i, 1).toString();
                              String amount = model.getValueAt(i, 2).toString();

                              String line = String.join(",", date, item, amount);
                              writer.write(line);
                              writer.newLine();
                          }

                          JOptionPane.showMessageDialog(null, 
                                  "Data exported to " + fileToSave.getAbsolutePath() + " successfully!");

                      } catch (IOException e1) {
                          JOptionPane.showMessageDialog(null, 
                                  "Error exporting to CSV: " + e1.getMessage(), 
                                  "Export Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              }
          }
      });
	
		ExportToCSV_1.setBounds(10, 547, 120, 30);
		panelsearchbydate_1.add(ExportToCSV_1);
		
		JButton btnNewButton_2_1 = new JButton("Delete Record");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Check if a row is selected
		        int selectedRow = table2.getSelectedRow(); // For table1 (Date-based search)
		        if (selectedRow != -1) {
		            // Get the date, item, and amount from the selected row
		            java.sql.Date date = (java.sql.Date) table2.getValueAt(selectedRow, 0);
		            String item = table2.getValueAt(selectedRow, 1).toString();
		            double amount = (double) table2.getValueAt(selectedRow, 2);

		            // Confirmation dialog
		            int confirm = JOptionPane.showConfirmDialog(null, 
		                    "Are you sure you want to delete the selected record?", 
		                    "Delete Confirmation", 
		                    JOptionPane.YES_NO_OPTION);

		            if (confirm == JOptionPane.YES_OPTION) {
		                // Connect to the database and delete the record
		                try {
		                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensedb", "root", "Benu");
		                    Statement st = conn.createStatement();
		                    
		                    // Prepare the DELETE query
		                    String query = "DELETE FROM expense WHERE Date = '" + date + "' AND Item = '" + item + "' AND Amount = " + amount;
		                    int rowsDeleted = st.executeUpdate(query);
		                    
		                    if (rowsDeleted > 0) {
		                        // Remove the row from the table model
		                        ((DefaultTableModel) table2.getModel()).removeRow(selectedRow);
		                        JOptionPane.showMessageDialog(null, "Record deleted successfully!");
		                    } else {
		                        JOptionPane.showMessageDialog(null, "No record found for deletion!");
		                    }
		                    
		                    st.close();
		                    conn.close();
		                } catch (Exception ex) {
		                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), 
		                            "Delete Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Please select a record to delete.", 
		                    "No Selection", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		btnNewButton_2_1.setBounds(366, 547, 120, 30);
		panelsearchbydate_1.add(btnNewButton_2_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Aemade\\Desktop\\Java_Programs\\final-project\\expensemanager-java\\images\\image 10.png"));
		lblNewLabel.setBounds(-17, 10, 444, 587);
		contentPane.add(lblNewLabel);
	}
}
