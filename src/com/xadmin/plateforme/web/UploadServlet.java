package com.xadmin.plateforme.web;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.UserDao;

import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
    public void init() throws ServletException {
    	DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.userDao = daoFactory.getUserDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        request.getRequestDispatcher("/userAccueil").forward(request,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.getRequestDispatcher("/userAccueil").forward(request,resp);
    }

}

