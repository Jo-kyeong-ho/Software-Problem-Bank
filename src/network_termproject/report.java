package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class report extends JFrame {
	/*
	 * gui of reports. it contain reported problem's txt,img and comement of reproter
	 */
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String input;
	String problem_id;
	String temp;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public report(Socket socket1,String line) {
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
		System.out.println(input);
		StringTokenizer token=new StringTokenizer(input,"/");
		problem_id=token.nextToken();
		problem_id=token.nextToken();
		temp="";
		int num=0;
     	try {
			temp=in.readLine();			
			System.out.println(temp);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	
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
     	String temp1 = null;
     	try {
			temp1=in.readLine();			
			System.out.println(temp1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	//System.out.println(temp);
     	
     	if(!temp.equals("0"))
     	{
     		num=Integer.parseInt(temp1);
     		for(int i=0;i<num;i++)
     		{
     			try {
					temp1=temp1+in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     			temp1=temp1+"\n";
     		}     		
     	}    	
     	System.out.println(temp1);
     	String imgcheck=null;
     	try {
			imgcheck=in.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	}		
     	
		setTitle("Detection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(87, 10, 613, 354);
		contentPane.add(panel);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText(temp);
		editorPane.setBounds(12, 10, 262, 329);
		panel.add(editorPane);
		
		if(imgcheck.equals("imgokay"))
		{
			ImageIcon imgIcon=null;
			imgIcon = new ImageIcon("temp1.jpg");
			JLabel label = new JLabel(imgIcon);
			label.setBounds(286, 10, 315, 329);
			panel.add(label);
		}
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(87, 374, 613, 82);
		contentPane.add(panel_1);
		
		JEditorPane dtrpnDetection = new JEditorPane();
		dtrpnDetection.setText(temp1);
		dtrpnDetection.setBounds(12, 10, 589, 62);
		panel_1.add(dtrpnDetection);
		
		JButton button = new JButton("신고 삭제");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Msgreportcomplete/"+problem_id);
				out.println("Msgadmin");
				setVisible(false);
			}
		});
		button.setBounds(134, 494, 144, 39);
		contentPane.add(button);
		
		JButton button_1 = new JButton("게시글 삭제");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Msgreportdelete/"+problem_id);
				out.println("Msgadmin");
				setVisible(false);
			}
		});
		button_1.setBounds(328, 494, 144, 39);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("아이디 정지");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				out.println("Msgiddelte/"+problem_id);
				out.println("Msgadmin");
				setVisible(false);
			}
		});
		button_2.setBounds(518, 494, 144, 39);
		contentPane.add(button_2);
		JLabel detect = new JLabel();
	      detect.setBounds(0, 0, 794, 589);
	      contentPane.add(detect);
	      
	      detect.setIcon(ImageClass.Detect_background()); 
	      detect.setLayout(null);
		
		setVisible(true);
		
	}

}
