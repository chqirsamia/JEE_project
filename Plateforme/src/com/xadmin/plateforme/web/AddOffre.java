package com.xadmin.plateforme.web;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.plateforme.bean.Offre;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.OffreDao;

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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertOffre(request, response);
				break;
			case "/delete":
				deleteOffre(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateOffre(request, response);
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
		request.setAttribute("listOffre", listOffre);
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id_offre"));
		Offre existingOffre = offreDao.selectOffre(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("offre-form.jsp");
		request.setAttribute("offre", existingOffre);
		dispatcher.forward(request, response);

	}

	private void insertOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String description = request.getParameter("description");
		String idP = request.getParameter("id");
		int id=Integer.parseInt(idP);
		String reduction_offreP = request.getParameter("reduction_offre");
		int reduction_offre=Integer.parseInt(reduction_offreP);
		Offre offre = new Offre(description,id,reduction_offre);
		offreDao.insertOffre(offre);
		response.sendRedirect("list");
	}

	private void updateOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id_offre = Integer.parseInt(request.getParameter("id_offre"));
		String description = request.getParameter("description");
		String idP = request.getParameter("id");
		int id=Integer.parseInt(idP);
		String reduction_offreP = request.getParameter("reduction_offre");
		int reduction_offre=Integer.parseInt(reduction_offreP);
		//String role =request.getParameter("role");

		Offre offre = new Offre(id_offre,description,id,reduction_offre);
		offreDao.updateOffre(offre);
		response.sendRedirect("list");
	}

	private void deleteOffre(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		offreDao.deleteOffre(id);
		response.sendRedirect("list");

	}
	
}
