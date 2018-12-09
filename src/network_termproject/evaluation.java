package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class evaluation extends JFrame {
	
	/*
	 * gui of problem that show problem's text and img
	 * it receive buffer or line of img,txt before contents of that.
	 */
	String input;
	private JPanel contentPane;
	private JPanel menu;
	private JButton btnMypage;
	private JButton btnQuestion;
	private JButton btnProblem;
	private JButton btnNewButton;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String problem_id;
	String problem_name;
	String upload_id;
	String evaluation;
	String level;
	String form;
	String temp;
	String temp_a;
	/**
	 * Launch the application.
	 */

	public evaluation(Socket socket1,String line) {
		/*
		     서버에서
           Msgproblem/문제이름/업로더 id/평점/난이도/형식
                   받음
               ->문제.txt받음
               ->정답.txt받음
               ->그림.jpg받음
               
              evaluation->contentpane
        */
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;		
		input=line;
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		
		StringTokenizer token=new StringTokenizer(input,"/");
		problem_id=token.nextToken();
		problem_id=token.nextToken();
		if(problem_id.equals("fail"))
		{
			setVisible(true);
			JOptionPane.showMessageDialog(null,"포인트가 부족합니다.","fail", 1);
			out.println("msgmypage");
			setVisible(false);
		}
		else {
		problem_name=token.nextToken();
		upload_id=token.nextToken();
		evaluation=token.nextToken();		
		form=token.nextToken();
		level=token.nextToken();
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
     	
     	temp_a="";
     	try {
			temp_a=in.readLine();			
			System.out.println(temp_a);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	//System.out.println(temp);
     	
     	if(!temp.equals("0"))
     	{
     		num=Integer.parseInt(temp_a);
     		for(int i=0;i<num;i++)
     		{
     			try {
					temp_a=temp_a+in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     			temp_a=temp_a+"\n";
     		}     		
     	}    	
     	System.out.println(temp);
     	String imgcheck = null;
     	try {
			imgcheck=in.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
     	FileOutputStream fos;
     	ImageIcon imgIcon=null;
     	if(imgcheck.equals("imgokay"))
     	{
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
			 //is.close();
			 //fos.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  imgIcon = new ImageIcon("temp1.jpg");
     	}


		
		setTitle("Evaluation Board");
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
		button_2.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background()); // 배경 이미지
		panel.setBounds(121, 0, 673, 589); 
		panel.setLayout(null);
		
		JButton btnEvaluate = new JButton("평가하기");
		btnEvaluate.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {       // 평가하기 클릭시 evaluateWindow 띄어줌
				new evaluateWindow(socket,problem_id,problem_name);
			}
		});
		btnEvaluate.setBounds(481, 71, 115, 53);
		contentPane.add(btnEvaluate);
		

		JLabel lblNewLabel = new JLabel("문제 이름");   //***********************************************
		lblNewLabel.setBounds(195, 41, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("출제자 이름");
		label_1.setBounds(195, 104, 80, 15);
		contentPane.add(label_1);                     //***********************************************
		
		JButton btDetect = new JButton("신고하기");
		btDetect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DetectWindow(socket,problem_id,problem_name);
				setVisible(false);
			}
		});
		btDetect.setBounds(616, 71, 115, 53);
		contentPane.add(btDetect);
		
		JEditorPane dtrpnR = new JEditorPane();
		dtrpnR.setText(problem_name);
		dtrpnR.setBounds(287, 41, 106, 21);
		contentPane.add(dtrpnR);
		
		JEditorPane dtrpnUploaderName = new JEditorPane();
		dtrpnUploaderName.setText(upload_id);
		dtrpnUploaderName.setBounds(287, 104, 106, 21);
		contentPane.add(dtrpnUploaderName);		
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText(temp);
		editorPane.setBounds(140, 152, 302, 412);
		contentPane.add(editorPane);
		
		if(imgcheck.equals("imgokay"))
		{
			JLabel label = new JLabel(imgIcon);
			label.setBounds(454, 152, 315, 329);
			contentPane.add(label);
		}
		
		JButton btnshow = new JButton("정답보기");
		btnshow.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {       
				new evaluationAnswer(temp_a);
			}
		});
		btnshow.setBounds(454, 506, 315, 73);
		contentPane.add(btnshow);
		
		JLabel panal = new JLabel();
		panal.setBackground(Color.WHITE);
		panal.setIcon(ImageClass.background()); // 배경 이미지
		panal.setBounds(121, 0, 673, 589);
		contentPane.add(panal);
		panel.setLayout(null);
	
		setVisible(true);
		}		
	}	
}


