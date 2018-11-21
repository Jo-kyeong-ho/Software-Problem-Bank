package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;

public class evaluation extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evaluation frame = new evaluation();
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
	public evaluation() {
		/*
		     ��������
           Msgproblem/�����̸�/���δ� id/����/���̵�/����
                   ����
               ->����.txt����
               ->����.txt����
               ->�׸�.jpg����
        */
		
		setTitle("Evaluation Board");
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
		button.addActionListener(new ActionListener() {      //Mypage ��ư ������ mypage�� �̵� 
			public void actionPerformed(ActionEvent e) {
				/*
				   Ŭ���� server�� 
				  Msgmypage����
				*/
				new mypage().setVisible(true);
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("�����Խ���");
		button_1.addActionListener(new ActionListener() {    //�����Խ��� ��ư ������ Question board�� �̵�
			public void actionPerformed(ActionEvent e) {
				/*
				 Ŭ���� server��
				Msgquestionboard ����
				*/
				new QuestionBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("�����Խ���");
		button_2.addActionListener(new ActionListener() {    //�����Խ��� ��ư ������ Problem board�� �̵�
			public void actionPerformed(ActionEvent e) {
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(121, 187, 673, 402);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("Show Answer");
		btnNewButton.setBounds(326, 298, 315, 73);
		panel_1.add(btnNewButton);
		
		JEditorPane dtrpnProblem = new JEditorPane();
		dtrpnProblem.setText("problem");
		dtrpnProblem.setBounds(12, 10, 302, 357);
		panel_1.add(dtrpnProblem);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setText("problem");
		editorPane.setBounds(326, 10, 315, 267);
		panel_1.add(editorPane);
		
		JButton btnEvaluate = new JButton("���ϱ�");
		btnEvaluate.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {       // ���ϱ� Ŭ���� evaluateWindow �����
				new evaluateWindow().setVisible(true);
	            setVisible(false);
			}
		});
		btnEvaluate.setBounds(522, 51, 239, 73);
		contentPane.add(btnEvaluate);
		
		JEditorPane dtrpnR = new JEditorPane();
		dtrpnR.setText("Problem name");
		dtrpnR.setBounds(165, 40, 106, 21);
		contentPane.add(dtrpnR);
		
		JEditorPane dtrpnUploaderName = new JEditorPane();
		dtrpnUploaderName.setText("uploader name");
		dtrpnUploaderName.setBounds(165, 103, 106, 21);
		contentPane.add(dtrpnUploaderName);
	}
}
