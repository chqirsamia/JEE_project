package com.xadmin.plateforme.forms;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xadmin.plateforme.bean.Offre;
import com.xadmin.plateforme.dao.interfaces.OffreDao;

public class OffreNew {
	 private OffreDao offreDao;

	    public OffreNew(OffreDao offreDao) {
	        this.offreDao = offreDao;
	    }
	    private static final String champdescription="description";
	    private static final  String champcarton_moyen="carton_moyen";
	    private static final String champcarton_petit="carton_petit";
	    private static final  String champcarton_grand="carton_moyen";
	    private static final  String champid="id";
	    private static final String champreduction_offre="reduction_offre"; 
		  
	    
	    private String resultat;
	    private Map<String, String> erreurs= new HashMap<String, String>();

	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }

	    public Offre addOffre(HttpServletRequest req){
	        Offre offre = new Offre();
	        try {
	            String description= getValeurChamp(req, champdescription);
	           int carton_moyen =  Integer.parseInt(getValeurChamp(req,champcarton_moyen));
	           int carton_petit = Integer.parseInt(getValeurChamp(req, champcarton_petit));
	            int carton_grand = Integer.parseInt(getValeurChamp(req, champcarton_grand));
	            int id = Integer.parseInt(getValeurChamp(req,champid));
	            float reduction_offre = Float.parseFloat(getValeurChamp(req,champreduction_offre));
	            
	            offre.setCarton_moyen(carton_moyen);
	   		 offre.setCarton_grand(carton_grand);
	   		 offre.setCarton_petit(carton_petit);
	   		 offre.setDescription(description);
	   		 offre.setId_admin(id);
	   		 offre.setReduction_offre(reduction_offre);
	   		
	                offre.setId(offreDao.insertOffre(offre));
	                
	        } catch (SQLException sql) {
	            sql.printStackTrace();
	        } 
	        
	        return offre;
	    }

	 
	    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
	    
	   
}
