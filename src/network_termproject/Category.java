package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class Category extends JFrame {
	/*
	 * gui that show user select gui and form
	 * if user click confirm send data to server
	 */
	String input;
	private JPanel contentPane;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String problem_name;
	String[] large = new String[3];
	String[] small = new String[3];	
	String imgset;
	String form;

	public Category(Socket socket1,String line) {		
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;
		input=line;
		System.out.println("Category"+  line);
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		StringTokenizer token=new StringTokenizer(input,"/");
		problem_name=token.nextToken();
		problem_name=token.nextToken();
		imgset=token.nextToken();
		System.out.println(imgset);
		large[0]=token.nextToken();
		small[0]=token.nextToken();
		large[1]=token.nextToken();
		small[1]=token.nextToken();
		large[2]=token.nextToken();
		small[2]=token.nextToken();
				
		setTitle("Category select");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(205, 529, 97, 23);
		contentPane.add(btnConfirm);
		
		JPanel panel = new JPanel();
		panel.setBounds(188, 250, 144, 39);
		contentPane.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel lblNew1 = new JLabel(large[0]);
		lblNew1.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		lblNew1.setBounds(45, 10, 120, 20);
		panel.add(lblNew1);
		lblNew1.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(188, 312, 144, 39);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		
		JLabel label = new JLabel(large[1]);
		label.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBounds(45, 10, 120, 20);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(188, 371, 144, 39);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel(large[2]);
		label_1.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setBounds(45, 10, 120, 20);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(438, 250, 144, 39);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		
		JLabel label_2 = new JLabel(small[0]);
		label_2.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_2.setBounds(45, 10, 120, 20);
		panel_3.add(label_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(438, 312, 144, 39);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		
		JLabel label_3 = new JLabel(small[1]);
		label_3.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setBounds(45, 10, 120, 20);
		panel_4.add(label_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(438, 371, 144, 39);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		
		JLabel label_4 = new JLabel(small[2]);
		label_4.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_4.setVerticalAlignment(SwingConstants.BOTTOM);
		label_4.setBounds(45, 10, 120, 20);
		panel_5.add(label_4);
		
		JLabel label1 = new JLabel("\uCD94\uCC9C1");
		label1.setBounds(119, 262, 57, 15);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("\uCD94\uCC9C2");
		label2.setBounds(119, 321, 57, 15);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("\uCD94\uCC9C3");
		label3.setBounds(119, 385, 57, 15);
		contentPane.add(label3);
		btnConfirm.setBounds(205, 529, 97, 23);
		contentPane.add(btnConfirm);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {      
					out.println("problemwindow");
		            setVisible(false);
				}
			});
		button_1.setBounds(498, 529, 97, 23);
		contentPane.add(button_1);
		
		Choice choice = new Choice();
		contentPane.add(choice);
		choice.setBounds(205, 107, 87, 21);
		
		Choice detail = new Choice();
		contentPane.add(detail);
		detail.setBounds(348, 107, 87, 21);
		
		Choice type = new Choice();                    
		type.setBounds(491, 107, 87, 21);
		contentPane.add(type);                          
		
		JLabel Select = new JLabel();
		Select.setIcon(ImageClass.Category_background());           	
		Select.setBounds(0, 0, 794, 589);
		contentPane.add(Select);
		
		Select.setLayout(null);
		detail.add("ºº∫Œ¡÷¡¶");                     
		detail.add("introduction");
		detail.add("applicationlayer");
		detail.add("transportlayer");
		detail.add("networklayer");
		detail.add("linklayer");
		detail.add("wireless");
		detail.select(0);
		
		choice.add("≈´¡÷¡¶");                        
		choice.add("os"); 
		choice.add("network");
		choice.add("algorithm");
		choice.add("dataBase");
		
		type.add("πÆ¡¶¿Ø«¸");                      
		type.add("short");
		type.add("select");
		type.add("long");
		type.select(0);     

		btnConfirm.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {

				out.println("Msgcategory/"+choice.getSelectedItem()+"/"+detail.getSelectedItem()+"/"+problem_name+"/"+type.getSelectedItem()+"/"+imgset);
				System.out.println("∞·¡§");
				try {
					Thread.sleep(500);
					out.println("msgmypage");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		
		setVisible(true);
	}
}
