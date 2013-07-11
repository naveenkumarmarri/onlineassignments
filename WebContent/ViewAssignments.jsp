<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>List Students</TITLE>
</HEAD>
<BODY>

<table border><tr><th>number</th><th>Assignment Title</th><th>Assignment Description</th><th>Submission DeadLine</th></tr>
<% 

        String url = "jdbc:mysql://localhost:3306/naveen"; 
	
    Connection conn = null;
    Statement stmt = null;
    CallableStatement call = null;
    try {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        conn = DriverManager.getConnection(url, "root", "naveen");

        

        String query = "SELECT * FROM assignments";

        stmt = conn.createStatement();

        ResultSet rst = stmt.executeQuery(query);

        while(rst.next()) 
        {
            out.print("<tr><td>"+rst.getString(1) + "<td>");
            
            out.print(rst.getString(3) + "<td>");
            out.print(rst.getString(4) +"<td>");
            out.print(rst.getString(2) + "<td>");
        }
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

%>
</table>
<p></p><form action="CheckTime" method="post">
	Enter the Assignment number to Upload:<input type="text" name="number"><br>
	<input type="Submit" name="submit">
	</form>

</body>
</html>