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
        String serverAddress = ""; //server주소넣는부분
        Socket socket = new Socket(serverAddress, 9001);//소켓생성
        int check=0;
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
        	if(check==0)
        	{
        		//login 창  구현
        		check=1;
        	}
        	else 
        	{
               String line = in.readLine();//서버로부터 입력받음
               if (line.startsWith("msgloginok")) {//statsWith=문자열 앞이 일치하면 true 아니면 false
            	   
            	   
               }             

 
        	}
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//frame이 닫힐때 프로그램도 종료되게 설정
        client.frame.setVisible(true);//보이게설정
        client.run();
    }
}