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

       
           sql = "select description,NbrCartonDecide,nom,prenom,U.id,reduction_offre,type_carton "
           		+ "from offre O,OffreCarton CA,users U,carton C"
           		+ "where O.id_offre=? and O.id_offre=CA.id_offre and u.id=O.id and CA.id_carton=C.id_carton ";
           preparedStmt = connection.prepareStatement(sql);
           preparedStmt.setInt(1,offre.getID_offre());
       
       resultset = preparedStmt.executeQuery();
       Offre returnedOffre;
       if( resultset.next() ) {
           returnedOffre = new Offre();
           returnedOffre.setID_offre(resultset.getInt("id_offre"));
           String type_carton=resultset.getString("type_carton");
           if(type_carton.equals("mo"))
           {
        	   returnedOffre.setCarton_moyen(resultset.getInt("NbrCartonDecide"));   
           }
           else if(type_carton.equals("gr"))
           {
        	   returnedOffre.setCarton_grand(resultset.getInt("NbrCartonDecide"));   
           }else
           {
        	   returnedOffre.setCarton_petit(resultset.getInt("NbrCartonDecide"));   
           }
           returnedOffre.setId(resultset.getInt("id"));
           returnedOffre.setReduction_offre(resultset.getFloat("reduction_offre"));
           
           returnedOffre.setDescription(resultset.getString("description"));
           returnedOffre.setNom(resultset.getString("nom"));
           returnedOffre.setPrenom(resultset.getString("prenom"));

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
       List<Offre> listofOffres = new ArrayList<Offre>();
       sql = "select description,NbrCartonDecide,nom,prenom,U.id,reduction_offre,type_carton "
       		+ "from offre O,OffreCarton CA,users U"
       		+ "where  O.id_offre=CA.id_offre and U.id=O.id ";
       stmt = connection.createStatement();
       resultset = stmt.executeQuery(sql);
       int id,id_offre,NbrCartonDecide;
       String  description,nom,prenom,type_carton;
       float reduction_offre;
       Offre OffretoAdd;

       while( resultset.next() ) {
           id_offre = resultset.getInt("id_offre");
           id = resultset.getInt("id");
           NbrCartonDecide = resultset.getInt("NbrCartonDecide");
           reduction_offre = resultset.getFloat("reduction_offre");
           description = resultset.getString("description");
           nom = resultset.getString("nom");
           prenom = resultset.getString("prenom");
           type_carton = resultset.getString("type_carton");
	       switch(type_carton)
	       {
	       case("gr"):
	       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,NbrCartonDecide,0,0);
	       break;
	       case("mo"):
		       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,0,NbrCartonDecide,0);
	       break;
	       default:
		       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,0,0,NbrCartonDecide);
	       break;
	       }
           
           listofOffres.add(OffretoAdd);
       }

       resultset.close();
       stmt.close();

       return listofOffres;
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
	   public int insertOffreCartonPetit(Offre offre) throws SQLException {
	       int idrowInserted;
	       String sql;
	       PreparedStatement preparedStmt = null;
	       ResultSet resultset = null;
	       Connection connection = daoFactory.getConnection();
	       sql = "INSERT INTO `offrecarton`( id_offre, id_carton, NbrCartonDecide) " +
	               "VALUES (?,?,?)";
	       preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	       preparedStmt.setInt(1, offre.getID_offre());
	       
	    	   preparedStmt.setInt(2, 3);
	       preparedStmt.setInt(3, offre.getCarton_petit());
	       
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
	   public int insertOffreCartonMoyen(Offre offre) throws SQLException {
	       int idrowInserted;
	       String sql;
	       PreparedStatement preparedStmt = null;
	       ResultSet resultset = null;
	       Connection connection = daoFactory.getConnection();
	       sql = "INSERT INTO `offrecarton`( id_offre, id_carton, NbrCartonDecide) " +
	               "VALUES (?,?,?)";
	       preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	       preparedStmt.setInt(1, offre.getID_offre());
	       
	    	   preparedStmt.setInt(2,1);
	       preparedStmt.setInt(3, offre.getCarton_moyen());
	       
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
	   public int insertOffreCartonGrand(Offre offre) throws SQLException {
	       int idrowInserted;
	       String sql;
	       PreparedStatement preparedStmt = null;
	       ResultSet resultset = null;
	       Connection connection = daoFactory.getConnection();
	       sql = "INSERT INTO `offrecarton`( id_offre, id_carton, NbrCartonDecide) " +
	               "VALUES (?,?,?)";
	       preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	       preparedStmt.setInt(1, offre.getID_offre());
	       
	    	   preparedStmt.setInt(2, 2);
	       preparedStmt.setInt(3, offre.getCarton_petit());
	       
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
   @Override
   public boolean deleteOffreCarton(Offre offre) throws SQLException {
       boolean rowDeleted;
       String sql;
       PreparedStatement preparedStmt;
       Connection connection = daoFactory.getConnection();
       sql = "DELETE from offrecarton where id_offre = ?";
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
				PreparedStatement preparedStatement = connection.prepareStatement("select description,NbrCartonDecide,nom,prenom,U.id,reduction_offre,type_carton"
						+ "from offre O,offrecarton CA,users U"
						+ "where O.id_offre=? and O.id_offre=CA.id_offre and U.id=O.id");
			preparedStatement.setInt(1, id_offre);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,NbrCartonDecide;
		       String  description,nom,prenom,type_carton;
		       float reduction_offre;
		       Offre OffretoAdd;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
		           id = resultset.getInt("id");
		           NbrCartonDecide = resultset.getInt("NbrCartonDecide");
		           reduction_offre = resultset.getFloat("reduction_offre");
		           description = resultset.getString("description");
		           nom = resultset.getString("nom");
		           prenom = resultset.getString("prenom");
		           type_carton = resultset.getString("type_carton");
		           switch(type_carton)
			       {
			       case("gr"):
			       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,NbrCartonDecide,0,0);
			       break;
			       case("mo"):
				       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,0,NbrCartonDecide,0);
			       break;
			       default:
				       OffretoAdd = new Offre(id,nom,prenom,reduction_offre,description,0,0,NbrCartonDecide);
			       break;
			       }
		           
				
			}
		
		return offre;
	}

   public List<Offre> selectAllOffres()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Offre> offres = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
           
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select description,NbrCartonDecide,nom,prenom,U.id,reduction_offre,type_carton"
					+ "from offre O,OffreCarton CA,users U,carton C where  O.id_offre=CA.id_offre and U.id=O.id and CA.id_carton=C.id_carton "); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,NbrCartonDecide;
		       String  description,nom,prenom,type_carton;
		       float reduction_offre;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id = resultset.getInt("id");
		           NbrCartonDecide = resultset.getInt("NbrCartonDecide");
		           reduction_offre = resultset.getFloat("reduction_offre");
		           description = resultset.getString("description");
		           nom = resultset.getString("nom");
		           prenom = resultset.getString("prenom");
		           type_carton = resultset.getString("type_carton");
		           switch(type_carton)
			       {
			       case("gr"):
			    	   offres.add( new Offre(id,nom,prenom,reduction_offre,description,NbrCartonDecide,0,0));
			       break;
			       case("mo"):
			    	   offres.add(new Offre(id,nom,prenom,reduction_offre,description,0,NbrCartonDecide,0));
			       break;
			       default:
			    	   offres.add(new Offre(id,nom,prenom,reduction_offre,description,0,0,NbrCartonDecide));
			       break;
			       }
		           
				
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

@Override
public boolean deleteOffreCarton(int id) throws SQLException {
	boolean rowDeleted;
    String sql;
    PreparedStatement preparedStmt;
    Connection connection = daoFactory.getConnection();
    sql = "DELETE from offrecarton where id_offre = id";
    preparedStmt = connection.prepareStatement(sql);
   
    rowDeleted = preparedStmt.executeUpdate() > 0;

    preparedStmt.close();

    return rowDeleted;
}





}
