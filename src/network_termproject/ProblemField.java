package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class ProblemField extends JFrame {

	/*
	 * gui of question's txt and img
	 * it recieve buffer and count of line first and recevie real contents 
	 */
	private JPanel contentPane;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String input;
	String question_id;
	String temp;
	String question_name;
 	int imgset=0;
	int check=0;

	public ProblemField(Socket socket1,String line) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;		
		input=line;
		String questionuser_id,form;
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		StringTokenizer token=new StringTokenizer(input,"/");
		question_id=token.nextToken();
		question_id=token.nextToken();
		question_name=token.nextToken();
		questionuser_id=token.nextToken();
		form=token.nextToken();
		int num=0;
		temp="";
     	try {
			temp=in.readLine();			
			System.out.println(temp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	//System.out.println(temp);
     	
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
     	}    	
     	System.out.println(temp);
     	String imgcheck=null;
     	try {
			imgcheck=in.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
     	ImageIcon imgIcon=null;
     	if(imgcheck.equals("imgokay"))
     	{
     	FileOutputStream fos;
		try {
			fos = new FileOutputStream("temp1.jpg");
			 InputStream is = socket.getInputStream();
			 byte buffer[] = new byte[2048];
			 // read header(10 bytes)
			 is.read(buffer, 0, 10);
			 String header = new String(buffer, 0, 10);
			 int bodysize = Integer.parseInt(header);
			 int readsize = 0;
			 // read body
			 while (readsize < bodysize) {
			   int rsize = is.read(buffer);
			   fos.write(buffer, 0, rsize);
			   readsize += rsize;
			 }			 
			 imgset=1;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		imgIcon = new ImageIcon("temp1.jpg");
     	}
     	
		setTitle("Problem Field");
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
		
		JButton button = new JButton("My Page");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				out.println("msgmypage");
				check=1;
	            setVisible(false);
	            }
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    
				out.println("Msgquestionboard/1");
				check=1;
	            setVisible(false);				
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {       
				out.println("problemwindow");
				check=1;
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JButton btnNewButton = new JButton("정답보기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetanswerlist/"+question_id+"/1");
				setVisible(false);
			}
		});
		btnNewButton.setBounds(242, 521, 144, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("질문자 / 문제 유형");  
		lblNewLabel.setBounds(167, 21, 144, 31);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("문제 이름");
		label.setBounds(196, 67, 144, 31);
		contentPane.add(label);
		btnNewButton.setBounds(241, 509, 144, 39);
		contentPane.add(btnNewButton);                   
		
		
		JButton btnShowAnswer = new JButton("정답쓰기");
		btnShowAnswer.addActionListener(new ActionListener() {    
			public void actionPerformed(ActionEvent e) {
				new WriteAnswer(socket,question_id,question_name,temp,imgset);
	            setVisible(false);
			}
		});
		btnShowAnswer.setBounds(533, 521, 144, 39);
		contentPane.add(btnShowAnswer);		
		
		JEditorPane dtrpnNameproblem = new JEditorPane();
		dtrpnNameproblem.setBounds(281, 67, 262, 31);
		contentPane.add(dtrpnNameproblem);
		dtrpnNameproblem.setText(question_name);
		
		JEditorPane dtrpnNameusertype = new JEditorPane();
		dtrpnNameusertype.setBounds(281, 21, 262, 31);
		contentPane.add(dtrpnNameusertype);
		dtrpnNameusertype.setText(questionuser_id+"    "+form);
		
		JEditorPane dtrpnProblemText = new JEditorPane();
		dtrpnProblemText.setBounds(167, 123, 262, 329);
		contentPane.add(dtrpnProblemText);
		dtrpnProblemText.setText(temp);
		
		JLabel lbPicture = new JLabel(imgIcon);
		lbPicture.setBounds(444, 122, 315, 329);
		contentPane.add(lbPicture);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background());
		panel.setBounds(121, 0, 673, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}
}

