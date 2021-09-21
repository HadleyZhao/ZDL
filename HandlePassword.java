package handle.data;
import java.sql.*;
import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HandlePassword extends HttpServlet 
{
	public void init(ServletConfig config) throws ServletException { 
		
	      super.init(config);
	      try {  Class.forName("com.mysql.jdbc.Driver");
	      }
	      catch(Exception e){} 
	   }
	   public String handleString(String s)
	   {
		   try{ byte bb[]=s.getBytes("UTF-8");
	            s=new String(bb);
	       }
	       catch(Exception ee){} 
	       return s;  
	   }
	   
	    public  void  doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	 	   this.doPost(request,response); 
	 	   }
	    
	   
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	    	 String uri="jdbc:mysql://127.0.0.1:3306/onlineshopping?"+"user=root&password=123456&characterEncoding=UTF-8";
	         Connection con; 
	         PreparedStatement sql; 
	        String password = request.getParameter("password");
	        String again_password=request.getParameter("again_password");
	     	response.setContentType("text/html;charset=UTF-8");
	   	    HttpSession session=request.getSession(true);
	   	     	   	      	     	     	
	   	    //浼犲叆鍙傛暟
	        if(!password.equals(again_password)){	       
	        	PrintWriter out=response.getWriter();
	        	out.println("<html><body>");
	        	out.println("<h2>涓ゆ瀵嗙爜杈撳叆涓嶄竴鑷达紝瀵嗙爜淇敼澶辫触锛�<br></h2>");
	        	out.println("<a href=changepassword.jsp>杩斿洖</a>");
	        	out.println("</body></html>");
	        	return;
	        }
	        boolean boo=password.length()>0;
	        try{
	        	con=DriverManager.getConnection(uri);
	        	String change="update seller set SPassword=?";
	        	sql=con.prepareStatement(change);
	        	
	        	if(boo){
	        		
	        		sql.setString(1, handleString(password));
	        		int m=sql.executeUpdate();
	        		if(m!=0){
	        			RequestDispatcher dispatcher=request.getRequestDispatcher("change_sucess.jsp");//杞彂
	        			dispatcher.forward(request,response);
	        			}
	        		}
	        	else{	        		
	        		PrintWriter out=response.getWriter();
		        	out.println("<html><body>");
		        	out.println("<h2>瀵嗙爜淇敼澶辫触锛�<br></h2>");
		        	out.println("<a href=changepassword.jsp>杩斿洖</a>");
		        	out.println("</body></html>");
		        	return;
	        	}
	        
	        }
	        catch(SQLException exp){
	        	exp.printStackTrace();
	        }
	     	}
	    
	    
	
}			   
			   

