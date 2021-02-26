package com.xadmin.plateforme.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.xadmin.plateform.classesneeded.ListeDemandeSpec;
import com.xadmin.plateforme.bean.Demande;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDaoImp;
import com.xadmin.plateforme.dao.interfaces.UserDao;
/**
 * Servlet implementation class ShowDemandeT
 */
@WebServlet("/ShowDemandeT")
public class ShowDemandeT extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DemandeDao demandeDao;
	private UserDao userDao;
	private CartonDemandeDao cd;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDemandeT() {
        super();
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
    		case "/ShowDemandeT":
    			listDemande(request, response);
    			break;}
    		}catch(SQLException e)
    		{
    			e.printStackTrace();
    		}
    		}
    	

    	private void listDemande(HttpServletRequest request, HttpServletResponse response)
    			throws SQLException, IOException, ServletException {
    		List<ListeDemandeSpec> listDemandes = demandeDao.selectAllDemandes();
    		List<User> listUsers = userDao.selectAllClients();

    		request.setAttribute("demandes", listDemandes);
    		request.setAttribute("listUsers", listUsers);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("list-demande.jsp");
    		dispatcher.forward(request, response);
    	}
    	

}
