package network_termproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.net.Socket;

import network_termproject.Mainframe;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

	/*
	 * gui of signup page and create dialoge according to server's respond
	 */
   private JFrame frmSignup;
   private JTextField textField;
   private JTextField textField_1;
   private JPasswordField passwordField;
   Socket socket;
   PrintWriter out = null;
   BufferedReader in=null;
   /**
    * Launch the application.
    */

   public SignUp(Socket socket1) {
	   socket=socket1;
		  try {
			  in = new BufferedReader(new InputStreamReader(
			            socket.getInputStream()));
		        out = new PrintWriter(socket.getOutputStream(), true);
		   	} catch (IOException e1) {
		   		// TODO Auto-generated catch block
		   		e1.printStackTrace();
		   	}
      setTitle("SignUp");
      setSize(430,110);
      setLocation(0,120);
      setBounds(100, 100, 520, 328);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setLayout(new CardLayout(0, 0));
      
      JPanel panel = new JPanel();
      getContentPane().add(panel, "name_188447017892630");
      panel.setLayout(null);
      
      JLabel Name = new JLabel("\uC774\uB984");
      Name.setBounds(112, 66, 57, 15);
      panel.add(Name);
      
      JLabel ID = new JLabel("ID");
      ID.setBounds(112, 94, 57, 15);
      panel.add(ID);
      
      JLabel Password = new JLabel("Password");
      Password.setBounds(112, 125, 77, 15);
      panel.add(Password);
      
      textField = new JTextField();
      textField.setBounds(201, 63, 116, 21);
      panel.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(201, 91, 116, 21);
      panel.add(textField_1);
      
      passwordField = new JPasswordField();
      passwordField.setBounds(201, 122, 116, 21);
      panel.add(passwordField);
      
      JButton confirm = new JButton("확인"); 
      confirm.setBounds(103, 193, 97, 23);
      panel.add(confirm);   
      
      confirm.addActionListener(new ActionListener() {  
         public void actionPerformed(ActionEvent e) {
         String password=textField_1.getText();
         String name=textField.getText();
         String id=passwordField.getText();
         String message="";
         String input="";
         message="msgjoin/"+id+"/"+name+"/"+password;
         System.out.println(message);
         out.println(message);
         try {
			input=in.readLine();
			System.out.println(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if(input.contains("okay")) {
         JOptionPane.showMessageDialog(null,"회원가입 성공","Success", 1);
         setVisible(false);
        }
        else {
        	JOptionPane.showMessageDialog(null,"중복된 아이디입니다","fail", 1);
         setVisible(false);
        }
         }
      });   
   
      JButton cancel = new JButton("취소");        
      cancel.addActionListener(new ActionListener() {      
         public void actionPerformed(ActionEvent e) { 
            setVisible(false);
         }
      });
      
      cancel.setBounds(256, 193, 97, 23);
      panel.add(cancel);
      
      JLabel Label = new JLabel();       
      Label.setIcon(ImageClass.EvaluateWindow_background());
      Label.setBounds(0,0,714,541);
      panel.add(Label);
      Label.setLayout(null);
      
      setVisible(true);
   }
   
}