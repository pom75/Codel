package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.service.LoginService;

/**
 * Servlet implementation class login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static Logger log = Logger.getLogger("LoginServlet");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean root = LoginService.checkLogin(login, password);

		if (root) {
			log.info("" + login + " sucessfully connected");
			getServletContext().getRequestDispatcher("/accueil.jsp").forward(
					request, response);

		} else {
			log.info("Failed attempt to log with id" + login);
			getServletContext().getRequestDispatcher("/login.jsp").forward(
					request, response);
			// maybe: can add information about the failed attempt
		}
	}

}