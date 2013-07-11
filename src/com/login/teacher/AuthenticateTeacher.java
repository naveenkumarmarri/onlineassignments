package com.login.teacher;

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
 * Servlet implementation class AuthenticateTeacher
 */
@WebServlet("/AuthenticateTeacher")
public class AuthenticateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID=request.getParameter("userName");
		String passwrd=request.getParameter("password");
		  String url = "jdbc:mysql://localhost:3306/naveen";
			PrintWriter out=response.getWriter();
		    Connection conn = null;
		    Statement stmt = null;
		    CallableStatement call = null; int count=0;
		    try {
		        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		        conn = DriverManager.getConnection(url, "root", "naveen");
		        String query = "SELECT * FROM TEACHERLOGIN";
		        stmt = conn.createStatement();
		        ResultSet rst = stmt.executeQuery(query);  
		        while(rst.next()) 
		        {
		
		            if ((rst.getString(1).compareTo(userID)==0)&& (rst.getString(2).compareTo(passwrd)==0)){
		            	HttpSession session = request.getSession(true);
		          	  session.setAttribute("userID",userID);
		            	response.sendRedirect("/student/teachersuccess.jsp");
		            count=1;}
		            else
		            	continue;
		        
		        }
		        if (count==0)
		        		response.sendRedirect("/student/relogin.jsp");
		       
		    } catch (SQLException sqle) {
		        out.println("Error Connecting: " + sqle+"");
		    } catch (Exception e) {
		        out.println("Error Connecting: " + e+"");
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


