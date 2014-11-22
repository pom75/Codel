package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.NewContactService;

/**
 * Servlet implementation class login
 */
public class NewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//FIXME
		String fname = null;//request.getParameter("fname");
		String lname = null;//request.getParameter("lname");
		String email = null;//request.getParameter("email");


		NewContactService.addContact(fname, lname, email, null, null, 0);
		getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

}