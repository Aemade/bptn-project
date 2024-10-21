package com.project._02_expense;

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

public class ViewExpense extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField totalamount1;
	private JTextField totalamount2;
	private JTable table2;
	private JTable table1;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewExpense frame = new ViewExpense();
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
	public ViewExpense() {
		setBounds(100, 100, 1037, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelsearchbydate = new JPanel();
		panelsearchbydate.setBounds(10, 10, 488, 487);
		contentPane.add(panelsearchbydate);
		panelsearchbydate.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search by date");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(174, 10, 134, 20);
		panelsearchbydate.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date From:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 52, 78, 20);
		panelsearchbydate.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date To:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 91, 78, 20);
		panelsearchbydate.add(lblNewLabel_1_1);
		
		JDateChooser date1 = new JDateChooser();
		date1.setBounds(97, 52, 111, 25);
		panelsearchbydate.add(date1);
		
		JDateChooser date2 = new JDateChooser();
		date2.setBounds(97, 91, 111, 25);
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
		btnNewButton.setBounds(264, 69, 134, 30);
		panelsearchbydate.add(btnNewButton);
		
		JLabel lbltotalamount1 = new JLabel("Total Amount:");
		lbltotalamount1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltotalamount1.setBounds(267, 447, 94, 30);
		panelsearchbydate.add(lbltotalamount1);
		
		totalamount1 = new JTextField();
		totalamount1.setBounds(352, 447, 105, 26);
		panelsearchbydate.add(totalamount1);
		totalamount1.setColumns(10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 149, 468, 288);
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
		
		JButton ExportToCSV = new JButton("ExportToCSV");
		ExportToCSV.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Get the table model from table1
		        DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        
		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses.csv"))) {
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

		            JOptionPane.showMessageDialog(null, "Data exported to expenses.csv successfully!");

		        } catch (IOException e1) {
		            JOptionPane.showMessageDialog(null, "Error exporting to CSV: " + e1.getMessage(), 
		                                          "Export Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		ExportToCSV.setBounds(10, 449, 105, 21);
		panelsearchbydate.add(ExportToCSV);
		
		JPanel panelsearchbydate_1 = new JPanel();
		panelsearchbydate_1.setLayout(null);
		panelsearchbydate_1.setBounds(525, 10, 488, 487);
		contentPane.add(panelsearchbydate_1);
		
		JLabel lblNewLabel_2 = new JLabel("Search by Category");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(151, 10, 168, 20);
		panelsearchbydate_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Date From:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 52, 78, 20);
		panelsearchbydate_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Date To:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 91, 78, 20);
		panelsearchbydate_1.add(lblNewLabel_1_1_1);
		
		JDateChooser date21 = new JDateChooser();
		date21.setBounds(97, 52, 111, 25);
		panelsearchbydate_1.add(date21);
		
		JDateChooser date22 = new JDateChooser();
		date22.setBounds(97, 91, 111, 25);
		panelsearchbydate_1.add(date22);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Clothing", "Education", "Food", "Housing", "Healthcare", "Personal", "Transportation", "Utilities"}));
		comboBox.setBounds(323, 54, 134, 23);
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
		                dtm.addRow(new Object[]{rs.getDate("Date"), rs.getString("Item"), t});
		            }
		            totalamount2.setText(total + "");
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		        }
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_1.setBounds(323, 86, 134, 30);
		panelsearchbydate_1.add(btnNewButton_1);
		
		JLabel lbltotalamount1_1 = new JLabel("Total Amount:");
		lbltotalamount1_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltotalamount1_1.setBounds(267, 447, 94, 30);
		panelsearchbydate_1.add(lbltotalamount1_1);
		
		totalamount2 = new JTextField();
		totalamount2.setColumns(10);
		totalamount2.setBounds(352, 447, 105, 26);
		panelsearchbydate_1.add(totalamount2);
		
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Category:");
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(254, 52, 78, 20);
		panelsearchbydate_1.add(lblNewLabel_1_2_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 148, 447, 283);
		panelsearchbydate_1.add(scrollPane_3);
		
		table2 = new JTable();
		scrollPane_3.setViewportView(table2);
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Item in category", "Amount"
			}
		));
		table2.getColumnModel().getColumn(0).setPreferredWidth(42);
		table2.getColumnModel().getColumn(1).setPreferredWidth(114);
		table2.getColumnModel().getColumn(2).setPreferredWidth(62);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 149, 447, 282);
		panelsearchbydate_1.add(scrollPane_2);
	}
}
