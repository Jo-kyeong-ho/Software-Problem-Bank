package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class DetectWindow extends JFrame {
	/*
	 * gui of page that report to manager about problem
	 */
	private JPanel contentPane;
	private JTextPane textField;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	/**
	 * Create the frame.
	 */
	public DetectWindow(Socket socket1,String problem_id,String problem_name) {
		socket=socket1;	
		try {
	   		  in = new BufferedReader(new InputStreamReader(
	   		            socket.getInputStream()));
	   	      out = new PrintWriter(socket.getOutputStream(), true);
	   	   	} catch (IOException e1) {
	   	   		// TODO Auto-generated catch block
	   	   		e1.printStackTrace();
	   	   	}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		setTitle("Detect Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextPane();
		textField.setText("신고내용을 입력하세요");
		textField.setBounds(97, 56, 592, 363);
		contentPane.add(textField);
		
		JButton button = new JButton(" 신고하기 ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content;
				String str="Msgenterreport/"+problem_id+"/"+problem_name;
				out.println(str);
				content=textField.getText();
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
		button.setBounds(387, 458, 145, 64);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton(" 취소 ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("msgmypage");
				setVisible(false);
			}
		});
		btnNewButton.setBounds(544, 458, 145, 64);
		contentPane.add(btnNewButton);
		
		JLabel panal = new JLabel();
		panal.setIcon(ImageClass.Detect_background());
		panal.setBounds(0, 0, 794, 589);
		contentPane.add(panal);
		panal.setLayout(null);
		setVisible(true);
	}
}
