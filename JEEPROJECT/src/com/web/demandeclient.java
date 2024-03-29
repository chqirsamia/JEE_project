package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CartonDemande;
import com.bean.Offre;
import com.dao.DaoFactory;
import com.dao.interfaces.CartonDao;
import com.dao.interfaces.CartonDemandeDao;
import com.dao.interfaces.DemandeDao;
import com.dao.interfaces.OffreDao;

/**
 * Servlet implementation class demandeclient
 */
@WebServlet("/demandeclient")
public class demandeclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DemandeDao demande;
    private CartonDao carton;
    private CartonDemandeDao lignedmd;
    private OffreDao offre;

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
	    	offre = daofactory.getOffreDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/demandeclient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int id_client = Integer.parseInt(session.getAttribute("userId").toString());

		//lignes
		int pc = 0, mc = 0, gc = 0, i = 0, j = 0, k = 0;
		if(request.getParameter("pc") != "") pc = Integer.parseInt(request.getParameter("pc"));
		if(request.getParameter("mc") != "") mc = Integer.parseInt(request.getParameter("mc"));
		if(request.getParameter("gc") != "") gc = Integer.parseInt(request.getParameter("gc"));
		
//save demande		
	String action = request.getParameter("action");

	if (action.equals("save")) {

		try {
			int id_demande = demande.insertDemande(id_client);
//			System.out.println("id_demande : "+id_demande);
			
			if(pc != 0 || mc != 0 || gc != 0) {
				int pcid = carton.getIdByType("P");
				CartonDemande cd = new CartonDemande();
				cd.setId_carton(pcid);
				cd.setId_demande(id_demande);
				cd.setNbr(pc);
//				System.out.println("ligne P : "+cd.getId_carton()+" "+cd.getId_demande()+" "+cd.getNbr());
				i = lignedmd.addCartonDemande(cd);
			
				int mcid = carton.getIdByType("M");
				CartonDemande cd1 = new CartonDemande();
				cd1.setId_carton(mcid);
				cd1.setId_demande(id_demande);
				cd1.setNbr(mc);
				j = lignedmd.addCartonDemande(cd1);				
			
				int gcid = carton.getIdByType("G");
				CartonDemande cd2 = new CartonDemande();
				cd2.setId_carton(gcid);
				cd2.setId_demande(id_demande);
				cd2.setNbr(gc);
				k = lignedmd.addCartonDemande(cd2);			
			}
		
		//send confirmation message
		Map<String, String> map = new HashMap<String, String>();
		if(id_demande != -1 && (i>0 || j>0 || k>0)) {
			map.put("msg", "Votre demande a été créée avec succès.");
            map.put("icon", "check");
		}else {
			map.put("msg", "Un problème est survenu !! Veuillez réessayer ultérieurement.");
            map.put("icon", "exclamation-triangle");
		}
		request.setAttribute("isDemandeInserted", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}else {
		//View prix
		List<Offre> ofs = offre.listerOffre();
		for(Offre of : ofs) {
			if(pc == of.getCarton_petit() && mc == of.getCarton_moyen() && gc == of.getCarton_grand()) {
				request.setAttribute("offre", of);
				break;
			}
		}

		Map<String, Integer> map = new HashMap<>();
		map.put("msg", 0);
		float costP=0, costM=0, costG=0;
		if(pc > 0) {
			costP = carton.getPriceByType("P")*pc;
			map.put("Petit", pc);
		}
		if(mc > 0) {
			costM = carton.getPriceByType("M")*mc;
			map.put("Moyen", mc);
		}
		if(gc > 0) {
			costG = carton.getPriceByType("G")*gc;
			map.put("Grand", gc);
		}
		request.setAttribute("qte", map);
		request.setAttribute("cost", costP+costM+costG);

	}
		doGet(request, response);
	}

}
