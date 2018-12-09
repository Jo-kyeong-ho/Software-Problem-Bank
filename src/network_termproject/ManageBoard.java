package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class ManageBoard extends JFrame {
	/*
	 * gui of manager's main page that containt reportlist
	 */
	private JPanel contentPane;
	private JTextField txtManagerBoard;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;
	String[] problem_id=new String[7];
	String[] problem_name=new String[7];
	String[] reporter_id=new String[7];

	public ManageBoard(Socket socket1,String line) {
		socket=socket1;
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		String input=line;
		try {
	   		  in = new BufferedReader(new InputStreamReader(
	   		            socket.getInputStream()));
	   	      out = new PrintWriter(socket.getOutputStream(), true);
	   	   	} catch (IOException e1) {
	   	   		// TODO Auto-generated catch block
	   	   		e1.printStackTrace();
	   	   	}
		StringTokenizer token=new StringTokenizer(input,"/");
		problem_id[0]=token.nextToken();
		int i=0;
		while(token.hasMoreTokens())
		{
			problem_id[i]=token.nextToken();
			problem_name[i]=token.nextToken();
			reporter_id[i]=token.nextToken();
			i++;
		}
		i--;
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtManagerBoard = new JTextField();
		txtManagerBoard.setBounds(93, 25, 582, 49);
		contentPane.add(txtManagerBoard);
		txtManagerBoard.setText("Manager Board");
		txtManagerBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtManagerBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtManagerBoard.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("문제번호");
		lblNewLabel.setBounds(183, 97, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("문제이름");
		label.setBounds(326, 97, 57, 15);
		contentPane.add(label);
		
		JLabel lblId = new JLabel("사용자 ID");
		lblId.setBounds(462, 97, 57, 15);
		contentPane.add(lblId);                    
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(108, 117, 552, 382);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		if(i>=0)
		{
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(12, 20, 530, 40);
		panel_1.add(panel_2);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[0]+"/"+reporter_id[0];
				out.println(str);
				setVisible(false);
			}
		});
		btnShow.setBounds(441, 0, 80, 40);
		panel_2.add(btnShow);
		
		JTextPane txtpnProblemid = new JTextPane();
		txtpnProblemid.setText(problem_id[0]);
		txtpnProblemid.setBounds(12, 0, 148, 40);
		panel_2.add(txtpnProblemid);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setText(problem_name[0]);
		txtpnName.setBounds(172, 0, 127, 40);
		panel_2.add(txtpnName);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText(reporter_id[0]);
		txtpnId.setBounds(311, 0, 118, 40);
		panel_2.add(txtpnId);
		}
		if(i>=1)
		{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 70, 530, 40);
		panel_1.add(panel);
		
		JButton button_1 = new JButton("Show");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[1]+"/"+reporter_id[1];
				out.println(str);
				setVisible(false);
			}
		});
		button_1.setBounds(441, 0, 80, 40);
		panel.add(button_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(problem_id[1]);
		textPane.setBounds(12, 0, 148, 40);
		panel.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText(problem_name[1]);
		textPane_1.setBounds(172, 0, 127, 40);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText(reporter_id[1]);
		textPane_2.setBounds(311, 0, 118, 40);
		panel.add(textPane_2);
		}
		if(i>=2)
		{
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(12, 120, 530, 40);
		panel_1.add(panel_3);
		
		JButton button_2 = new JButton("Show");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[2]+"/"+reporter_id[2];
				out.println(str);
				setVisible(false);
			}
		});
		button_2.setBounds(441, 0, 80, 40);
		panel_3.add(button_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText(problem_id[2]);
		textPane_3.setBounds(12, 0, 148, 40);
		panel_3.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText(problem_name[2]);
		textPane_4.setBounds(172, 0, 127, 40);
		panel_3.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText(reporter_id[2]);
		textPane_5.setBounds(311, 0, 118, 40);
		panel_3.add(textPane_5);
		}
		if(i>=3)
		{
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(12, 170, 530, 40);
		panel_1.add(panel_4);
		
		JButton button_3 = new JButton("Show");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[3]+"/"+reporter_id[3];
				out.println(str);
				setVisible(false);
			}
		});
		button_3.setBounds(441, 0, 80, 40);
		panel_4.add(button_3);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText(problem_id[3]);
		textPane_6.setBounds(12, 0, 148, 40);
		panel_4.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText(problem_name[3]);
		textPane_7.setBounds(172, 0, 127, 40);
		panel_4.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText(reporter_id[3]);
		textPane_8.setBounds(311, 0, 118, 40);
		panel_4.add(textPane_8);
		}
		if(i>=4)
		{
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(12, 220, 530, 40);
		panel_1.add(panel_5);
		
		JButton button_4 = new JButton("Show");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[4]+"/"+reporter_id[4];
				out.println(str);
				setVisible(false);
			}
		});
		button_4.setBounds(441, 0, 80, 40);
		panel_5.add(button_4);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText(problem_id[4]);
		textPane_9.setBounds(12, 0, 148, 40);
		panel_5.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText(problem_name[4]);
		textPane_10.setBounds(172, 0, 127, 40);
		panel_5.add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText(reporter_id[4]);
		textPane_11.setBounds(311, 0, 118, 40);
		panel_5.add(textPane_11);
		}
		if(i>=5)
		{
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(12, 270, 530, 40);
		panel_1.add(panel_6);
		
		JButton button_5 = new JButton("Show");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[5]+"/"+reporter_id[5];
				out.println(str);
				setVisible(false);
			}
		});
		button_5.setBounds(441, 0, 80, 40);
		panel_6.add(button_5);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText(problem_id[5]);
		textPane_12.setBounds(12, 0, 148, 40);
		panel_6.add(textPane_12);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText(problem_name[5]);
		textPane_13.setBounds(172, 0, 127, 40);
		panel_6.add(textPane_13);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText(reporter_id[5]);
		textPane_14.setBounds(311, 0, 118, 40);
		panel_6.add(textPane_14);
		}
		if(i>=6)
		{
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(12, 320, 530, 40);
		panel_1.add(panel_7);
		
		JButton button_6 = new JButton("Show");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msggetreport/"+problem_id[6]+"/"+reporter_id[6];
				out.println(str);
				setVisible(false);
			}
		});
		button_6.setBounds(441, 0, 80, 40);
		panel_7.add(button_6);
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText(problem_id[6]);
		textPane_15.setBounds(12, 0, 148, 40);
		panel_7.add(textPane_15);
		
		JTextPane textPane_16 = new JTextPane();
		textPane_16.setText(problem_name[6]);
		textPane_16.setBounds(172, 0, 127, 40);
		panel_7.add(textPane_16);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText(reporter_id[6]);
		textPane_17.setBounds(311, 0, 118, 40);
		panel_7.add(textPane_17);
		}
		JLabel lbl = new JLabel();
		lbl.setIcon(ImageClass.Manage_background()); 
		lbl.setBounds(0, 0, 794, 589);
		contentPane.add(lbl);
		lbl.setLayout(null);
		setVisible(true);
	}
}
