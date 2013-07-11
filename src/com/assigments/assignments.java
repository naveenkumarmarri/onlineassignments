package com.assigments;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Date.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class assignments
 */
@WebServlet("/assignments")
public class assignments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out=response.getWriter();
		
		String connectionURL = "jdbc:mysql://127.0.0.1:3306/naveen";// newData is the database
	    Connection connection;
	    try{
	    	String date=request.getParameter("deadline");
	    String title=request.getParameter("title");
	      String description = request.getParameter("description");
	      
	      Class.forName("com.mysql.jdbc.Driver");
	      connection = DriverManager.getConnection(connectionURL, "root", "naveen");
	      PreparedStatement pst = connection.prepareStatement("insert into assignments (date,title,description) values (?,?,?)");
	      pst.setString(1,date);
	      pst.setString(2,title);
	      pst.setString(3,description);      
	     

	      int i = pst.executeUpdate();
	      if(i!=0){
	        out.println("<br>Record has been inserted<br>");
	       out.println("<a href=");
	       out.println("http://localhost:8080/student/assignments.html");
	       out.println(">");
	       		out.println("Give some more assignments for students</a>");

	      }
	      else{
	        out.println("failed to insert the data");
	       }
	    }
	    catch (Exception e){
	      out.println(e);
	    }
	  }
	
		
	}



