package Servlet;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.subpolicebean;
import Imple.imple;
import Inter.inter;


/**
 * Servlet implementation class subpolicelogservlet
 */
@WebServlet("/subpolicelogservlet")
public class subpolicelogservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public subpolicelogservlet() {
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
		
		String zone=request.getParameter("zone");
		System.out.println("username:"+zone);
		
		String pcid=request.getParameter("pcid");
		System.out.println("password:"+pcid);
		
		String status="Activate";
		
		HttpSession sess=request.getSession();
		sess.setAttribute( "pcids",pcid);
		System.out.println("email: "+pcid);
		
		subpolicebean ur=new subpolicebean();
		ur.setZone(zone);
		ur.setPass(pcid);
		ur.setStatus(status);
		
		inter in=new imple();
		boolean log=in.spl(ur);
		
		if(log==true){
			response.sendRedirect("subpolicemain.jsp");
		}
		else{
			response.sendRedirect("error.jsp");
		}

		
		
		
	}

}
