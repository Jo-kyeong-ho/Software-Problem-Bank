package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class QuestionBoard extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuestionBoard;

	/**
	 * Launch the application.
	 */
	public static QuestionBoard frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new QuestionBoard();
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
	public QuestionBoard() {
        /*
 		        서버로부터
		     Msgquestionboard/질문id*문제이름*큰주제*작은주제*서술형*질문자/똑같고/똑같고10개받음(없으면 null)
		        받음
        */
		setTitle("Question Board");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBounds(0, 0, 121, 589);
		menu.setLayout(null);
		contentPane.add(menu);
		
		JButton button = new JButton("My Page");                 
		button.addActionListener(new ActionListener() {          //Mypage 버튼 누르면 mypage로 이동 
			public void actionPerformed(ActionEvent e) {
				new mypage().setVisible(true);
	            setVisible(false);
	            
			}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {        //질문게시판 버튼 누르면 Question board로 이동 
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭시 server로
				Msgquestionboard 보냄
				*/
				new QuestionBoard().setVisible(true);
	            setVisible(false);
	            }
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2 .addActionListener(new ActionListener() {       //문제게시판 버튼 누르면 Problem board로 이동
			public void actionPerformed(ActionEvent e) {
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(121, 0, 673, 589);
		contentPane.add(panel);
		
		txtQuestionBoard = new JTextField();
		txtQuestionBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtQuestionBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuestionBoard.setText("Question Board");
		txtQuestionBoard.setColumns(10);
		txtQuestionBoard.setBounds(44, 44, 582, 49);
		panel.add(txtQuestionBoard);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(44, 133, 582, 418);
		panel.add(panel_2);
		
		JButton btnSendquestion = new JButton("sendQuestion");
		btnSendquestion.setBounds(448, 376, 122, 32);
		panel_2.add(btnSendquestion);
		
		JPanel List1 = new JPanel();
		List1.setLayout(null);
		List1.setBounds(12, 21, 558, 40);
		panel_2.add(List1);
		
		JButton btnNewButton = new JButton("No.1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			/*
			 클릭하면 서버로
			 Msggetquestion/질문아이디 보냄
			*/
			}
		});
		btnNewButton.setBounds(0, 0, 59, 40);
		List1.add(btnNewButton);
		
		JTextPane textName1 = new JTextPane();
		textName1.setText("문제이름1");
		textName1.setBounds(59, 0, 148, 40);
		List1.add(textName1);
		
		JTextPane textBig1 = new JTextPane();
		textBig1.setText("큰주제1");
		textBig1.setBounds(211, 0, 99, 40);
		List1.add(textBig1);
		
		JTextPane textSmall1 = new JTextPane();
		textSmall1.setText("작은주제1");
		textSmall1.setBounds(314, 0, 99, 40);
		List1.add(textSmall1);
		
		JTextPane Type1 = new JTextPane();
		Type1.setText("주관식");
		Type1.setBounds(417, 0, 59, 40);
		List1.add(Type1);
		
		JTextPane textUser1 = new JTextPane();
		textUser1.setText("질문자");
		textUser1.setBounds(487, 0, 59, 40);
		List1.add(textUser1);
		
		JPanel List2 = new JPanel();
		List2.setLayout(null);
		List2.setBounds(12, 75, 558, 40);
		panel_2.add(List2);
		
		JButton btnNo = new JButton("No.2");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭하면 서버로
				 Msggetquestion/질문아이디 보냄
				*/
			}
		});
		btnNo.setBounds(0, 0, 59, 40);
		List2.add(btnNo);
		
		JTextPane textName2 = new JTextPane();
		textName2.setText("문제이름2");
		textName2.setBounds(59, 0, 148, 40);
		List2.add(textName2);
		
		JTextPane textBig2 = new JTextPane();
		textBig2.setText("큰주제2");
		textBig2.setBounds(211, 0, 99, 40);
		List2.add(textBig2);
		
		JTextPane textSmall2 = new JTextPane();
		textSmall2.setText("작은주제2");
		textSmall2.setBounds(314, 0, 99, 40);
		List2.add(textSmall2);
		
		JTextPane Type2 = new JTextPane();
		Type2.setBounds(417, 0, 59, 40);
		List2.add(Type2);
		Type2.setText("주관식");
		
		JTextPane textUser2 = new JTextPane();
		textUser2.setText("질문자");
		textUser2.setBounds(488, 0, 59, 40);
		List2.add(textUser2);
		
		JPanel List3 = new JPanel();
		List3.setLayout(null);
		List3.setBounds(12, 125, 558, 40);
		panel_2.add(List3);
		
		JButton btnNo_1 = new JButton("No.3");
		btnNo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				     클릭하면 서버로
				   Msggetquestion/질문아이디
				     보냄
				*/
			}
		});
		btnNo_1.setBounds(0, 0, 59, 40);
		List3.add(btnNo_1);
		
		JTextPane textName3 = new JTextPane();
		textName3.setText("문제이름3");
		textName3.setBounds(59, 0, 148, 40);
		List3.add(textName3);
		
		JTextPane textBig3 = new JTextPane();
		textBig3.setText("큰주제3");
		textBig3.setBounds(211, 0, 99, 40);
		List3.add(textBig3);
		
		JTextPane textSmall3 = new JTextPane();
		textSmall3.setText("작은주제3");
		textSmall3.setBounds(314, 0, 99, 40);
		List3.add(textSmall3);
		
		JTextPane Type3 = new JTextPane();
		Type3.setBounds(417, 0, 59, 40);
		List3.add(Type3);
		Type3.setText("주관식");
		
		JTextPane textUser3 = new JTextPane();
		textUser3.setText("질문자");
		textUser3.setBounds(488, 0, 59, 40);
		List3.add(textUser3);
		
		JPanel List4 = new JPanel();
		List4.setLayout(null);
		List4.setBounds(12, 175, 558, 40);
		panel_2.add(List4);
		
		JButton btnNo_2 = new JButton("No.4");
		btnNo_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭하면 서버로
				 Msggetquestion/질문아이디 보냄
				*/
			}
		});
		btnNo_2.setBounds(0, 0, 59, 40);
		List4.add(btnNo_2);
		
		JTextPane textName4 = new JTextPane();
		textName4.setText("문제이름4");
		textName4.setBounds(59, 0, 148, 40);
		List4.add(textName4);
		
		JTextPane textBig4 = new JTextPane();
		textBig4.setText("큰주제4");
		textBig4.setBounds(211, 0, 99, 40);
		List4.add(textBig4);
		
		JTextPane textSmall4 = new JTextPane();
		textSmall4.setText("작은주제4");
		textSmall4.setBounds(314, 0, 99, 40);
		List4.add(textSmall4);
		
		JTextPane Type4 = new JTextPane();
		Type4.setBounds(417, 0, 59, 40);
		List4.add(Type4);
		Type4.setText("주관식");
		
		JTextPane textUser4 = new JTextPane();
		textUser4.setText("질문자");
		textUser4.setBounds(488, 0, 59, 40);
		List4.add(textUser4);
		
		JPanel List5 = new JPanel();
		List5.setLayout(null);
		List5.setBounds(12, 225, 558, 40);
		panel_2.add(List5);
		
		JButton btnNo_3 = new JButton("No.5");
		btnNo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			/*
			 클릭하면 서버로
			 Msggetquestion/질문아이디 보냄
			*/
		});
		btnNo_3.setBounds(0, 0, 59, 40);
		List5.add(btnNo_3);
		
		JTextPane textName5 = new JTextPane();
		textName5.setText("문제이름5");
		textName5.setBounds(59, 0, 148, 40);
		List5.add(textName5);
		
		JTextPane textBig5 = new JTextPane();
		textBig5.setText("큰주제5");
		textBig5.setBounds(211, 0, 99, 40);
		List5.add(textBig5);
		
		JTextPane textSmall5 = new JTextPane();
		textSmall5.setText("작은주제5");
		textSmall5.setBounds(314, 0, 99, 40);
		List5.add(textSmall5);
		
		JTextPane Type5 = new JTextPane();
		Type5.setBounds(417, 0, 59, 40);
		List5.add(Type5);
		Type5.setText("주관식");
		
		JTextPane textUser5 = new JTextPane();
		textUser5.setText("질문자");
		textUser5.setBounds(488, 0, 59, 40);
		List5.add(textUser5);
		
		JPanel List6 = new JPanel();
		List6.setLayout(null);
		List6.setBounds(12, 275, 558, 40);
		panel_2.add(List6);
		
		JButton btnNo_4 = new JButton("No.6");
		btnNo_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭하면 서버로
				 Msggetquestion/질문아이디 보냄
				*/
			}
		});
		btnNo_4.setBounds(0, 0, 59, 40);
		List6.add(btnNo_4);
		
		JTextPane textName6 = new JTextPane();
		textName6.setText("문제이름6");
		textName6.setBounds(59, 0, 148, 40);
		List6.add(textName6);
		
		JTextPane textBig6 = new JTextPane();
		textBig6.setText("큰주제6");
		textBig6.setBounds(211, 0, 99, 40);
		List6.add(textBig6);
		
		JTextPane textSmall6 = new JTextPane();
		textSmall6.setText("작은주제6");
		textSmall6.setBounds(314, 0, 99, 40);
		List6.add(textSmall6);
		
		JTextPane Type6 = new JTextPane();
		Type6.setBounds(417, 0, 59, 40);
		List6.add(Type6);
		Type6.setText("주관식");
		
		JTextPane textUser6 = new JTextPane();
		textUser6.setText("질문자");
		textUser6.setBounds(488, 0, 59, 40);
		List6.add(textUser6);
		
		JPanel List7 = new JPanel();
		List7.setLayout(null);
		List7.setBounds(12, 326, 558, 40);
		panel_2.add(List7);
		
		JButton btnNo_5 = new JButton("No.7");
		btnNo_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭하면 서버로
				 Msggetquestion/질문아이디 보냄
				*/
			}
		});
		btnNo_5.setBounds(0, 0, 59, 40);
		List7.add(btnNo_5);
		
		JTextPane textName7 = new JTextPane();
		textName7.setText("문제이름7");
		textName7.setBounds(59, 0, 148, 40);
		List7.add(textName7);
		
		JTextPane textBig7 = new JTextPane();
		textBig7.setText("큰주제7");
		textBig7.setBounds(211, 0, 99, 40);
		List7.add(textBig7);
		
		JTextPane textSmall7 = new JTextPane();
		textSmall7.setText("작은주제7");
		textSmall7.setBounds(314, 0, 99, 40);
		List7.add(textSmall7);
		
		JTextPane Type7 = new JTextPane();
		Type7.setBounds(417, 0, 59, 40);
		List7.add(Type7);
		Type7.setText("주관식");
		
		JTextPane textUser7 = new JTextPane();
		textUser7.setText("질문자");
		textUser7.setBounds(488, 0, 59, 40);
		List7.add(textUser7);
		
		setVisible(true);
	}
}
