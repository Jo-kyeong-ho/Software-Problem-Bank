package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemBoard extends JFrame {

	/*
	 * gui of select subject of problem and send selected subject to server
	 */
	private JPanel contentPane;
	private JTextField txtProblemBoard;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public static ProblemBoard frame;

	public ProblemBoard(Socket socket1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		setTitle("Problem Board");
		socket=socket1;
		 try {
    		  in = new BufferedReader(new InputStreamReader(
    		            socket.getInputStream()));
    	        out = new PrintWriter(socket.getOutputStream(), true);
    	   	} catch (IOException e1) {
    	   		// TODO Auto-generated catch block
    	   		e1.printStackTrace();
    	   	}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel menu = new JLabel();
		menu.setIcon(ImageClass.Menu_background()); // 배경 이미지
		menu.setBounds(0, 0, 128, 589);
		contentPane.add(menu);
		menu.setLayout(null);
		
		JButton button = new JButton("My Page");
		button.addActionListener(new ActionListener() {      //Mypage 버튼 누르면 mypage로 이동 
			public void actionPerformed(ActionEvent e) {
				out.println("msgmypage");
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {    //질문게시판 버튼 누르면 Question board로 이동
			public void actionPerformed(ActionEvent e) {
				/*
				 클릭시 server로
				Msgquestionboard 보냄
				*/
				out.println("Msgquestionboard/1");
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {    //문제게시판 버튼 누르면 Problem board로 이동
			public void actionPerformed(ActionEvent e) {
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		txtProblemBoard = new JTextField();
		txtProblemBoard.setBounds(172, 32, 582, 49);
		contentPane.add(txtProblemBoard);
		txtProblemBoard.setText("Problem Board");
		txtProblemBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtProblemBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtProblemBoard.setColumns(10);
		
				Choice Type = new Choice();
				Type.setBounds(301, 261, 87, 21);
				contentPane.add(Type);
				
				Choice detail = new Choice();
				detail.setBounds(430, 262, 87, 21);
				contentPane.add(detail);
		
				JButton btnSearch = new JButton("search");     // 검색 버튼 
				/*
				클릭하면 서버로
				Msgproblemsearch/큰주제/작은주제/1 전송
		        */
				btnSearch.addActionListener(new ActionListener() {    //문제게시판 버튼 누르면 Problem board로 이동
					public void actionPerformed(ActionEvent e) {
						String a=Type.getName();
						System.out.println(a);
						String b=detail.getName();
						System.out.println(b);
						String k="Msgproblemsearch/"+Type.getSelectedItem()+"/"+detail.getSelectedItem()+"/1";
						out.println(k);
			            setVisible(false);
					}
				});
				btnSearch.setBounds(536, 260, 97, 23);
				contentPane.add(btnSearch);
				
				JLabel panel  = new JLabel();
				panel.setBackground(Color.WHITE);
				panel.setIcon(ImageClass.background()); // 배경 이미지
				panel.setBounds(121, 0, 673, 589); ///////////////////////////////////////////////////////////////////////////////////////////////////////
				contentPane.add(panel);
				panel.setLayout(null);
				
				detail.add("세부주제");                      // 문항 집어넣기
				detail.add("introduction");
				detail.add("applicationlayer");
				detail.add("transportlayer");
				detail.add("networklayer");				
				detail.add("linklayer");
				detail.add("wireless");
				detail.select(0);
				
				Type.add("큰주제");                         // 문항 집어넣기
				Type.add("os"); 
				Type.add("network");
				Type.add("algorithm");
				Type.add("dataBase");
				
		Type.select(0);
		setVisible(true);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

