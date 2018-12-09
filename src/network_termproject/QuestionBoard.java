package network_termproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class QuestionBoard extends JFrame {

	/*
	 * gui of question's list
	 * it request certain question's content to server after user click go
	 */
	private JPanel contentPane;
	private JTextField txtQuestionBoard;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String input;
	String[] question_id = new String[7];
	String[] question_name=new String[7];
	String[] largesubject=new String[7];
	String[] smallsubject=new String[7];
	String[] form=new String[7];
	String[] questionuserid=new String[7];
	int check=0;
	String page;
	int page_i;
	public QuestionBoard(Socket socket1,String line) {
       
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;
		String input=line;
		System.out.println(line);
		StringTokenizer token=new StringTokenizer(input,"/");
		question_id[0]=token.nextToken();
		page=token.nextToken();
		page_i=Integer.parseInt(page);
		int i=0;
		while(token.hasMoreTokens())
		{
			question_id[i]=token.nextToken();
			question_name[i]=token.nextToken();
			largesubject[i]=token.nextToken();
			smallsubject[i]=token.nextToken();
			form[i]=token.nextToken();
			questionuserid[i]=token.nextToken();
			i++;
		}
		i--;
		 try {
    		  in = new BufferedReader(new InputStreamReader(
    		            socket.getInputStream()));
    	        out = new PrintWriter(socket.getOutputStream(), true);
    	   	} catch (IOException e1) {
    	   		
    	   		e1.printStackTrace();
    	   	}
		System.out.println(question_id[0]);
		System.out.println(i);
		setTitle("Question Board");
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
			public void actionPerformed(ActionEvent e) {
				out.println("msgmypage");
	            setVisible(false);
	            
			}			
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
		button_2 .addActionListener(new ActionListener() {       
			public void actionPerformed(ActionEvent e) {
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		txtQuestionBoard = new JTextField();
		txtQuestionBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtQuestionBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuestionBoard.setText("Question Board");
		txtQuestionBoard.setColumns(10);
		txtQuestionBoard.setBounds(166, 56, 582, 49);
		contentPane.add(txtQuestionBoard);
		
		JButton btnSendquestion = new JButton("sendQuestion");
		btnSendquestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new QuestionUpload(socket);
				setVisible(false);
			}
		});
		btnSendquestion.setBounds(600, 494, 122, 32);
		contentPane.add(btnSendquestion);
		
		JLabel lblNewLabel = new JLabel("문제번호"); 
		lblNewLabel.setBounds(177, 110, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("문제이름");
		label.setBounds(275, 110, 57, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("큰주제");
		label_1.setBounds(394, 110, 57, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("세부주제");
		label_2.setBounds(494, 110, 57, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("유형");
		label_3.setBounds(600, 110, 34, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("질문자");     
		label_4.setBounds(670, 110, 57, 15);
		contentPane.add(label_4);
		
		if(i>=0)
		{
		System.out.println("1번구역");
		JPanel List1 = new JPanel();
		List1.setLayout(null);
		List1.setBounds(177, 135, 558, 40);
		contentPane.add(List1);
		
		JButton btnNewButton = new JButton(question_id[0]);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Msggetquestion/"+question_id[0]);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(0, 0, 59, 40);
		List1.add(btnNewButton);
		
		JTextPane textName1 = new JTextPane();
		textName1.setText(question_name[0]);
		textName1.setBounds(59, 0, 148, 40);
		List1.add(textName1);
		
		JTextPane textBig1 = new JTextPane();
		textBig1.setText(largesubject[0]);
		textBig1.setBounds(211, 0, 99, 40);
		List1.add(textBig1);
		
		JTextPane textSmall1 = new JTextPane();
		textSmall1.setText(smallsubject[0]);
		textSmall1.setBounds(314, 0, 99, 40);
		List1.add(textSmall1);
		
		JTextPane Type1 = new JTextPane();
		Type1.setText(form[0]);
		Type1.setBounds(417, 0, 59, 40);
		List1.add(Type1);
		
		JTextPane textUser1 = new JTextPane();
		textUser1.setText(questionuserid[0]);
		textUser1.setBounds(487, 0, 59, 40);
		List1.add(textUser1);
		}
		if(i>=1)
		{
			System.out.println("2번구역");
		JPanel List2 = new JPanel();
		List2.setLayout(null);
		List2.setBounds(177, 189, 558, 40);
		contentPane.add(List2);
		
		JButton btnNo = new JButton(question_id[1]);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[1]);
				System.out.println("check");
				setVisible(false);
			}
		});
		btnNo.setBounds(0, 0, 59, 40);
		List2.add(btnNo);
		
		JTextPane textName2 = new JTextPane();
		textName2.setText(question_name[1]);
		textName2.setBounds(59, 0, 148, 40);
		List2.add(textName2);
		
		JTextPane textBig2 = new JTextPane();
		textBig2.setText(largesubject[1]);
		textBig2.setBounds(211, 0, 99, 40);
		List2.add(textBig2);
		
		JTextPane textSmall2 = new JTextPane();
		textSmall2.setText(smallsubject[1]);
		textSmall2.setBounds(314, 0, 99, 40);
		List2.add(textSmall2);
		
		JTextPane Type2 = new JTextPane();
		Type2.setBounds(417, 0, 59, 40);
		List2.add(Type2);
		Type2.setText(form[1]);
		
		JTextPane textUser2 = new JTextPane();
		textUser2.setText(questionuserid[1]);
		textUser2.setBounds(488, 0, 59, 40);
		List2.add(textUser2);
		}
		if(i>=2)
		{
		System.out.println("3번구역");
		JPanel List3 = new JPanel();
		List3.setLayout(null);
		List3.setBounds(177, 239, 558, 40);
		contentPane.add(List3);
		
		JButton btnNo_1 = new JButton(question_id[2]);
		btnNo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[2]);
				setVisible(false);
			}
		});
		btnNo_1.setBounds(0, 0, 59, 40);
		List3.add(btnNo_1);
		
		JTextPane textName3 = new JTextPane();
		textName3.setText(question_name[2]);
		textName3.setBounds(59, 0, 148, 40);
		List3.add(textName3);
		
		JTextPane textBig3 = new JTextPane();
		textBig3.setText(largesubject[2]);
		textBig3.setBounds(211, 0, 99, 40);
		List3.add(textBig3);
		
		JTextPane textSmall3 = new JTextPane();
		textSmall3.setText(smallsubject[2]);
		textSmall3.setBounds(314, 0, 99, 40);
		List3.add(textSmall3);
		
		JTextPane Type3 = new JTextPane();
		Type3.setBounds(417, 0, 59, 40);
		List3.add(Type3);
		Type3.setText(form[2]);
		
		JTextPane textUser3 = new JTextPane();
		textUser3.setText(questionuserid[2]);
		textUser3.setBounds(488, 0, 59, 40);
		List3.add(textUser3);
		}
		if(i>=3)
		{
		JPanel List4 = new JPanel();
		List4.setLayout(null);
		List4.setBounds(177, 289, 558, 40);
		contentPane.add(List4);
		
		JButton btnNo_2 = new JButton(question_id[3]);
		btnNo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[3]);
				setVisible(false);
			}
		});
		btnNo_2.setBounds(0, 0, 59, 40);
		List4.add(btnNo_2);
		
		JTextPane textName4 = new JTextPane();
		textName4.setText(question_name[3]);
		textName4.setBounds(59, 0, 148, 40);
		List4.add(textName4);
		
		JTextPane textBig4 = new JTextPane();
		textBig4.setText(largesubject[3]);
		textBig4.setBounds(211, 0, 99, 40);
		List4.add(textBig4);
		
		JTextPane textSmall4 = new JTextPane();
		textSmall4.setText(smallsubject[3]);
		textSmall4.setBounds(314, 0, 99, 40);
		List4.add(textSmall4);
		
		JTextPane Type4 = new JTextPane();
		Type4.setBounds(417, 0, 59, 40);
		List4.add(Type4);
		Type4.setText(form[3]);
		
		JTextPane textUser4 = new JTextPane();
		textUser4.setText(questionuserid[3]);
		textUser4.setBounds(488, 0, 59, 40);
		List4.add(textUser4);
		}
		if(i>=4)
		{
		JPanel List5 = new JPanel();
		List5.setLayout(null);
		List5.setBounds(177, 339, 558, 40);
		contentPane.add(List5);
		
		JButton btnNo_3 = new JButton(question_id[4]);
		btnNo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[4]);
				setVisible(false);
			}			
		});
		btnNo_3.setBounds(0, 0, 59, 40);
		List5.add(btnNo_3);
		
		JTextPane textName5 = new JTextPane();
		textName5.setText(question_name[4]);
		textName5.setBounds(59, 0, 148, 40);
		List5.add(textName5);
		
		JTextPane textBig5 = new JTextPane();
		textBig5.setText(largesubject[4]);
		textBig5.setBounds(211, 0, 99, 40);
		List5.add(textBig5);
		
		JTextPane textSmall5 = new JTextPane();
		textSmall5.setText(smallsubject[4]);
		textSmall5.setBounds(314, 0, 99, 40);
		List5.add(textSmall5);
		
		JTextPane Type5 = new JTextPane();
		Type5.setBounds(417, 0, 59, 40);
		List5.add(Type5);
		Type5.setText(form[4]);
		
		JTextPane textUser5 = new JTextPane();
		textUser5.setText(questionuserid[4]);
		textUser5.setBounds(488, 0, 59, 40);
		List5.add(textUser5);
		}
		if(i>=5)
		{
		JPanel List6 = new JPanel();
		List6.setLayout(null);
		List6.setBounds(177, 389, 558, 40);
		contentPane.add(List6);
		
		
		JButton btnNo_4 = new JButton(question_id[5]);
		btnNo_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[5]);
				setVisible(false);
			}
		});
		btnNo_4.setBounds(0, 0, 59, 40);
		List6.add(btnNo_4);
		
		JTextPane textName6 = new JTextPane();
		textName6.setText(question_name[5]);
		textName6.setBounds(59, 0, 148, 40);
		List6.add(textName6);
		
		JTextPane textBig6 = new JTextPane();
		textBig6.setText(largesubject[5]);
		textBig6.setBounds(211, 0, 99, 40);
		List6.add(textBig6);
		
		JTextPane textSmall6 = new JTextPane();
		textSmall6.setText(smallsubject[5]);
		textSmall6.setBounds(314, 0, 99, 40);
		List6.add(textSmall6);
		
		JTextPane Type6 = new JTextPane();
		Type6.setBounds(417, 0, 59, 40);
		List6.add(Type6);
		Type6.setText(form[5]);
		
		JTextPane textUser6 = new JTextPane();
		textUser6.setText(questionuserid[5]);
		textUser6.setBounds(488, 0, 59, 40);
		List6.add(textUser6);
		}
		if(i>=6)
		{
		JPanel List7 = new JPanel();
		List7.setLayout(null);
		List7.setBounds(177, 440, 558, 40);
		contentPane.add(List7);
		
		JButton btnNo_5 = new JButton(question_id[6]);
		btnNo_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetquestion/"+question_id[6]);
				setVisible(false);
			}
		});
		btnNo_5.setBounds(0, 0, 59, 40);
		List7.add(btnNo_5);
		
		JTextPane textName7 = new JTextPane();
		textName7.setText(question_name[6]);
		textName7.setBounds(59, 0, 148, 40);
		List7.add(textName7);
		
		JTextPane textBig7 = new JTextPane();
		textBig7.setText(largesubject[6]);
		textBig7.setBounds(211, 0, 99, 40);
		List7.add(textBig7);
		
		JTextPane textSmall7 = new JTextPane();
		textSmall7.setText(smallsubject[6]);
		textSmall7.setBounds(314, 0, 99, 40);
		List7.add(textSmall7);
		
		JTextPane Type7 = new JTextPane();
		Type7.setBounds(417, 0, 59, 40);
		List7.add(Type7);
		Type7.setText(form[6]);
		
		JTextPane textUser7 = new JTextPane();
		textUser7.setText(questionuserid[6]);
		textUser7.setBounds(488, 0, 59, 40);
		List7.add(textUser7);	
		}
		JButton btLEFT = new JButton("<");              
		btLEFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				page_i--;
				if(page_i!=0)
				{
					out.println("Msgquestionboard/"+page_i);
					setVisible(false);
				}
			}
		});
		btLEFT.setBounds(370, 499, 51, 23);
		contentPane.add(btLEFT);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("page number");
		textPane.setBounds(419, 499, 58, 23);
		contentPane.add(textPane);
		
		JButton btRIGHT = new JButton(">");                
		btRIGHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page_i++;
				if(page_i!=0)
				{
					out.println("Msgquestionboard/"+page_i);
					setVisible(false);
				}
			}
		});
		btRIGHT.setBounds(476, 499, 51, 23);
		contentPane.add(btRIGHT);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background()); 
		panel.setBounds(121, 0, 673, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		
		setVisible(true);
		
	}
}

