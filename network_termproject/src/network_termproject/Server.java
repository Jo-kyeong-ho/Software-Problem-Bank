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

    private static final int PORT = 9001;//port number 설정

    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    //writer hash로관리
    
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);//소켓생성
        try {
            while (true) {
                new Handler(listener.accept()).start();//accept에서 연결대기 후 연결시 thread시작
            }
        } finally {
            listener.close();//소켓제거
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

        public void run() {//thread 실행코드
            try {

                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                writers.add(out);
                while (true) {
                	//login부분
                	break;//지우고쓰면됨 unreachable오류 막으려고넣음
                }
                while (true) {
                	check=0;
                    String input = in.readLine();//client로 부터 메세지수신            	
                    if(input.startsWith("msg"))
                    {
                    	//이런식구현
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out!=null) {
                    writers.remove(out);
                }
                try {
                    socket.close();//소켓종료
                } catch (IOException e) {
                }
            }
        }
    }
}