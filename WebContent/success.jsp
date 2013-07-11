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
Welcome <%=session.getAttribute("userID")%><br/><br/>
your roll number is <%=session.getAttribute("rollnum")%>
<br/>
<br/>
<br/>
<br/>


<a href="/student/ViewAssignments.jsp">click here to see the assignments given</a> 
<form method="post" action="marksretrieve">
<input type="submit" name="submit" value="see marks">
</center>
</form>
</body>
</html>