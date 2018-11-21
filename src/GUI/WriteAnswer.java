package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WriteAnswer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WriteAnswer frame = new WriteAnswer();
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
	public WriteAnswer() {
		setTitle("Write Answer");
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
		
		JButton button_1 =  new JButton("질문게시판");
		button_1.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {          //질문게시판 버튼 누르면 Question board로 이동
				new QuestionBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		panel.add(button_1);
		
		JButton button_2 =  new JButton("문제게시판");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //문제게시판 버튼 누르면 Problem board로 이동
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(157, 10, 614, 309);
		contentPane.add(panel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText("Problem- text");
		editorPane.setBounds(12, 10, 278, 289);
		panel_1.add(editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setText("Problem- image");
		editorPane_1.setBounds(302, 10, 300, 289);
		panel_1.add(editorPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(157, 341, 614, 130);
		contentPane.add(panel_2);
		
		JEditorPane dtrpnWriteAnswer = new JEditorPane();
		dtrpnWriteAnswer.setText("Write Answer");
		dtrpnWriteAnswer.setBounds(12, 10, 590, 110);
		panel_2.add(dtrpnWriteAnswer);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				       서버로
				    Msgputanswer/질문 id/답변자ID/답변
				       보냄
                */
			}
		});
		btnUpload.setBounds(377, 509, 149, 48);
		contentPane.add(btnUpload);
	}

}
