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


public class Client {


    static BufferedReader in;
    static PrintWriter out;

    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = ""; //server ip address
        Socket socket = new Socket(serverAddress, 9001);//make socket
        int check=0;
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
        	if(check==0)
        	{
        		//login window
        		check=1;
        	}
        	else 
        	{
               String line = in.readLine();//receive from server
               if (line.startsWith("msgloginok")) {//startWith *
            	   
            	   
               }             

 
        	}
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        //client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //when frame is close, window is close
        //client.frame.setVisible(true);//it set visible
        client.run();
    }
}