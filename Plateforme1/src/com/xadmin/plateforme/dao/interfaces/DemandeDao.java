package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.Demande;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;

public class DemandeDao implements DemandeDaoImp{
	private DaoFactory daoFactory;
	 public DemandeDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

  public List<Demande> selectAllDemandes()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Demande> demandes = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
          
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cartondemande"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,carton_moyen,carton_grand,carton_petit,id_user;
		       float prix_total;
		       String etat;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id = resultset.getInt("id_carton");
		           id_user = resultset.getInt("id");
		           carton_moyen = resultset.getInt("carton_moyen");
		           carton_grand = resultset.getInt("carton_grand");
		           carton_petit = resultset.getInt("carton_petit");
		           prix_total = resultset.getFloat("prix_total");
		           etat = resultset.getString("etat");
		           
		           demandes.add(new Demande(id,prix_total,id_user,carton_moyen,carton_grand,carton_petit,etat));
			}
		
		return demandes;
	}
  public List<Demande> selectDemandesNT()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Demande> demandes = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
        
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from cartondemande where etat='nt'"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();
			int id,carton_moyen,carton_grand,carton_petit,id_user;
		       float prix_total;
		       String etat;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				id = resultset.getInt("id_carton");
		           id_user = resultset.getInt("id");
		           carton_moyen = resultset.getInt("carton_moyen");
		           carton_grand = resultset.getInt("carton_grand");
		           carton_petit = resultset.getInt("carton_petit");
		           prix_total = resultset.getFloat("prix_total");
		           etat = resultset.getString("etat");
		           
		           demandes.add(new Demande(id,prix_total,id_user,carton_moyen,carton_grand,carton_petit,etat));
			}
		
		return demandes;
	}
  public Demande selectDemande(int id)throws SQLException {
		Demande demande = null;
		// Step 1: Establishing a Connection
		Connection connection = daoFactory.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select id,carton_moyen,carton_grand,carton_petit,prix_total,etat from cartondemande where id =?;");
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			int carton_moyen,carton_grand,carton_petit,id_user;
		       float prix_total;
		       String etat;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				
		           id_user = resultset.getInt("id");
		           carton_moyen = resultset.getInt("carton_moyen");
		           carton_grand = resultset.getInt("carton_grand");
		           carton_petit = resultset.getInt("carton_petit");
		           prix_total = resultset.getFloat("prix_total");
		           etat = resultset.getString("etat");
		           
		           demande=new Demande(id,prix_total,id_user,carton_moyen,carton_grand,carton_petit,etat);
			
			}
		
		return demande;
		
	}
  @Override
  public boolean updateDemande(int id) throws SQLException {
      boolean rowUpdated;
      String sql;
      PreparedStatement preparedStmt = null;
      Connection connection = daoFactory.getConnection();
      sql = "UPDATE cartondemande set etat = 'nt' where id=?";
      preparedStmt = connection.prepareStatement(sql);
      
      preparedStmt.setInt(1, id);
      rowUpdated = preparedStmt.executeUpdate() > 0;

      preparedStmt.close();

      return rowUpdated;
  }


  





}
