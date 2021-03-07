package com.dao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dao.interfaces.CartonDao;
import com.dao.interfaces.CartonDaoImpl;
import com.dao.interfaces.CartonDemandeDao;
import com.dao.interfaces.CartonDemandeImp;
import com.dao.interfaces.DemandeDao;
import com.dao.interfaces.DemandeDaoImp;
import com.dao.interfaces.OffreDao;
import com.dao.interfaces.OffreDaoImp;
import com.dao.interfaces.UserDao;
import com.dao.interfaces.UserDaoImp;
import com.dao.DaoFactory;

public class DaoFactory {

    private String url;
    private String username;
    private String password;

    /*
     * r�cup�rer les informations de connexion � la bdd, charger le driver JDBC 
     * */
    DaoFactory( String url, String username, String password ) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public static DaoFactory getInstance() throws SQLException{
    	/*String dbdriver=System.getenv("dbDriver");
		String url=System.getenv("url");
		String user=System.getenv("user");
		String password=System.getenv("password");*/
    	try {
    		
    		Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

    	DaoFactory instance = new DaoFactory("jdbc:mysql://localhost/plateforme?serverTimezone=UTC", "root", "");
        return instance;
    }

    /*
     * retourne une connexion � la bdd
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    /*
     * M�thodes de r�cup�ration de l'impl�mentation des diff�rents DAO
     */
    public UserDao getUserDao() {
        return new UserDaoImp( this );
    }

    public CartonDao getCartondao() {
    	return new CartonDaoImpl(this);
    }
    
    public CartonDemandeDao getCartonDemDao() {
    	return new CartonDemandeImp(this);
    }
    
    public DemandeDao getDemandeDao() {
    	return new DemandeDaoImp(this);
    }
    public OffreDao getOffreDao() {
    	return new OffreDaoImp(this);
    }
}
