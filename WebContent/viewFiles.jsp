<%@ page import="java.io.*"%>
<html>
<table>
<tr><th>File Name</th><th>Download File</th></tr>
<%
File f = new File("F:/prjct/webapps/uploadeddata");
        File[] files = f.listFiles();
        for(int i=0;i<files.length;i++){
            String name=files[i].getName();
            String path=files[i].getPath();
%>
<tr><td><%=name%></td><td><a href="download.jsp?f=<%=path%>">Download File</a></td></tr>
     <%
        }
%>
</table>
</html>