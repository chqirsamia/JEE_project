package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.Offre;
import com.xadmin.plateforme.dao.DaoFactory;

public class OffreDaoImp implements OffreDao {
	private DaoFactory daoFactory;
	 public OffreDaoImp(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	public Offre findSpecificOffre(Offre offre) throws SQLException {
       String sql;
       PreparedStatement preparedStmt = null;
       ResultSet resultset;
       Connection connection = daoFactory.getConnection();

       
           sql = "SELECT  `reduction_offre`, `id`, `description`"+
                   "FROM `offre` WHERE email = id_offre ";
           preparedStmt = connection.prepareStatement(sql);
           preparedStmt.setInt(1,offre.getID_offre());
       
       resultset = preparedStmt.executeQuery();
       Offre returnedUser;
       if( resultset.next() ) {
           returnedUser = new Offre();
           returnedUser.setID_offre(resultset.getInt("id_offre"));
           returnedUser.setReduction_offre(resultset.getFloat("reduction_offre"));
           returnedUser.setId(resultset.getInt("id"));
           returnedUser.setDescription(resultset.getString("description"));
       } else {
           returnedUser = null;
       }

       resultset.close();
       preparedStmt.close();

       return returnedUser;
   }

   @Override
   public List<Offre> findAllOffres() throws SQLException {
       String sql;
       Statement stmt;
       ResultSet resultset;
       Connection connection = daoFactory.getConnection();
       List<Offre> listofUsers = new ArrayList<Offre>();
       sql = "SELECT  `reduction_offre`, `id`, `description`"+
               "FROM `offre`  ";
       stmt = connection.createStatement();
       resultset = stmt.executeQuery(sql);
       int id,id_offre;
       String  description;
       float reduction_offre;
       Offre OffretoAdd;

       while( resultset.next() ) {
           id_offre = resultset.getInt("id_offre");
           reduction_offre = resultset.getFloat("reduction_offre");
           id=resultset.getInt("id");
           description = resultset.getString("description");
          
           OffretoAdd = new Offre(id, description,id_offre,reduction_offre);
           listofUsers.add(OffretoAdd);
       }

       resultset.close();
       stmt.close();

       return listofUsers;
   }

	@Override
   public int insertOffre(Offre offre) throws SQLException {
       int idrowInserted;
       String sql;
       PreparedStatement preparedStmt = null;
       ResultSet resultset = null;
       Connection connection = daoFactory.getConnection();
       sql = "INSERT INTO `offre`( reduction_offre, id, description) " +
               "VALUES (?,?,?)";
       preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
       preparedStmt.setFloat(1, offre.getReduction_offre());
       preparedStmt.setInt(2, offre.getId());
       preparedStmt.setString(3, offre.getDescription());
       
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
   public boolean updateOffre(Offre offre) throws SQLException {
       boolean rowUpdated;
       String sql;
       PreparedStatement preparedStmt = null;
       Connection connection = daoFactory.getConnection();
       sql = "UPDATE offre set reduction_offre = ? ,description = ? ,id = ? where id_offre = ?";
       preparedStmt = connection.prepareStatement(sql);
       preparedStmt.setFloat(1, offre.getReduction_offre());
       preparedStmt.setInt(2, offre.getId());
       preparedStmt.setString(3, offre.getDescription());
       rowUpdated = preparedStmt.executeUpdate() > 0;

       preparedStmt.close();

       return rowUpdated;
   }

   @Override
   public boolean deleteOffre(Offre offre) throws SQLException {
       boolean rowDeleted;
       String sql;
       PreparedStatement preparedStmt;
       Connection connection = daoFactory.getConnection();
       sql = "DELETE from offre where id = ?";
       preparedStmt = connection.prepareStatement(sql);
       preparedStmt.setLong(1, offre.getId());
       rowDeleted = preparedStmt.executeUpdate() > 0;

       preparedStmt.close();

       return rowDeleted;
   }
 
   public Offre selectOffre(int id_offre)throws SQLException {
		Offre offre = null;
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select reduction_offre, id, description from offre where id =?");
			preparedStatement.setInt(1, id_offre);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				
		           float reduction_offre = rs.getFloat("reduction_offre");
		           int id=rs.getInt("id");
		           String description =rs.getString("description");
				offre = new Offre(id,description,id_offre,reduction_offre);
			}
		
		return offre;
	}

   public List<Offre> selectAllOffres()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Offre> users = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
           
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from offre"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String description = rs.getString("description");
				int id_offre = rs.getInt("id_offre");
				float reduction_offre  = rs.getFloat("reduction_offre");
				
				users.add(new Offre(id,description,id_offre,reduction_offre));
			}
		
		return users;
	}


@Override
public boolean deleteOffre(int id) throws SQLException {
	boolean rowDeleted;
    String sql;
    PreparedStatement preparedStmt;
    Connection connection = daoFactory.getConnection();
    sql = "DELETE from offre where id_offre = id";
    preparedStmt = connection.prepareStatement(sql);
   
    rowDeleted = preparedStmt.executeUpdate() > 0;

    preparedStmt.close();

    return rowDeleted;
}






}
