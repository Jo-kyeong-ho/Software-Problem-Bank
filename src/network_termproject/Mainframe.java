package network_termproject;


import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import network_termproject.SignUp;
import java.awt.Canvas;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JInternalFrame;
import javax.swing.JTree;

public class Mainframe extends JFrame {

   private JPanel Mainwindow;
   private JTextField textField;
   private JPasswordField passwordField;
   public static Mainframe frame;
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               frame = new Mainframe();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public Mainframe() {
      setTitle("SoftWare Problem Bank");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 810, 628);
      
      Mainwindow = new JPanel();
      Mainwindow.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(Mainwindow);
      Mainwindow.setLayout(null);
      Mainwindow.setLayout(new CardLayout(0, 0));

        JPanel panel = new JPanel();
        Mainwindow.add(panel, "name_182714014352507");
        panel.setLayout(null);
        
        JTextPane txtpnPicture = new JTextPane();
        txtpnPicture.setText("Picture");
        txtpnPicture.setBounds(76, 50, 203, 154);
        panel.add(txtpnPicture);
        
        JLabel EnterID = new JLabel("ID");
        EnterID.setBounds(211, 244, 79, 15);
        panel.add(EnterID);
        
        JLabel EnterPW = new JLabel("Password");
        EnterPW.setBounds(211, 288, 79, 15);
        panel.add(EnterPW);
        
        textField = new JTextField();
        textField.setBounds(290, 241, 116, 21);
        panel.add(textField);
        textField.setColumns(10);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(290, 285, 116, 18);
        panel.add(passwordField);
        
        
        JLabel lblNewLabel = new JLabel("Not register?");       
        lblNewLabel.setBounds(232, 426, 116, 15);
        panel.add(lblNewLabel);
        
        JButton LoginBT = new JButton("LogIn");        //로그인 버튼
        LoginBT.setBounds(269, 339, 97, 23);
        panel.add(LoginBT);
        
        LoginBT.addActionListener(new ActionListener() {         //로그인 액션
            public void actionPerformed(ActionEvent e) {
            	String id;
            	char[] get_password;
            	String pw="";
            	id=textField.getText();
            	get_password=passwordField.getPassword();
            	for(char cha : get_password)
            	{
            		Character.toString(cha);
            		pw+=(pw.equals("")) ? ""+cha+"":""+cha+"";
            	}
            	/*
            	out.println("msglogin/"+id+"/"+pw);
            	 *
            	 * 이 부분에 로그인 성공유무 확인하는 코드 후 성공할시 로그인창을 닫음
            	frame.setVisible(false);
            	 */
            	JOptionPane.showMessageDialog(null,"환영합니다","Success", 1);
            }
         });
        
        JButton CancelBT = new JButton("Cancel");      //취소 버튼 
        CancelBT.setBounds(406, 339, 97, 23); 
        panel.add(CancelBT);
        
        CancelBT.addActionListener(new ActionListener() {         //취소 버튼 누르면 닫힘 
           public void actionPerformed(ActionEvent e) {
           System.exit(0);
           }
        });
        
      JButton SignupBT = new JButton("SignUp");       //회원가입 버튼   
      
      SignupBT.addActionListener(new ActionListener() {         // 회원가입 버튼 누르면 회원가입 창으로 
         public void actionPerformed(ActionEvent e) {
        	new SignUp();
        	frame.setVisible(false); 
         }
      });
      
      SignupBT.setBounds(406, 422, 97, 23);
      panel.add(SignupBT);
      
   }
}
