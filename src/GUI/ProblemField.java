package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemField extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProblemField frame = new ProblemField();
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
	public ProblemField() {
		/*
		     ��������
           Msgproblem/����id/�����̸�/������ id/����
                   ����
               ->����.txt����
               ->�׸�.jpg����
		 */
		setTitle("Problem Field");
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
			public void actionPerformed(ActionEvent arg0) {		  //Mypage ��ư ������ mypage�� �̵� 
				new mypage().setVisible(true);
	            setVisible(false);}
		});
		button.setBounds(0, 80, 120, 47);
		menu.add(button);
		
		JButton button_1 = new JButton("�����Խ���");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //�����Խ��� ��ư ������ Question board�� �̵�
				new QuestionBoard().setVisible(true);
	            setVisible(false);
				
			}
		});
		button_1.setBounds(0, 173, 120, 47);
		menu.add(button_1);
		
		JButton button_2 = new JButton("�����Խ���");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {          //�����Խ��� ��ư ������ Problem board�� �̵�
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		button_2.setBounds(0, 269, 120, 47);
		menu.add(button_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(168, 126, 582, 364);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JEditorPane dtrpnProblemText = new JEditorPane();
		dtrpnProblemText.setText("Problem- text");
		dtrpnProblemText.setBounds(12, 10, 262, 344);
		panel_1.add(dtrpnProblemText);
		
		JEditorPane dtrpnProblemImage = new JEditorPane();
		dtrpnProblemImage.setText("Problem- image");
		dtrpnProblemImage.setBounds(286, 10, 284, 344);
		panel_1.add(dtrpnProblemImage);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(168, 10, 582, 99);
		contentPane.add(panel_2);
		
		JEditorPane dtrpnNameproblem = new JEditorPane();
		dtrpnNameproblem.setText("Name_Problem");
		dtrpnNameproblem.setBounds(12, 10, 262, 31);
		panel_2.add(dtrpnNameproblem);
		
		JEditorPane dtrpnNameusertype = new JEditorPane();
		dtrpnNameusertype.setText("Name_User&Type ");
		dtrpnNameusertype.setBounds(12, 58, 262, 31);
		panel_2.add(dtrpnNameusertype);
		
		JButton btnNewButton = new JButton("Show Answer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {           
			/*
			 ������
			 Msggetanswerlist/����id ����
			*/
			}
		});
		btnNewButton.setBounds(242, 521, 144, 39);
		contentPane.add(btnNewButton);
		
		JButton btnShowAnswer = new JButton("Write Answer");
		btnShowAnswer.addActionListener(new ActionListener() {     // �亯�ϱ� â ���
			public void actionPerformed(ActionEvent e) {
				new WriteAnswer().setVisible(true);
	            setVisible(false);
			}
		});
		btnShowAnswer.setBounds(533, 521, 144, 39);
		contentPane.add(btnShowAnswer);
	}
}
