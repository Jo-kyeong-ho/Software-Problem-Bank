package network_termproject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.imageio.ImageIO;

import java.sql.*;
import java.lang.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import cc.mallet.types.*;
import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.*;
import cc.mallet.topics.*;
import cc.mallet.util.Randoms;

import java.util.regex.*;

 
public class server {
	//duplicate code's explain is omitted.
    private static final int PORT = 9003;//port number 
	public static Connection conn;
	public static Statement stmt = null;
	public static int problem_total;
	public static int question_total;
	public static int question_max;
	public static int answer_max;
	public static int gset=0;
	public static int firstcount=0;
	public static int gcount=1;
	public static String[] topic1=new String[7];
	public static String[] topic2=new String[7];
	public static String[] topic3=new String[7];
	public static String[] topic4=new String[7];
	public static String[] topic5=new String[7];
	public static String[] topic6=new String[7];
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    //writer manage by hash
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);//make socket
		try {//prepare JDBC use
			Class.forName("com.mysql.jdbc.Driver"); // MySQL driver load
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root","12345"); // JDBC connect
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); 
			ResultSet srs=stmt.executeQuery("select max(question_id) from questiondb");//53~84 setting basic data
			while(srs.next())
			{
				question_total=srs.getInt("max(question_id)");
				System.out.println(question_total);
			}
			question_total++;
			srs=stmt.executeQuery("select max(question_id) from questiondb");
			while(srs.next())
			{
				question_max=srs.getInt("max(question_id)");
				System.out.println(question_max);
			}
			srs=stmt.executeQuery("select max(answer_id) from answerdb");
			while(srs.next())
			{
				answer_max=srs.getInt("max(answer_id)");
			}
			answer_max++;
			System.out.println(answer_max);
			srs=stmt.executeQuery("select max(problem_id) from problemdb");
			while(srs.next())
			{
			problem_total=srs.getInt("max(problem_id)");
			}
			problem_total++;
			System.out.println(problem_total);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver load error");
		} catch (SQLException e) {
			System.out.println("SQL excution error");
		}
        try {
        	new Timer().start();
        	new Settopic().start();
            while (true) {
                new Handler(listener.accept()).start();//accept-wait connection and start thread
            }
        } finally {
            listener.close();//delete socket
        }
    }
    private static class Timer extends Thread{
    	/*description: control game open and close
    	 * input: null
    	 * output: null
    	 */
    	public void run()
    	{
    		while(true)
    		{
    			try {    				
					Thread.sleep(3300000);// wait 55 minutes
					gset=1;
					Thread.sleep(300000);// open while 5 minutes
					gset=0;
					firstcount=0;					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}    	
    }
    private static class Settopic extends Thread{
    	/*description: run machinrunning(topic modeling) in certain time
    	 * input: null
    	 * output: null
    	 */
    	public void run()
    	{
    		while(true)
    		{
    			TopicModeling k=new TopicModeling();//use topicmodeling code
    			try {
    				k.a();
    				topic1=k.get1();
    				topic2=k.get2();
    				topic3=k.get3();
    				topic4=k.get4();
    				topic5=k.get5();
    				topic6=k.get6();
    				Thread.sleep(3600000);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
    }
    private static class Handler extends Thread {
    	public BufferedReader inputStream = null;
        public PrintWriter outputStream = null;
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String def;
        private int check=0;
        public String user_id;
        public String fileuser_id;
        public int admin=0;
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
                	String input;
                	input=in.readLine();
                	/* description: confirm id and password 
                	 * input: message from client
                	 * output: if it is correct user id->send client "msglogin/okay"
                	 *         if it is manager id->send client "msgadmin/okay"
                	 *         if it is false -> send client "msglogin/fail"
                	 */
                  	if(input.startsWith("msglogin"))
                  	{               
                    	String id;
                    	String password;
                  		String query;
                  		StringTokenizer token=new StringTokenizer(input,"/");// analyze message
                    	id=token.nextToken();
                    	id=token.nextToken();
                    	System.out.println(id);
                    	password=token.nextToken();
                    	System.out.println(password);
                    	if(id.equals("admin12345"))
                    	{
                    		if(password.equals("12345"))
                    		{
                    			System.out.println("okay");
                    			out.println("msgadmin/okay");
                    			admin=1;
                    			break;
                    		}
                    		else {
                    			System.out.println("fail");
                				out.println("msglogin/fail");
                    		}
                    	}
                    	else {
                    		fileuser_id=id;
                    		id="'"+id+"'";
                    		password="'"+password+"'";
                    		query="select user_id from userdb where user_id="+id;
                    		query=query+" and password = "+password;
                    		System.out.println(query);
                    		try {
                    			ResultSet srs=stmt.executeQuery(query);
                    			if(!srs.next())
                    			{
                    				System.out.println("fail");
                    				out.println("msglogin/fail");
                    			}
                    			else {
                    				System.out.println("okay");
                    				user_id=id;
                    				out.println("msglogin/okay");
                    				break;
                    			}
                    		}
                    		catch (SQLException e) {
                    			e.printStackTrace();
                    		}
                    	}
                  	}
                  	/* description: create new id and confirm id is already exist
                  	 * input: message startwith msgjoin and content of user id 
                  	 * output: if id is already exist-> "msgjoin/fail"
                  	 *         if create id is complete->"msgjoin/okay"
                  	 */
                  	if(input.startsWith("msgjoin"))
                	{
                		String id;
                    	String name;
                  		String password;
                  		String query;
                  		StringTokenizer token=new StringTokenizer(input,"/");
                  		id=token.nextToken();
                  		id=token.nextToken();
                  		name=token.nextToken();
                  		password=token.nextToken();
                  		id="'"+id+"'";
                  		query="select user_id from userdb where user_id = "+id;
                  		System.out.println(query);
                  		try {
                        	ResultSet srs=stmt.executeQuery(query);
                        	if(srs.next())
                        	{
                        		System.out.println("exist");
                        		out.println("msgjoin/fail");
                        	}
                        	else {
                        		System.out.println("not exist");
                        		out.println("msgjoin/okay");
                        		int a=0;
                        		query="insert into userdb values("+id+",'"+name+"','"+password+"',";
                        		query=query+a+","+a+","+a+","+a+","+a+",'null')";
                        		System.out.println(query);
                        		int k=stmt.executeUpdate(query);
                        	}
            			}
                    	catch (SQLException e) {
            				e.printStackTrace();
            			}
                	}
                 }
                if(admin==1)//manager mode
                {
                	String input;
                	input=in.readLine();
                	Msgadmin();
                	while(true)
                	{
                		System.out.println("asd");
                		input = in.readLine();
                		System.out.println(input);
                		if(input.startsWith("Msgadmin"))
                		{
                			Msgadmin();
                		}
                		if(input.startsWith("Msggetreport"))
                		{
                			Msggetreport(input);
                		}
                		if(input.startsWith("Msgreportcomplete"))
                		{
                			Msgreportcomplete(input);
                		}
                		if(input.startsWith("Msgreportdelete"))
                		{
                			Msgreportdelte(input);
                		}
                		if(input.startsWith("Msgiddelte"))
                		{
                			Msgiddelete(input);
                		}
                	}
                }
                else {// common user mode
                while (true) {
                	check=0;
                    String input = in.readLine();//receive message from client
                    System.out.println(input);
                    //control server system by analyze header
                    if(input.startsWith("msgmypage"))
                    {
                    	Msgmypage(input);
                    }
                    if(input.startsWith("msgyourpage"))
                    {
                    	Msgyourpage(input);
                    }
                    if(input.startsWith("Msgmyquestion"))
                    {
                    	Msgmyquestion(input);
                    }
                    if(input.startsWith("Msgmyproblem"))
                    {
                    	Msgmyproblem(input);
                    }
                    if(input.startsWith("Msgenterreport"))
                    {
                    	Msgenterreport(input);
                    }
                    if(input.startsWith("Msgplaygame"))
                    {
                    	Msgplaygame();
                    }
                    if(input.startsWith("Msgplaysubmit"))
                    {
                    	Msgplaysubmit(input);
                    }
                    if(input.startsWith("Msgchangeintroduce"))
                    {
                    	Msgchangeintroduce(input);
                    }
                    if(input.startsWith("Msgproblemsearch"))
                    {
                    	Msgproblemsearch(input);
                    }
                    if(input.startsWith("Msgproblemupload"))
                    {
                    	 Msgproblemupload(input);
                    }
                    if(input.startsWith("problemwindow"))
                    {
                    	out.println("problemwindow");
                    }
                    if(input.startsWith("Msgcategory"))
                    {
                    	Msgcategory(input);
                    }
                    if(input.startsWith("Msggetproblem"))
                    {
                    	Msggetproblem(input);                  	    
                    }
                    if(input.startsWith("Msgevaluate"))
                    {
                    	Msgevaluate(input);
                    }
                    if(input.startsWith("Msgquestionboard"))
                    {
                    	Msgquestionboard(input);
                    }
                    if(input.startsWith("Msggetquestion"))
                    {
                    	Msggetquestion(input);
                    }
                    if(input.startsWith("Msgquestionupload"))
                    {
                    	Msgquestionupload(input);
                    }
                    if(input.startsWith("Msgrecommendplus"))
                    {
                    	Msgrecommendplus(input);
                    }
                    if(input.startsWith("Msgrecommendminus"))
                    {
                    	Msgrecommendminus(input);
                    }
                    if(input.startsWith("Msggoodanswer"))
                    {
                    	Msggoodanswer(input);
                    }
                    if(input.startsWith("Msggetanswerlist"))
                    {
                    	Msggetanswerlist(input);
                    }
                    if(input.startsWith("Msggetanswer/"))
                    {
                    	Msggetanswer(input);
                    }
                    if(input.startsWith("Msgputanswer"))
                    {
                    	Msgputanswer(input);
                    }
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
        /* description: lookup database by userid and send client data related with mypage
         * input: message from client
         * output: start with msgmypage and send data related with mypage
         */
        public void Msgmypage(String input)
        {
        	String name="";
        	int total=0;
        	int selectnum=0;
        	int accumnum=0;
        	int rank=1;
        	int current_point = 0;
        	String query="";
        	String message="";
        	String introducetxt="";
        	query="select name,total_answer,countselect,accum_point,current_point,introduction_txtname from userdb where user_id = "+user_id;
        	try {
				ResultSet srs=stmt.executeQuery(query);
				while(srs.next())
				{
					name=srs.getString("name");
					total=srs.getInt("total_answer");
					selectnum=srs.getInt("countselect");
					accumnum=srs.getInt("accum_point");
					current_point=srs.getInt("current_point");
					introducetxt=srs.getString("introduction_txtname");
				}
				if(accumnum>150)
				{
					rank=2;
				}
				if(accumnum>300)
				{
					rank=3;
				}
				if(accumnum>500)
				{
					rank=4;
				}				
				message="msgmypage/"+user_id+"/"+name+"/"+total+"/"+selectnum+"/";
				message=message+current_point+"/"+accumnum+"/"+rank;      
				out.println(message);
				try {
					sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!introducetxt.equals("null"))//read introduction file and send to client 
				{
					String l = null;					
					int count = 0;
					inputStream = new BufferedReader(new FileReader(introducetxt));
					while ((l = inputStream.readLine()) != null) {
						count++;
	            }
					System.out.println(count);
					out.println(count);					
					inputStream.close();
					inputStream = new BufferedReader(new FileReader(introducetxt));
					while ((l = inputStream.readLine()) != null) {
						out.println(l);
					}  
				}
				else {
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.println("0");
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  	
        }
        /* description: lookup database by userid and send client data related with certain user's page
         * input: message from client
         * output: start with msgyourpage and send data related with page that user want
         */
        public void Msgyourpage(String input)
        {
        	String name="";
        	int total=0;
        	int selectnum=0;
        	int accumnum=0;
        	int rank=1;
        	int current_point = 0;
        	String your_id;
        	String query="";
        	String message="";
        	String introducetxt="";
        	StringTokenizer token=new StringTokenizer(input,"/");
        	your_id=token.nextToken();
        	your_id=token.nextToken();
        	query="select name,total_answer,countselect,accum_point,current_point,introduction_txtname from userdb where user_id = "+"'"+your_id+"'";
        	try {
				ResultSet srs=stmt.executeQuery(query);
				while(srs.next())
				{
					name=srs.getString("name");
					total=srs.getInt("total_answer");
					selectnum=srs.getInt("countselect");
					accumnum=srs.getInt("accum_point");
					current_point=srs.getInt("current_point");
					introducetxt=srs.getString("introduction_txtname");
				}
				if(accumnum>150)
				{
					rank=2;
				}
				if(accumnum>300)
				{
					rank=3;
				}
				if(accumnum>500)
				{
					rank=4;
				}				
				message="msgyourpage/"+your_id+"/"+name+"/"+total+"/"+selectnum+"/";
				message=message+current_point+"/"+accumnum+"/"+rank;      
				out.println(message);
				try {
					sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(!introducetxt.equals("null"))//read introduction file and send to client 
				{
					String l = null;					
					int count = 0;
					inputStream = new BufferedReader(new FileReader(introducetxt));
					while ((l = inputStream.readLine()) != null) {
						count++;
	            }
					System.out.println(count);
					out.println(count);					
					inputStream.close();
					inputStream = new BufferedReader(new FileReader(introducetxt));
					while ((l = inputStream.readLine()) != null) {
						out.println(l);
					}  
				}
				else {
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					out.println("0");
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  	
        }
        /* description: lookup reportdb database and send data to client related with managerpage(report list)
         * input:null
         * output: send data related with managerpage that starts Msgadmin
         */
        public void Msgadmin()
        {
        	String query;
        	String str="Msgadmin";
        	query="select problem_id,problem_name,reporter_id from reportdb";
        	ResultSet srs;
			try {
				System.out.println(query);
				srs = stmt.executeQuery(query);
				int count=1;
				while(srs.next())
				{					
					str=str+"/"+srs.getInt("problem_id");
		    		str=str+"/"+srs.getString("problem_name");
		    		str=str+"/"+srs.getString("reporter_id");
		    		if(count==7)
		    			break;
					count++;
					System.out.println(str);
				}				
	        	out.println(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }
        /* description: lookup problemdb database by problem_id and send data to client
         *              it send data that report's contents that client want to see.
         * input: message from client( it has problem_id that client want to get)
         * output: send data related that targeted problem.
         */
        public void Msggetreport(String input)
        {
        	String problem_id;
        	String reporter_id;
        	String query;
        	String p_txtname = null;
        	String r_txtname = null;
        	String imgname = null;
        	String l;
        	String str;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	reporter_id=token.nextToken();
        	str="Msggetreport/"+problem_id;

        	out.println(str);
        	problem_id="'"+problem_id+"'";
        	reporter_id="'"+reporter_id+"'";
        	query="select problem_txtname,problem_imgname from problemdb where problem_id = "+problem_id;
        	System.out.println(query);
        	ResultSet srs;
			try {
				srs = stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        		p_txtname=srs.getString("problem_txtname");
	        		imgname=srs.getString("problem_imgname");
	        	}
	        	query="select report_comment from reportdb where reporter_id="+reporter_id;
	        	srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        		r_txtname=srs.getString("report_comment");
;	        	}
	        	int count = 0;
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);	
				System.out.println(count);
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count=0;
				inputStream = new BufferedReader(new FileReader(r_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(r_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!imgname.equals("null"))// send image to client
				{
					out.println("imgokay");
					byte buffer[] = new byte[2048];
					File imgfile = new File(imgname);
					String flen = String.valueOf(imgfile.length());
					String header = "0000000000".substring(0, 10-flen.length())+flen;
					FileInputStream fis = new FileInputStream(imgfile);
					OutputStream os = socket.getOutputStream();
					os.write(header.getBytes());
					while (fis.available() > 0) {
						int readsz = fis.read(buffer);
						os.write(buffer, 0, readsz);
					}
				}
				else
				{
					out.println("imgfail");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}      	
        	int count = 0;
        }
        /* description: delete target report from reportdb database
         * input: header and report's problem_id that client want to delete
         * output: null
         * */
        public void Msgreportcomplete(String input)
        {
        	String problem_id;
        	String query="";
           	StringTokenizer token=new StringTokenizer(input,"/");
        	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	problem_id="'"+problem_id+"'";
        	query="delete from reportdb where problem_id = "+problem_id;
        	try {
        		synchronized(this)
        		{
				int check=stmt.executeUpdate(query);
        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        /* description: delete target report from reportdb database and problem from problemdb
         * input: header and target problem_id that client want to delte
         * output: null
         */
        public void Msgreportdelte(String input)
        {
        	String problem_id;
        	String query="";
           	StringTokenizer token=new StringTokenizer(input,"/");
           	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	problem_id="'"+problem_id+"'";
        	query="delete from reportdb where problem_id = "+problem_id;
        	try {
        		synchronized(this) {
				int check=stmt.executeUpdate(query);
				query="delete from problemdb where problem_id = "+problem_id;
				check=stmt.executeUpdate(query);
        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        /* description: delete target report from reportdb database, problem from problemdb and user from userdb
         * input: header and target problem_id that client want to delte
         * output: null
         */
        public void Msgiddelete(String input)
        {
        	String problem_id;
        	String query="";
        	String user_id = null;
           	StringTokenizer token=new StringTokenizer(input,"/");
           	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	problem_id="'"+problem_id+"'";
        	query="delete from reportdb where problem_id = "+problem_id;
        	try {
        		synchronized(this) {
				int check=stmt.executeUpdate(query);
				query="select upload_id from problemdb where problem_id = "+problem_id;
				ResultSet srs=stmt.executeQuery(query);
				while(srs.next())
				{
					user_id=srs.getString("upload_id");
				}				
				query="delete from problemdb where problem_id = "+problem_id;
				check=stmt.executeUpdate(query);
				query="delete from userdb where user_id = "+user_id;
        		}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }        
       /* description: confirm game is open or close and if game is opened send related data
        * input: null
        * output: send result that confirm game's state
        *         if game is opened send related data
        */
        public void Msgplaygame()
        {
        	int mod = 0;
        	String query="";
        	int problem_id=0;
        	String problem_name=null;
        	String imgname=null;
        	ResultSet srs;
        	System.out.println(gset);
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(gset==0)
        	{
        		out.println("Msggameclose");
        	}
        	else {
        	query="select count(problem_id) from problemdb where form = "+"'select'";
        	try {
				srs=stmt.executeQuery(query);
				while(srs.next())
				{
					mod=srs.getInt("count(problem_id)");
				}
	        	int i=1;
	        	query="select problem_id, problem_name from problemdb where form = "+"'select'";
	        	srs=stmt.executeQuery(query);
	        	if(gcount==mod)
	        		gcount=1;
	        	while(srs.next())
	        	{
	        		problem_id=srs.getInt("problem_id");
	        		problem_name=srs.getString("problem_name");
	        		if(gcount==i)
	        			break;
	        		i++;
	        	}
	        	out.println("Msgplaygame/"+problem_id+"/"+problem_name);
	        	String p_txtname = null;
	        	String l;
	        	query="select problem_txtname, problem_imgname from problemdb where problem_id="+problem_id;
	        	srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        		p_txtname=srs.getString("problem_txtname");
	        		imgname=srs.getString("problem_imgname");
	        	}
	        	int count = 0;
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}
				if(!imgname.equals("null"))
				{
					out.println("imgokay");
	        	byte buffer[] = new byte[2048];
	      	    File imgfile = new File(imgname);
	      	    String flen = String.valueOf(imgfile.length());
	      	    String header = "0000000000".substring(0, 10-flen.length())+flen;
	      	    FileInputStream fis = new FileInputStream(imgfile);
	      	    OutputStream os = socket.getOutputStream();
	      	    os.write(header.getBytes());
	      	    while (fis.available() > 0) {
	      	      int readsz = fis.read(buffer);
	      	      os.write(buffer, 0, readsz);
	      	    }
				}
				else
				{
					out.println("imgfail");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        }
        /* description: confirm client's answer is correct and how fast submitted.
         *              if it is fast submit and correct, user get point.
         * input: message from user(contain answer and problem's data)
         * output: send result to client
         */
        public void Msgplaysubmit(String input)
        {
        	String answer=null;
        	String l;
        	String query=null;
        	String problem_id=null;
        	String infilename=null;
        	String answer1=null;
        	String answer_check;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	query="select answer_txtname from problemdb where problem_id="+problem_id;
        	ResultSet srs;
        	try {
				srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        		infilename=srs.getString("answer_txtname");
	        	}        	
	        	inputStream = new BufferedReader(new FileReader(infilename));
	        	answer_check=inputStream.readLine();
	            inputStream.close();
				answer1=in.readLine();
				if(firstcount==5)
					out.println("fail");
				else {
					if(!answer1.equals(answer_check))
					{
						out.println("fail");
					}
					else
					{
						query="update userdb set current_point=current_point+50 where user_id="+user_id;// user get point
						int check=stmt.executeUpdate(query);
						query="update userdb set acum_point=acum_point+50 where user_id="+user_id;
						check=stmt.executeUpdate(query);
						out.println("okay");
						firstcount++;
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        /* description: lookup user's question in questiondb database
         * input : message from client(page that client want)
         * output : send list of question and data related with that question.
         */
        public void Msgmyquestion(String input)
        {
        	String page;
        	String query;
        	String str="Msgquestionboard";
        	int temp=0;
        	int page_i;
        	int check=0;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	page=token.nextToken();
        	page=token.nextToken();
        	page_i=Integer.parseInt(page);
        	str=str+"/"+page_i;
        	query="select question_id,question_name,large_subject,small_subject,form,user_id from questiondb where user_id="+user_id;
        	ResultSet srs;
        	String a;
			try {
				System.out.println(query);
				srs = stmt.executeQuery(query);
				System.out.println(query);
				int pagecount=1;
				int count=1;
				while(srs.next())
				{					
					if(pagecount==page_i)
					{
						str=str+"/"+srs.getInt("question_id");
		    			str=str+"/"+srs.getString("question_name");
		    			str=str+"/"+srs.getString("large_subject");
		    			str=str+"/"+srs.getString("small_subject");
		    			str=str+"/"+srs.getString("form");
		    			str=str+"/"+srs.getString("user_id");
					}
					if(count==7)
					{
						pagecount++;
						count=1;
					}
					count++;
					System.out.println(str);
				}				
	        	out.println(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }
        /* description: lookup user upload problem in problemdb database
         * input : message from client(page that client want)
         * output : send list of problem and data related with that problem.
         */
        public void Msgmyproblem(String input)
        {
        	String largesubject;
        	String smallsubject;
        	String page;
        	String query="";
        	String str="";
        	int page_i;
        	System.out.println(input);
        	StringTokenizer token=new StringTokenizer(input,"/");
        	page=token.nextToken();
        	page=token.nextToken();
        	page_i=Integer.parseInt(page);
        	str=str+"/"+page_i;
        	query="select problem_id,problem_name, evaluation, large_subject, small_subject, form, level from problemdb";
        	query=query+" where upload_id="+user_id;
        	str="Msgproblemboard/"+page_i;
        	ResultSet srs;
        	try {
				srs = stmt.executeQuery(query);
				System.out.println(query);
				int pagecount=1;
				int count=1;
				while(srs.next())
				{
					
					if(pagecount==page_i)
					{
						str=str+"/"+srs.getInt("problem_id");
						str=str+"/"+srs.getString("problem_name");
	        			str=str+"/"+srs.getInt("evaluation");
	        			str=str+"/"+srs.getString("large_subject");
	        			str=str+"/"+srs.getString("small_subject");
	        			str=str+"/"+srs.getString("form");
						str=str+"/"+srs.getInt("level");
		    			
					}
					if(count==7)
					{
						pagecount++;
						count=0;
					}
					count++;
					System.out.println(str);
				}         	
			} catch (SQLException e) {
				e.printStackTrace();
			}   
        	out.println(str);
        }
        /* description: store complain report that user submit
         * input: message from client( contents of report and data related with problem)
         * output: null
         */
        public void Msgenterreport(String input)
        {
        	String problem_id;
        	String problem_name;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	problem_id=token.nextToken();
        	problem_id=token.nextToken();
        	problem_name=token.nextToken();
        	int count;
        	String query1="";
        	String filename;
        	String temp = null;
        	filename="C:\\\\\\\\software\\\\\\\\report\\\\\\\\"+fileuser_id+problem_id+".txt";   
        	query1="insert into reportdb values ("+problem_id+",'"+problem_name+"',"+user_id+",'"+filename+"')"; 
        	try {
				temp=in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	count=Integer.parseInt(temp);
        	try {				
	    		outputStream = new PrintWriter(new FileWriter(filename));
	    		for(int i=0;i<count;i++)
	    		{
	    			outputStream.println(in.readLine());
	    		}	    		
	        	outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}        	      	
        	try {
        		System.out.println(query1);
        		synchronized(this) {
				int check=stmt.executeUpdate(query1);
        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
        /*description: change the introduction file 
         * input: changed introduction content
         * output: null
         */
        public void Msgchangeintroduce(String input)
        {
        	String query;
        	String content;
        	String filename="";
        	int count_int;
        	String count = null;
        	String query1="";
        	try {
				count=in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	count_int=Integer.parseInt(count);        	
        	filename="'C:\\\\\\\\software\\\\\\\\introduce\\\\\\\\"+fileuser_id+".txt'";   
        	query1="update userdb set introduction_txtname="+filename;       	
        	try {
				int k=stmt.executeUpdate(query1);
				query1="select introduction_txtname from userdb where user_id="+user_id;		
	        	ResultSet srs=stmt.executeQuery(query1);
	        	while(srs.next())
	        	{
	        		filename=srs.getString("introduction_txtname");
	        		System.out.println(filename);
	        	}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}        	
			try {				
	    		outputStream = new PrintWriter(new FileWriter(filename));
	    		for(int i=0;i<count_int;i++)
	    		{
	    			outputStream.println(in.readLine());
	    		}	    		
	        	outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        /*decription: search list of problem from datablase by subject that client select
         * input: message that client send(subject that user select and page)
         * output: send messge to client contain information of 7 problem
         */
        public void Msgproblemsearch(String input)
        {
        	String largesubject;
        	String smallsubject;
        	String page;
        	String query="";
        	String str="";
        	int page_i;
        	System.out.println(input);
        	StringTokenizer token=new StringTokenizer(input,"/");
        	largesubject=token.nextToken();
        	largesubject=token.nextToken();
        	smallsubject=token.nextToken();
        	largesubject="'"+largesubject+"'";
        	smallsubject="'"+smallsubject+"'";
        	page=token.nextToken();
        	page_i=Integer.parseInt(page);
        	str=str+"/"+page_i;
        	query="select problem_id,problem_name, evaluation, large_subject, small_subject, form, level from problemdb";
        	query=query+" where large_subject = "+largesubject+" and small_subject in (select small_subject from problemdb ";
        	query=query+"where small_subject = "+smallsubject+")";
        	str="Msgproblemboard/"+page_i;
        	ResultSet srs;
        	try {
				srs = stmt.executeQuery(query);
				System.out.println(query);
				int pagecount=1;
				int count=1;
				while(srs.next())
				{
					
					if(pagecount==page_i)
					{
						str=str+"/"+srs.getInt("problem_id");
						str=str+"/"+srs.getString("problem_name");
	        			str=str+"/"+srs.getInt("evaluation");
	        			str=str+"/"+srs.getString("large_subject");
	        			str=str+"/"+srs.getString("small_subject");
	        			str=str+"/"+srs.getString("form");
						str=str+"/"+srs.getInt("level");
		    			
					}
					if(count==7)
					{
						pagecount++;
						count=0;
					}
					count++;
					System.out.println(str);
				}	        	
	        	out.println(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	        
        }
        /*description: store problem and img in temporary store space and make recommend subjectlist
         * input: temporary problem,answer,img file
         * output: send recommend subject list to client
         */
        public void Msgproblemupload(String input)
        {
        	String uploader_id;
        	String filename="";
        	String imgname="";
        	String afilename="";
        	String problem_name;
        	int imgset;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	problem_name=token.nextToken();
        	problem_name=token.nextToken();    	
        	
        	filename=fileuser_id+".txt";
        	String temp="";
        	try {
				temp=in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	int count;
        	count=Integer.parseInt(temp);
        	try {				
	    		outputStream = new PrintWriter(new FileWriter(filename));
	    		for(int i=0;i<count;i++)
	    		{
	    			outputStream.println(in.readLine());
	    		}	    		
	        	outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	try {
				temp=in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	count=0;
        	filename=fileuser_id+"answer.txt";
        	count=Integer.parseInt(temp);
        	System.out.println(count);
        	try {				
	    		outputStream = new PrintWriter(new FileWriter(filename));
	    		for(int i=0;i<count;i++)
	    		{
	    			outputStream.println(in.readLine());
	    		}	    		
	        	outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	String c = null;
        	System.out.println("check");
        	try {
				c=in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	imgset=0;
        	if(c.equals("okay"))
        	{
        	imgset=1;
        	FileOutputStream fos;
    		try {
    			fos = new FileOutputStream(fileuser_id+".jpg");
    			 InputStream is = socket.getInputStream();
    			 byte buffer[] = new byte[2048];
    			 // read header(10 bytes)
    			 System.out.println("c");
    			 is.read(buffer, 0, 10);
    			 String header = new String(buffer, 0, 10);
    			 int bodysize = Integer.parseInt(header);
    			 int readsize = 0;
    			 // read body
    			 while (readsize < bodysize) {
    			   int rsize = is.read(buffer);
    			   fos.write(buffer, 0, rsize);
    			   readsize += rsize;
    			 }
    			 //is.close();
    			 //fos.close();
    			 System.out.println("check2");
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	}
        	try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String l;
        	int c1=0;
        	int c2=0;
        	int c3=0;
        	int c4=0;
        	int c5=0;
        	int c6=0;
        	String[] large=new String[3];
        	String[] small = new String[3];
        	for(int i=1;i<6;i++)
        	{
        		System.out.println(topic1[i]);
        	}
        	try {
        		inputStream = new BufferedReader(new FileReader(fileuser_id+".txt"));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);					
					for(int i=1;i<6;i++)
					{
						if(l.contains(topic1[i]))
						{
							c1++;
						}
						if(l.contains(topic2[i]))
						{
							c2++;
						}
						if(l.contains(topic3[i]))
						{
							c3++;
						}
						if(l.contains(topic4[i]))
						{
							c4++;
						}
						if(l.contains(topic5[i]))
						{
							c5++;
						}
						if(l.contains(topic6[i]))
						{
							c6++;
						}						
					}
				}
	            inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            int max=0;
            for(int i=0;i<3;i++)
            {
            	max=0;
            	int maxset=0;
            	if(c1>max)
            	{
            		max=c1;
            		maxset=1;
            	}
            	if(c2>max)
            	{
            		max=c2;
            		maxset=2;
            	}
            	if(c3>max)
            	{
            		max=c3;
            		maxset=3;
            	}
            	if(c4>max)
            	{
            		max=c4;
            		maxset=4;
            	}
            	if(c5>max)
            	{
            		max=c5;
            		maxset=5;
            	}
            	if(c6>max)
            	{
            		max=c6;
            		maxset=6;
            	}
            	if(maxset==1)
            	{
            		StringTokenizer token1=new StringTokenizer(topic1[0],"/");
            		large[i]=token1.nextToken();
            		small[i]=token1.nextToken();
            		c1=0;
            	}
            	if(maxset==2)
            	{
            		StringTokenizer token2=new StringTokenizer(topic2[0],"/");
            		large[i]=token2.nextToken();
            		small[i]=token2.nextToken();
            		c2=0;
            	}
            	if(maxset==3)
            	{
            		StringTokenizer token3=new StringTokenizer(topic3[0],"/");
            		large[i]=token3.nextToken();
            		small[i]=token3.nextToken();
            		c3=0;
            	}
            	if(maxset==4)
            	{
            		StringTokenizer token4=new StringTokenizer(topic4[0],"/");
            		large[i]=token4.nextToken();
            		small[i]=token4.nextToken();
            		c4=0;
            	}
            	if(maxset==5)
            	{
            		StringTokenizer token5=new StringTokenizer(topic5[0],"/");
            		large[i]=token5.nextToken();
            		small[i]=token5.nextToken();
            		c5=0;
            	}
            	if(maxset==6)
            	{
            		StringTokenizer token6=new StringTokenizer(topic6[0],"/");
            		large[i]=token6.nextToken();
            		small[i]=token6.nextToken();
            		c6=0;
            	}          	
            	
            }
        	out.println("Msgrecommend/"+problem_name+"/"+imgset+"/"+large[0]+"/"+small[0]+"/"+large[1]+"/"+small[1]+"/"+large[2]+"/"+small[2]);
        }
        /*description: store temporary file to proper space and update problemdb database and update point of user
         * input: problem's detail content that client select
         * output: null
         */
        public void Msgcategory(String input)
        {
        	String largesubject;
        	String smallsubject;
        	String form;
        	String l;
        	String tempfilename="";
        	String infilename;
        	String outfilename;
        	String inafilename;
        	String outafilename;
        	String inimgfilename;
        	String outimgfilename;
        	String problemname;
        	String query;
        	String imgset;
        	System.out.println("confirm Msgcategory");
        	StringTokenizer token=new StringTokenizer(input,"/");
        	largesubject=token.nextToken();
        	largesubject=token.nextToken();
        	smallsubject=token.nextToken();
        	problemname=token.nextToken();
        	form=token.nextToken(); 
        	imgset=token.nextToken();
        	infilename=fileuser_id+".txt";
        	outfilename="C:\\\\\\\\software\\\\\\\\problem\\\\\\\\"+largesubject+smallsubject+"\\\\\\\\"+problemname+".txt";
        	try {
				inputStream = new BufferedReader(new FileReader(infilename));
				outputStream = new PrintWriter(new FileWriter(outfilename));
	        	while ((l = inputStream.readLine()) != null) {
	        		outputStream.println(l);
	            }
	            inputStream.close();
	            outputStream.close();
	            inafilename=fileuser_id+"answer.txt";
	        	outafilename="C:\\\\\\\\software\\\\\\\\problem2\\\\\\\\"+largesubject+smallsubject+"\\\\\\\\"+problemname+"ans.txt";
	        	inputStream = new BufferedReader(new FileReader(inafilename));
	        	outputStream = new PrintWriter(new FileWriter(outafilename));
	        	while ((l = inputStream.readLine()) != null) {
	        		outputStream.println(l);
	            }
	            inputStream.close();
	            outputStream.close();
	            System.out.println(imgset);
	            if(imgset.equals("1"))
	            {	
	            Image image;
	            String imgFormat ="jpg";
	            inimgfilename=fileuser_id+".jpg";
	            outimgfilename="C:\\\\\\\\software\\\\\\\\problem2\\\\\\\\"+largesubject+smallsubject+"\\\\\\\\"+problemname+"img.jpg";
	            image = ImageIO.read(new File(inimgfilename));
	            BufferedImage newImage = new BufferedImage(315, 329, BufferedImage.TYPE_INT_RGB);
	            Graphics g = newImage.getGraphics();
	            g.drawImage(image, 0, 0, null);
	            g.dispose();
	            ImageIO.write(newImage, imgFormat, new File(outimgfilename));
	            }
	            else {
	            	inimgfilename="null";
	            	outimgfilename="null";
	            }
	            System.out.println(problem_total);
	      	    query="insert into problemdb values ("+problem_total+",'"+problemname+"',"+user_id+","+"0,0,0,'";
	      	    query=query+largesubject+"','"+smallsubject+"','"+form+"',0,0,0,'"+outfilename+"','"+outimgfilename+"','"+outafilename+"')";
	      	    System.out.println(query);
	      	  synchronized(this) {
	      	    int a=stmt.executeUpdate(query);
	      	    problem_total++;
	            query="update userdb set current_point = current_point + 50 where user_id = "+user_id;
	            System.out.println(query);
	            a=stmt.executeUpdate(query);
	            System.out.println(query);
	            query="update userdb set acum_point = acum_point + 50 where user_id = "+user_id;
	            System.out.println(query);
	      	  }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
        }
        /*description: determine user's point and search problem and send related content to client
         * input: problem's id
         * output: send message that problem's content
         */
        public void Msggetproblem(String input)
        {
        	String str = null;
        	String query;
        	String problem_id;
        	String p_txtname = null;
        	String a_txtname = null;
        	String imgname = null;
        	String l=null;
        	int point=0;
        	ResultSet srs;
        	
        	query="select current_point from userdb where user_id = "+user_id;
        	try {
				srs=stmt.executeQuery(query);
				while(srs.next())
				{
					point=srs.getInt("current_point");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(point>20)
        	{        	
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	problem_id=token.nextToken();
        	problem_id=token.nextToken();//get problem name
        	System.out.println(problem_id);
        	query="select problem_id, problem_name, upload_id, evaluation, form, level from problemdb where problem_id = "+problem_id;
        	//make query message
        	
        	try {
				srs = stmt.executeQuery(query);
				System.out.println(query);
				while(srs.next())
				{
				str="Msgevaluation"+"/"+srs.getInt("problem_id");
	        	str=str+"/"+srs.getString("problem_name");
	        	str=str+"/"+srs.getString("upload_id");
	        	str=str+"/"+srs.getInt("evaluation");
	        	str=str+"/"+srs.getString("form");
	        	str=str+"/"+srs.getInt("level");	        	
				}
	        	out.println(str);
	        	try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	        	
	        	query="select problem_txtname, problem_imgname, answer_txtname from problemdb where problem_id="+problem_id;	        	
	        	srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        	p_txtname=srs.getString("problem_txtname");
	        	imgname=srs.getString("problem_imgname");
	        	a_txtname=srs.getString("answer_txtname");
	        	}
	        	System.out.println(p_txtname);
	        	System.out.println(imgname);
	        	//problem.txt transfer	        					
				int count = 0;
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count=0;
				inputStream = new BufferedReader(new FileReader(a_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(a_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				query="update userdb set current_point = current_point-20 where user_id="+user_id;
				synchronized(this) {
				int check=stmt.executeUpdate(query);
				}
				if(!imgname.equals("null"))
				{
					out.println("imgokay");
	        	byte buffer[] = new byte[2048];
	      	    File imgfile = new File(imgname);
	      	    String flen = String.valueOf(imgfile.length());
	      	    String header = "0000000000".substring(0, 10-flen.length())+flen;
	      	    FileInputStream fis = new FileInputStream(imgfile);
	      	    OutputStream os = socket.getOutputStream();
	      	    os.write(header.getBytes());
	      	    while (fis.available() > 0) {
	      	      int readsz = fis.read(buffer);
	      	      os.write(buffer, 0, readsz);
	      	    }
	      	    //os.close();
	      	    //fis.close();
				}
				else
				{
					out.println("imgfail");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	}
        	else {
        		out.println("Msgevaluation/fail");
        	}
        	System.out.println("finish");       	
        }
        /*description use evaluate data and update problem database
         * input: client's evaluate data about problem 
         * output: null
         */
        public void Msgevaluate(String input)
        {
        	String query="";
        	String p_id;
        	String evaluate;
        	String level;
        	int evaluate_int;
        	int level_int;
        	int eval_count = 0,level_count = 0,eval_acum = 0,level_acum = 0;
        	float result;
        	int result_i;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	p_id=token.nextToken();
        	p_id=token.nextToken();//get problem name
        	evaluate=token.nextToken();
        	level=token.nextToken();        	
        	query="select eval_acum, eval_count, level_acum,level_count from problemdb where problem_id="+p_id;
        	System.out.println(query);
        	ResultSet srs;
			try {
				srs = stmt.executeQuery(query);
				while(srs.next())
				{
				eval_acum=srs.getInt("eval_acum");
	        	eval_count=srs.getInt("eval_count");
	        	level_acum=srs.getInt("level_acum");
	        	level_count=srs.getInt("level_count");
				}
	        	evaluate_int=Integer.parseInt(evaluate);
	        	level_int=Integer.parseInt(level);
	        	eval_acum=eval_acum+evaluate_int;
	        	eval_count++;
	        	level_acum=level_acum+level_int;
	        	level_count++;
	        	result=eval_acum/eval_count;
	        	System.out.println(eval_acum+"   "+eval_count+"    "+result);
	        	if(result%1>=0.5)
	        	{
	        		result=result/1+1;
	        	}
	        	else {
	        		result=result/1;
	        	}
	        	result_i=(int) result;
	        	synchronized(this) {
	        	query="update problemdb set evaluation="+result_i+" where problem_id="+p_id;
	        	int check=stmt.executeUpdate(query);
	        	query="update problemdb set eval_acum ="+eval_acum+" where problem_id="+p_id;
	        	check=stmt.executeUpdate(query);
	        	query="update problemdb set eval_count ="+eval_count+" where problem_id="+p_id;
	        	check=stmt.executeUpdate(query);
	        	result=level_acum/level_count;
	        	if(result%1>=0.5)
	        	{
	        		result=result/1+1;
	        	}
	        	else {
	        		result=result/1;
	        	}
	        	result_i=(int) result;
	        	query="update problemdb set level="+result_i+" where problem_id="+p_id;
	        	check=stmt.executeUpdate(query);
	        	query="update problemdb set level_acum ="+level_acum+" where problem_id="+p_id;
	        	check=stmt.executeUpdate(query);
	        	query="update problemdb set level_count ="+level_count+" where problem_id="+p_id;
	        	check=stmt.executeUpdate(query);
	        	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        /*description: get question's list from questiondb 
         * input: message of page and header(Msgquestionboard)
         * output: send question's list and related data
         */
        public void Msgquestionboard(String input)
        {
        	String page;
        	String query;
        	String str="Msgquestionboard";
        	int temp=0;
        	int page_i;
        	int check=0;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	page=token.nextToken();
        	page=token.nextToken();
        	page_i=Integer.parseInt(page);
        	str=str+"/"+page_i;
        	query="select question_id,question_name,large_subject,small_subject,form,user_id from questiondb";
        	ResultSet srs;
        	String a;
			try {
				System.out.println(query);
				srs = stmt.executeQuery(query);
				System.out.println(query);
				int pagecount=1;
				int count=1;
				while(srs.next())
				{					
					if(pagecount==page_i)
					{
						str=str+"/"+srs.getInt("question_id");
		    			str=str+"/"+srs.getString("question_name");
		    			str=str+"/"+srs.getString("large_subject");
		    			str=str+"/"+srs.getString("small_subject");
		    			str=str+"/"+srs.getString("form");
		    			str=str+"/"+srs.getString("user_id");
					}
					if(count==7)
					{
						pagecount++;
						count=1;
					}
					count++;
					System.out.println(str);
				}				
	        	out.println(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }
        /*description: lookup question in questiondb and get file of question. send question's data to client
         *input: message of client(contain question_id)
         *output: send question's data to client
         */
        public void Msggetquestion(String input)
        {
        	int question_id;
        	String id_temp;
        	String query;
        	String str="";
        	String p_txtname = null;
        	String imgname = null;
        	int count1=0;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	id_temp=token.nextToken();
        	id_temp=token.nextToken();
        	question_id=Integer.parseInt(id_temp);
        	System.out.println(question_id);
        	query="select question_id, question_name, user_id, form from questiondb where question_id="+question_id;
        	System.out.println(query);
        	ResultSet srs;    
			try {
				srs = stmt.executeQuery(query);
				System.out.println(query);
				while(srs.next())
				{
				str="Msgproblem"+"/"+srs.getInt("question_id");
	        	str=str+"/"+srs.getString("question_name");
	        	str=str+"/"+srs.getString("user_id");
	        	str=str+"/"+srs.getString("form");
				}
	        	out.println(str);
	        	try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	        	
	        	query="select question_txtname, question_imgname from questiondb where question_id="+question_id;
	        	srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        	p_txtname=srs.getString("question_txtname");
	        	imgname=srs.getString("question_imgname");
	        	}
	        	System.out.println(p_txtname);
	        	System.out.println(imgname);
	        	//problem.txt transfer	        	
				String l = null;					
				int count = 0;
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
	            }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!imgname.equals("null"))
				{
					out.println("imgokay");
	        	byte buffer[] = new byte[2048];
	      	    File imgfile = new File(imgname);
	      	    String flen = String.valueOf(imgfile.length());
	      	    String header = "0000000000".substring(0, 10-flen.length())+flen;
	      	    FileInputStream fis = new FileInputStream(imgfile);
	      	    OutputStream os = socket.getOutputStream();
	      	    os.write(header.getBytes());
	      	    while (fis.available() > 0) {
	      	      int readsz = fis.read(buffer);
	      	      os.write(buffer, 0, readsz);
	      	    }
	      	    //os.close();
	      	    //fis.close();
				}
				else {
					out.println("imgfail");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	System.out.println("finish");
        }
        /*description: store question that sended by client and update qestiondb database
         * input:question's file and data related with question
         * output:null
         */
        public void Msgquestionupload(String input)
        {
        	String filename="";
        	String imgname="";
        	String question_name;
        	String large;
        	String small;
        	String form;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	question_name=token.nextToken();
        	question_name=token.nextToken();
        	large=token.nextToken();
        	small=token.nextToken();
        	form=token.nextToken();
        	filename="C:\\\\\\\\software\\\\\\\\question\\\\\\\\"+question_name+".txt";
        	imgname="C:\\\\\\\\software\\\\\\\\question\\\\\\\\"+question_name+"img.jpg";
        	String temp="";
        	try {
				temp=in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	int count;
        	count=Integer.parseInt(temp);
        	try {				
	    		outputStream = new PrintWriter(new FileWriter(filename));
	    		for(int i=0;i<count;i++)
	    		{
	    			outputStream.println(in.readLine());
	    		}	    		
	        	outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	System.out.println(filename);      	
        	String c = null;
        	try {
				c=in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	if(c.equals("okay"))
        	{
        	FileOutputStream fos;
    		try {
    			fos = new FileOutputStream(imgname);
    			 InputStream is = socket.getInputStream();
    			 byte buffer[] = new byte[2048];
    			 // read header(10 bytes)
    			 is.read(buffer, 0, 10);
    			 String header = new String(buffer, 0, 10);
    			 int bodysize = Integer.parseInt(header);
    			 int readsize = 0;
    			 // read body
    			 while (readsize < bodysize) {
    			   int rsize = is.read(buffer);
    			   fos.write(buffer, 0, rsize);
    			   readsize += rsize;
    			 }
    			 //is.close();
    			 //fos.close();
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        	}
        	else {
        		imgname="null";
        	}
        	String query;
        	synchronized(this) {
        	query="insert into questiondb values("+question_total+",'"+question_name+"','"+large+"','"+small+"','";
        	query=query+form+"','"+fileuser_id+"','"+filename+"','"+imgname+"')";
        	question_total++;
        	System.out.println(query);
        	}
        	try {
				int check=stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       	
        }
        /*description: plus 1 to certaion answer's recommend and update relate database
         * input: data that related with answer
         * output:null
         */
        public void Msgrecommendplus(String input)
        {
        	String question_id;
        	String answerperson_id;
        	String questioner_id = null;
        	String recommender_id;
        	String query;
        	String answer_id;
        	String dbname="";
        	int next_id=0;
        	int check=0;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	answerperson_id=token.nextToken();
        	recommender_id=user_id;
        	
        	ResultSet srs;
			try {
				srs = stmt.executeQuery("select question_id, user_id from questiondb where question_id = "+question_id);
				int q_id = 0;
				while(srs.next())
	        	{
					q_id=srs.getInt("question_id");
	        		questioner_id=srs.getString("user_id");
	        	}
	        	dbname=q_id+questioner_id;
	        	System.out.println(dbname);
	        	query="select recommender from "+dbname+" where recommender="+recommender_id;
	        	while(srs.next())
	        	{
	        		check=1;
	        	}
	        	if(check!=1)
	        	{
	        		query="select answer_id, recommend from answerdb where question_id = "+question_id+" and answerperson_id in";
	            	query=query+"(select answerperson_id from answerdb where answerperson_id = "+answerperson_id+")";
	            	srs=stmt.executeQuery(query);
	            	int k=0;
	            	int id = 0;
	            	while(srs.next())
	            	{
	            		id=srs.getInt("answer_id");
	            		k=srs.getInt("recommend");	            		
	            	}
	            	k++;
	            	synchronized(this) {
	            	query="update answerdb set recommend = "+k+" where answer_id = "+id;
	            	k=stmt.executeUpdate(query);
	            	
	            	query="insert into "+dbname+" values ("+user_id+","+"'null'"+")";
	            	k=stmt.executeUpdate(query);
	            	}
	        	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
        /*description:minus 1 to certaion answer's recommend and update relate database
         *input: data that related with answer
         * output:null
         */
        public void Msgrecommendminus(String input)
        {
        	String question_id;
        	String answerperson_id;
        	String questioner_id = null;
        	String derecommender_id;
        	String query;
        	String answer_id;
        	String dbname="";
        	int next_id=0;
        	int check=0;
        	StringTokenizer token=new StringTokenizer(input,"/");//divide by '/'
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	answerperson_id=token.nextToken();
        	derecommender_id=user_id;
        	
        	ResultSet srs;
			try {
				srs = stmt.executeQuery("select question_id, user_id from questiondb where question_id = "+question_id);
				int q_id = 0;
				while(srs.next())
	        	{
					q_id=srs.getInt("question_id");
	        		questioner_id=srs.getString("user_id");
	        	}
	        	dbname=q_id+questioner_id;
	        	System.out.println(dbname);
	        	query="select recommender from "+dbname+" where recommender="+derecommender_id;
	        	while(srs.next())
	        	{
	        		check=1;
	        	}
	        	if(check!=1)
	        	{
	        		query="select answer_id, derecommend from answerdb where question_id = "+question_id+" and answerperson_id in";
	            	query=query+"(select answerperson_id from answerdb where answerperson_id = "+answerperson_id+")";
	            	srs=stmt.executeQuery(query);
	            	int k=0;
	            	int id = 0;
	            	while(srs.next())
	            	{
	            		id=srs.getInt("answer_id");
	            		k=srs.getInt("derecommend");	            		
	            	}
	            	k++;
	            	synchronized(this) {
	            	query="update answerdb set derecommend = "+k+" where answer_id = "+id;
	            	k=stmt.executeUpdate(query);
	            	
	            	query="insert into "+dbname+" values ("+"'null'"+","+user_id+")";
	            	k=stmt.executeUpdate(query);
	            	}
	        	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
        /* descripton: make certain answer to selectanswer and update relate database
         * input: data related with that answer
         * output: null
         */
        public void Msggoodanswer(String input)
        {
        	String question_id;
        	String answerperson_id;
        	String query;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	answerperson_id=token.nextToken();
        	int id=0;
        	query="select answer_id from answerdb where question_id = "+question_id+"and answerperson_id in";
        	query= query+"(select answerperson_id from answerdb where answerperson_id = "+answerperson_id+")";
        	System.out.println(query);
        	ResultSet srs;
			try {
				srs = stmt.executeQuery(query);
				while(srs.next())
	        	{
	        		id=srs.getInt("answer_id");
	        	}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}        	
        	query="update answerdb set choice = 1 where answer_id="+id;
        	System.out.println(query);
        	int check;
			try {
				synchronized(this) {
				check = stmt.executeUpdate(query);
				query="update userdb set accum_point = accum_point+50 where user_id = "+answerperson_id;
	        	check=stmt.executeUpdate(query);
	        	query="update userdb set current_point = current_point+50 where user_id = "+answerperson_id;
	        	check=stmt.executeUpdate(query);
	        	query="update userdb set countselect = countselect+1 where user_id = "+answerperson_id;
	        	System.out.println(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        /*description: lookup answer's list related certain question and send it to client
         * input: question's id and related data
         * output: send client answer's list and relate data
         */
        public void Msggetanswerlist(String input)
        {
        	String question_id;
        	String answerperson_id;
        	int recommend=0;
        	int derecommend=0;
        	int choice=0;
        	String page;
        	String query;
        	String str="";
        	int page_i;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	page=token.nextToken();
        	question_id="'"+question_id+"'";
        	page_i=Integer.parseInt(page);
        	query="select question_id,question_name,answerperson_id,recommend,derecommend,choice from answerdb where question_id="+question_id;
        	ResultSet srs;
        	str="Msganswerlist"+"/"+page;
			try {
				srs = stmt.executeQuery(query);
				System.out.println(query);
				int pagecount=1;
				int count=1;
				while(srs.next())
				{
					
					if(pagecount==page_i)
					{
						str=str+"/"+srs.getInt("question_id");
						str=str+"/"+srs.getString("question_name");
	        			str=str+"/"+srs.getString("answerperson_id");
	        			recommend=srs.getInt("recommend");
	        			str=str+"/"+recommend;
	        			derecommend=srs.getInt("derecommend");
	        			str=str+"/"+derecommend;                
	        			choice=srs.getInt("choice");
	        			str=str+"/"+choice;
		    			
					}
					if(count==7)
					{
						pagecount++;
						count=0;
					}
					count++;
					System.out.println(str);
				}	        	
	        	out.println(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }
        /*description: look up answer's file and relate data from database and send it to client
         * input: data that contain question_id and answerperon_id etc..
         * output: send answer's file and relate data to client
         */
        public void Msggetanswer(String input)
        {
        	String question_id;
        	String answerperson_id;
        	String a_txtname = null;
        	String str="";
        	String questionperson_id = null;
        	String imgname="null";
        	String query="";
        	String check_id = null;
        	int count1=0;
        	int imgset=0;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	answerperson_id=token.nextToken();
        	question_id="'"+question_id+"'";
        	answerperson_id="'"+answerperson_id+"'";
        	ResultSet srs;
        	String p_txtname = null;
        	query="select user_id,question_txtname,question_imgname from questiondb where question_id = "+question_id;        	
        	try {
        		srs=stmt.executeQuery(query);
        		while(srs.next())
        		{
				check_id=srs.getString("user_id");
				p_txtname=srs.getString("question_txtname");
				imgname=srs.getString("question_imgname");
				System.out.println(p_txtname);
        		}
				if(check_id.equals(fileuser_id))
				{
					check_id="1";
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 		
        	if(!imgname.equals("null"))
        	{
        		imgset=1;
        	}
        	query="select answerperson_id, answer_txtname from answerdb where question_id = "+question_id+" and answerperson_id in";
        	query=query+"(select answerperson_id from answerdb where answerperson_id = "+answerperson_id+")";
			try {
				srs = stmt.executeQuery(query);
				while(srs.next())
				{
				questionperson_id=srs.getString("answerperson_id");
	        	a_txtname=srs.getString("answer_txtname");
				}				
				System.out.println(a_txtname);
	        	str="Msggetanswer/"+question_id+"/"+answerperson_id+"/"+check_id+"/"+imgset;
	        	System.out.println(str);
	        	int count = 0;
	        	String l;
				out.println(str);
	        	System.out.println(str);
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					count++;
		        }
				System.out.println("              "+count);
				out.println(count);					
				inputStream.close();
				inputStream = new BufferedReader(new FileReader(p_txtname));
				while ((l = inputStream.readLine()) != null) {
					System.out.println(l);
					out.println(l);
				}  				
	        	
	        	inputStream = new BufferedReader(new FileReader(a_txtname));	        	
	        	while ((l = inputStream.readLine()) != null) {
	                count1++;
	            }
	        	out.println(count1);
	        	inputStream.close();
	        	inputStream = new BufferedReader(new FileReader(a_txtname));
	        	while ((l = inputStream.readLine()) != null) {
	        		System.out.println(l);
	                out.println(l);
	            }
	        	inputStream.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
        }
        /*description: upload answer to relate database and store answerfile
         * input: answer file and related data
         * output: null
         */
        public void Msgputanswer(String input)
        {
        	String question_id;
        	String answerperson_id;
        	String answer;
        	String question_name;
        	String count;
        	String query="";
        	String filename="";
        	int count_int;
        	StringTokenizer token=new StringTokenizer(input,"/");
        	question_id=token.nextToken();
        	question_id=token.nextToken();
        	question_name=token.nextToken();
        	count=token.nextToken();
        	count_int=Integer.parseInt(count);
        	System.out.println(input);
        	question_name="'"+question_name+"'";
        	filename="'C:\\\\\\\\software\\\\\\\\answer\\\\\\\\"+question_id+fileuser_id+".txt'";
        	try {
        		int k=0;
        		int check_double=0;
        		query="select answerperson_id from answerdb where question_id = "+question_id;
        		ResultSet srs=stmt.executeQuery(query);
        		while(srs.next())
        		{
        			String s=srs.getString("answerperson_id");
        			if(s.equals(fileuser_id))
        			{
        				check=1;
        			}
        		}
        		if(check!=1)
        		{
        			query="insert into answerdb values ("+answer_max+","+question_id+","+question_name+","+user_id+","+k+","+k+","+k+","+filename+")";
        			System.out.println(query);
        			question_max++;
        			int check=stmt.executeUpdate(query);	
        		}
	            query="select answer_txtname from answerdb where question_id="+question_id+" and answerperson_id in ";
	            query=query+"(select answerperson_id from answerdb where answerperson_id = "+user_id+")";
	            System.out.println(query);
	        	srs=stmt.executeQuery(query);
	        	while(srs.next())
	        	{
	        		filename=srs.getString("answer_txtname");
	        		System.out.println(filename);
	        	}
				outputStream = new PrintWriter(new FileWriter(filename));
				String l;
	            for(int i=0;i<count_int;i++) {
	            	System.out.println("check");
	                outputStream.println(in.readLine());
	            }	          
	            outputStream.close();
	            query="update userdb set accum_point = point+10 where user_id = "+user_id;
		        check=stmt.executeUpdate(query);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	synchronized(this) {
        	query="create table "+question_id+fileuser_id+"( recommender varchar(20), derecommender varchar(20))";
            System.out.println(query);
            try {
				check=stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        }
    }
}