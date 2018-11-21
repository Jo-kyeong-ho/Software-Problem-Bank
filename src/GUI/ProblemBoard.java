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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemBoard extends JFrame {

	private JPanel contentPane;
	private JTextField txtProblemBoard;

	/**
	 * Launch the application.
	 */
	public static ProblemBoard frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ProblemBoard();
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
	public ProblemBoard() {
		setTitle("Problem Board");
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
		
		Panel Body = new Panel();
		Body.setLayout(null);
		Body.setBounds(121, 0, 673, 589);
		contentPane.add(Body);
		
		txtProblemBoard = new JTextField();
		txtProblemBoard.setText("Problem Board");
		txtProblemBoard.setHorizontalAlignment(SwingConstants.CENTER);
		txtProblemBoard.setFont(new Font("����", Font.BOLD, 18));
		txtProblemBoard.setColumns(10);
		txtProblemBoard.setBounds(44, 44, 582, 49);
		Body.add(txtProblemBoard);
		
		JPanel Search = new JPanel();
		Search.setLayout(null);
		Search.setBounds(44, 133, 582, 418);
		Body.add(Search);
		
		Choice Type = new Choice();                // ū ���� ���ϱ� 
		Type.setBounds(89, 144, 87, 21);
		
		Type.add("ū����");                         // ���� ����ֱ�
		Type.add("OS"); 
		Type.add("Network");
		Type.add("Algorithm");
		Type.add("DataBase");
		   
		Type.select(0);  //�ʱ� ���� ��(�ڸ�) ����
		Search.add(Type);
		
		Choice detail = new Choice();              // ���� ���� ���ϱ� 
		detail.setBounds(225, 144, 87, 21);
			
		detail.add("��������");                      // ���� ����ֱ�
		detail.add("Network");
		detail.add("Algorithm");
		detail.add("DataBase");
		detail.select(0); //�ʱ� ���� ��(�ڸ�) ����
		Search.add(detail);
		
		JButton btnSearch = new JButton("search");     // �˻� ��ư 
		/*
		Ŭ���ϸ� ������
		Msgproblemsearch/ū����/��������/1 ����
        */
		btnSearch.setBounds(360, 144, 97, 23);
		Search.add(btnSearch);
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
