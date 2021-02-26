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
           preparedStmt.setInt(1,offre.getId());
       
       resultset = preparedStmt.executeQuery();
       Offre returnedOffre;
       if( resultset.next() ) {
           returnedOffre = new Offre();
           returnedOffre.setId(resultset.getInt("id_offre"));
        	   returnedOffre.setCarton_moyen(resultset.getInt("carton_moyen"));   
        	   returnedOffre.setCarton_grand(resultset.getInt("carton_grand")); 
        	   returnedOffre.setCarton_petit(resultset.getInt("carton_petit")); 
          
           returnedOffre.setId_admin(resultset.getInt("id"));
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
       preparedStmt.setInt(2, offre.getId_admin());
       preparedStmt.setString(3, offre.getDescription());
       preparedStmt.setInt(4, offre.getCarton_moyen());
       preparedStmt.setInt(5, offre.getCarton_petit());
       preparedStmt.setInt(6, offre.getCarton_grand());
		System.out.println(preparedStmt);

       preparedStmt.execute();
       resultset = preparedStmt.getGeneratedKeys();
       if (resultset.next()) {
           idrowInserted = resultset.getInt(1);
       	offre.setId(idrowInserted); 
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
       sql = "UPDATE offre set reduction_offre = ? ,id = ?,description = ?,carton_petit=?,carton_moyen=?,carton_grand=?  where id_offre = ?";
       preparedStmt = connection.prepareStatement(sql);
       System.out.print(preparedStmt);
       preparedStmt.setFloat(1, offre.getReduction_offre());
       preparedStmt.setInt(2, offre.getId_admin());
       preparedStmt.setString(3, offre.getDescription());
       preparedStmt.setInt(4, offre.getCarton_petit());
       preparedStmt.setInt(5, offre.getCarton_moyen());
       preparedStmt.setInt(6, offre.getCarton_grand());
       preparedStmt.setInt(7, offre.getId());
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
       sql = "DELETE from offre where id_offre = ?";
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
				PreparedStatement preparedStatement = connection.prepareStatement("select id_offre ,reduction_offre, id, description,carton_moyen,carton_petit,carton_grand"
						+ " from offre "
						+ "where id_offre=? ");
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

   public int setid(Offre offre)throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
          
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select id_offre from offre where description=?"); 
			System.out.println(preparedStatement);
			preparedStatement.setString(1,offre.getDescription());
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id_offre = 0;
		       
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id_offre = resultset.getInt("id_offre");
				offre.setId(id_offre); 
			}
		
		return id_offre;
	}


@Override
public boolean deleteOffre(int id) throws SQLException {
	boolean rowDeleted;
    String sql;
    PreparedStatement preparedStmt;
    Connection connection = daoFactory.getConnection();
    sql = "DELETE from offre where id_offre =?";
    preparedStmt = connection.prepareStatement(sql);
    preparedStmt.setInt(1, id);
    rowDeleted = preparedStmt.executeUpdate() > 0;
	System.out.println(preparedStmt);

    preparedStmt.close();

    return rowDeleted;
}


@Override
public List<Offre> listerOffre() {
	List<Offre> offres = new ArrayList<Offre>();
	String sql = "select * from offre";
	Statement stmt;
	ResultSet rs;
	
	try {
		Connection con = daoFactory.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			Offre o = new Offre();
			o.setId(rs.getInt("id_offre"));
			o.setCarton_petit(getQteByOffre(o.getId(), daoFactory.getCartondao().getIdByType("P")));
			o.setCarton_moyen(getQteByOffre(o.getId(), daoFactory.getCartondao().getIdByType("M")));
			o.setCarton_grand(getQteByOffre(o.getId(), daoFactory.getCartondao().getIdByType("G")));
			o.setDescription(rs.getString("description"));
			o.setReduction_offre(rs.getFloat("reduction_offre"));
			o.setId_admin(rs.getInt("id"));
			
			offres.add(o);
		}
	} catch (SQLException e) {

		e.printStackTrace();
	}

	return offres;
}

@Override
public int getQteByOffre(int id_offre, int id_carton) {
	String sql = "select nbrcartondecide from offrecarton where id_offre = ? and id_carton = ?";
	PreparedStatement pstmt;
	ResultSet rs;
	int nbr = -1;
	try {
		Connection con = daoFactory.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id_offre);
		pstmt.setInt(2, id_carton);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			nbr = rs.getInt("nbrcartondecide");
		}
	} catch (SQLException e) {

		e.printStackTrace();
	}


	return nbr;
}




}
