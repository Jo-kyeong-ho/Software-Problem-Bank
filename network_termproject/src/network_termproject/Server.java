package network_termproject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;
import java.io.*;

 
public class Server {

    private static final int PORT = 9001;//port number 
	public static Connection conn;
	public static Statement stmt = null;
	public static BufferedReader inputStream = null;
    public static PrintWriter outputStream = null;
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    //writer manage by hash
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);//make socket
		try {//prepare JDBC use
			Class.forName("com.mysql.jdbc.Driver"); // MySQL driver load
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","12345"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); 
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver load error");
		} catch (SQLException e) {
			System.out.println("SQL excution error");
		}
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
                    if(input.startsWith("Msggetproblem"))
                    {
                    	String st1,st2;
                    	String query;
                    	String p_txtname;
                    	String a_txtname;
                    	String imgname;
                    	String l;
                    	int count1=0;
                        
                    	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
                    	st1=token.nextToken();
                    	st1=token.nextToken();//get problem name
                    	st1="'"+st1+"'";                    	
                    	query="select problem_name, uploader_id, grade, level, form from problemdb where problem_id = "+st1;
                    	//make query message
                    	ResultSet srs = stmt.executeQuery(query);
                    	st2=srs.getString("problem_name");
                    	st2=st2+"/"+srs.getString("uploader_id");
                    	st2=st2+"/"+srs.getString("grade");
                    	st2=st2+"/"+srs.getString("level");
                    	st2=st2+"/"+srs.getString("form");
                    	out.println(st2);
                    	//use mysql to find each txt,img's name
                    	query="select p_txt, a_txt, img from problemdb where problem_id = "+st1;
                    	srs=stmt.executeQuery(query);
                    	p_txtname=srs.getString("p_txt");
                    	a_txtname=srs.getString("a_txt");
                    	imgname=srs.getString("img");
                    	//problem.txt transfer
                    	inputStream = new BufferedReader(new FileReader(p_txtname));
                    	while ((l = inputStream.readLine()) != null) {
                            count1++;
                        }
                    	out.println(count1);
                    	inputStream.close();
                    	inputStream = new BufferedReader(new FileReader(p_txtname));
                    	while ((l = inputStream.readLine()) != null) {
                            out.println(l);
                        }                    	
                    	count1=0;
                    	inputStream.close();
                    	//answer.txt transfer
                    	inputStream = new BufferedReader(new FileReader(a_txtname));
                    	while ((l = inputStream.readLine()) != null) {
                            count1++;
                        }
                    	out.println(count1);
                    	inputStream.close();
                    	inputStream = new BufferedReader(new FileReader(a_txtname));
                    	while ((l = inputStream.readLine()) != null) {
                            out.println(l);
                        }
                    	inputStream.close();
                    	// img transfer
                    	byte buffer[] = new byte[2048];
                  	    File imgfile = new File(imgname);
                  	    String flen = String.valueOf(imgfile.length());
                  	    // change "1234" to "0000001234", to make sure 10 size.
                  	    String header = "0000000000".substring(0, 10-flen.length())+flen;
                  	    FileInputStream fis = new FileInputStream(imgfile);
                  	    OutputStream os = socket.getOutputStream();
                  	    // send header
                  	    os.write(header.getBytes());
                  	    // send body
                  	    while (fis.available() > 0) {
                  	      int readsz = fis.read(buffer);
                  	      os.write(buffer, 0, readsz);
                  	    }
                  	    os.close();
                  	    fis.close();
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (SQLException e) {
            	System.out.println("SQL excution error");
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