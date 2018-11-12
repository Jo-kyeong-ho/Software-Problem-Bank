package network_termproject;


public class join {
	
    private String id;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPwd() {
        return pwd;
    }


    public void setPwd(String pwd) {
        this.pwd = pwd;
    }




    public String toString() {
    	System.out.println("test");
    	System.out.println("test2");
        return "MemberDTO [id=" + id + ", pwd=" + pwd+"]" ;
        
    }


}


 
