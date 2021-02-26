package com.xadmin.plateforme.form;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;

import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.interfaces.UserDao;

public class UserInscriptionForm {
    private UserDao userDao;

    public UserInscriptionForm(UserDao userDao) {
        this.userDao = userDao;
    }

    private static final String CHAMP_NOM       = "nom";
    private static final String CHAMP_PRENOM    = "prenom";
    private static final String CHAMP_SEXE   = "sexe";
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_TEL = "tel";
    private static final String CHAMP_MOT_DE_PASSE     = "motdepasse";
    private static final String CHAMP_CONFIRMATION_MOT_DE_PASSE = "confirmationmotdepasse";
    private static final String CHAMP_ACCEPTE_TERMES = "accepte";

    private String resultat;
    private Map<String, String> erreurs= new HashMap();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public User inscrireUser(HttpServletRequest req){
        User user = new User();
        try {
            String nom = getValeurChamp(req, CHAMP_NOM);
            String prenom = getValeurChamp(req, CHAMP_PRENOM);
            String sexe = getValeurChamp(req, CHAMP_SEXE);
            String email = getValeurChamp(req, CHAMP_EMAIL);
            String tel = getValeurChamp(req, CHAMP_TEL);
            String motDePasse = getValeurChamp(req, CHAMP_MOT_DE_PASSE);
            String confirmation = getValeurChamp(req, CHAMP_CONFIRMATION_MOT_DE_PASSE);
            String accepteTermes = getValeurChamp(req, CHAMP_ACCEPTE_TERMES);

            user.setSexe(sexe);
            traiterNom(nom, user);
            traiterPrenom(prenom, user);
            traiterEmail(email, user);
            traiterTel(tel, user);
            traiterMotDePasse(motDePasse, confirmation, user);
            traiterAccepter(accepteTermes);
            user.setRole("C");
            if (erreurs.isEmpty()) {
                user.setId(userDao.insertUser(user));
                resultat = "Succés de l'inscription";
            } else {
                resultat = "Echec de l'inscription";
            }
        } catch (SQLException sql) {
            resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            sql.printStackTrace();
        } 
        
        return user;
    }

    /**
     * Méthodes de traitement
     */

    private void traiterAccepter(String accepteTermes){
        try {
            if (accepteTermes == null) {
                throw new Exception("Vous devez obligatoirement accepter les termes et les conditions pour continuer.");
            }
        } catch (Exception e) {
            setErreur(CHAMP_ACCEPTE_TERMES, e.getMessage());
        }
    }

    private void traiterNom(String nom,User user){
        try {
            validationNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        user.setNom( nom );
    }
    private void traiterPrenom(String prenom,User user){
        try {
            validationPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        user.setPrenom( prenom );
    }
    
    private void traiterEmail(String email,User user){
        try {
            validationEmail( email );
        } catch ( Exception e ) {
        	e.printStackTrace();
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setEmail(email);
    }
    private void traiterTel(String tel,User user){
        try {
            validationTel( tel );
        } catch ( Exception e ) {
        	e.printStackTrace();
            setErreur( CHAMP_TEL, e.getMessage() );
        }
        user.setTel(tel);
    }
    private void traiterMotDePasse(String motDePasse , String confirmation,User user){
        try {
            validationMotDePasse( motDePasse , confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_MOT_DE_PASSE, e.getMessage() );
        }
	        try {
				user.setPassword( toHexString(getSHA(motDePasse))) ;
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
        
    }

    /**
     * Méthodes de validation
     */

    private void validationNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom d'utilisateur." );
        }
    }

    private void validationPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new Exception( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
        }
        if (prenom == null) {
            throw new Exception( "Merci de saisir une prénom d'utilisateur valide." );
        }
    }

    private void validationMotDePasse(String motDePasse,String confirmation) throws Exception{
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }
    private void validationEmail(String email) throws Exception{
        User user = new User();
        user.setEmail(email);
        if ( email != null) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }else if ( userDao.findSpecificUser( user ) != null ) {
                throw new Exception( "Cette adresse email est déjà utilisée, merci d'en choisir une autre." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }
    private void validationTel(String tel) throws Exception{
        if ( tel != null) {
            if ( !tel.matches( "[0]{1}[0-9]{9}" ) ) {
                throw new Exception( "Merci de saisir un numéro de téléphone valide." );
            }
        } else {
            throw new Exception( "Merci de saisir un numéro de téléphone." );
        }
    }

    /**
     * Méthodes utiles
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
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
