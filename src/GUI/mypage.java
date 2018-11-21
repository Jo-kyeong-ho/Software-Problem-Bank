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
		     Server�κ��� 
             msgmypage/id/�̸�/��ü�亯��/ü�ô亯��/��������Ʈ/��ŷ/�Ұ���.txt 
                       ����
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
		btnMypage.addActionListener(new ActionListener() {        //mypage ��ư ������ My Page�� �̵� 
			public void actionPerformed(ActionEvent e) {
		    /*
                              Ŭ���� server��
                 Msgmypage ����
		    */
				new mypage().setVisible(true);
	            setVisible(false);
	            }
		});
		btnMypage.setBounds(0, 80, 120, 47);
		menu.add(btnMypage);
		
		btnQuestion = new JButton("�����Խ���");
		btnQuestion.addActionListener(new ActionListener() {        //�����Խ��� ��ư ������ Question board�� �̵� 
			public void actionPerformed(ActionEvent e) {
            /*				
                            Ŭ���� server��
				Msgquestionboard ����
            */
				new QuestionBoard().setVisible(true);
	            setVisible(false);
	            }
		});
		btnQuestion.setBounds(0, 173, 120, 47);
		menu.add(btnQuestion);
		
		btnProblem = new JButton("�����Խ���");
		btnProblem.addActionListener(new ActionListener() {        //�����Խ��� ��ư ������ Question board�� �̵�
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
		txtImage.setText("image");                 //Ŭ���̾�Ʈ�� ����      ��������Ʈ���� ���������

		txtImage.setBounds(44, 34, 189, 158);
		panel.add(txtImage);
		txtImage.setColumns(10);
		
		introduce = new JPanel();
		introduce.setBounds(44, 288, 189, 158);
		panel.add(introduce);
		introduce.setLayout(null);
		
		btnUpdate = new JButton("����");
		btnUpdate.addActionListener(new ActionListener() {        //���� ��ư ������ �Ұ��� ���� â���� 
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
			       Ŭ���� ������
                Msgmyquestion����
            */
			}
		});
		btnMyquestion_1.setBounds(12, 66, 91, 41);
		buttons.add(btnMyquestion_1);
		
		btnMyanswer = new JButton("myAnswer");
		btnMyanswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            /*
				Ŭ���� ������
				Msgmyanswer����
            */
			}
		});
		btnMyanswer.setBounds(122, 66, 91, 41);
		buttons.add(btnMyanswer);
		
		btnMyupload = new JButton("myUpload");
		btnMyupload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			       Ŭ���� ������
                Msgmyupload����
            */
			}
		});
		btnMyupload.setBounds(237, 66, 91, 41);
		buttons.add(btnMyupload);
	}
}
