package codel.as.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codel.as.util.PathUtils;

public class DeleteContactServlet extends ContactServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));

		CS.deleteContact(id); // FIXME GET DAO?
		//FIXME MASSAGE
		getServletContext().getRequestDispatcher(PathUtils.ACCUEIL).forward(request, response);
	}


}
