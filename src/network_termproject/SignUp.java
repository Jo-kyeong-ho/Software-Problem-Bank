package network_termproject;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import network_termproject.Mainframe;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame {

   private JFrame frmSignup;
   private JTextField textField;
   private JTextField textField_1;
   private JPasswordField passwordField;

   /**
    * Launch the application.
    */
   /*
   * 
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               SignUp window = new SignUp();
               window.frmSignup.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

    */
   public SignUp() {
      
      setTitle("SignUp");
      setSize(430,110);
      setLocation(0,120);
      setBounds(100, 100, 810, 628);
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
      textField_1.setBounds(201, 122, 116, 21);
      panel.add(textField_1);
      
      passwordField = new JPasswordField();
      passwordField.setBounds(201, 91, 116, 21);
      panel.add(passwordField);
      
      JButton confirm = new JButton("confirm");   //확인 버튼
      confirm.setBounds(103, 193, 97, 23);
      panel.add(confirm);   
      
      confirm.addActionListener(new ActionListener() {  //확인 버튼 누르면 성공 팝업창
         public void actionPerformed(ActionEvent e) {
        	 /*
        	  * 이 부분에 받은 아이디로 이미 있는아이디 인지 유무 판단
        	  */
         String id=textField_1.getText();
         /*
          * if(id=="이미있는아이디")
          * JOptionPane.showMessageDialog(null,"ID alredy exists","Fail", 1);
          */
         JOptionPane.showMessageDialog(null,"회원가입 성공","Success", 1);
         new Mainframe().setVisible(true);
         setVisible(false);
         
         }
      });   
   
      JButton cancel = new JButton("cancel");   //취소 버튼
      
      cancel.addActionListener(new ActionListener() {      //취소 버튼 누르면 메인 화면으로 
         public void actionPerformed(ActionEvent e) { 
            new Mainframe().setVisible(true);
            setVisible(false);
         }
      });
      
      cancel.setBounds(256, 193, 97, 23);
      panel.add(cancel);
      setVisible(true);
   }
   
}