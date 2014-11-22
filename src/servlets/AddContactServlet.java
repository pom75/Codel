package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 * FIXME This is login!!!
 */
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Trop petit traitement pour utiliser un service
		
		if (request.getParameter("login").equals(request.getParameter("password"))) {
			getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}