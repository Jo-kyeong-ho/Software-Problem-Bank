package network_termproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class client {


    static BufferedReader in;
    static PrintWriter out;
    private String line;
    static int check;
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = ""; //server ip address
        Socket socket = new Socket(serverAddress, 9003);//make socket
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
        	if(check==0)        		
        	{
        		new Mainframe(socket);           		
           		check=1;
           		out.println("msgmypage");
        	}
        	else 
        	{
        		//recognize server's message and excute gui class
        		System.out.println("what is line");
               line = in.readLine();//receive from server
               System.out.println(line);
               if (line.startsWith("msgmypage")) {//startWith *
            	   new mypage(socket,line);
               }
               else if(line.startsWith("msgyourpage"))
               {
            	   new yourpage(socket,line);
               }
               else if(line.startsWith("Msgadmin"))
               {
            	   new ManageBoard(socket,line);
               }
               else if(line.startsWith("Msggetreport"))
               {
            	   new report(socket,line);
               }
               else if(line.startsWith("Msgplaygame"))
               {
            	   new MiniGame(socket,line);
               }
               else if(line.startsWith("Msggameclose"))
               {
            	   JOptionPane.showMessageDialog(null,"game is closed","fail", 1);
            	   out.println("msgmypage");
               }
               else if(line.startsWith("Msgproblemboard"))
               {
            	   new ProblemResult(socket,line);
               }
               else if(line.startsWith("problemwindow"))
               {
            	   new ProblemBoard(socket);
               }
               else if(line.startsWith("Msgrecommend"))
               {
            	   new Category(socket,line);
               }      
               else if(line.startsWith("Msgevaluation"))
               {
            	   new evaluation(socket,line);
               }
               else if(line.startsWith("Msgquestionboard"))
               {
            	  new QuestionBoard(socket,line);
               }
               else if(line.startsWith("Msgproblem"))
               {
            	   new ProblemField(socket,line);
               }
               else if(line.startsWith("Msganswerlist"))
               {
            	   new AnswerField(socket,line);
               }
               else if(line.startsWith("Msggetanswer"))
               {
            	   new ShowAnswer(socket,line);
               }               
               else {}
        	}
        }
    }

    public static void main(String[] args) throws Exception {
        client client = new client();
        client.run();
    }
}