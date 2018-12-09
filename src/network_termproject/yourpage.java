package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.StringTokenizer;

public class yourpage extends JFrame {

	private JPanel myPage;
	private JPanel menu;
	private JButton btnMypage;
	private JButton btnQuestion;
	private JButton btnProblem;
	private JTextField txtImage;
	private JPanel introduce;
	private JTextPane txtIntroduce;
	private JButton btnUpdate;
	private JPanel buttons;
	private JButton btnMyquestion_1;
	private JButton btnMyanswer;
	private JButton btnMyupload;
	private JPanel Point;
	private JPanel image;
	private JLabel lblNewLabel;
	private Label label_4;
	private Label label_5;
	private Label label_3;
	PrintWriter out = null;
	BufferedReader in=null;
	Socket socket;
	String id;
	/**
	 * Launch the application.
	 */
	private JTextField txtPointBar;
	private JTextField txtPointRank;
	private JTextField txtdAll;
	private JTextField txtdSelect;
	private JTextField txtchoose;
	String input;

	/**
	 * Create the frame.
	 */
	public yourpage(Socket socket1,String input) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		System.out.println(input);
		String name;
    	String total;
    	String selectnum;
    	String accumnum;
    	String current_point;
    	String rank;
    	String introducecontent="";
    	int num;
    	String temp="";
		socket=socket1;
		 try {
      		  in = new BufferedReader(new InputStreamReader(
      		            socket.getInputStream()));
      	        out = new PrintWriter(socket.getOutputStream(), true);
      	   	} catch (IOException e1) {
      	   		// TODO Auto-generated catch block
      	   		e1.printStackTrace();
      	   	}
		StringTokenizer token=new StringTokenizer(input,"/");
     	id=token.nextToken();
     	id=token.nextToken();
     	name=token.nextToken();
     	total=token.nextToken();
     	selectnum=token.nextToken();
     	current_point=token.nextToken();
     	accumnum=token.nextToken();
     	rank=token.nextToken();
     	System.out.println("mypage test");
     	int total_i,select_i;
     	total_i=Integer.parseInt(total);
     	select_i=Integer.parseInt(selectnum);
     	int k;
     	if(total_i!=0)
     	{
     		k=(select_i*100)/total_i;
     	}
     	else
     		k=0;
     	temp="";
        try {
			temp=in.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}			
		System.out.println(temp);
	
     	System.out.println(temp);
     	if(!temp.equals("0"))
     	{
     		num=Integer.parseInt(temp);
     		for(int i=0;i<num;i++)
     		{
     			try {
					temp=temp+in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     			temp=temp+"\n";
     		}     		
     		introducecontent=temp;
     		System.out.println(introducecontent);
     	}
     	else {
     		introducecontent="empty";
     	}
		setTitle("My page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		myPage = new JPanel();
		myPage.setBackground(new Color(1, 9, 22));
		myPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(myPage);
		myPage.setLayout(null);
		
		JLabel menu = new JLabel();
		menu.setIcon(ImageClass.Menu_background()); 
		menu.setBounds(0, 0, 128, 589);
		myPage.add(menu);
		menu.setLayout(null);
		
		btnMypage = new JButton("마이페이지");
		btnMypage.addActionListener(new ActionListener() {        
			public void actionPerformed(ActionEvent e) {		    
                out.println("msgmypage");
	            setVisible(false);
	            }
		});
		btnMypage.setBounds(0, 80, 120, 47);
		menu.add(btnMypage);
		
		btnQuestion = new JButton("질문게시판");
		btnQuestion.addActionListener(new ActionListener() {        
			public void actionPerformed(ActionEvent e) {
				out.println("Msgquestionboard/1");				
	            setVisible(false);
	            }
		});
		btnQuestion.setBounds(0, 173, 120, 47);
		menu.add(btnQuestion);
		
		btnProblem = new JButton("문제게시판");
		btnProblem.addActionListener(new ActionListener() {        
			public void actionPerformed(ActionEvent e) {
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		btnProblem.setBounds(0, 269, 120, 47);
		menu.add(btnProblem);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background()); 
		panel.setBounds(121, 0, 673, 589); 
		myPage.add(panel);
		panel.setLayout(null);
		
		image = new JPanel();       
		image.setBounds(44, 34, 189, 180);
		panel.add(image); 
		image.setLayout(null);
		
		lblNewLabel = new JLabel();  
		ImageIcon imgIcon=null;
	
	    if(rank.equals("1"))
		{
			imgIcon = new ImageIcon("grade1.png");
		}
		else if(rank.equals("2"))
		{
			imgIcon = new ImageIcon("grade2.png");
		}
		else if(rank.equals("3"))
		{
			imgIcon = new ImageIcon("grade3.png"); 
		}
		else if(rank.equals("4"))
		{
			imgIcon = new ImageIcon("grade4.png");
		}
		
	    JLabel lbPicture = new JLabel(imgIcon);
		lbPicture.setBounds(0, 0, 189, 180);
		image.add(lbPicture);
		
		
		introduce = new JPanel();
		introduce.setBounds(44, 288, 189, 158);
		panel.add(introduce);
		introduce.setLayout(null);
		
		txtIntroduce = new JTextPane();
		txtIntroduce.setBounds(0, 0, 189, 158);
		txtIntroduce.setText(introducecontent);
		introduce.add(txtIntroduce);
		
		Point = new JPanel();
		Point.setBackground(Color.WHITE);
		Point.setLayout(null);
		Point.setBounds(271, 34, 355, 180);
		panel.add(Point);
		
		txtPointBar = new JTextField();
		txtPointBar.setText(current_point);
		txtPointBar.setBounds(85, 28, 80, 21);
		Point.add(txtPointBar);
		txtPointBar.setColumns(10);
		
		txtdAll = new JTextField();
		txtdAll.setText(total);
		txtdAll.setColumns(10);
		txtdAll.setBounds(72, 92, 38, 21);
		Point.add(txtdAll);
		
		txtdSelect = new JTextField();
		txtdSelect.setText(selectnum);
		txtdSelect.setColumns(10);
		txtdSelect.setBounds(162, 92, 38, 21);
		Point.add(txtdSelect);
		
		String a=k+"%";
		txtchoose = new JTextField();
		txtchoose.setText(a);
		txtchoose.setColumns(10);
		txtchoose.setBounds(250, 92, 38, 21);
		Point.add(txtchoose);
		
		JTextField textField = new JTextField();
		textField.setText(accumnum);
		textField.setColumns(10);
		textField.setBounds(251, 28, 80, 21);
		Point.add(textField);
		
		Label label = new Label("현재 포인트");
		label.setBounds(10, 28, 69, 23);
		Point.add(label);
		
		Label label_1 = new Label("누적 포인트");
		label_1.setBounds(176, 28, 69, 23);
		Point.add(label_1);
		
		Label label_2 = new Label("전체 답변수"); 
		label_2.setBounds(57, 67, 69, 23);
		Point.add(label_2);
		
		label_4 = new Label("채택 답변수");
		label_4.setBounds(145, 67, 69, 23);
		Point.add(label_4);
		
		label_5 = new Label("채택률");
		label_5.setBounds(249, 67, 62, 23);
		Point.add(label_5);
		
		setVisible(true);
	}
}