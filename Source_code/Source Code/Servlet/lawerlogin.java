package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.lawerbean;
import Imple.imple;
import Inter.inter;

/**
 * Servlet implementation class lawerlogin
 */
@WebServlet("/lawerlogin")
public class lawerlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lawerlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("email");
		System.out.println("Username: "+uname);
		String pass=request.getParameter("password");
		System.out.println("password: "+pass);
		
		HttpSession sess=request.getSession();
		sess.setAttribute( "adid",uname);
		System.out.println("adid: "+uname);
		
		String status="Verified";
		
		lawerbean n=new lawerbean();
		n.setEmail(uname);
		n.setPassword(pass);
		n.setStatus(status);
		
		inter b=new imple();
		boolean c=b.log(n);
		if(c==true){
			response.sendRedirect("Lawermain.jsp");
		}
		else{
			response.sendRedirect("error.jsp");
		}
				


	}}

