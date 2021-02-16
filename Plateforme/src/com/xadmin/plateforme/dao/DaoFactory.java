package com.xadmin.plateforme.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.xadmin.plateforme.dao.interfaces.UserDao;
import com.xadmin.plateforme.dao.interfaces.UserDaoImp;

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
    	try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost/plateforme?serverTimezone=UTC", "root", "samia24799");
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

}