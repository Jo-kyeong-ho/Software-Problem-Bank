package network_termproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.*;


 
public class Server {

    private static final int PORT = 9001;//port number 

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    //writer hash·Î°ü¸®
    
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);//make socket
        try {
            while (true) {
                new Handler(listener.accept()).start();//accept-wait connection and start thread
            }
        } finally {
            listener.close();//delete socket
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String def;
        private int check=0;
        int index=0;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {//thread excution code
            try {

                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                writers.add(out);
                while (true) {
                	//login
                	break;//when you write code, delete it
                }
                while (true) {
                	check=0;
                    String input = in.readLine();//receive message from client            	
                    if(input.startsWith("msg"))
                    {
                    	//go
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out!=null) {
                    writers.remove(out);
                }
                try {
                    socket.close();//finish socket
                } catch (IOException e) {
                }
            }
        }
    }
}