package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class EditIntroduce extends JFrame {
	/*
	 * gui about edit page of introduce
	 */
	private JPanel contentPane;
	private JTextPane textIntroduce;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public EditIntroduce(Socket socket1,String id) {
		socket=socket1;
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		 try {
     		  in = new BufferedReader(new InputStreamReader(
     		            socket.getInputStream()));
     	        out = new PrintWriter(socket.getOutputStream(), true);
     	   	} catch (IOException e1) {
     	   		// TODO Auto-generated catch block
     	   		e1.printStackTrace();
     	   	}
		setTitle("Edit Introduce");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textIntroduce = new JTextPane();
		textIntroduce.setText("소개글을 입력하세요");   
		textIntroduce.setBounds(59, 24, 745, 453);
		contentPane.add(textIntroduce);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.setBounds(493, 524, 120, 41);
		btnConfirm.addActionListener(new ActionListener() {   // introduce file change
			public void actionPerformed(ActionEvent e) { 
				String content;
				content=textIntroduce.getText();
				String message="Msgchangeintroduce";
				out.println(message);
				int count=0;
				StringTokenizer token1=new StringTokenizer(content,"\n");
				StringTokenizer token2=new StringTokenizer(content,"\n");
				while(token1.hasMoreTokens())
				{
					String a=token1.nextToken();
					System.out.println(a);
					count++;
				}
				out.println(count);
				while(token2.hasMoreTokens())
				{
					out.println(token2.nextToken());
				}
				out.println("msgmypage");
				setVisible(false);
	        }
		});
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				out.println("msgmypage");
				setVisible(false);
	        }
		});
		btnCancel.setBounds(688, 524, 112, 41);
		contentPane.add(btnCancel);
		
		JLabel lbl = new JLabel("");
		lbl.setIcon(ImageClass.Introduce_background()); 
		lbl.setBounds(0, 0, 863, 639);
		contentPane.add(lbl);
		lbl.setLayout(null);
		
		 setVisible(true);
	}
}
