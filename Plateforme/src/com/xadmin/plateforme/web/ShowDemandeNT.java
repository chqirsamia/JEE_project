package com.xadmin.plateforme.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.plateforme.bean.Demande;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.DemandeDaoImp;
import com.xadmin.plateforme.dao.interfaces.UserDao;

/**
 * Servlet implementation class ShowDemandeNT
 */
@WebServlet("/ShowDemandeNT")
public class ShowDemandeNT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DemandeDaoImp demandeDao;
	private UserDao userDao;
	public void init() throws ServletException {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.demandeDao = daoFactory.getDemandeDao();
        this.userDao = daoFactory.getUserDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
try {
		switch (action) {
		case "/ShowDemandeNT":
			listDemandeNT(request, response);
			break;}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
	

	private void listDemandeNT(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Demande> listDemandes = demandeDao.selectDemandesNT();
		List<User> listUsers = userDao.selectAllClients();

		request.setAttribute("listDemandes", listDemandes);
		request.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("demande-list.jsp");
		dispatcher.forward(request, response);
	}
	

}
