package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnswerField extends JFrame {

	private JPanel contentPane;
	private JTextField txtAnswerField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnswerField frame = new AnswerField();
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
	public AnswerField() {
		/*
		           서버로부터
               Msganswerlist/질문이름*답변자아이디*추천수*비추천수*채택유무/똑같고/똑같고10개받음(없으면 null)
                          받음
		*/
		
		setTitle("AnswerField");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 121, 589);
		contentPane.add(panel);
		
		JButton button = new JButton("My Page");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //Mypage 버튼 누르면 mypage로 이동 
				new mypage().setVisible(true);
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		panel.add(button);
		
		JButton button_1 = new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {          //질문게시판 버튼 누르면 Question board로 이동
				new QuestionBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		panel.add(button_1);
		
		JButton button_2 =new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //문제게시판 버튼 누르면 Problem board로 이동
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		panel.add(button_2);
		
		txtAnswerField = new JTextField();
		txtAnswerField.setText("Answer Field");
		txtAnswerField.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswerField.setFont(new Font("굴림", Font.BOLD, 18));
		txtAnswerField.setColumns(10);
		txtAnswerField.setBounds(168, 10, 582, 49);
		contentPane.add(txtAnswerField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(170, 127, 582, 418);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(12, 21, 558, 40);
		panel_1.add(panel_2);
		
		JButton btnSelect = new JButton("채택");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 /*
				클릭하면 서버로
                Msggetanswer/질문아이디/답변자아이디
                            보냄
			 */
			}
		});
		btnSelect.setBounds(0, 0, 59, 40);
		panel_2.add(btnSelect);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText("\uB2F5\uBCC0\uC790 ID");
		txtpnId.setBounds(59, 0, 148, 40);
		panel_2.add(txtpnId);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("\uB2F5\uBCC0");
		textPane_1.setBounds(211, 0, 194, 40);
		panel_2.add(textPane_1);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("\uCD94\uCC9C");
		textPane_3.setBounds(417, 0, 59, 40);
		panel_2.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("\uBE44\uCD94\uCC9C");
		textPane_4.setBounds(487, 0, 59, 40);
		panel_2.add(textPane_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(12, 71, 558, 40);
		panel_1.add(panel_3);
		
		JButton button_4 = new JButton("\uCC44\uD0DD");
		button_4.setBounds(0, 0, 59, 40);
		panel_3.add(button_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("\uB2F5\uBCC0\uC790 ID");
		textPane.setBounds(59, 0, 148, 40);
		panel_3.add(textPane);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("\uB2F5\uBCC0");
		textPane_2.setBounds(211, 0, 194, 40);
		panel_3.add(textPane_2);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("\uCD94\uCC9C");
		textPane_5.setBounds(417, 0, 59, 40);
		panel_3.add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("\uBE44\uCD94\uCC9C");
		textPane_6.setBounds(487, 0, 59, 40);
		panel_3.add(textPane_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(12, 121, 558, 40);
		panel_1.add(panel_4);
		
		JButton button_5 = new JButton("\uCC44\uD0DD");
		button_5.setBounds(0, 0, 59, 40);
		panel_4.add(button_5);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("\uB2F5\uBCC0\uC790 ID");
		textPane_7.setBounds(59, 0, 148, 40);
		panel_4.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("\uB2F5\uBCC0");
		textPane_8.setBounds(211, 0, 194, 40);
		panel_4.add(textPane_8);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("\uCD94\uCC9C");
		textPane_9.setBounds(417, 0, 59, 40);
		panel_4.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("\uBE44\uCD94\uCC9C");
		textPane_10.setBounds(487, 0, 59, 40);
		panel_4.add(textPane_10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(12, 171, 558, 40);
		panel_1.add(panel_5);
		
		JButton button_6 = new JButton("\uCC44\uD0DD");
		button_6.setBounds(0, 0, 59, 40);
		panel_5.add(button_6);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText("\uB2F5\uBCC0\uC790 ID");
		textPane_11.setBounds(59, 0, 148, 40);
		panel_5.add(textPane_11);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText("\uB2F5\uBCC0");
		textPane_12.setBounds(211, 0, 194, 40);
		panel_5.add(textPane_12);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText("\uCD94\uCC9C");
		textPane_13.setBounds(417, 0, 59, 40);
		panel_5.add(textPane_13);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText("\uBE44\uCD94\uCC9C");
		textPane_14.setBounds(487, 0, 59, 40);
		panel_5.add(textPane_14);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(12, 221, 558, 40);
		panel_1.add(panel_6);
		
		JButton button_7 = new JButton("\uCC44\uD0DD");
		button_7.setBounds(0, 0, 59, 40);
		panel_6.add(button_7);
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText("\uB2F5\uBCC0\uC790 ID");
		textPane_15.setBounds(59, 0, 148, 40);
		panel_6.add(textPane_15);
		
		JTextPane textPane_16 = new JTextPane();
		textPane_16.setText("\uB2F5\uBCC0");
		textPane_16.setBounds(211, 0, 194, 40);
		panel_6.add(textPane_16);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText("\uCD94\uCC9C");
		textPane_17.setBounds(417, 0, 59, 40);
		panel_6.add(textPane_17);
		
		JTextPane textPane_18 = new JTextPane();
		textPane_18.setText("\uBE44\uCD94\uCC9C");
		textPane_18.setBounds(487, 0, 59, 40);
		panel_6.add(textPane_18);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(12, 271, 558, 40);
		panel_1.add(panel_7);
		
		JButton button_8 = new JButton("\uCC44\uD0DD");
		button_8.setBounds(0, 0, 59, 40);
		panel_7.add(button_8);
		
		JTextPane textPane_19 = new JTextPane();
		textPane_19.setText("\uB2F5\uBCC0\uC790 ID");
		textPane_19.setBounds(59, 0, 148, 40);
		panel_7.add(textPane_19);
		
		JTextPane textPane_20 = new JTextPane();
		textPane_20.setText("\uB2F5\uBCC0");
		textPane_20.setBounds(211, 0, 194, 40);
		panel_7.add(textPane_20);
		
		JTextPane textPane_21 = new JTextPane();
		textPane_21.setText("\uCD94\uCC9C");
		textPane_21.setBounds(417, 0, 59, 40);
		panel_7.add(textPane_21);
		
		JTextPane textPane_22 = new JTextPane();
		textPane_22.setText("\uBE44\uCD94\uCC9C");
		textPane_22.setBounds(487, 0, 59, 40);
		panel_7.add(textPane_22);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(12, 321, 558, 40);
		panel_1.add(panel_8);
		
		JButton button_9 = new JButton("\uCC44\uD0DD");
		button_9.setBounds(0, 0, 59, 40);
		panel_8.add(button_9);
		
		JTextPane textPane_23 = new JTextPane();
		textPane_23.setText("\uB2F5\uBCC0\uC790 ID");
		textPane_23.setBounds(59, 0, 148, 40);
		panel_8.add(textPane_23);
		
		JTextPane textPane_24 = new JTextPane();
		textPane_24.setText("\uB2F5\uBCC0");
		textPane_24.setBounds(211, 0, 194, 40);
		panel_8.add(textPane_24);
		
		JTextPane textPane_25 = new JTextPane();
		textPane_25.setText("\uCD94\uCC9C");
		textPane_25.setBounds(417, 0, 59, 40);
		panel_8.add(textPane_25);
		
		JTextPane textPane_26 = new JTextPane();
		textPane_26.setText("\uBE44\uCD94\uCC9C");
		textPane_26.setBounds(487, 0, 59, 40);
		panel_8.add(textPane_26);
	}

}
