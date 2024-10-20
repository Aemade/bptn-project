package com.project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ExpenseManager0 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpenseManager0 frame = new ExpenseManager0();
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
	public ExpenseManager0() {
		setFont(new Font("Times New Roman", Font.BOLD, 16));
		setTitle("Personal Expense Manager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1088, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 971, 118);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record Your Expense Here");
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		lblNewLabel.setBounds(377, 10, 218, 23);
		panel.add(lblNewLabel);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setToolTipText("");
		lblDate.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblDate.setBounds(27, 40, 39, 23);
		panel.add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(77, 40, 130, 23);
		panel.add(dateChooser);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setToolTipText("");
		lblItem.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblItem.setBounds(245, 43, 45, 23);
		panel.add(lblItem);
		
		textField = new JTextField();
		textField.setBounds(289, 43, 194, 23);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setToolTipText("");
		lblCategory.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblCategory.setBounds(511, 43, 71, 23);
		panel.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(581, 40, 140, 25);
		panel.add(comboBox);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setToolTipText("");
		lblAmount.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblAmount.setBounds(754, 43, 71, 23);
		panel.add(lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(821, 41, 124, 23);
		panel.add(textField_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSave.setBounds(183, 87, 107, 31);
		panel.add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnReset.setBounds(404, 87, 116, 31);
		panel.add(btnReset);
		
		JButton btnViewExpense = new JButton("View Expense");
		btnViewExpense.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnViewExpense.setBounds(656, 87, 185, 31);
		panel.add(btnViewExpense);
	}
}
