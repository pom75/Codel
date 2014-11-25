package codel.as.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codel.as.util.AuthUtils;
import codel.as.util.PathUtils;
import static codel.as.util.AuthUtils.*;

/**
 * Servlet implementation class login
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger("LogoutServlet");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// TODO Make a filter!!
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Store secret in context
		ServletContext ctx = getServletContext();
		String secret = AuthUtils.newSecret();
		ctx.setAttribute(SECRET, secret);
		HttpSession session = request.getSession(true);
		session.setAttribute(SECRET, secret);

		boolean ok;
		// Session

		if (ctx.getAttribute(SECRET).equals(session.getAttribute(SECRET))) {
			ok = true;
			ctx.removeAttribute(SECRET);
			session.removeAttribute(SECRET);
		} else {
			log.warning("Malicious attempt to logout");
			ok = false;
		}
		request.setAttribute(PathUtils.LOGOUT, ok);

		ctx.getRequestDispatcher(PathUtils.ACCUEIL).forward(request, response);
		;

	}
}