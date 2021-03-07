package com.xadmin.plateforme.forms;

import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.interfaces.UserDao;

import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAuthForm {
    private UserDao userDao;

    public UserAuthForm(UserDao userDao) {
        this.userDao = userDao;
    }

    private static final String CHAMP_MOT_DE_PASSE = "motdepasse";
    private static final String CHAMP_EMAIL = "email";

    private String resultat;
    private Map<String, String> erreurs = new HashMap();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public User authentification(HttpServletRequest req) {
        User userConnect = new User();
        User userConnected = new User();
        try{
            String email = getValeurChamp(req, CHAMP_EMAIL);
            String motDePasse = getValeurChamp(req, CHAMP_MOT_DE_PASSE);

            traiterEmail(email,userConnect);
            userConnected = traiterUser(email,motDePasse,userConnect);
            if (erreurs.isEmpty()) {
                resultat = "Succés de l'authentification";
            } else {
                
            }
        } catch (Exception e){
            resultat = "Échec de l'authentification : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        }
        return userConnected;


    }
    /**
     * Méthodes de traitement
     */

    private void traiterEmail(String email,User user){
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        user.setEmail(email);

    }
    public User traiterUser(String email,String motDePasse , User user){
        User userConnected = new User();
        try {
            userConnected = validationUser(email, motDePasse);
        } catch (Exception e) {
            setErreur(CHAMP_MOT_DE_PASSE, e.getMessage());
        }
        return userConnected;
    }


    /**
     * Méthodes de validation
     */


    private void validationEmail(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            } 
       }else if ( userDao.findSpecificUser( user ) == null ) {
            throw new Exception( "Cette adresse email n'existe pas . Veuillez entrez une adresse email existante" );
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    private User validationUser(String email, String motDePasse) throws Exception {
        User user = new User();
        user.setEmail(email);
        if (email!=null && motDePasse == null) {
            throw new Exception("Merci de saisir un mot de passe");
        } 
        else if(email==null && motDePasse == null) throw new Exception("Merci de saisir vos coordonnées");
        else {
            User usr = userDao.findSpecificUser(user);
            
            if(usr==null) throw new Exception("Utilisateur introuvable. Merci de réessayer");
            if(usr.getPassword().equals(toHexString(getSHA(motDePasse)))){
                return usr;
            } else {
                throw new Exception("Mot de passe invalide. Merci de réessayer");
            }
        }
    }


    /**
     * Méthoes utiles
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
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