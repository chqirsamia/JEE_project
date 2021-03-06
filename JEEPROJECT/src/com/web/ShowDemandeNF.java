package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classesneeded.ListeDemandeSpec;
import com.bean.Offre;
import com.bean.User;
import com.dao.DaoFactory;
import com.dao.interfaces.CartonDemandeDao;
import com.dao.interfaces.DemandeDao;
import com.dao.interfaces.OffreDao;
import com.dao.interfaces.UserDao;

/**
 * Servlet implementation class ShowDemandeNF
 */
@WebServlet("/ShowDemandeNF")
public class ShowDemandeNF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DemandeDao demandeDao;
	private UserDao userDao;
	private CartonDemandeDao cd;
    private OffreDao offre;

       
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
    	offre = daoFactory.getOffreDao();

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
		List<Offre> ofs = offre.listerOffre();

		HashMap<HashMap<String,Integer>,Float> offre = new HashMap<HashMap<String,Integer>,Float>();
		for(Offre of : ofs) {
			HashMap<String,Integer> cd = new HashMap<String,Integer>();

			cd.put("carton petit",of.getCarton_petit());
			cd.put("carton moyen",of.getCarton_moyen());
			cd.put("carton grand",of.getCarton_grand());
			offre.put(cd,of.getReduction_offre());
			
			}
		request.setAttribute("offre", offre);
		request.setAttribute("demandes", listDemandes);
		request.setAttribute("listUsers", listUsers);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-demande.jsp");
		dispatcher.forward(request, response);
	}
}
