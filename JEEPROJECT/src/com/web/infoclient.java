package com.web;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.DaoFactory;
import com.dao.interfaces.UserDao;

/**
 * Servlet implementation class infoclient
 */
@WebServlet("/infoclient")
public class infoclient extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao user;
    
    public void init() {
    	DaoFactory daofactry;
		try {
			daofactry = DaoFactory.getInstance();
	    	user = daofactry.getUserDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public infoclient() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") != null) {
			try {
				User client = user.findSpecificUser(Integer.parseInt(session.getAttribute("userId").toString()));
				request.setAttribute("client", client);
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/infoclient.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(session.getAttribute("userId").toString());
		//edit profile
		User u = new User();
		u.setNom(request.getParameter("nom"));
		u.setPrenom(request.getParameter("prenom"));
		u.setEmail(request.getParameter("email"));
		u.setTel(request.getParameter("tel"));
		u.setSexe(request.getParameter("sexe"));
		u.setId(id);
		if ((request.getParameter("pw")).length()==0) {
			if(session.getAttribute("userId") != null) {
				try {
					User client = user.findSpecificUser(Integer.parseInt(session.getAttribute("userId").toString()));
					u.setPassword(client.getPassword());
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
			
			try {
				boolean b = user.updateUser(u);
				//send confirmation message
				Map<String, String> map = new HashMap<String, String>();
				if (b == true) {
					map.put("msg", "Vos informations ont été modifiées avec succès !!");
		            map.put("icon", "check");
				}else {
					map.put("msg", "Un problème est survenu !! Veuillez réessayer ultérieurement.");
		            map.put("icon", "exclamation-triangle");
				}
				
				request.setAttribute("isEdited", map);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				u.setPassword(toHexString(getSHA(request.getParameter("pw"))));
				String confPw = toHexString(getSHA(request.getParameter("cpw")));

				if(confPw.equals(u.getPassword())) {

					try {
						boolean b = user.updateUser(u);
						//send confirmation message
						Map<String, String> map = new HashMap<String, String>();
						if (b == true) {
							map.put("msg", "Vos informations ont été modifiées avec succès !!");
				            map.put("icon", "check");
						}else {
							map.put("msg", "Un problème est survenu !! Veuillez réessayer ultérieurement.");
				            map.put("icon", "exclamation-triangle");
						}
						
						request.setAttribute("isEdited", map);
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
				else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("msg", "Les mots de passe ne sont pas identiques !! Veuillez réessayer.");
				
					map.put("icon", "exclamation-triangle");
					request.setAttribute("isEdited", map);
				}
			     

			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		doGet(request, response);
	}
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    } 

}
