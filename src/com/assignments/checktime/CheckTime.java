package com.assignments.checktime;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckTime
 */
@WebServlet("/CheckTime")
public class CheckTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String url = "jdbc:mysql://localhost:3306/naveen"; 
			
		    Connection conn = null;
		    Statement stmt = null;
		    CallableStatement call = null;

	        PrintWriter out=response.getWriter();
		    try {
		        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		        conn = DriverManager.getConnection(url, "root", "naveen");

		        String i= request.getParameter("number");
		        String query = "SELECT * FROM assignments ";
		        stmt = conn.createStatement();
		        int k=0;
		        Date d1=new Date();
		        ResultSet rst = stmt.executeQuery(query);
		        DateFormat [] dfa=new DateFormat[1];
		        dfa[0]=DateFormat.getInstance();
		        String d=dfa[0].format(d1);
		        
		        String [] month2=d.split("/");
		        String [] day2=month2[2].split(" ");
		        String [] time2=day2[1].split(":");
		        String [] format2={month2[0],month2[1],day2[0],day2[2],time2[0],time2[1]};
		        int hrSystem=Integer.parseInt(format2[4]);	
		        if(format2[3].equals("PM")){
		        	if(format2[4].equals("12")){
		        		k=1;
		        	}
		        	else{
		        		
		        	hrSystem=hrSystem+12;
		        }
		        }
		        
		        int count=0;
		        while(rst.next()) 
		        {
		        	if((rst.getString(1).compareTo(i))==0)
		        	{
		        		String date=rst.getString(2);
		        		 String [] month = date.split("-");
		        		 String[] day=month[2].split("T");
		        		 String[] time=day[1].split(":");
		        		String []format={month[0],month[1],day[0],time[0],time[1]};
		        		
		        		int yearInput=Integer.parseInt(format[0]);
		        		out.println("yr input is "+yearInput);
		        		int yearSystem=Integer.parseInt(format2[2]);
		        		yearSystem +=2000;
		        		out.println("yr system is "+yearSystem);
	    				int monthInput=Integer.parseInt(format[1]);
	    				out.println("month input is "+monthInput);
		          		int monthSystem=Integer.parseInt(format2[0]);
		          		out.println("month system is "+monthSystem);
	       		  		int dayInput=Integer.parseInt(format[2]);
	       		  	out.println("day input is "+dayInput);
		        		int daySystem=Integer.parseInt(format2[1]);
		        		out.println("day system is "+daySystem);
		        		int hrInput=Integer.parseInt(format[3]);
		        		out.println("hr input is "+hrInput);
		        		
		        		out.println("hr systsem is "+hrSystem);
		        		int minInput=Integer.parseInt(format[4]);
		        		out.println("min input is "+minInput);
		        		int minSystem=Integer.parseInt(format2[5]);		
		        		out.println("min System is "+minSystem);
		        		if(minInput==0){
		        			minInput=60;
		        		}
		        		if(yearSystem<yearInput)
		        		{
		        			response.sendRedirect("page.jsp");
		        		}
		        		else if(yearSystem==yearInput)
		        		{
		        			if(monthSystem<monthInput)
		        			{
		        				response.sendRedirect("page.jsp");
		        			}
		        			else if(monthSystem==monthInput)
		        			{
		        				if(daySystem<dayInput)
		        				{
		        					response.sendRedirect("page.jsp");
		        				}
		        				else if(daySystem==dayInput)
		        				{
		        					if(hrSystem<hrInput)
		        					{
		        						response.sendRedirect("page.jsp");
		        					}
		        					else if(hrSystem==hrInput)
		        					{
		        						if(minSystem<=minInput)
		        						{
		        							response.sendRedirect("page.jsp");
		        						}
		        						else
		        							response.sendRedirect("errorupload.jsp");
		        					}
		        					else
	        							response.sendRedirect("errorupload.jsp");
		        				}
		        				else
        							response.sendRedirect("errorupload.jsp");
		        			}
		        			else
    							response.sendRedirect("errorupload.jsp");
		        		}
		        		else
							response.sendRedirect("errorupload.jsp");
		
		        }	
		        }
		    }catch (SQLException sqle) {
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
