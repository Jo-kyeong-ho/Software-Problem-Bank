package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class evaluateWindow extends JFrame {

	/*
	 * gui of evaluate certain problem
	 */
	private JPanel contentPane;
	private JTextField txtEnterRate;
	private JTextField txtStarStarStar;
	private JTextField txtProblemid;
	private JLabel lbl;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public evaluateWindow(Socket socket1,String problem_id,String problem_name) {
				
		socket=socket1;
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		try {
   		  in = new BufferedReader(new InputStreamReader(
   		            socket.getInputStream()));
   	      out = new PrintWriter(socket.getOutputStream(), true);
   	   	} catch (IOException e1) {
   	   		// TODO Auto-generated catch block
   	   		e1.printStackTrace();
   	   	}
		
		
		setTitle("Evaluate Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEvaluateRate = new JLabel("평점 입력(~5)");
		lblEvaluateRate.setBounds(282, 260, 150, 15);
		contentPane.add(lblEvaluateRate);
		
		JLabel lblLevelOfDificulty = new JLabel("난이도 입력(~5)");
		lblLevelOfDificulty.setBounds(282, 312, 97, 15);
		contentPane.add(lblLevelOfDificulty);
		
		txtEnterRate = new JTextField();
		txtEnterRate.setBounds(397, 257, 116, 21);
		contentPane.add(txtEnterRate);
		txtEnterRate.setColumns(10);
		
		txtStarStarStar = new JTextField();
		txtStarStarStar.setBounds(397, 309, 116, 21);
		contentPane.add(txtStarStarStar);
		
		JButton btnSubmit = new JButton("제출");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eval=txtEnterRate.getText();
				String level=txtStarStarStar.getText();
				out.println("Msgevaluate/"+problem_id+"/"+eval+"/"+level);
				setVisible(false);
			}
		});
		

		btnSubmit.setBounds(519, 453, 176, 73);
		contentPane.add(btnSubmit);
		
		txtProblemid = new JTextField();
		txtProblemid.setText("평가하기");
		txtProblemid.setBounds(175, 57, 116, 21);
		contentPane.add(txtProblemid);
		txtProblemid.setColumns(10);
		
		lbl = new JLabel();
		lbl.setIcon(ImageClass.EvaluateWindow_background()); 
		lbl.setBounds(0, 0, 794, 589);
		contentPane.add(lbl);
		lbl.setLayout(null);
		
		setVisible(true);
	}

}

