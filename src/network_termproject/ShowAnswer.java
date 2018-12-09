package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
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
import java.awt.event.ActionEvent;

public class ShowAnswer extends JFrame {
	
	/*
	 * gui that show answer's txt 
	 */
	private JPanel contentPane;
	String input;
	Socket socket;
	String question_id;
	String answeruser_id;
	String temp;
	String answer_temp;
	PrintWriter out = null;
	BufferedReader in=null;

	public ShowAnswer(Socket socket1,String line) {

		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;		
		input=line;
		String check;
		String imgset;
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		System.out.println(line);
		StringTokenizer token=new StringTokenizer(input,"/");
		question_id=token.nextToken();
		question_id=token.nextToken();
		answeruser_id=token.nextToken();
		check=token.nextToken();
		imgset=token.nextToken();
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
     	
     	num=0;
     	try {
			answer_temp=in.readLine();			
			System.out.println(answer_temp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	
     	if(!temp.equals("0"))
     	{
     		num=Integer.parseInt(answer_temp);
     		for(int i=0;i<num;i++)
     		{
     			try {
					answer_temp=answer_temp+in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     			answer_temp=answer_temp+"\n";
     		}     		
     	}    	
     	System.out.println(answer_temp);		
		
		setTitle("ShowAnswer");
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
		
		JButton button = new JButton("마이페이지");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {         
				out.println("msgmypage");
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		contentPane.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {          
				out.println("Msgquestionboard/1");
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {         
				//채워야됨
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		contentPane.add(button_2);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText(temp);
		editorPane.setBounds(153, 35, 278, 329);
		contentPane.add(editorPane);
		
		if(imgset.equals("1"))
		{
			ImageIcon imgIcon=null;
			imgIcon = new ImageIcon("temp1.jpg");
			JLabel lblPicture = new JLabel(imgIcon);
			lblPicture.setBounds(439, 34, 315, 329);
			contentPane.add(lblPicture);
		}
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setText(answer_temp);
		editorPane_2.setBounds(164, 378, 590, 110);
		contentPane.add(editorPane_2);
		
		JButton btnGood = new JButton("추천");
		btnGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msgrecommendplus/"+question_id+"/"+answeruser_id);
			}
		});
		btnGood.setBounds(219, 513, 122, 32);;
		contentPane.add(btnGood);
		
		JButton btnBad = new JButton("비추천");
		btnBad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 	out.println("Msgrecommendminus/"+question_id+"/"+answeruser_id);
			}
		});
		btnBad.setBounds(383, 513, 122, 32);
		contentPane.add(btnBad);
		if(check.equals("1"))
		{
		JButton btnSelect = new JButton("채택");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
				 질문자 id = user id 랑 같으면 보여줌,
                             클릭시 서버로
                 Msggoodanswer/질문id/답변자id
                             보냄
			*/
				out.println("Msggoodanswer/"+question_id+"/"+answeruser_id);
			}
		});
		btnSelect.setBounds(555, 513, 122, 32);
		contentPane.add(btnSelect);
		}
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.Showanswer_background()); 
		panel.setBounds(121, 0, 673, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}

}
