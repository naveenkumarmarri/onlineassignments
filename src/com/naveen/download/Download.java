package com.naveen.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/mp3");
		ServletContext ctx=getServletContext();
		InputStream is=ctx.getResourceAsStream("/navin.mp3");
		
		int read=0;
		byte[] bytes=new byte[1024];
		OutputStream os=response.getOutputStream();
		while((read=is.read(bytes))!=-1)
		{
			os.write(bytes,0,read);
			
		}
		os.flush();
		os.close();
	}

}
