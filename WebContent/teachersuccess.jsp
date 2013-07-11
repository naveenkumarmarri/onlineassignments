<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Successful login</title>
</head>
<body>
<center>
Welcome <%=session.getAttribute("userID")%>
<br/>
<br/>
<br/>
<br/>
<a href="/student/assignments.html">give assignments</a> 
<br/><br/><br/><a href="/student/marks.html">add marks</a> <br/><br/>
<a href="viewFiles.jsp">View Files</a>
</center>
</body>
</html>