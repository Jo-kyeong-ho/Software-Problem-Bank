package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class WriteAnswer extends JFrame {

	/*
	 * 
	 */
	private JPanel contentPane;
	String question_id;
	String question_name;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public WriteAnswer(Socket socket1,String q_id,String q_n,String t,int set) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;		
		question_id=q_id;
		question_name=q_n;
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		setTitle("Write Answer");
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
			public void actionPerformed(ActionEvent e) {         
				System.out.println("msgmypage");
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 =  new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {          
				System.out.println("mygqeustionboard");
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 =  new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText(t);
		editorPane.setBounds(160, 31, 278, 329);
		contentPane.add(editorPane);
		
		if(set==1)
		{
			ImageIcon imgIcon=null;
			imgIcon = new ImageIcon("temp1.jpg");
			JLabel lblPicture = new JLabel(imgIcon);            
			lblPicture.setBounds(302, 10, 315, 329);
			contentPane.add(lblPicture);
		}
		JEditorPane dtrpnWriteAnswer = new JEditorPane();
		dtrpnWriteAnswer.setText("Write Answer");
		dtrpnWriteAnswer.setBounds(164, 383, 590, 110);
		contentPane.add(dtrpnWriteAnswer);
		
		JButton btnUpload = new JButton("submit answer");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String content;
				content=dtrpnWriteAnswer.getText();
				int count=0;
				StringTokenizer token1=new StringTokenizer(content,"\n");
				StringTokenizer token2=new StringTokenizer(content,"\n");
				while(token1.hasMoreTokens())
				{
					String a=token1.nextToken();
					count++;
				}
				out.println("Msgputanswer/"+question_id+"/"+question_name+"/"+count);		
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while(token2.hasMoreTokens())
				{
					out.println(token2.nextToken());
					System.out.println("count");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				out.println("msgmypage");
				setVisible(false);
			}
		});
		btnUpload.setBounds(375, 518, 149, 48);
		contentPane.add(btnUpload);		
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.Writeanswer_background()); 
		panel.setBounds(121, 0, 673, 589);
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}

}
