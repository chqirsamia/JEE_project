package com.xadmin.plateforme.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.plateforme.bean.CartonDemande;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.CartonDao;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDao;

/**
 * Servlet implementation class demandeclient
 */
@WebServlet("/demandeclient")
public class demandeclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DemandeDao demande;
    private CartonDao carton;
    private CartonDemandeDao lignedmd;

    public demandeclient() {
        super();
    }

    public void init() {
    	DaoFactory daofactory;
		try {
			daofactory = DaoFactory.getInstance();
	    	demande = daofactory.getDemandeDao();
	    	carton = daofactory.getCartondao();
	    	lignedmd = daofactory.getCartonDemDao();		
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/demandeclient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//demande
		HttpSession session = request.getSession();
		int id_client = Integer.parseInt(session.getAttribute("userId").toString());
		
		try {
			int id_demande = demande.insertDemande(id_client);
//			System.out.println("id_demande : "+id_demande);
			//lignes
			int pc = 0, mc = 0, gc = 0;
			if(request.getParameter("pc") != null) pc = Integer.parseInt(request.getParameter("pc"));
			if(request.getParameter("mc") != null) mc = Integer.parseInt(request.getParameter("mc"));
			if(request.getParameter("gc") != null) gc = Integer.parseInt(request.getParameter("gc"));
			
			if(pc != 0) {
				int pcid = carton.getIdByType("P");
				System.out.println("id_carton petit: "+pcid);
				CartonDemande cd = new CartonDemande();
				cd.setId_carton(pcid);
				cd.setId_demande(id_demande);
				cd.setNbr(pc);
//				System.out.println("ligne P : "+cd.getId_carton()+" "+cd.getId_demande()+" "+cd.getNbr());
				lignedmd.addCartonDemande(cd);
			}
			
			if(mc != 0) {
				int mcid = carton.getIdByType("M");
				CartonDemande cd = new CartonDemande();
				cd.setId_carton(mcid);
				cd.setId_demande(id_demande);
				cd.setNbr(mc);
				lignedmd.addCartonDemande(cd);				
			}
			
			if(gc != 0) {
				int gcid = carton.getIdByType("G");
				CartonDemande cd = new CartonDemande();
				cd.setId_carton(gcid);
				cd.setId_demande(id_demande);
				cd.setNbr(gc);
				lignedmd.addCartonDemande(cd);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
