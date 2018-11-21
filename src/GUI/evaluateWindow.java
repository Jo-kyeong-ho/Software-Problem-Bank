package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class evaluateWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterRate;
	private JTextField txtStarStarStar;
	private JTextField txtProblemid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evaluateWindow frame = new evaluateWindow();
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
	public evaluateWindow() {
		setTitle("Evaluate Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEvaluateRate = new JLabel("Evaluate rate");
		lblEvaluateRate.setBounds(282, 260, 150, 15);
		contentPane.add(lblEvaluateRate);
		
		JLabel lblLevelOfDificulty = new JLabel("Level of dificulty");
		lblLevelOfDificulty.setBounds(282, 312, 97, 15);
		contentPane.add(lblLevelOfDificulty);
		
		txtEnterRate = new JTextField();
		txtEnterRate.setBounds(397, 257, 116, 21);
		contentPane.add(txtEnterRate);
		txtEnterRate.setColumns(10);
		
		txtStarStarStar = new JTextField();
		txtStarStarStar.setText("star 클릭해서 찍는거");
		txtStarStarStar.setColumns(10);
		txtStarStarStar.setBounds(397, 309, 116, 21);
		contentPane.add(txtStarStarStar);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				       서버에게 
				    Msgevaluate/문제아이디/평점/난이도
				       전송
				*/
			}
		});
		

		btnSubmit.setBounds(519, 453, 176, 73);
		contentPane.add(btnSubmit);
		
		txtProblemid = new JTextField();
		txtProblemid.setText("Problem id name");
		txtProblemid.setBounds(95, 58, 116, 21);
		contentPane.add(txtProblemid);
		txtProblemid.setColumns(10);
	}

}
