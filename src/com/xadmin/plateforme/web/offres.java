package com.xadmin.plateforme.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.plateforme.bean.Offre;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.OffreDao;

/**
 * Servlet implementation class offres
 */
@WebServlet("/offres")
public class offres extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OffreDao offredao;   

    public offres() {
        super();
    }


	public void init() throws ServletException {
		try {
			DaoFactory daofactory = DaoFactory.getInstance();
			offredao = daofactory.getOffreDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Offre> offres = offredao.listerOffre();
		request.setAttribute("offres", offres);
		this.getServletContext().getRequestDispatcher("/offres.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
