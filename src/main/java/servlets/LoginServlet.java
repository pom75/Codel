package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;

/**
 * Servlet implementation class login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean root = LoginService.checkLogin(login, password);
				
		if (root) {
			getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			//maybe: can add information about the failed attempt
		}
	}

}