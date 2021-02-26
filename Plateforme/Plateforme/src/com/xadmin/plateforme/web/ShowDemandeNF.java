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

import com.xadmin.plateform.classesneeded.ListeDemandeSpec;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDao;
import com.xadmin.plateforme.dao.interfaces.UserDao;

/**
 * Servlet implementation class ShowDemandeNF
 */
@WebServlet("/ShowDemandeNF")
public class ShowDemandeNF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DemandeDao demandeDao;
	private UserDao userDao;
	private CartonDemandeDao cd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDemandeNF() {
        super();
        // TODO Auto-generated constructor stub
    }

	
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
        cd = daoFactory.getCartonDemDao();
        
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
		case "/ShowDemandeNF":
			listDemandeNF(request, response);
			break;}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		}
	

	private void listDemandeNF(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ListeDemandeSpec> listDemandes = demandeDao.selectAllDemandesNF();
		List<User> listUsers = userDao.selectAllClients();

		request.setAttribute("demandes", listDemandes);
		request.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-demande.jsp");
		dispatcher.forward(request, response);
	}
}
