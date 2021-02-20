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

import com.xadmin.plateforme.bean.Offre;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.OffreDao;
@WebServlet("/offre")
public class AddOffre  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OffreDao offreDao;
	
	public void init() throws ServletException {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.offreDao = daoFactory.getOffreDao();
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
			case "/newOffre":
				showNewForm(request, response);
				break;
			case "/insertOffre":
				insertOffre(request, response);
				break;
			case "/deleteOffre":
				deleteOffre(request, response);
				break;
			default:
				listOffre(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listOffre(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Offre> listOffre = offreDao.selectAllOffres();
		request.setAttribute("listOffre",listOffre);
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-form.jsp");
		dispatcher.forward(request, response);
	}

	/*private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id_offre"));
		Offre existingOffre = offreDao.selectOffre(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-form.jsp");
		request.setAttribute("offre", existingOffre);
		dispatcher.forward(request, response);

	}*/

	private void insertOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
	   String description;
	   int carton_moyen;
	    int carton_petit;
	    int carton_grand;
	    int id;
	     float reduction_offre; 
	  
		 description = request.getParameter("description");
		 carton_moyen= Integer.parseInt(request.getParameter("carton_moyen"));
		 carton_petit= Integer.parseInt(request.getParameter("carton_petit"));
		 carton_grand= Integer.parseInt(request.getParameter("carton_grand"));
		  id = Integer.parseInt((request.getParameter("id")));
		 reduction_offre =Float.parseFloat(request.getParameter("reduction_offre"));
		Offre offre = new Offre(id,reduction_offre,description,carton_moyen,carton_grand,carton_petit);
		offreDao.insertOffre(offre);
		
		response.sendRedirect("offre-list");
	}

	/*private void updateOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int carton_moyen;
	    int carton_petit;
	    int carton_grand;
	    int id;
	    String type_carton;
	     float reduction_offre; 
	     String nom;
	     String prenom;
		 String description = request.getParameter("description");
		 type_carton = request.getParameter("type_carton");
		 carton_moyen= Integer.parseInt(request.getParameter("carton_moyen"));
		 carton_petit= Integer.parseInt(request.getParameter("carton_petit"));
		 carton_grand= Integer.parseInt(request.getParameter("carton_grand"));
		  id = Integer.parseInt((request.getParameter("id")));
		 nom = request.getParameter("nom");
		 prenom = request.getParameter("prenom");

		 reduction_offre =Float.parseFloat(request.getParameter("reduction_offre"));
			Offre offre = new Offre(id,nom,prenom,reduction_offre,description,carton_moyen,carton_grand,carton_petit);
	
		offreDao.updateOffre(offre);
		response.sendRedirect("list");
	}*/

	private void deleteOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		offreDao.deleteOffre(id);
		response.sendRedirect("offre-list");

	}
	
}
