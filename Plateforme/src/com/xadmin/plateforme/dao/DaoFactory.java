package com.xadmin.plateforme.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.xadmin.plateforme.dao.interfaces.CartonDao;
import com.xadmin.plateforme.dao.interfaces.CartonDaoImpl;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeDao;
import com.xadmin.plateforme.dao.interfaces.CartonDemandeImp;
import com.xadmin.plateforme.dao.interfaces.DemandeDao;
import com.xadmin.plateforme.dao.interfaces.DemandeDaoImp;
import com.xadmin.plateforme.dao.interfaces.OffreDao;
import com.xadmin.plateforme.dao.interfaces.OffreDaoImp;
import com.xadmin.plateforme.dao.interfaces.UserDao;
import com.xadmin.plateforme.dao.interfaces.UserDaoImp;

public class DaoFactory {

    private String url;
    private String username;
    private String password;

    /*
     * récupérer les informations de connexion à la bdd, charger le driver JDBC 
     * */
    DaoFactory( String url, String username, String password ) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public static DaoFactory getInstance() throws SQLException{
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost/plateforme?serverTimezone=UTC", "root", "samia24799");
        return instance;
    }

    /*
     * retourne une connexion à la bdd
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO
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