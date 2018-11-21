package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Category extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
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
	public Category() {
		/*
		        서버로부터
             Msgrecommend/큰주제/작은주제/큰주제/작은주제/큰주제/작은주제 
                      받음 
        */
		setTitle("Category select");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Select = new JPanel();
		Select.setLayout(null);
		Select.setBounds(0, 0, 794, 162);
		contentPane.add(Select);
		
		Choice choice = new Choice();
		choice.setBounds(192, 71, 87, 21);
		Select.add(choice);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(328, 71, 87, 21);
		Select.add(choice_1);
		
		JButton btnSelect = new JButton("select");
		btnSelect.setBounds(463, 71, 97, 23);
		Select.add(btnSelect);
		
		JPanel Select1 = new JPanel();
		Select1.setLayout(null);
		Select1.setBounds(0, 161, 794, 326);
		contentPane.add(Select1);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {    //결정버튼
			public void actionPerformed(ActionEvent e) {
			/*  
			        서버로
	             Msgcategory/선택된거(큰주제/세부주제)/문제형식  
	                      전송
	        */
			}
		});
		btnConfirm.setBounds(205, 529, 97, 23);
		contentPane.add(btnConfirm);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {      //취소버튼 누르면 Problem board로 이동
					new ProblemBoard().setVisible(true);
		            setVisible(false);
				}
			});
		button_1.setBounds(498, 529, 97, 23);
		contentPane.add(button_1);

	}
}
