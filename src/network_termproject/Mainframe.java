package network_termproject;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import network_termproject.SignUp;

public class Mainframe extends JFrame {
	/*
	 * gui that related with login page
	 */
	BufferedImage img = null;
	Socket socket;
	String message;
	String input="";
	int check=0;
	PrintWriter out = null;
	BufferedReader in = null;
	
   private JPanel Mainwindow;
   private Image bgImg = Toolkit.getDefaultToolkit().getImage("Back.png");
   private JTextField textField;
   private JPasswordField passwordField;
   public static Mainframe frame;
   public  void go(Socket socket1) {
	   socket=socket1;
	   try {
	        out = new PrintWriter(socket.getOutputStream(), true);
	   	} catch (IOException e1) {
	   		// TODO Auto-generated catch block
	   		e1.printStackTrace();
	   	}
	  try {
          frame = new Mainframe(socket);
          frame.setVisible(true);
       } catch (Exception e) {
          e.printStackTrace();
       }
   }
   public Mainframe(Socket socket1) {
	   socket=socket1;
	   try {
    		  in = new BufferedReader(new InputStreamReader(
    		            socket.getInputStream()));
    	        out = new PrintWriter(socket.getOutputStream(), true);
    	   	} catch (IOException e1) {
    	   		// TODO Auto-generated catch block
    	   		e1.printStackTrace();
    	   	}
      setTitle("SoftWare Problem Bank");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 730, 580);
      
      Mainwindow = new JPanel();
      Mainwindow.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(Mainwindow);
      Mainwindow.setLayout(null);
      
      
      JLabel MainImage = new JLabel();
      MainImage.setIcon(ImageClass.MainImage_background());// 메인이미지
      MainImage.setBounds(53,32,235,173);
      Mainwindow.add(MainImage);
     
      
        JLabel EnterID = new JLabel("ID");
        EnterID.setBounds(217, 256, 79, 15);
        Mainwindow.add(EnterID);
        
        JLabel EnterPW = new JLabel("Password");
        EnterPW.setBounds(217, 300, 79, 15);
        Mainwindow.add(EnterPW);
        
        textField = new JTextField();
        textField.setBounds(296, 253, 116, 21);
        Mainwindow.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(296, 297, 116, 18);
        Mainwindow.add(passwordField);
        
        JLabel lblNewLabel = new JLabel("Not register?");       
        lblNewLabel.setBounds(238, 438, 116, 15);
        Mainwindow.add(lblNewLabel);
        
        JButton LoginBT = new JButton("LogIn");        //로그인 버튼
        LoginBT.setBounds(275, 351, 97, 23);
        Mainwindow.add(LoginBT);
        
        LoginBT.addActionListener(new ActionListener() {         //로그인 액션
            public void actionPerformed(ActionEvent e) {
            	String id;
            	char[] get_password;
            	String pw="";
            	String input = null;
            	id=textField.getText();
            	get_password=passwordField.getPassword();
            	for(char cha : get_password)
            	{
            		Character.toString(cha);
            		pw+=(pw.equals("")) ? ""+cha+"":""+cha+"";
            	}
            	message="msglogin/"+id+"/"+pw;
            	out.println(message);
            	try {
					input = in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	if(input.contains("okay"))
            	{
            		JOptionPane.showMessageDialog(null,"환영합니다","Success", 1);
            		check=1;            		
            	}
            	if(input.contains("fail"))
            	{
            		JOptionPane.showMessageDialog(null,"아이디나 비밀번호가 일치하지않습니다.","fail", 1);
            	}
            }
         });
        
        JButton CancelBT = new JButton("취소");      //취소 버튼 
        CancelBT.setBounds(412, 351, 97, 23); 
        Mainwindow.add(CancelBT);
        
        CancelBT.addActionListener(new ActionListener() {         //취소 버튼 누르면 닫힘 
           public void actionPerformed(ActionEvent e) {
           System.exit(0);
           }
        });
        
      JButton SignupBT = new JButton("회원가입");       //회원가입 버튼   
      
      SignupBT.addActionListener(new ActionListener() {         // 회원가입 버튼 누르면 회원가입 창으로 
         public void actionPerformed(ActionEvent e) {
        	new SignUp(socket);
         }
      });
      
      SignupBT.setBounds(412, 434, 97, 23);
      Mainwindow.add(SignupBT);
      
      JLabel Label = new JLabel();       
      Label.setIcon(ImageClass.Mainframe_background());
      Label.setBounds(0,0,714,541);
      Mainwindow.add(Label);
      Label.setLayout(null);
    
      
      setVisible(true);
      while(true)
      {
    	  System.out.println(" ");
    	  if(check==1)
    	  {
    		  setVisible(false);
    		  break;
    	  }
      }
      

   }
}
