package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mypage extends JFrame {

	private JPanel myPage;
	private JPanel menu;
	private JButton btnMypage;
	private JButton btnQuestion;
	private JButton btnProblem;
	private JTextField txtImage;
	private JPanel introduce;
	private JTextField txtIntroduce;
	private JButton btnUpdate;
	private JPanel buttons;
	private JButton btnMyquestion_1;
	private JButton btnMyanswer;
	private JButton btnMyupload;
	private JPanel Point;

	/**
	 * Launch the application.
	 */
	public static mypage frame;
	private JTextField txtPointBar;
	private JTextField txtPointRank;
	private JTextField txtdAll;
	private JTextField txtdSelect;
	private JTextField txtchoose;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new mypage();
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
	public mypage() {
		/*
		     Server로부터 
             msgmypage/id/이름/전체답변수/체택답변수/누적포인트/랭킹/소개글.txt 
                       받음
        */
		
		setTitle("My page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		myPage = new JPanel();
		myPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(myPage);
		myPage.setLayout(null);
		
		menu = new JPanel();
		menu.setBounds(0, 0, 120, 589);
		myPage.add(menu);
		menu.setLayout(null);
		
		btnMypage = new JButton("My Page");
		btnMypage.addActionListener(new ActionListener() {        //mypage 버튼 누르면 My Page로 이동 
			public void actionPerformed(ActionEvent e) {
		    /*
                              클릭시 server로
                 Msgmypage 보냄
		    */
				new mypage().setVisible(true);
	            setVisible(false);
	            }
		});
		btnMypage.setBounds(0, 80, 120, 47);
		menu.add(btnMypage);
		
		btnQuestion = new JButton("질문게시판");
		btnQuestion.addActionListener(new ActionListener() {        //질문게시판 버튼 누르면 Question board로 이동 
			public void actionPerformed(ActionEvent e) {
            /*				
                            클릭시 server로
				Msgquestionboard 보냄
            */
				new QuestionBoard().setVisible(true);
	            setVisible(false);
	            }
		});
		btnQuestion.setBounds(0, 173, 120, 47);
		menu.add(btnQuestion);
		
		btnProblem = new JButton("문제게시판");
		btnProblem.addActionListener(new ActionListener() {        //문제게시판 버튼 누르면 Question board로 이동
			public void actionPerformed(ActionEvent e) {
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		btnProblem.setBounds(0, 269, 120, 47);
		menu.add(btnProblem);
		
		Panel panel = new Panel();
		panel.setBounds(121, 0, 673, 589);
		myPage.add(panel);
		panel.setLayout(null);
		
		txtImage = new JTextField();                
		txtImage.setText("image");                 //클라이언트에 저장      누적포인트보고 가져오면됨

		txtImage.setBounds(44, 34, 189, 158);
		panel.add(txtImage);
		txtImage.setColumns(10);
		
		introduce = new JPanel();
		introduce.setBounds(44, 288, 189, 158);
		panel.add(introduce);
		introduce.setLayout(null);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {        //수정 버튼 누르면 소개글 수정 창으로 
			public void actionPerformed(ActionEvent e) {    
				new EditIntroduce();
				frame.setVisible(false); 
			}
		});
		btnUpdate.setBounds(118, 135, 71, 23);
		introduce.add(btnUpdate);
		
		txtIntroduce = new JTextField();
		txtIntroduce.setBounds(0, 0, 189, 158);
		txtIntroduce.setText("introduce");
		introduce.add(txtIntroduce);
		txtIntroduce.setColumns(10);
		
		Point = new JPanel();
		Point.setLayout(null);
		Point.setBounds(271, 34, 355, 158);
		panel.add(Point);
		
		txtPointBar = new JTextField();
		txtPointBar.setText("Point Bar");
		txtPointBar.setBounds(54, 10, 246, 21);
		Point.add(txtPointBar);
		txtPointBar.setColumns(10);
		
		txtPointRank = new JTextField();
		txtPointRank.setText("Point Rank");
		txtPointRank.setColumns(10);
		txtPointRank.setBounds(53, 116, 246, 21);
		Point.add(txtPointRank);
		
		txtdAll = new JTextField();
		txtdAll.setText("%d all ");
		txtdAll.setColumns(10);
		txtdAll.setBounds(32, 67, 80, 21);
		Point.add(txtdAll);
		
		txtdSelect = new JTextField();
		txtdSelect.setText("%d select");
		txtdSelect.setColumns(10);
		txtdSelect.setBounds(144, 67, 80, 21);
		Point.add(txtdSelect);
		
		txtchoose = new JTextField();
		txtchoose.setText("%f choose");
		txtchoose.setColumns(10);
		txtchoose.setBounds(250, 67, 80, 21);
		Point.add(txtchoose);
		
		buttons = new JPanel();
		buttons.setLayout(null);
		buttons.setBounds(271, 288, 355, 158);
		panel.add(buttons);
		
		btnMyquestion_1 = new JButton("myQuestion");
		btnMyquestion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			       클릭시 서버로
                Msgmyquestion전송
            */
			}
		});
		btnMyquestion_1.setBounds(12, 66, 91, 41);
		buttons.add(btnMyquestion_1);
		
		btnMyanswer = new JButton("myAnswer");
		btnMyanswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            /*
				클릭시 서버로
				Msgmyanswer전송
            */
			}
		});
		btnMyanswer.setBounds(122, 66, 91, 41);
		buttons.add(btnMyanswer);
		
		btnMyupload = new JButton("myUpload");
		btnMyupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			       클릭시 서버로
                Msgmyupload전송
            */
			}
		});
		btnMyupload.setBounds(237, 66, 91, 41);
		buttons.add(btnMyupload);
	}
}
