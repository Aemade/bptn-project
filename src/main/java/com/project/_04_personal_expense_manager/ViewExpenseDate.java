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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ViewExpenseDate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField totalamount1;
	private JTable table1;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewExpenseDate frame = new ViewExpenseDate();
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
	public ViewExpenseDate() {
		setFont(new Font("Times New Roman", Font.BOLD, 20));
		setTitle("View Your Expense by Date");
		setBounds(100, 100, 963, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelsearchbydate = new JPanel();
		panelsearchbydate.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.inactiveCaptionText, SystemColor.inactiveCaption, null, null));
		panelsearchbydate.setBounds(10, 10, 488, 583);
		contentPane.add(panelsearchbydate);
		panelsearchbydate.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Date From:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 15, 78, 20);
		panelsearchbydate.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date To:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 54, 64, 20);
		panelsearchbydate.add(lblNewLabel_1_1);
		
		JDateChooser date1 = new JDateChooser();
		date1.setBounds(82, 15, 111, 25);
		panelsearchbydate.add(date1);
		
		JDateChooser date2 = new JDateChooser();
		date2.setBounds(82, 54, 113, 25);
		panelsearchbydate.add(date2);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
	                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) table1.getModel();
	                int rc = dtm.getRowCount();
	                while (rc-- != 0) {
	                    dtm.removeRow(0);
	                }

	                java.sql.Date sqlDate1 = new java.sql.Date(date1.getDate().getTime());
	                java.sql.Date sqlDate2 = new java.sql.Date(date2.getDate().getTime());

	                // Replace with your actual database connection details
	                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensedb", "root", "Benu");
	                Statement st = conn.createStatement();
	                ResultSet rs = st.executeQuery("SELECT * FROM expense WHERE Date >= '" + sqlDate1 + "' AND Date <= '" + sqlDate2 + "'Order by Date asc");
	                double total = 0; // Use double to hold the total amount
	                
	                while (rs.next()) {
	                	double t = rs.getDouble("Amount"); // Use double to store the amount
	                	total +=t;
	                    dtm.addRow(new Object[]{rs.getDate("Date"), rs.getString("Item"), t});
	                }
	                totalamount1.setText(total+"");
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	            }
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(273, 28, 134, 30);
		panelsearchbydate.add(btnNewButton);
		
		JLabel lbltotalamount1 = new JLabel("Total Amount:");
		lbltotalamount1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltotalamount1.setBounds(303, 87, 94, 30);
		panelsearchbydate.add(lbltotalamount1);
		
		totalamount1 = new JTextField();
		totalamount1.setBounds(391, 91, 87, 26);
		panelsearchbydate.add(totalamount1);
		totalamount1.setColumns(10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 127, 468, 406);
		panelsearchbydate.add(scrollPane_4);
		
		table1 = new JTable();
		scrollPane_4.setViewportView(table1);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Item", "Amount"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 468, 288);
		panelsearchbydate.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 149, 468, 285);
		panelsearchbydate.add(scrollPane_1);
		
		JButton ExportToCSV = new JButton("Export To CSV");
		ExportToCSV.addMouseListener(new MouseAdapter() {
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
                        DefaultTableModel model = (DefaultTableModel) table1.getModel();

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
		ExportToCSV.setBounds(10, 543, 120, 30);
		panelsearchbydate.add(ExportToCSV);
		
		JButton btnNewButton_2 = new JButton("Delete Record");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			       // Check if a row is selected
		        int selectedRow = table1.getSelectedRow(); // For table1 (Date-based search)
		        if (selectedRow != -1) {
		            // Get the date, item, and amount from the selected row
		            java.sql.Date date = (java.sql.Date) table1.getValueAt(selectedRow, 0);
		            String item = table1.getValueAt(selectedRow, 1).toString();
		            double amount = (double) table1.getValueAt(selectedRow, 2);

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
		                        ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);
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
		
		btnNewButton_2.setBounds(358, 543, 120, 30);
		panelsearchbydate.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Aemade\\Desktop\\Java_Programs\\final-project\\expensemanager-java\\images\\images 4.jpg"));
		lblNewLabel.setBounds(438, 10, 511, 583);
		contentPane.add(lblNewLabel);
	}
}
