package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Acceuil extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String VUE_ACCUEIL = "/Acceuil.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req,resp);
    }
}
