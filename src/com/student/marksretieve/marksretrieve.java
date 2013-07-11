package com.student.marksretieve;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class marksretrieve
 */
@WebServlet("/marksretrieve")
public class marksretrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String userID=(String) session.getAttribute("userID");
		String rollnum=(String) session.getAttribute("rollnum");
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		String url = "jdbc:mysql://localhost:3306/naveen";
		PrintWriter out=response.getWriter();
	    Connection conn = null;
	    Statement stmt = null;
	    CallableStatement call = null; 
	    try {
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	        conn = DriverManager.getConnection(url, "root", "naveen");
	        String query = "SELECT * FROM marks where rollnum='"+rollnum+"'";
	        stmt = conn.createStatement();
	        ResultSet rst = stmt.executeQuery(query);	 
	        pw.println("<CENTER>");
	        pw.println("<br/>");
	        pw.println("congrats "+userID+" your marks are");
	        pw.println("<br/>");
	        while(rst.next()) 
	        {	      
	        	String mark=rst.getString(3);
	        	if(mark.compareTo("-1")==0)
	        	{
	        		mark="absent";
	        	}
	        
	         pw.println(" "+rst.getString(2)+   "               "+mark);
	         pw.println("<br/>");
	        }       
	       pw.println("</CENTER>");
	    } catch (SQLException sqle) {
	        out.println("Error Connecting: " + sqle.getMessage()+"");
	    } catch (Exception e) {
	        out.println("Error Connecting: " + e.getMessage()+"");
	    } finally {
	        try {
	            if (stmt != null)
	                stmt.close();
	            if (call != null)
	                call.close();
	            if (conn != null)
	                conn.close();
	        } catch (SQLException sqle) {
	            sqle.printStackTrace();
	        }
	    }
	}

}
