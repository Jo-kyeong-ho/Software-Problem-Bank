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
import java.awt.Color;

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

public class ProblemUpload extends JFrame {

	/*
	 * gui of problem's upload 
	 * it send problem's text and img's filepath and send their's real conten to server(first buffer,count of line after real data) 
	 */
	private JPanel contentPane;
	private JTextField txtEnterName;
	private JTextPane txtEnterProblem;
	private JTextPane txtEnterAnswer;
	private JTextField txtInputFile;
	Socket socket;
	PrintWriter out = null;
	BufferedReader in=null;

	public ProblemUpload(Socket socket1) {
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
		
		setTitle("Problem Upload");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ProblemName = new JLabel("Problem Name");
		ProblemName.setBounds(190, 19, 126, 32);
		contentPane.add(ProblemName);
		ProblemName.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		
		txtEnterName = new JTextField();
		txtEnterName.setBounds(327, 24, 116, 21);
		contentPane.add(txtEnterName);
		txtEnterName.setText("Enter name");
		txtEnterName.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		txtEnterName.setColumns(10);
		
		txtEnterProblem = new JTextPane();
		txtEnterProblem.setText("Enter problem");
		txtEnterProblem.setFont(new Font("±º∏≤", Font.PLAIN, 12));
		//txtEnterProblem.setColumns(10);
		txtEnterProblem.setBounds(155, 71, 491, 187);
		contentPane.add(txtEnterProblem);
		
		txtEnterAnswer = new JTextPane();
		txtEnterAnswer.setText("Enter answer");
		txtEnterAnswer.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		//txtEnterAnswer.setColumns(10);
		txtEnterAnswer.setBounds(156, 278, 490, 90);
		contentPane.add(txtEnterAnswer);
		
		txtInputFile = new JTextField();
		txtInputFile.setText("Input FIle");
		txtInputFile.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		txtInputFile.setColumns(10);
		txtInputFile.setBounds(156, 391, 490, 90);
		contentPane.add(txtInputFile);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="Msgproblemupload/";
				String content;
				content=txtEnterName.getText();
				str=str+content;
				out.println(str);
				content=txtEnterProblem.getText();
				int count=0;
				StringTokenizer token1=new StringTokenizer(content,"\n");
				StringTokenizer token2=new StringTokenizer(content,"\n");
				while(token1.hasMoreTokens())
				{
					String a=token1.nextToken();
					System.out.println(a);
					count++;
				}
				out.println(count);
				while(token2.hasMoreTokens())
				{
					out.println(token2.nextToken());
				}
				content=txtEnterAnswer.getText();
				count=0;
				StringTokenizer token3=new StringTokenizer(content,"\n");
				StringTokenizer token4=new StringTokenizer(content,"\n");
				while(token3.hasMoreTokens())
				{
					String a=token3.nextToken();
					System.out.println(a);
					count++;
				}
				out.println(count);
				while(token4.hasMoreTokens())
				{
					out.println(token4.nextToken());
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
				System.out.println("temp");
	      	    File imgfile = new File("temp.jpg");
	      	    String flen = String.valueOf(imgfile.length());
	      	    String header = "0000000000".substring(0, 10-flen.length())+flen;
	      	    FileInputStream fis;
				try {
					fis = new FileInputStream(imgfile);
					out.println("okay");
					 OutputStream os = socket.getOutputStream();
			      	    os.write(header.getBytes());
			      	    while (fis.available() > 0) {
			      	      int readsz = fis.read(buffer);
			      	      System.out.println(readsz);
			      	      os.write(buffer, 0, readsz);
			      	    }
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
				}
				setVisible(false);
			}
		});
		btnUpload.setBounds(232, 525, 97, 23);
		contentPane.add(btnUpload);
		
		JButton btnCancel = new JButton("√Îº“");
		btnCancel.addActionListener(new ActionListener() {       
			public void actionPerformed(ActionEvent e) {
				out.println("problemwindow");
	            setVisible(false);
			}
		});
		btnCancel.setBounds(476, 525, 97, 23);
		contentPane.add(btnCancel);
		
		JLabel panel  = new JLabel();
		panel.setBackground(Color.WHITE);
		panel.setIcon(ImageClass.Problemupload_background()); 
		panel.setBounds(0, 0, 794, 589); 
		contentPane.add(panel);
		panel.setLayout(null);
		setVisible(true);
	}
}
