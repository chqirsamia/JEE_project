package com.xadmin.plateforme.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.UserDao;

/**
 * Servlet implementation class infoclient
 */
@WebServlet("/infoclient")
public class infoclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao user;
    
    public void init() {
    	DaoFactory daofactry;
		try {
			daofactry = DaoFactory.getInstance();
	    	user = daofactry.getUserDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public infoclient() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			try {
				User client = user.findSpecificUser(Integer.parseInt(session.getAttribute("userId").toString()));
				request.setAttribute("client", client);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/infoclient.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(session.getAttribute("userId").toString());
		//edit profile
		User u = new User();
		u.setNom(request.getParameter("nom"));
		u.setPrenom(request.getParameter("prenom"));
		u.setEmail(request.getParameter("email"));
		u.setTel(request.getParameter("tel"));
		u.setSexe(request.getParameter("sexe"));
		u.setId(id);
		u.setPassword(request.getParameter("pw"));
		String confPw = request.getParameter("cpw");
		
		if(confPw == u.getPassword()) {
			try {
				boolean b = user.updateUser(u);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
