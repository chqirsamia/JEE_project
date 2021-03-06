package com.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.dao.DaoFactory;

public class UserDaoImp implements UserDao{
	private DaoFactory daoFactory;
	 public UserDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	 public List<User> selectAllClients()throws SQLException {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<User> users = new ArrayList<>();
			 
			// Step 1: Establishing a Connection
			Connection connection = daoFactory.getConnection();
	            
					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select * from users where role='C'"); 
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String tel = rs.getString("tel");
					String email = rs.getString("email");
					String sexe = rs.getString("sexe");
					String password = rs.getString("password");
					String role = rs.getString("role");
					users.add(new User(id, name, prenom,email,sexe,tel,password,role));
				}
			
			return users;
		}
	public User findSpecificUser(User user) throws SQLException {
        String sql;
        PreparedStatement preparedStmt = null;
        ResultSet resultset;
        Connection connection = daoFactory.getConnection();

        if(user.getEmail() != null) {
            sql = "SELECT `id`, `nom`, `prenom`, `email`, `sexe`,`tel`, `password`, `role`"+
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
            returnedUser.setPassword(resultset.getString("password"));
            returnedUser.setRole(resultset.getString("role"));
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
                "VALUES (?,?,?,?,?,?,'C')";
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
        sql = "UPDATE users set nom = ? , prenom = ? , email = ? ,sexe= ?,tel= ?, password = ? where id = ?";
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString(1, user.getNom());
        preparedStmt.setString(2, user.getPrenom());
        preparedStmt.setString(3, user.getEmail());
        preparedStmt.setString(4, user.getSexe());
        preparedStmt.setString(5, user.getTel());
        preparedStmt.setString(6, user.getPassword());
        preparedStmt.setInt(7, user.getId());
        rowUpdated = preparedStmt.executeUpdate() > 0;

        preparedStmt.close();

        return rowUpdated;
    }
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        String sql;
        PreparedStatement preparedStmt;
        Connection connection = daoFactory.getConnection();
        sql = "DELETE from users where id = ?;";
       
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setInt(1, id);
        rowDeleted = preparedStmt.executeUpdate() > 0;

        preparedStmt.close();

        return rowDeleted;
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
	@Override
	public User findSpecificUser(int id) throws SQLException {
		String sql;
        PreparedStatement preparedStmt = null;
        ResultSet resultset;
        Connection connection = daoFactory.getConnection();

        sql = "SELECT `id`, `nom`, `prenom`, `email`, `sexe`,`tel`, `password`, `role`"+
                "FROM `users` WHERE id = ? ";
        preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setInt(1,id);
        
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
            returnedUser.setPassword(resultset.getString("password"));
            returnedUser.setRole(resultset.getString("role"));
        } else {
            returnedUser = null;
        }

        resultset.close();
        preparedStmt.close();

        return returnedUser;
	}
	

	public List<User> selectAllUsers()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
            
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from users where role='A'"); 
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String sexe = rs.getString("sexe");
				String password = rs.getString("password");
				String role = rs.getString("role");
				users.add(new User(id, name, prenom,email,sexe,tel,password,role));
			}
		
		return users;
	}
	public User selectUser(int id)throws SQLException {
		User user = null;
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select id,nom,prenom,email,sexe,tel,password,role from users where id =?;");
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String sexe = rs.getString("sexe");
				String tel = rs.getString("tel");
				String password = rs.getString("password");
				String role = rs.getString("role");
				user = new User(id, name,prenom, email,sexe, tel,password,role);
			}
		
		return user;
	}

	@Override
	public int insertAdmin(User user) throws SQLException {
        int idrowInserted;
        String sql;
        
        PreparedStatement preparedStmt = null;
        ResultSet resultset = null;
        Connection connection = daoFactory.getConnection();
        Statement stat=connection.createStatement();
        sql = "INSERT INTO `users`(nom,prenom,email,sexe,tel,password,role) " +
                "VALUES (?,?,?,?,?,?,'A')";
      
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
	

}
