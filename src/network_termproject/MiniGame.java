package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.Toolkit;

public class MiniGame extends JFrame {
	/*
	 * gui of minigame, send answer to server
	 */
	private JPanel contentPane;
	private JTextField txtProblemName;
	private JLabel lbl;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public MiniGame(Socket socket1, String line) {
		socket=socket1;	
		String input=line;
		String temp;
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		try {
	   		  in = new BufferedReader(new InputStreamReader(
	   		            socket.getInputStream()));
	   	      out = new PrintWriter(socket.getOutputStream(), true);
	   	   	} catch (IOException e1) {
	   	   		// TODO Auto-generated catch block
	   	   		e1.printStackTrace();
	   	   	}
		StringTokenizer token=new StringTokenizer(input,"/");
		String problem_name=token.nextToken();
		String problem_id=token.nextToken();
		problem_name=token.nextToken();
		int num=0;
		temp="";
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
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     	
		imgIcon = new ImageIcon("temp1.jpg");
     	}
     	
     	
		setTitle("MiniGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtProblemName = new JTextField();
		txtProblemName.setText(problem_name);
		txtProblemName.setHorizontalAlignment(SwingConstants.CENTER);
		txtProblemName.setFont(new Font("굴림", Font.BOLD, 18));
		txtProblemName.setColumns(10);
		txtProblemName.setBounds(114, 23, 229, 49);
		contentPane.add(txtProblemName);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(95, 107, 278, 329);
		contentPane.add(editorPane);
		editorPane.setText(temp);
		
		JLabel label = new JLabel(imgIcon);
		label.setBounds(397, 107, 315, 329);
		contentPane.add(label);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(95, 446, 278, 99);
		contentPane.add(editorPane_1);
		editorPane_1.setText("Problem- text");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(510, 477, 202, 68);
		contentPane.add(btnSubmit);
		
		JLabel lbl_1 = new JLabel();
		lbl_1.setIcon(ImageClass.Minigame_background()); // 배경 이미지
		lbl_1.setBounds(0, 0, 794, 589);
		contentPane.add(lbl_1);
		lbl_1.setLayout(null);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content;
				content=editorPane_1.getText();
				String message="Msgplaysubmit/"+problem_id;
				out.println(message);
				StringTokenizer token1=new StringTokenizer(content,"\n");
				content=token1.nextToken();
				out.println(content);
				String check = null;
				try {
					check=in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(check.contains("okay"))
				{
					 JOptionPane.showMessageDialog(null,"포인트를 획득하였습니다","Success", 1);
					out.println("msgmypage");
			         setVisible(false);
				}
				else {
					 JOptionPane.showMessageDialog(null,"선착순 실패","fail", 1);
					out.println("msgmypage");
			         setVisible(false);
				}
				
			}
		});
		setVisible(true);
	}
}
