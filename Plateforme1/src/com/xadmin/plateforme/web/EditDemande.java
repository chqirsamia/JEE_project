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
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.DemandeDaoImp;

/**
 * Servlet implementation class EditDemande
 */
@WebServlet("/EditDemande")
public class EditDemande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DemandeDaoImp demandeDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
   	
    	
 public void init() throws ServletException {
    		DaoFactory daoFactory = null;
    		try {
    			daoFactory = DaoFactory.getInstance();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            this.demandeDao = daoFactory.getDemandeDao();
        }

    	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		doGet(request, response);
    	}

    	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
    		String action = request.getServletPath();
    System.out.println(action);
    		switch (action) {
			case "/EditDemande":
				try {
					edit(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			
 		}
    	}

    	
    	private void edit(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException, SQLException {
    		int id = Integer.parseInt(request.getParameter("id"));
    		Demande existingDemande = demandeDao.selectDemande(id);
    		demandeDao.updateDemande(id);
    		response.sendRedirect("showDemande");
    		
    	}

    	
}
