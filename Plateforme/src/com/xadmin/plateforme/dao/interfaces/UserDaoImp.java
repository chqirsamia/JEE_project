package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;

public class UserDaoImp implements UserDao{
	private DaoFactory daoFactory;
	 public UserDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	public User findSpecificUser(User user) throws SQLException {
        String sql;
        PreparedStatement preparedStmt = null;
        ResultSet resultset;
        Connection connection = daoFactory.getConnection();

        if(user.getEmail() != null) {
            sql = "SELECT `id`, `nom`, `prenom`, `email`, `sexe`,`tel`, `password`,'role'"+
                    "FROM `users` WHERE email = ? ";
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1,user.getEmail());
        }
        resultset = preparedStmt.executeQuery();
        User returnedUser;
        if( resultset.next() ) {
            returnedUser = new User();
            returnedUser.setId(resultset.getInt("id"));
            returnedUser.setNom(resultset.getString("nom"));
            returnedUser.setPrenom(resultset.getString("prenom"));
            returnedUser.setEmail(resultset.getString("email"));
            returnedUser.setSexe(resultset.getString("sexe"));
            returnedUser.setTel(resultset.getString("tel"));
            returnedUser.setTel(resultset.getString("role"));
            returnedUser.setPassword(resultset.getString("password"));
        } else {
            returnedUser = null;
        }

        resultset.close();
        preparedStmt.close();

        return returnedUser;
    }

    @Override
    public List<User> findAllUsers() throws SQLException {
        String sql;
        Statement stmt;
        ResultSet resultset;
        Connection connection = daoFactory.getConnection();
        List<User> listofUsers = new ArrayList<User>();
        sql = "SELECT id, nom, prenom, email,sexe,tel,role" +
                "FROM users";
        stmt = connection.createStatement();
        resultset = stmt.executeQuery(sql);
        int id;
        String nom, prenom, email,sexe,tel,password,role;
        User usertoAdd;

        while( resultset.next() ) {
            id = resultset.getInt("id");
            nom = resultset.getString("nom");
            prenom = resultset.getString("prenom");;
            email = resultset.getString("email");
            password = resultset.getString("password");
            sexe = resultset.getString("sexe");
            tel = resultset.getString("tel");
            role = resultset.getString("role");
            usertoAdd = new User(id,nom,prenom,email,sexe,tel,password,role);
            listofUsers.add(usertoAdd);
        }

        resultset.close();
        stmt.close();

        return listofUsers;
    }

	@Override
    public int insertUser(User user) throws SQLException {
        int idrowInserted;
        String sql;
        PreparedStatement preparedStmt = null;
        ResultSet resultset = null;
        Connection connection = daoFactory.getConnection();
        sql = "INSERT INTO `users`(nom,prenom,email,sexe,tel,password,role) " +
                "VALUES (?,?,?,?,?,?,'client')";
        preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, user.getNom());
        preparedStmt.setString(2, user.getPrenom());
        preparedStmt.setString(3, user.getEmail());
        preparedStmt.setString(4, user.getSexe());
        preparedStmt.setString(5, user.getTel());
        preparedStmt.setString(6, user.getPassword());
        preparedStmt.execute();
        resultset = preparedStmt.getGeneratedKeys();
        if (resultset.next()) {
            idrowInserted = resultset.getInt(1);
        } else {
            idrowInserted = -1;
        }
        preparedStmt.close();
        resultset.close();

        return idrowInserted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        String sql;
        PreparedStatement preparedStmt = null;
        Connection connection = daoFactory.getConnection();
        sql = "UPDATE users set nom = ? , prenom = ? , email = ? ,sexe= ?,tel= ?, password = ? where ID = ?";
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, user.getNom());
        preparedStmt.setString(2, user.getPrenom());
        preparedStmt.setString(3, user.getEmail());
        preparedStmt.setString(4, user.getSexe());
        preparedStmt.setString(5, user.getTel());
        preparedStmt.setString(6, user.getPassword());
        rowUpdated = preparedStmt.executeUpdate() > 0;

        preparedStmt.close();

        return rowUpdated;
    }

    @Override
    public boolean deleteUser(User user) throws SQLException {
        boolean rowDeleted;
        String sql;
        PreparedStatement preparedStmt;
        Connection connection = daoFactory.getConnection();
        sql = "DELETE from users where id = ?";
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setLong(1, user.getId());
        rowDeleted = preparedStmt.executeUpdate() > 0;

        preparedStmt.close();

        return rowDeleted;
    }






}
