package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemResult extends JFrame {

	private JPanel contentPane;
	private JTextField txtProblemBoard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProblemResult frame = new ProblemResult();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProblemResult() {
        /*		
	                 서버로부터
		      Msgproblemboard/문제이름*평점*큰주제*작은주제*서술형*난이도/똑같고/똑같고10개받음(없으면 null)/page수
		          받음
        */
		setTitle("Problem Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(0, 0, 121, 589);
		contentPane.add(menu);
		
		JButton button = new JButton("My Page");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		  //Mypage 버튼 누르면 mypage로 이동 
				new mypage().setVisible(true);
	            setVisible(false);}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //질문게시판 버튼 누르면 Question board로 이동
				new QuestionBoard().setVisible(true);
	            setVisible(false);	
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //문제게시판 버튼 누르면 Problem board로 이동
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBounds(121, 0, 673, 589);
		contentPane.add(panel);
		
		txtProblemBoard = new JTextField();
		txtProblemBoard.setText("Problem Board");
		txtProblemBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtProblemBoard.setFont(new Font("굴림", Font.BOLD, 18));
		txtProblemBoard.setColumns(10);
		txtProblemBoard.setBounds(44, 44, 582, 49);
		panel.add(txtProblemBoard);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(44, 133, 582, 418);
		panel.add(panel_1);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {           //Upload 버튼 누르면 ProblemUpload(업로드창)으로 이동 
						new ProblemUpload().setVisible(true);
			            setVisible(false);
			            }
				});
		btnUpload.setBounds(448, 376, 122, 32);
		panel_1.add(btnUpload);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(12, 21, 558, 40);
		panel_1.add(panel_2);
		
		JButton button_4 = new JButton("문제 아이디");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
				    클릭하면 서버로
                   Msggetproblem/문제아이디  보냄
            */
			}
		});
		button_4.setBounds(0, 0, 59, 40);
		panel_2.add(button_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\uBB38\uC81C\uC774\uB9841");
		textPane.setBounds(59, 0, 89, 40);
		panel_2.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\uD070\uC8FC\uC81C1");
		textPane_1.setBounds(212, 0, 107, 40);
		panel_2.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_2.setBounds(322, 0, 99, 40);
		panel_2.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("\uC8FC\uAD00\uC2DD");
		textPane_3.setBounds(425, 0, 70, 40);
		panel_2.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("\uB09C\uC774\uB3C4");
		textPane_4.setBounds(499, 0, 59, 40);
		panel_2.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("\uD3C9\uC810");
		textPane_5.setBounds(145, 0, 66, 40);
		panel_2.add(textPane_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(12, 71, 558, 40);
		panel_1.add(panel_3);
		
		JButton button_5 = new JButton("No.1");
		button_5.setBounds(0, 0, 59, 40);
		panel_3.add(button_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_6.setBounds(59, 0, 89, 40);
		panel_3.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("\uD070\uC8FC\uC81C1");
		textPane_7.setBounds(212, 0, 107, 40);
		panel_3.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_8.setBounds(322, 0, 99, 40);
		panel_3.add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("\uC8FC\uAD00\uC2DD");
		textPane_9.setBounds(425, 0, 70, 40);
		panel_3.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("\uB09C\uC774\uB3C4");
		textPane_10.setBounds(499, 0, 59, 40);
		panel_3.add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText("\uD3C9\uC810");
		textPane_11.setBounds(145, 0, 66, 40);
		panel_3.add(textPane_11);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(12, 121, 558, 40);
		panel_1.add(panel_4);
		
		JButton button_6 = new JButton("No.1");
		button_6.setBounds(0, 0, 59, 40);
		panel_4.add(button_6);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_12.setBounds(59, 0, 89, 40);
		panel_4.add(textPane_12);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText("\uD070\uC8FC\uC81C1");
		textPane_13.setBounds(212, 0, 107, 40);
		panel_4.add(textPane_13);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_14.setBounds(322, 0, 99, 40);
		panel_4.add(textPane_14);
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText("\uC8FC\uAD00\uC2DD");
		textPane_15.setBounds(425, 0, 70, 40);
		panel_4.add(textPane_15);
		
		JTextPane textPane_16 = new JTextPane();
		textPane_16.setText("\uB09C\uC774\uB3C4");
		textPane_16.setBounds(499, 0, 59, 40);
		panel_4.add(textPane_16);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText("\uD3C9\uC810");
		textPane_17.setBounds(145, 0, 66, 40);
		panel_4.add(textPane_17);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(12, 171, 558, 40);
		panel_1.add(panel_5);
		
		JButton button_7 = new JButton("No.1");
		button_7.setBounds(0, 0, 59, 40);
		panel_5.add(button_7);
		
		JTextPane textPane_18 = new JTextPane();
		textPane_18.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_18.setBounds(59, 0, 89, 40);
		panel_5.add(textPane_18);
		
		JTextPane textPane_19 = new JTextPane();
		textPane_19.setText("\uD070\uC8FC\uC81C1");
		textPane_19.setBounds(212, 0, 107, 40);
		panel_5.add(textPane_19);
		
		JTextPane textPane_20 = new JTextPane();
		textPane_20.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_20.setBounds(322, 0, 99, 40);
		panel_5.add(textPane_20);
		
		JTextPane textPane_21 = new JTextPane();
		textPane_21.setText("\uC8FC\uAD00\uC2DD");
		textPane_21.setBounds(425, 0, 70, 40);
		panel_5.add(textPane_21);
		
		JTextPane textPane_22 = new JTextPane();
		textPane_22.setText("\uB09C\uC774\uB3C4");
		textPane_22.setBounds(499, 0, 59, 40);
		panel_5.add(textPane_22);
		
		JTextPane textPane_23 = new JTextPane();
		textPane_23.setText("\uD3C9\uC810");
		textPane_23.setBounds(145, 0, 66, 40);
		panel_5.add(textPane_23);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(12, 221, 558, 40);
		panel_1.add(panel_6);
		
		JButton button_8 = new JButton("No.1");
		button_8.setBounds(0, 0, 59, 40);
		panel_6.add(button_8);
		
		JTextPane textPane_24 = new JTextPane();
		textPane_24.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_24.setBounds(59, 0, 89, 40);
		panel_6.add(textPane_24);
		
		JTextPane textPane_25 = new JTextPane();
		textPane_25.setText("\uD070\uC8FC\uC81C1");
		textPane_25.setBounds(212, 0, 107, 40);
		panel_6.add(textPane_25);
		
		JTextPane textPane_26 = new JTextPane();
		textPane_26.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_26.setBounds(322, 0, 99, 40);
		panel_6.add(textPane_26);
		
		JTextPane textPane_27 = new JTextPane();
		textPane_27.setText("\uC8FC\uAD00\uC2DD");
		textPane_27.setBounds(425, 0, 70, 40);
		panel_6.add(textPane_27);
		
		JTextPane textPane_28 = new JTextPane();
		textPane_28.setText("\uB09C\uC774\uB3C4");
		textPane_28.setBounds(499, 0, 59, 40);
		panel_6.add(textPane_28);
		
		JTextPane textPane_29 = new JTextPane();
		textPane_29.setText("\uD3C9\uC810");
		textPane_29.setBounds(145, 0, 66, 40);
		panel_6.add(textPane_29);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(12, 271, 558, 40);
		panel_1.add(panel_7);
		
		JButton button_9 = new JButton("No.1");
		button_9.setBounds(0, 0, 59, 40);
		panel_7.add(button_9);
		
		JTextPane textPane_30 = new JTextPane();
		textPane_30.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_30.setBounds(59, 0, 89, 40);
		panel_7.add(textPane_30);
		
		JTextPane textPane_31 = new JTextPane();
		textPane_31.setText("\uD070\uC8FC\uC81C1");
		textPane_31.setBounds(212, 0, 107, 40);
		panel_7.add(textPane_31);
		
		JTextPane textPane_32 = new JTextPane();
		textPane_32.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_32.setBounds(322, 0, 99, 40);
		panel_7.add(textPane_32);
		
		JTextPane textPane_33 = new JTextPane();
		textPane_33.setText("\uC8FC\uAD00\uC2DD");
		textPane_33.setBounds(425, 0, 70, 40);
		panel_7.add(textPane_33);
		
		JTextPane textPane_34 = new JTextPane();
		textPane_34.setText("\uB09C\uC774\uB3C4");
		textPane_34.setBounds(499, 0, 59, 40);
		panel_7.add(textPane_34);
		
		JTextPane textPane_35 = new JTextPane();
		textPane_35.setText("\uD3C9\uC810");
		textPane_35.setBounds(145, 0, 66, 40);
		panel_7.add(textPane_35);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(12, 321, 558, 40);
		panel_1.add(panel_8);
		
		JButton button_10 = new JButton("No.1");
		button_10.setBounds(0, 0, 59, 40);
		panel_8.add(button_10);
		
		JTextPane textPane_36 = new JTextPane();
		textPane_36.setText("\uBB38\uC81C\uC774\uB9841");
		textPane_36.setBounds(59, 0, 89, 40);
		panel_8.add(textPane_36);
		
		JTextPane textPane_37 = new JTextPane();
		textPane_37.setText("\uD070\uC8FC\uC81C1");
		textPane_37.setBounds(212, 0, 107, 40);
		panel_8.add(textPane_37);
		
		JTextPane textPane_38 = new JTextPane();
		textPane_38.setText("\uC791\uC740\uC8FC\uC81C1");
		textPane_38.setBounds(322, 0, 99, 40);
		panel_8.add(textPane_38);
		
		JTextPane textPane_39 = new JTextPane();
		textPane_39.setText("\uC8FC\uAD00\uC2DD");
		textPane_39.setBounds(425, 0, 70, 40);
		panel_8.add(textPane_39);
		
		JTextPane textPane_40 = new JTextPane();
		textPane_40.setText("\uB09C\uC774\uB3C4");
		textPane_40.setBounds(499, 0, 59, 40);
		panel_8.add(textPane_40);
		
		JTextPane textPane_41 = new JTextPane();
		textPane_41.setText("\uD3C9\uC810");
		textPane_41.setBounds(145, 0, 66, 40);
		panel_8.add(textPane_41);
	}
}
