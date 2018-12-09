package network_termproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JEditorPane;

public class evaluationAnswer extends JFrame {

	/*
	 * gui that evaluate certain answer and send that data to server
	 */
	private JPanel contentPane;
	private JPanel menu;

	public evaluationAnswer(String temp_a) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\eclipse-workspace\\Term_Pro\\aa.png"));	
		
		setTitle("Evaluation Answer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("√Îº“");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(366, 333, 166, 57);
		contentPane.add(button);
		
		JEditorPane dtrpnAnswer = new JEditorPane();
		dtrpnAnswer.setText(temp_a);
		dtrpnAnswer.setBounds(35, 23, 496, 279);
		contentPane.add(dtrpnAnswer);
		
		JLabel label_3 = new JLabel();
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(0, 0, 563, 420);
		contentPane.add(label_3);
		setVisible(true);
		
	}
}
