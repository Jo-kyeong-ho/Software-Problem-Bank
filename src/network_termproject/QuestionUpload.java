package network_termproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Choice;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class QuestionUpload extends JFrame {

	/*
	 * gui of question upload page.
	 * it send server quesion's txt and img
	 * it send buffer and count of line first and send data after that
	 */
	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextPane txtEnterProblem;
	private JTextPane txtEnterAnswer;
	private JTextField txtInputFile;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public QuestionUpload(Socket socket1) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("aa.png"));
		socket=socket1;	
		try {
	   		  in = new BufferedReader(new InputStreamReader(
	   		            socket.getInputStream()));
	   	      out = new PrintWriter(socket.getOutputStream(), true);
	   	   	} catch (IOException e1) {
	   	   		// TODO Auto-generated catch block
	   	   		e1.printStackTrace();
	   	   	}
		
		
		setTitle("Question Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ProblemName = new JLabel("Question Name");
		ProblemName.setFont(new Font("굴림", Font.PLAIN, 18));
		ProblemName.setBounds(201, 35, 126, 32);
		contentPane.add(ProblemName);
		
		txtEnterName = new JTextField();
		txtEnterName.setText("Enter name");
		txtEnterName.setFont(new Font("굴림", Font.PLAIN, 18));
		txtEnterName.setBounds(341, 43, 116, 21);
		contentPane.add(txtEnterName);
		txtEnterName.setColumns(10);
		
		txtEnterProblem = new JTextPane();
		txtEnterProblem.setText("Enter Question");
		txtEnterProblem.setFont(new Font("굴림", Font.PLAIN, 12));
		txtEnterProblem.setBounds(156, 89, 491, 187);
		contentPane.add(txtEnterProblem);
		
		txtInputFile = new JTextField();
		txtInputFile.setText("Input FIle");
		txtInputFile.setFont(new Font("굴림", Font.PLAIN, 18));
		txtInputFile.setColumns(10);
		txtInputFile.setBounds(157, 313, 490, 90);
		contentPane.add(txtInputFile);
		
		Choice Type = new Choice();
		Type.setBounds(219, 426, 87, 21);
		contentPane.add(Type);
		
		Choice detail = new Choice();
		detail.setBounds(355, 426, 87, 21);
		contentPane.add(detail);
		
		Choice feature = new Choice();
		feature.setBounds(486, 426, 87, 21);
		contentPane.add(feature);
		
		feature.add("form");                     
		feature.add("select");
		feature.add("short");
		feature.add("long");
		
		detail.add("세부주제");                      
		detail.add("Introduction");
		detail.add("applicationlayer");
		detail.add("transportlayer");
		detail.add("networklayer");
		detail.add("linklayer");
		detail.add("wireless");
		detail.select(0);
		
		Type.add("큰주제");                       
		Type.add("os"); 
		Type.add("network");
		Type.add("algorithm");
		Type.add("dataBase");
		
		JButton btnUpload = new JButton("업로드");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content=null;
				int count=0;
				String large,small,form;
				content=txtEnterName.getText();
				large=Type.getSelectedItem();
				small=detail.getSelectedItem();
				form=feature.getSelectedItem();
				out.println("Msgquestionupload/"+content+"/"+large+"/"+small+"/"+form);
				content=txtEnterProblem.getText();
				StringTokenizer token1=new StringTokenizer(content,"\n");
				StringTokenizer token2=new StringTokenizer(content,"\n");
				while(token1.hasMoreTokens())
				{
					String a=token1.nextToken();
					count++;
				}
				out.println(count);
				while(token2.hasMoreTokens())
				{
					out.println(token2.nextToken());
				}
				String imgname=txtInputFile.getText();
				
				Image image;
				int check=1;
			    int imageWidth;
			    int imageHeight;
			    int w=315;
		        int h=329;
		        String imgFormat="jpg";
				try {
					image = ImageIO.read(new File(imgname));
					imageWidth = image.getWidth(null);
		            imageHeight = image.getHeight(null);
		            Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		            
		            // 새 이미지  저장하기
		            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		            Graphics g = newImage.getGraphics();
		            g.drawImage(resizeImage, 0, 0, null);
		            g.dispose();
		            ImageIO.write(newImage, imgFormat, new File("temp.jpg"));					
				} catch (IOException e2) {
					out.println("null");
					System.out.println("no image");
					check=0;
				}
				if(check==1)
				{
				byte buffer[] = new byte[2048];
	      	    File imgfile = new File("temp.jpg");
	      	    String flen = String.valueOf(imgfile.length());
	      	    String header = "0000000000".substring(0, 10-flen.length())+flen;
	      	    FileInputStream fis;
				try {
					fis = new FileInputStream("temp.jpg");
					out.println("okay");
					 OutputStream os = socket.getOutputStream();
			      	    os.write(header.getBytes());
			      	    while (fis.available() > 0) {
			      	      int readsz = fis.read(buffer);
			      	      os.write(buffer, 0, readsz);
			      	    }
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				out.println("Msgquestionboard/1");
				setVisible(false);
			}
		});
		btnUpload.setBounds(254, 483, 108, 37);
		contentPane.add(btnUpload);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {      
			public void actionPerformed(ActionEvent e) {
				out.println("Msgquestionboard/1");
	            setVisible(false);
			}
		});
		btnCancel.setBounds(448, 483, 108, 37);
		contentPane.add(btnCancel);
		
		JLabel panel  = new JLabel();
		panel.setIcon(ImageClass.Questionupload_background()); 
		panel.setBounds(0, 0, 794, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}
}
