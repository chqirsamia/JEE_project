package com.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Carton;
import com.bean.CartonDemande;
import com.bean.Demande;
import com.bean.Offre;
import com.classesneeded.LigneDemandeSpec;
import com.classesneeded.ListeDemandeSpec;
import com.dao.DaoFactory;
import com.dao.interfaces.CartonDemandeDao;
import com.dao.interfaces.DemandeDao;
import com.dao.interfaces.OffreDao;

/**
 * Servlet implementation class histoclient
 */
@WebServlet("/histoclient")
public class histoclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DemandeDao demande;
	private CartonDemandeDao cd;
    private OffreDao offre;
    private CartonDemandeDao lignedmd;


	
    public histoclient() {
        super();
    }

    public void init() throws ServletException{
    	try {
			DaoFactory daofactory = DaoFactory.getInstance();
			demande = daofactory.getDemandeDao();
			cd = daofactory.getCartonDemDao();
	    	offre = daofactory.getOffreDao();
	    	lignedmd = daofactory.getCartonDemDao();	


		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int id_client = Integer.parseInt(session.getAttribute("userId").toString());
		List<Offre> ofs = offre.listerOffre();
		//int pc = 0, mc = 0, gc = 0;

//		try {
//			System.out.println(demande.findAllDmdsWithSpec(id_client, "F").get(0).getLds().get(0).getCd().getNbr());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			request.setAttribute("demandes", demande.findAllDmds(id_client));
			/*for(ListeDemandeSpec d : demande.findAllDmds(id_client)) {
				List<LigneDemandeSpec> lds = d.getLds();
				for(LigneDemandeSpec ds : lds) {
					CartonDemande cd = ds.getCd(); 
					if(cd.getId_carton()==1) pc=cd.getNbr(); 
					if(cd.getId_carton()==2) mc=cd.getNbr();
					if(cd.getId_carton()==3) gc=cd.getNbr();
					
					
					for(Offre of : ofs) {
					if(pc == of.getCarton_petit() && mc == of.getCarton_moyen() && gc == of.getCarton_grand()) {
						request.setAttribute("offre", of);
						break;
				}
			}
				}
}*/
			HashMap<HashMap<String,Integer>,Float> offre = new HashMap<HashMap<String,Integer>,Float>();
			for(Offre of : ofs) {
				HashMap<String,Integer> cd = new HashMap<String,Integer>();

				cd.put("carton petit",of.getCarton_petit());
				cd.put("carton moyen",of.getCarton_moyen());
				cd.put("carton grand",of.getCarton_grand());
				offre.put(cd,of.getReduction_offre());
				
				}
			request.setAttribute("offre", offre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/histoclient.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}
	}


