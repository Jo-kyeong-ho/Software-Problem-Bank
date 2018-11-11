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
    private static ResultSet rs;
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    //writer manage by hash
    
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);//make socket
      try {//prepare JDBC use
         Class.forName("com.mysql.jdbc.Driver"); // MySQL driver load
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","1234"); // JDBC 연결
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
        private String[] login;
        private String[] join;
       
        private String msg;
        int index=0;
        
        
        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {//thread excution code
            try 
            {

                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                writers.add(out);
                String ID;
                String Password;
                String query;
                while (true) {
                	System.out.println("login page");
                	out.println("msglogin"); // login protocol
                	msg=in.readLine();
                	String st;
                	if(msg.startsWith("msgjoin"))
                	{
                		while(true)
                		{
                			
                			join=msg.split("/");
                			st="'"+join[2]+"'";
                			query="select id from user where id="+st;
                			rs=stmt.executeQuery(query);
                			
                			if(rs.next())// 레코드가잇으면 true 없으면 false리턴함
                			{
                				out.println("msgjoin/fail");
                				
                			}
                			else
                			{
                				out.println("msgjoin/ok");
                				break;
                				
                			}
                		}
                	}
                	login=msg.split("/");
                	st="'"+login[1]+"'";
                	ID="select id FROM user WHERE id="+st;
                	Password="select password FROM user WHERE id="+st;
                	rs=stmt.executeQuery(ID);
                	if(rs.next())
                	{
                		String str;
                		rs=stmt.executeQuery(Password);
                		str=rs.getString("passowrd");
                		if(str.equals(login[2]))
                		{
                			
                    		out.println("msglogin/ok");
                    		break;

                		}
                	}
                	else
                	{
                		out.println("msglogin/fail");
                	}
                	
                }
  // -------------------- login & Join -----------------------------
                while (true) 
                {
                   check=0;
                    String input = in.readLine();//receive message from client 
                    if(input.startsWith("Msgmypage"))
                    {
                    	int count1=0;
                    	String l;
                    	String introduce_txt;
                    	String st1,st2;
                    	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
                        st1=token.nextToken();
                        st1=token.nextToken();//get ID
                        st1="'"+st1+"'"; 
                    	query="select id, name, totalComment, selectComment, point, rank from user where id="+st1;
                    	rs=stmt.executeQuery(query);
                    	st2=rs.getString("id");
                        st2=st2+"/"+rs.getString("name");
                        st2=st2+"/"+rs.getString("totlaComment");
                        st2=st2+"/"+rs.getString("selectComment");
                        st2=st2+"/"+rs.getString("point");
                        st2=st2+"/"+rs.getString("rank");
                        out.println(st2);

                        query ="select introduce from user where id="+st1;
                        
                        introduce_txt=rs.getString("introduce");
                        inputStream = new BufferedReader(new FileReader(introduce_txt));
                        while ((l = inputStream.readLine()) != null) {
                             count1++;
                         }
                        out.println(count1);
                        inputStream.close();
                        inputStream = new BufferedReader(new FileReader(introduce_txt));
                        while ((l = inputStream.readLine()) != null) {
                             out.println(l);
                         }                       
                        count1=0;
                        inputStream.close();
                    }
                    
                    if(input.startsWith("Msgchangeintroduce")) //잘 모르겟음...
                    { 
                    	String[] changeMyPage;
                    	String st1,st2;
                    	changeMyPage=input.split("/");
                    	st1="'"+changeMyPage[1]+"'";
                    	st2="'"+changeMyPage[2]+"'";
                    	
                    	query="update user set introduce="+st2+"where id="+st1;
                    	
                    }
                    if(input.startsWith("Msgproblemsearch")) // ?
                    {
                    	String[] search;
                    	search=input.split("/");
                    }
                    if(input.startsWith("Msgprobelmupload"))
                    {
                    	
                    }
                    if(input.startsWith("Msgcategory"))
                    {
                    	
                    }
                    /*
                     * ...
                     * 
                     */
                    if(input.startsWith("Msggetproblem"))
                    {
                       String st1,st2;
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
                    if(input.startsWith("Msgevaluate"))
                    {
                    	String[] problem;
                    	String sql;
                    	problem=input.split("/");
                    	String st="'"+problem[0]+"'";
                    	sql="update problemdb set evaluator=evaluator+1 where problem_name="+st;
                    	rs=stmt.executeQuery(sql);
                    	sql="update problemdb set grade=(grade+"+problem[1]+") / evaluator where problem_name="+st;
                    	rs=stmt.executeQuery(sql);
                    	sql="update problemdb set level=(level+"+problem[2]+") / evaluator where problem_name="+st;
                    	rs=stmt.executeQuery(sql);
                    	
                    }
                    if(input.startsWith("Msgquestionboard")) //?
                        
                    {
                    	String[] questionBoard;
                    	
                    }
                    if(input.startsWith("Msggetquestion")) //?
                        
                    {
                    	String[] question;
                    	question=input.split("/");
                    	String st="'"+question[1]+"'";
                    	String sql="select problem_id,name,uploader_id,form from problemdb where id="+st;
                    	rs=stmt.executeQuery(sql);
                    	st="Msgproblem";
                    	st=st+"/"+rs.getString("problem_id");
                    	st=st+"/"+rs.getString("name");
                        st=st+"/"+rs.getString("uploader_id");
                        st=st+"/"+rs.getString("form");
                        out.println(st);
                        /*
                         *  ???
                         */
                    	
                    }
                    if(input.startsWith("Msgrecommendplus"))
                    {
                    	String[] plus;
                    	plus=input.split("/");
                    	String st="'"+plus[1]+"'";
                    	String sql="select recommender_id from questiondb where recommender_id="+st;
                    	rs=stmt.executeQuery(sql);
                    	if(rs.next())
                    	{
                    		out.println("already exists");
                    	}
                    	else
                    	{
                    		sql="update questiondb set recommend=recommend+1";
                    		rs=stmt.executeQuery(sql);
                    	}
                    	//답변자 ID는 그냥안햇습니다 왜냐하면 자기거를 자기가 추천해도 2번 추천할려고하면 안되서 자기거 추천하는것 정도는 허용해도 된다고 생각해서요
                    	
                    }
                    if(input.startsWith("Msgrecommendminus"))
                    {
                    	String[] minus;
                    	minus=input.split("/");
                    	String st="'"+minus[1]+"'";
                    	String sql="select recommender_id from questiondb where recommender_id="+st;
                    	rs=stmt.executeQuery(sql);
                    	if(rs.next())
                    	{
                    		out.println("already exists");
                    	}
                    	else
                    	{
                    		sql="update questiondb set recommend=recommend-1";
                    		rs=stmt.executeQuery(sql);
                    	}
                    	
                    }
                    if(input.startsWith("Msggoodanswer"))
                    {
                    	String[] good;
                    	good=input.split("/");
                    	String st="'"+good[1]+"'";
                    	/*
                    	 * ???
                    	 */
                    }
                    if(input.startsWith("Msggetanswerlist"))
                    {
                    	String[] list;
                    	list=input.split("/");
                    	String st="'"+list[1]+"'";
                    	String sql="select question_name,answer_id,recommend,ok from questiondb where uploader_id="+st;
                    	rs=stmt.executeQuery(sql);
                    	st="Msganswerlist";
                    	st=st+"/"+rs.getString("question_name");
                    	st=st+"/"+rs.getString("answer_id");
                        st=st+"/"+rs.getString("recommend");
                        st=st+"/"+rs.getString("ok");
                        out.println(st);
                    }
                    if(input.startsWith("Msggetanswer"))
                    {
                    	String[] answer;
                    	answer=input.split("/");
                    	String st="'"+answer[1]+"'";
                    	String st2="'"+answer[2]+"'";
                    	String sql="select question_id, answer_id,uploader_id from answerdb where question_id="
                    	+st+" and answer_id="+st2;
                    	rs=stmt.executeQuery(sql);
                    	st="Msggetanswer";
                    	st=st+"/"+rs.getString("question_id");
                    	st=st+"/"+rs.getString("answer_id");
                        st=st+"/"+rs.getString("uploader_id");
                        
                        out.println(st);
                    	/*
                    	 * ???
                    	 */
                    	
                    }
                    if(input.startsWith("Msgputanswer"))
                    {
                    	String[] answer;
                    	answer=input.split("/");
                    	String st="'"+answer[1]+"'";
                    	String st2="'"+answer[2]+"'";
                    	
                    	/*
                    	 * I don't know transfer to txt file and input textfile to db 
                    	 */
                    	
                    }
                    
                    
                }
            }
        
             catch (IOException e) {
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