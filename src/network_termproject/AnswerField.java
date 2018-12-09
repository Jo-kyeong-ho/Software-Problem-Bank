package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class AnswerField extends JFrame {
	/*
	 * gui that show answerlist
	 * analyze server's data and show to user
	 */
	private JPanel contentPane;
	private JTextField txtAnswerField;
	String[] question_id = new String[7];
	String[] question_name=new String[7];
	String[] answerperson_id=new String[7];
	String[] recommend=new String[7];
	String[] derecommend=new String[7];
	String[] choice=new String[7];
	String input;
	Socket socket;
	String page;
	int page_i;
	PrintWriter out = null;
	BufferedReader in=null;

	public AnswerField(Socket socket1,String line) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		input=line;
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
		question_id[0]=token.nextToken();
		page=token.nextToken();
		page_i=Integer.parseInt(page);
		int i=0;
		while(token.hasMoreTokens())
		{
			question_id[i]=token.nextToken();
			question_name[i]=token.nextToken();
			answerperson_id[i]=token.nextToken();
			recommend[i]=token.nextToken();
			derecommend[i]=token.nextToken();
			choice[i]=token.nextToken();
			if(choice[i].equals("0"))
			{
				choice[i]="not choosed";
			}
			else {
				choice[i]="choosed";
			}
			i++;
		}
		i--;				
		
		setTitle("AnswerField");
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
		
		JButton button_2 =new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JLabel lblNewLabel = new JLabel("채택 유무");
		lblNewLabel.setBounds(228, 89, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblId = new JLabel("답변자 ID");
		lblId.setBounds(373, 89, 57, 15);
		contentPane.add(lblId);
		
		JLabel lblCnjs = new JLabel("추천");
		lblCnjs.setBounds(503, 89, 32, 15);
		contentPane.add(lblCnjs);
		
		JLabel label_2 = new JLabel("비추천");
		label_2.setBounds(595, 89, 41, 15);
		contentPane.add(label_2);
		
		txtAnswerField = new JTextField();
		txtAnswerField.setText("Answer Field");
		txtAnswerField.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswerField.setFont(new Font("굴림", Font.BOLD, 18));
		txtAnswerField.setColumns(10);
		txtAnswerField.setBounds(168, 10, 582, 49);
		contentPane.add(txtAnswerField);
		
		if(i>=0)
		{
			JPanel panel_2 = new JPanel();
			contentPane.add(panel_2);
			panel_2.setLayout(null);
			panel_2.setBounds(191, 114, 532, 40);
		
		JButton btnSelect = new JButton("go");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("Msggetanswer/"+question_id[0]+"/"+answerperson_id[0]);
				setVisible(false);
			}
		});
		
		btnSelect.setBounds(474, 0, 59, 40);
		panel_2.add(btnSelect);
		
		JButton id = new JButton(answerperson_id[0]);
		id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("msgyourpage/"+answerperson_id[0]);
				setVisible(false);
			}
		});
		id.setBounds(146, 0, 122, 40);
		panel_2.add(id);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText(recommend[0]);
		textPane_3.setBounds(280, 0, 85, 40);
		panel_2.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText(derecommend[0]);
		textPane_4.setBounds(377, 0, 85, 40);
		panel_2.add(textPane_4);
		
		JTextPane txtpnSelect = new JTextPane();
		txtpnSelect.setText(choice[0]);
		txtpnSelect.setBounds(0, 0, 134, 40);
		panel_2.add(txtpnSelect);
		}
		if(i>=1)
		{
			JPanel panel_3 = new JPanel();
			contentPane.add(panel_3);
			panel_3.setLayout(null);
			panel_3.setBounds(191, 164, 532, 40);
		
			JButton id2 = new JButton(answerperson_id[1]);
			id2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[1]);
					setVisible(false);
				}
			});
			id2.setBounds(474, 0, 59, 40);
			panel_3.add(id2);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText(recommend[1]);
		textPane_5.setBounds(146, 0, 122, 40);
		panel_3.add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText(derecommend[1]);
		textPane_6.setBounds(280, 0, 85, 40);
		panel_3.add(textPane_6);
		
		JButton button_3 = new JButton("go");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Msggetanswer/"+question_id[1]+"/"+answerperson_id[1]);
				setVisible(false);
			}
		});
		button_3.setBounds(377, 0, 85, 40);
		panel_3.add(button_3);
		
		JTextPane textPane_27 = new JTextPane();
		textPane_27.setText(choice[1]);
		textPane_27.setBounds(0, 0, 134, 40);
		panel_3.add(textPane_27);
		}
		
		if(i>=2)
		{
			JPanel panel_4 = new JPanel();
			contentPane.add(panel_4);
			panel_4.setLayout(null);
			panel_4.setBounds(191, 214, 532, 40);
		
			JButton id3 = new JButton(answerperson_id[2]);
			id3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[2]);
					setVisible(false);
				}
			});
			id3.setBounds(474, 0, 59, 40);
			panel_4.add(id3);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText(recommend[2]);
		textPane_9.setBounds(146, 0, 122, 40);
		panel_4.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText(derecommend[2]);
		textPane_10.setBounds(280, 0, 85, 40);
		panel_4.add(textPane_10);
		
		JButton button_10 = new JButton("go");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Msggetanswer/"+question_id[2]+"/"+answerperson_id[2]);
				setVisible(false);
			}
		});
		button_10.setBounds(377, 0, 85, 40);
		panel_4.add(button_10);
		
		JTextPane textPane_28 = new JTextPane();
		textPane_28.setText(choice[2]);
		textPane_28.setBounds(0, 0, 134, 40);
		panel_4.add(textPane_28);
		}
		if(i>=3)
		{
			JPanel panel_5 = new JPanel();
			contentPane.add(panel_5);
			panel_5.setLayout(null);
			panel_5.setBounds(191, 264, 532, 40);
		
			JButton id4 = new JButton(answerperson_id[3]);
			id4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[3]);
					setVisible(false);
				}
			});
			id4.setBounds(474, 0, 59, 40);
			panel_5.add(id4);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText(recommend[3]);
		textPane_13.setBounds(146, 0, 122, 40);
		panel_5.add(textPane_13);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText(derecommend[3]);
		textPane_14.setBounds(280, 0, 85, 40);
		panel_5.add(textPane_14);
		
		JButton button_11 = new JButton("go");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Msggetanswer/"+question_id[3]+"/"+answerperson_id[3]);
				setVisible(false);
			}
		});
		button_11.setBounds(377, 0, 85, 40);
		panel_5.add(button_11);
				
		JTextPane textPane_29 = new JTextPane();
		textPane_29.setText(choice[3]);
		textPane_29.setBounds(0, 0, 134, 40);
		panel_5.add(textPane_29);
		}
		
		if(i>=4)
		{
			JPanel panel_6 = new JPanel();
			contentPane.add(panel_6);
			panel_6.setLayout(null);
			panel_6.setBounds(191, 314, 532, 40);
		
			JButton id5 = new JButton(answerperson_id[4]);
			id5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[4]);
					setVisible(false);
				}
			});
			id5.setBounds(474, 0, 59, 40);
			panel_6.add(id5);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText(recommend[4]);
		textPane_17.setBounds(146, 0, 122, 40);
		panel_6.add(textPane_17);
		
		JTextPane textPane_18 = new JTextPane();
		textPane_18.setText(derecommend[4]);
		textPane_18.setBounds(280, 0, 85, 40);
		panel_6.add(textPane_18);
		
		JButton button_12 = new JButton("go");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				out.println("Msggetanswer/"+question_id[4]+"/"+answerperson_id[4]);
				setVisible(false);
			}
		});
		button_12.setBounds(377, 0, 85, 40);
		panel_6.add(button_12);
		
		JTextPane textPane_30 = new JTextPane();
		textPane_30.setText(choice[4]);
		textPane_30.setBounds(0, 0, 134, 40);
		panel_6.add(textPane_30);
		}
		if(i>=5)
		{
			JPanel panel_7 = new JPanel();
			contentPane.add(panel_7);
			panel_7.setLayout(null);
			panel_7.setBounds(191, 364, 532, 40);
		
			JButton id6 = new JButton(answerperson_id[5]);
			id6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[5]);
					setVisible(false);
				}
			});
			id6.setBounds(474, 0, 59, 40);
			panel_7.add(id6);
		
		JTextPane textPane_21 = new JTextPane();
		textPane_21.setText(recommend[5]);
		textPane_21.setBounds(146, 0, 122, 40);
		panel_7.add(textPane_21);
		
		JTextPane textPane_22 = new JTextPane();
		textPane_22.setText(derecommend[5]);
		textPane_22.setBounds(280, 0, 85, 40);
		panel_7.add(textPane_22);
		
		JButton button_13 = new JButton("go");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				out.println("Msggetanswer/"+question_id[5]+"/"+answerperson_id[5]);
				setVisible(false);
			}
		});
		button_13.setBounds(377, 0, 85, 40);
		panel_7.add(button_13);
		
		JTextPane textPane_31 = new JTextPane();
		textPane_31.setText(choice[5]);
		textPane_31.setBounds(0, 0, 134, 40);
		panel_7.add(textPane_31);
		}
		
		if(i>=6)
		{
			JPanel panel_8 = new JPanel();
			contentPane.add(panel_8);
			panel_8.setLayout(null);
			panel_8.setBounds(191, 414, 532, 40);
		
			JButton id7 = new JButton(answerperson_id[6]);
			id7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					out.println("msgyourpage/"+answerperson_id[6]);
					setVisible(false);
				}
			});
			id7.setBounds(474, 0, 59, 40);
			panel_8.add(id7);
		
		JTextPane textPane_25 = new JTextPane();
		textPane_25.setText(recommend[6]);
		textPane_25.setBounds(146, 0, 122, 40);
		panel_8.add(textPane_25);
		
		JTextPane textPane_26 = new JTextPane();
		textPane_26.setText(derecommend[6]);
		textPane_26.setBounds(280, 0, 85, 40);
		panel_8.add(textPane_26);
		
		JButton button_14 = new JButton("go");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				out.println("Msggetanswer/"+question_id[6]+"/"+answerperson_id[6]);
				setVisible(false);
			}
		});
		button_14.setBounds(377, 0, 85, 40);
		panel_8.add(button_14);
		
		JTextPane textPane_32 = new JTextPane();
		textPane_32.setText(choice[6]);
		textPane_32.setBounds(0, 0, 134, 40);
		panel_8.add(textPane_32);
		}
		JButton btnNewButton = new JButton("<");
		contentPane.add(btnNewButton);
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setForeground(Color.PINK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				page_i--;
				if(page_i!=0)
				{
					out.println("Msggetanswerlist/"+question_id+"/"+"/1");
					setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(392, 478, 51, 23);
		
		JButton button_4 = new JButton(">"); 
		contentPane.add(button_4);//페이지 버튼 ->
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				page_i++;
				out.println("Msggetanswerlist/"+question_id+"/"+page_i);
				setVisible(false);
			}
		});
		button_4.setBounds(498, 478, 51, 23);
		
		JTextPane txtpnPageNumber = new JTextPane();
		txtpnPageNumber.setText("page number");
		txtpnPageNumber.setBounds(441, 478, 58, 23);
		contentPane.add(txtpnPageNumber);
		
		JLabel panel  = new JLabel(); // make img of background
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.background()); 
		panel.setBounds(121, 0, 673, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		
		setVisible(true);
	}

}
