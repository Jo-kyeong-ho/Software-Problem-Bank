package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditIntroduce extends JFrame {

	private JPanel contentPane;
	private JTextField textIntroduce;

	/**
	 * Launch the application.
	 */
	public static EditIntroduce frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditIntroduce frame = new EditIntroduce();
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
	public EditIntroduce() {
		setTitle("Edit Introduce");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textIntroduce = new JTextField();
		textIntroduce.setText("소개글들어갈자리");    //안에 소개글.txt내용넣고 변경가능하게 설정
		textIntroduce.setBounds(59, 24, 745, 453);
		contentPane.add(textIntroduce);
		textIntroduce.setColumns(10);
		
		JButton btnConfirm = new JButton("확인");
		btnConfirm.setBounds(493, 524, 120, 41);
        /*
 		서버로
		Msgchangeintroduce/id/소개글내용 전송
        */
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {   // 취소 버튼 누르면 다시 mypage창으로 
			public void actionPerformed(ActionEvent e) { 
				new mypage().setVisible(true);
	            setVisible(false);
	        }
		});
		btnCancel.setBounds(688, 524, 112, 41);
		contentPane.add(btnCancel);
		
		 setVisible(true);
	}
}
