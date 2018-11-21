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

public class ShowAnswer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAnswer frame = new ShowAnswer();
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
	public ShowAnswer() {
		/*
		       �����κ���
            Msggetanswer/����id/�亯��id/������id
               ->�亯.txt����
		*/
		
		setTitle("ShowAnswer");
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
			public void actionPerformed(ActionEvent e) {          //Mypage ��ư ������ mypage�� �̵� 
				new mypage().setVisible(true);
	            setVisible(false);
			}
		});
		button.setBounds(0, 80, 120, 47);
		panel.add(button);
		
		JButton button_1 = new JButton("�����Խ���");
		button_1.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {          //�����Խ��� ��ư ������ Question board�� �̵�
				new QuestionBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		panel.add(button_1);
		
		JButton button_2 = new JButton("�����Խ���");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //�����Խ��� ��ư ������ Problem board�� �̵�
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		panel.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(151, 10, 614, 309);
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
		panel_2.setBounds(151, 314, 614, 130);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setText("Write Answer");
		editorPane_2.setBounds(12, 10, 590, 110);
		panel_2.add(editorPane_2);
		
		JButton btnGood = new JButton("��õ");
		btnGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 /*
				 ������
                 Msgrecommendplus/����id/�亯��id/��õ��id 
                             ����
		     */
			}
		});
		btnGood.setBounds(210, 504, 122, 32);
		contentPane.add(btnGood);
		
		JButton btnBad = new JButton("����õ");
		btnBad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 /*
				 ������
                 Msgrecommendminus/����id/�亯��id/��õ��id 
                             ����
             */
			}
		});
		btnBad.setBounds(375, 504, 122, 32);
		contentPane.add(btnBad);
		
		JButton btnSelect = new JButton("ä��");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
				 ������ id = user id �� ������ ������,
                             Ŭ���� ������
                 Msggoodanswer/����id/�亯��id
                             ����
			*/
			}
		});
		btnSelect.setBounds(549, 504, 122, 32);
		contentPane.add(btnSelect);
	}

}
