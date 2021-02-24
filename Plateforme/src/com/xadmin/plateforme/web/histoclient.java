package com.xadmin.plateforme.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDao;

/**
 * Servlet implementation class histoclient
 */
@WebServlet("/histoclient")
public class histoclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DemandeDao demande;
	private CartonDemandeDao cd;
	
    public histoclient() {
        super();
    }

    public void init() throws ServletException{
    	try {
			DaoFactory daofactory = DaoFactory.getInstance();
			demande = daofactory.getDemandeDao();
			cd = daofactory.getCartonDemDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int id_client = Integer.parseInt(session.getAttribute("userId").toString());
		System.out.println("id client : "+id_client);
//		try {
//			System.out.println(demande.findAllDmdsWithSpec(id_client, "F").get(0).getLds().get(0).getCd().getNbr());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			request.setAttribute("demandes", demande.findAllDmdsWithSpec(id_client, "F"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/histoclient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
