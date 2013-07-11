package com.teacher.marksentry;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Entry
 */
@WebServlet("/Entry")
public class Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] marks=request.getParameterValues("rollno");
		String courseID= request.getParameter("courseID");
		PrintWriter out=response.getWriter();
		int n=marks.length;
		int [] mark=new int[n];
		for(int i=0;i<n;i++)
		{
			mark[i]=Integer.parseInt(marks[i]);
		}
		int [] rollnum=new int[10];
	     rollnum[0]=10906001;
	     for(int j=1;j<10;j++)
	     {
	    	 rollnum[j]=rollnum[j-1]+1;
	     }
		String connectionURL = "jdbc:mysql://127.0.0.1:3306/naveen";
	    Connection connection;
	    Statement stmt=null;
	    try{
	      Class.forName("com.mysql.jdbc.Driver");
	      connection = DriverManager.getConnection(connectionURL, "root", "naveen");
	      String query="select * from marks where courseID='"+courseID + "'";
	      stmt=(Statement) connection.createStatement();
	      ResultSet rst=stmt.executeQuery(query);
	      while(rst.next())
	      {
	    	  if(rst.next())
	    	  {
	    		  response.sendRedirect("duplicate.html");
	    	  }
	      }
	     int total=0;
	      for(int j=0;j<rollnum.length;j++)
	      {
	      PreparedStatement pst = connection.prepareStatement("insert into marks (rollnum,courseID,marks) values (?,?,?)");
	      pst.setInt(1,rollnum[j]);
	      pst.setString(2,courseID);
	      pst.setInt(3,mark[j]);      
	      pst.executeUpdate();
	      total=j;
	      }
	      
	    }
	    catch (Exception e){
	     out.println(e.getMessage());
	    }
	  }
}
