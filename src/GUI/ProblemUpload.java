package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Choice;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProblemUpload extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextField txtEnterProblem;
	private JTextField txtEnterAnswer;
	private JTextField txtInputFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProblemUpload frame = new ProblemUpload();
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
	public ProblemUpload() {
		setTitle("Problem Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel InputName = new JPanel();
		InputName.setLayout(null);
		InputName.setBounds(0, 10, 794, 52);
		contentPane.add(InputName);
		
		JLabel ProblemName = new JLabel("Problem Name");
		ProblemName.setFont(new Font("굴림", Font.PLAIN, 18));
		ProblemName.setBounds(251, 10, 126, 32);
		InputName.add(ProblemName);
		
		txtEnterName = new JTextField();
		txtEnterName.setText("Enter name");
		txtEnterName.setFont(new Font("굴림", Font.PLAIN, 18));
		txtEnterName.setBounds(396, 16, 116, 21);
		InputName.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		JPanel InputProblem = new JPanel();
		InputProblem.setLayout(null);
		InputProblem.setBounds(0, 61, 794, 207);
		contentPane.add(InputProblem);
		
		txtEnterProblem = new JTextField();
		txtEnterProblem.setText("Enter problem");
		txtEnterProblem.setFont(new Font("굴림", Font.PLAIN, 18));
		txtEnterProblem.setColumns(10);
		txtEnterProblem.setBounds(155, 10, 491, 187);
		InputProblem.add(txtEnterProblem);
		
		JPanel InputAnswer = new JPanel();
		InputAnswer.setLayout(null);
		InputAnswer.setBounds(0, 268, 794, 110);
		contentPane.add(InputAnswer);
		
		txtEnterAnswer = new JTextField();
		txtEnterAnswer.setText("Enter answer");
		txtEnterAnswer.setFont(new Font("굴림", Font.PLAIN, 18));
		txtEnterAnswer.setColumns(10);
		txtEnterAnswer.setBounds(156, 10, 490, 90);
		InputAnswer.add(txtEnterAnswer);
		
		JPanel InputImage = new JPanel();
		InputImage.setLayout(null);
		InputImage.setBounds(0, 378, 794, 110);
		contentPane.add(InputImage);
		
		txtInputFile = new JTextField();
		txtInputFile.setText("Input FIle");
		txtInputFile.setFont(new Font("굴림", Font.PLAIN, 18));
		txtInputFile.setColumns(10);
		txtInputFile.setBounds(156, 10, 490, 90);
		InputImage.add(txtInputFile);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 upload 버튼 클릭시
				 서버로 Msgproblemupload/문제내용/이미지/user id 전송
				*/
			}
		});
		btnUpload.setBounds(232, 525, 97, 23);
		contentPane.add(btnUpload);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {       //취소 버튼 누르면 Problem board로 이동
			public void actionPerformed(ActionEvent e) {
				new ProblemBoard().setVisible(true);
	            setVisible(false);
			}
		});
		btnCancel.setBounds(476, 525, 97, 23);
		contentPane.add(btnCancel);
	}
}
