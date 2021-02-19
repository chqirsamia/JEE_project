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

       
           sql = "select id_offre,description,id,reduction_offre,carton_moyen,carton_petit,carton_grand"
           		+ "from offre "
           		+ "where id_offre=?";
           preparedStmt = connection.prepareStatement(sql);
           preparedStmt.setInt(1,offre.getID_offre());
       
       resultset = preparedStmt.executeQuery();
       Offre returnedOffre;
       if( resultset.next() ) {
           returnedOffre = new Offre();
           returnedOffre.setID_offre(resultset.getInt("id_offre"));
        	   returnedOffre.setCarton_moyen(resultset.getInt("carton_moyen"));   
        	   returnedOffre.setCarton_grand(resultset.getInt("carton_grand")); 
        	   returnedOffre.setCarton_petit(resultset.getInt("carton_petit")); 
          
           returnedOffre.setId(resultset.getInt("id"));
           returnedOffre.setReduction_offre(resultset.getFloat("reduction_offre"));
           
           returnedOffre.setDescription(resultset.getString("description"));
          

       } else {
           returnedOffre = null;
       }

       resultset.close();
       preparedStmt.close();

       return   returnedOffre;
   }

   @Override
   public List<Offre> findAllOffres() throws SQLException {
       String sql;
       Statement stmt;
       ResultSet resultset;
       Connection connection = daoFactory.getConnection();
       List<Offre> listOffre = new ArrayList<Offre>();
       sql = "select id_offre,description,id,reduction_offre,carton_moyen,carton_petit,carton_grand "
       		+ "from offre";
       stmt = connection.createStatement();
       resultset = stmt.executeQuery(sql);
       int id,id_offre,carton_grand,carton_moyen,carton_petit;
       String  description;
       float reduction_offre;
       Offre OffretoAdd;

       while( resultset.next() ) {
           id_offre = resultset.getInt("id_offre");
           id = resultset.getInt("id");
           carton_moyen = resultset.getInt("carton_moyen");
           carton_grand = resultset.getInt("carton_grand");
           carton_petit = resultset.getInt("carton_petit");
           reduction_offre = resultset.getFloat("reduction_offre");
           description = resultset.getString("description");
           
	       
	       OffretoAdd = new Offre(id,reduction_offre,description,id_offre,carton_moyen,carton_grand,carton_petit);
	       
           
           listOffre.add(OffretoAdd);
       }

       resultset.close();
       stmt.close();

       return listOffre;
   }

	@Override
   public int insertOffre(Offre offre) throws SQLException {
       int idrowInserted;
       String sql;
       PreparedStatement preparedStmt = null;
       ResultSet resultset = null;
       Connection connection = daoFactory.getConnection();
       sql = "INSERT INTO `offre`( reduction_offre, id, description,carton_moyen,carton_petit,carton_grand) " +
               "VALUES (?,?,?,?,?,?)";
       preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
       preparedStmt.setFloat(1, offre.getReduction_offre());
       preparedStmt.setInt(2, offre.getId());
       preparedStmt.setString(3, offre.getDescription());
       preparedStmt.setInt(4, offre.getCarton_moyen());
       preparedStmt.setInt(5, offre.getCarton_petit());
       preparedStmt.setInt(6, offre.getCarton_grand());
       
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
	
	/*	
   @Override
   public boolean updateOffre(Offre offre) throws SQLException {
       boolean rowUpdated;
       String sql;
       PreparedStatement preparedStmt = null;
       Connection connection = daoFactory.getConnection();
       sql = "UPDATE offre set reduction_offre = ? ,id = ?,description = ?  where id_offre = ?";
       preparedStmt = connection.prepareStatement(sql);
       
       preparedStmt.setFloat(1, offre.getReduction_offre());
       preparedStmt.setInt(2, offre.getId());
       preparedStmt.setString(3, offre.getDescription());
       preparedStmt.setInt(4, offre.getID_offre());
       rowUpdated = preparedStmt.executeUpdate() > 0;

       preparedStmt.close();

       return rowUpdated;
   }
  
*/
   @Override
   public boolean deleteOffre(Offre offre) throws SQLException {
       boolean rowDeleted;
       String sql;
       PreparedStatement preparedStmt;
       Connection connection = daoFactory.getConnection();
       sql = "DELETE from offre where id_offre = ?";
       preparedStmt = connection.prepareStatement(sql);
       preparedStmt.setLong(1, offre.getID_offre());
       rowDeleted = preparedStmt.executeUpdate() > 0;

       preparedStmt.close();

       return rowDeleted;
   }
   
   public Offre selectOffre(int id_offre)throws SQLException {
		Offre offre = null;
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select reduction_offre, id, description,carton_moyen,carton_petit,carton_grand"
						+ "from offre "
						+ "where O.id_offre=? ");
			preparedStatement.setInt(1, id_offre);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,carton_moyen,carton_grand,carton_petit;
		       String  description;
		       float reduction_offre;
		      
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id_offre = resultset.getInt("id_offre");
		           id = resultset.getInt("id");
		           carton_moyen = resultset.getInt("carton_moyen");
		           carton_grand = resultset.getInt("carton_grand");
		           carton_petit = resultset.getInt("carton_petit");
		           reduction_offre = resultset.getFloat("reduction_offre");
		           description = resultset.getString("description");
		           
			       
			       offre = new Offre(id,reduction_offre,description,id_offre,carton_moyen,carton_grand,carton_petit);
			       
				
			}
		
		return offre;
	}

   public List<Offre> selectAllOffres()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Offre> offres = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
           
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from offre"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,carton_moyen,carton_grand,carton_petit,id_offre;
		       String  description;
		       float reduction_offre;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id_offre = resultset.getInt("id_offre");
		           id = resultset.getInt("id");
		           carton_moyen = resultset.getInt("carton_moyen");
		           carton_grand = resultset.getInt("carton_grand");
		           carton_petit = resultset.getInt("carton_petit");
		           reduction_offre = resultset.getFloat("reduction_offre");
		           description = resultset.getString("description");
		           
		           offres.add(new Offre(id,reduction_offre,description,id_offre,carton_moyen,carton_grand,carton_petit));
			}
		
		return offres;
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
