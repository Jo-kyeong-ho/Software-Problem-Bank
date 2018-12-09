package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class ProblemResult extends JFrame {

	/*
	 * gui of problem's list that related certain subject
	 */
	private JPanel contentPane;
	private JTextField txtProblemBoard;
	String input;
	String[] problem_id = new String[7];
	String[] problem_name=new String[7];
	String[] largesubject=new String[7];
	String[] smallsubject=new String[7];
	String[] form=new String[7];
	String[] evaluation=new String[7];
	String[] level=new String[7];
	int page_i;
	PrintWriter out = null;
	BufferedReader in=null;
	Socket socket;
	

	public ProblemResult(Socket socket1,String line) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;
		input=line;
		String page;
		System.out.println(line);
		StringTokenizer token=new StringTokenizer(input,"/");
		problem_id[0]=token.nextToken();
		page=token.nextToken();
		page_i=Integer.parseInt(page);
		int i=0;
		while(token.hasMoreTokens())
		{
			problem_id[i]=token.nextToken();
			problem_name[i]=token.nextToken();
			evaluation[i]=token.nextToken();
			largesubject[i]=token.nextToken();
			smallsubject[i]=token.nextToken();
			form[i]=token.nextToken();
			level[i]=token.nextToken();
			i++;
		}
		i--;
		 try {
    		  in = new BufferedReader(new InputStreamReader(
    		            socket.getInputStream()));
    	        out = new PrintWriter(socket.getOutputStream(), true);
    	   	} catch (IOException e1) {
    	   		// TODO Auto-generated catch block
    	   		e1.printStackTrace();
    	   	}
		 
		
		setTitle("Problem Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel menu = new JLabel();
		menu.setIcon(ImageClass.Menu_background()); 
		menu.setBounds(0, 0, 128, 589);
		contentPane.add(menu);
		menu.setLayout(null);
		
		JButton button = new JButton("마이페이지");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		 
				out.println("msgmypage");
	            setVisible(false);}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          
				out.println("Msgquestionboard/1");
	            setVisible(false);	
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {         
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JLabel label_1 = new JLabel("문제이름");
		label_1.setBounds(245, 109, 57, 15);
		contentPane.add(label_1);
		
		JLabel label_6 = new JLabel("평점");
		label_6.setBounds(336, 109, 57, 15);
		contentPane.add(label_6);
		
		JLabel label_2 = new JLabel("큰주제");
		label_2.setBounds(425, 109, 57, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("세부주제");
		label_3.setBounds(531, 109, 57, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("유형");
		label_4.setBounds(620, 109, 34, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("난이도");
		label_5.setBounds(690, 109, 57, 15);
		contentPane.add(label_5);
		
		txtProblemBoard = new JTextField();
		txtProblemBoard.setBounds(165, 50, 582, 49);
		contentPane.add(txtProblemBoard);
		txtProblemBoard.setText("Problem Board");
		txtProblemBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtProblemBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtProblemBoard.setColumns(10);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {         
						new ProblemUpload(socket);
			            setVisible(false);
			            }
				});
		btnUpload.setBounds(617, 510, 122, 32);
		contentPane.add(btnUpload);
		
		
		if(i>=0)
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(181, 134, 558, 40);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
		
		JButton button_4 = new JButton(problem_id[0]);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[0]);
				setVisible(false);
			}
		});
		button_4.setBounds(0, 0, 59, 40);
		panel_2.add(button_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(problem_name[0]);
		textPane.setBounds(59, 0, 89, 40);
		panel_2.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText(largesubject[0]);
		textPane_1.setBounds(212, 0, 107, 40);
		panel_2.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText(smallsubject[0]);
		textPane_2.setBounds(322, 0, 99, 40);
		panel_2.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText(form[0]);
		textPane_3.setBounds(425, 0, 70, 40);
		panel_2.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText(level[0]);
		textPane_4.setBounds(499, 0, 59, 40);
		panel_2.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText(evaluation[0]);
		textPane_5.setBounds(145, 0, 66, 40);
		panel_2.add(textPane_5);
		}
		if(i>=1)
		{
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(181, 184, 558, 40);
			contentPane.add(panel_3);
			panel_3.setLayout(null);
		
		JButton button_5 = new JButton(problem_id[1]);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[1]);
				setVisible(false);
			}
		});
		button_5.setBounds(0, 0, 59, 40);
		panel_3.add(button_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText(problem_name[1]);
		textPane_6.setBounds(59, 0, 89, 40);
		panel_3.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText(largesubject[1]);
		textPane_7.setBounds(212, 0, 107, 40);
		panel_3.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText(smallsubject[1]);
		textPane_8.setBounds(322, 0, 99, 40);
		panel_3.add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText(form[1]);
		textPane_9.setBounds(425, 0, 70, 40);
		panel_3.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText(level[1]);
		textPane_10.setBounds(499, 0, 59, 40);
		panel_3.add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText(evaluation[1]);
		textPane_11.setBounds(145, 0, 66, 40);
		panel_3.add(textPane_11);
		}
		if(i>=2)
		{
			JPanel panel_4 = new JPanel();
			panel_4.setBounds(181, 234, 558, 40);
			contentPane.add(panel_4);
			panel_4.setLayout(null);
		
		JButton button_6 = new JButton(problem_id[2]);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Msggetproblem/"+problem_id[2]);
				setVisible(false);
			}
		});
		button_6.setBounds(0, 0, 59, 40);
		panel_4.add(button_6);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText(problem_name[2]);
		textPane_12.setBounds(59, 0, 89, 40);
		panel_4.add(textPane_12);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText(largesubject[2]);
		textPane_13.setBounds(212, 0, 107, 40);
		panel_4.add(textPane_13);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText(smallsubject[2]);
		textPane_14.setBounds(322, 0, 99, 40);
		panel_4.add(textPane_14);
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText(form[2]);
		textPane_15.setBounds(425, 0, 70, 40);
		panel_4.add(textPane_15);
		
		JTextPane textPane_16 = new JTextPane();
		textPane_16.setText(level[2]);
		textPane_16.setBounds(499, 0, 59, 40);
		panel_4.add(textPane_16);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText(evaluation[2]);
		textPane_17.setBounds(145, 0, 66, 40);
		panel_4.add(textPane_17);
		}
		if(i>=3)
		{
			JPanel panel_5 = new JPanel();
			panel_5.setBounds(181, 284, 558, 40);
			contentPane.add(panel_5);
			panel_5.setLayout(null);
		
		JButton button_7 = new JButton(problem_id[3]);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[3]);
				setVisible(false);
			}
		});
		button_7.setBounds(0, 0, 59, 40);
		panel_5.add(button_7);
		
		JTextPane textPane_18 = new JTextPane();
		textPane_18.setText(problem_name[3]);
		textPane_18.setBounds(59, 0, 89, 40);
		panel_5.add(textPane_18);
		
		JTextPane textPane_19 = new JTextPane();
		textPane_19.setText(largesubject[3]);
		textPane_19.setBounds(212, 0, 107, 40);
		panel_5.add(textPane_19);
		
		JTextPane textPane_20 = new JTextPane();
		textPane_20.setText(smallsubject[3]);
		textPane_20.setBounds(322, 0, 99, 40);
		panel_5.add(textPane_20);
		
		JTextPane textPane_21 = new JTextPane();
		textPane_21.setText(form[3]);
		textPane_21.setBounds(425, 0, 70, 40);
		panel_5.add(textPane_21);
		
		JTextPane textPane_22 = new JTextPane();
		textPane_22.setText(level[3]);
		textPane_22.setBounds(499, 0, 59, 40);
		panel_5.add(textPane_22);
		
		JTextPane textPane_23 = new JTextPane();
		textPane_23.setText(evaluation[3]);
		textPane_23.setBounds(145, 0, 66, 40);
		panel_5.add(textPane_23);
		}
		if(i>=4)
		{
			JPanel panel_6 = new JPanel();
			panel_6.setBounds(181, 334, 558, 40);
			contentPane.add(panel_6);
			panel_6.setLayout(null);
		
		JButton button_8 = new JButton(problem_id[4]);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[4]);
				setVisible(false);
			}
		});
		button_8.setBounds(0, 0, 59, 40);
		panel_6.add(button_8);
		
		JTextPane textPane_24 = new JTextPane();
		textPane_24.setText(problem_name[4]);
		textPane_24.setBounds(59, 0, 89, 40);
		panel_6.add(textPane_24);
		
		JTextPane textPane_25 = new JTextPane();
		textPane_25.setText(largesubject[4]);
		textPane_25.setBounds(212, 0, 107, 40);
		panel_6.add(textPane_25);
		
		JTextPane textPane_26 = new JTextPane();
		textPane_26.setText(smallsubject[4]);
		textPane_26.setBounds(322, 0, 99, 40);
		panel_6.add(textPane_26);
		
		JTextPane textPane_27 = new JTextPane();
		textPane_27.setText(form[4]);
		textPane_27.setBounds(425, 0, 70, 40);
		panel_6.add(textPane_27);
		
		JTextPane textPane_28 = new JTextPane();
		textPane_28.setText(level[4]);
		textPane_28.setBounds(499, 0, 59, 40);
		panel_6.add(textPane_28);
		
		JTextPane textPane_29 = new JTextPane();
		textPane_29.setText(evaluation[4]);
		textPane_29.setBounds(145, 0, 66, 40);
		panel_6.add(textPane_29);
		}
		if(i>=5)
		{
			JPanel panel_7 = new JPanel();
			panel_7.setBounds(181, 384, 558, 40);
			contentPane.add(panel_7);
			panel_7.setLayout(null);
		
		JButton button_9 = new JButton(problem_id[5]);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[5]);
				setVisible(false);
			}
		});
		button_9.setBounds(0, 0, 59, 40);
		panel_7.add(button_9);
		
		JTextPane textPane_30 = new JTextPane();
		textPane_30.setText(problem_name[5]);
		textPane_30.setBounds(59, 0, 89, 40);
		panel_7.add(textPane_30);
		
		JTextPane textPane_31 = new JTextPane();
		textPane_31.setText(largesubject[5]);
		textPane_31.setBounds(212, 0, 107, 40);
		panel_7.add(textPane_31);
		
		JTextPane textPane_32 = new JTextPane();
		textPane_32.setText(smallsubject[5]);
		textPane_32.setBounds(322, 0, 99, 40);
		panel_7.add(textPane_32);
		
		JTextPane textPane_33 = new JTextPane();
		textPane_33.setText(form[5]);
		textPane_33.setBounds(425, 0, 70, 40);
		panel_7.add(textPane_33);
		
		JTextPane textPane_34 = new JTextPane();
		textPane_34.setText(level[5]);
		textPane_34.setBounds(499, 0, 59, 40);
		panel_7.add(textPane_34);
		
		JTextPane textPane_35 = new JTextPane();
		textPane_35.setText(evaluation[5]);
		textPane_35.setBounds(145, 0, 66, 40);
		panel_7.add(textPane_35);
		}
		if(i>=6)
		{
			JPanel panel_8 = new JPanel();
			panel_8.setBounds(181, 434, 558, 40);
			contentPane.add(panel_8);
			panel_8.setLayout(null);
		
		JButton button_10 = new JButton(problem_id[6]);
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetproblem/"+problem_id[6]);
				setVisible(false);
			}
		});
		button_10.setBounds(0, 0, 59, 40);
		panel_8.add(button_10);
		
		JTextPane textPane_36 = new JTextPane();
		textPane_36.setText(problem_name[6]);
		textPane_36.setBounds(59, 0, 89, 40);
		panel_8.add(textPane_36);
		
		JTextPane textPane_37 = new JTextPane();
		textPane_37.setText(largesubject[6]);
		textPane_37.setBounds(212, 0, 107, 40);
		panel_8.add(textPane_37);
		
		JTextPane textPane_38 = new JTextPane();
		textPane_38.setText(smallsubject[6]);
		textPane_38.setBounds(322, 0, 99, 40);
		panel_8.add(textPane_38);
		
		JTextPane textPane_39 = new JTextPane();
		textPane_39.setText(form[6]);
		textPane_39.setBounds(425, 0, 70, 40);
		panel_8.add(textPane_39);
		
		JTextPane textPane_40 = new JTextPane();
		textPane_40.setText(level[6]);
		textPane_40.setBounds(499, 0, 59, 40);
		panel_8.add(textPane_40);
		
		JTextPane textPane_41 = new JTextPane();
		textPane_41.setText(evaluation[6]);
		textPane_41.setBounds(145, 0, 66, 40);
		panel_8.add(textPane_41);
		}
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				page_i--;
				out.println("Msgproblemsearch/"+largesubject[0]+"/"+smallsubject[0]+"/"+page_i);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(348, 510, 63, 32);
		contentPane.add(btnNewButton);
		
		JButton button_3 = new JButton(">");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_i++;
				out.println("Msgproblemsearch/"+largesubject[0]+"/"+smallsubject[0]+"/"+page_i);
				setVisible(false);
			}
		});
		button_3.setBounds(493, 510, 63, 32);
		contentPane.add(button_3);
		
		JEditorPane dtrpnPageNumber = new JEditorPane();
		dtrpnPageNumber.setText("page number");
		dtrpnPageNumber.setBounds(408, 510, 86, 32);
		contentPane.add(dtrpnPageNumber);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background()); 
		panel.setBounds(121, 0, 673, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}
}

