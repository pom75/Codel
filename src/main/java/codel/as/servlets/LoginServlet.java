package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.service.LoginService;
import codel.as.util.PathUtils;

/**
 * Servlet implementation class login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private static Logger log = Logger.getLogger("LoginServlet");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher(PathUtils.LOGIN_PAGE).forward(request,
				response);
	}

	
	// TODO Make a filter!!
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean root = LoginService.checkLogin(login, password);

		if (root) {
			log.info("" + login + " sucessfully connected");
			getServletContext().getRequestDispatcher(PathUtils.ACCUEIL).forward(
					request, response);

		} else {
			log.info("Failed attempt to log with id" + login);
			getServletContext().getRequestDispatcher(PathUtils.LOGIN_PAGE).forward(
					request, response);
			// maybe: can add information about the failed attempt
		}
	}

}