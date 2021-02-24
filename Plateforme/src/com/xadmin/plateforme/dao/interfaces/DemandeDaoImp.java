package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.Demande;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateform.classesneeded.LigneDemandeSpec;
import com.xadmin.plateform.classesneeded.ListeDemandeSpec;
import com.xadmin.plateforme.dao.DaoFactory;
import java.sql.Date;

public class DemandeDaoImp implements DemandeDao {

    private DaoFactory daofactory;
	
	
	public DemandeDaoImp(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}
	
	@Override
	public List<Demande> findDemandes(int id_client, String etat) throws SQLException {
		String sql;
        PreparedStatement preparedStmt = null;
        ResultSet rs;
        Connection con = daofactory.getConnection();
        List<Demande> listeDmd = new ArrayList<>();
        
        sql = "SELECT * FROM demande WHERE id_client = ? and etat = ?";
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, id_client);
        preparedStmt.setString(2, etat);
        rs = preparedStmt.executeQuery();
        
        while( rs.next() ) {
            Demande dmd = new Demande();
            
            dmd.setId(rs.getInt("id"));
            dmd.setDate_creation(rs.getObject("date_creation", Date.class));
            dmd.setEtat(rs.getString("etat"));
            dmd.setId_client(rs.getInt("id_client"));
            
            listeDmd.add(dmd);
        } 

        rs.close();
        preparedStmt.close();

        return listeDmd;
	}

	@Override
	public List<ListeDemandeSpec> findAllDmdsWithSpec(int id_client, String etat) throws SQLException {
		String sql;
        PreparedStatement preparedStmt = null;
        ResultSet rs;
        Connection con = daofactory.getConnection();
        List<ListeDemandeSpec> listeDmd = new ArrayList<>();
        
        sql = "SELECT * FROM demande WHERE id_client = ? and etat = ?";
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, id_client);
        preparedStmt.setString(2, etat);
        rs = preparedStmt.executeQuery();
        
        while( rs.next() ) {
            Demande dmd = new Demande();
            
            dmd.setId(rs.getInt("id"));
            dmd.setDate_creation(rs.getObject("date_creation", Date.class));
            dmd.setEtat(rs.getString("etat"));
            dmd.setId_client(rs.getInt("id_client"));
            
            List<LigneDemandeSpec> lds = daofactory.getCartonDemDao().findCartonDemande(rs.getInt("id"));
            ListeDemandeSpec dmdspec = new ListeDemandeSpec(dmd, lds);
            
            listeDmd.add(dmdspec);
        } 

        rs.close();
        preparedStmt.close();

        return listeDmd;
	}
	
	public List<ListeDemandeSpec>  selectAllDemandes()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Demande> demandes = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daofactory.getConnection();
		List<ListeDemandeSpec> listeDmd = new ArrayList<>();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from demande "); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

	        while( rs.next() ) {
	            Demande dmd = new Demande();
	            
	            dmd.setId(rs.getInt("id"));
	            dmd.setDate_creation(rs.getObject("date_creation", Date.class));
	            dmd.setEtat(rs.getString("etat"));
	            dmd.setId_client(rs.getInt("id_client"));
	            List<LigneDemandeSpec> lds = daofactory.getCartonDemDao().findCartonDemande(rs.getInt("id"));
	            ListeDemandeSpec dmdspec = new ListeDemandeSpec(dmd, lds);
	            
	            listeDmd.add(dmdspec);
			}
		
	        rs.close();
	       

	        return listeDmd;
	}
	public List<ListeDemandeSpec>  selectAllDemandesF()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Demande> demandes = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daofactory.getConnection();
		List<ListeDemandeSpec> listeDmd = new ArrayList<>();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from demande where etat='F'"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

	        while( rs.next() ) {
	            Demande dmd = new Demande();
	            
	            dmd.setId(rs.getInt("id"));
	            dmd.setDate_creation(rs.getObject("date_creation", Date.class));
	            dmd.setEtat(rs.getString("etat"));
	            dmd.setId_client(rs.getInt("id_client"));
	            List<LigneDemandeSpec> lds = daofactory.getCartonDemDao().findCartonDemande(rs.getInt("id"));
	            ListeDemandeSpec dmdspec = new ListeDemandeSpec(dmd, lds);
	            
	            listeDmd.add(dmdspec);
			}
		
	        rs.close();
	       

	        return listeDmd;
	}
	public List<ListeDemandeSpec>  selectAllDemandesNF()throws SQLException {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Demande> demandes = new ArrayList<>();
		 
		// Step 1: Establishing a Connection
		Connection connection = daofactory.getConnection();
		List<ListeDemandeSpec> listeDmd = new ArrayList<>();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from demande where etat='NF'"); 
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

	        while( rs.next() ) {
	            Demande dmd = new Demande();
	            
	            dmd.setId(rs.getInt("id"));
	            dmd.setDate_creation(rs.getObject("date_creation", Date.class));
	            dmd.setEtat(rs.getString("etat"));
	            dmd.setId_client(rs.getInt("id_client"));
	            List<LigneDemandeSpec> lds = daofactory.getCartonDemDao().findCartonDemande(rs.getInt("id"));
	            ListeDemandeSpec dmdspec = new ListeDemandeSpec(dmd, lds);
	            
	            listeDmd.add(dmdspec);
			}
		
	        rs.close();
	       

	        return listeDmd;
	}
	
	@Override
	public int insertDemande(int id) throws SQLException {
		int idrowInserted;
        String sql;
        PreparedStatement preparedStmt = null;
        ResultSet resultset = null;
        Connection connection = daofactory.getConnection();
        
        sql = "INSERT INTO demande (date_creation,id_client, etat) VALUES (sysdate(),?,'NF')";
        preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setInt(1, id);


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

	public Demande selectDemande(int id)throws SQLException {
		Demande demande = null;
		// Step 1: Establishing a Connection
		Connection connection = daofactory.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select id,date_creation,id_client,,etat from demande where id =?");
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultset = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			int id_client;
		      
		       String etat;
		       Date date_creation;
			// Step 4: Process the ResultSet object.
			while (resultset.next()) {
				
				id_client = resultset.getInt("id_client");
				date_creation = resultset.getDate("date_creation");
		          
		           etat = resultset.getString("etat");
		           
		           demande=new Demande(id, date_creation, id_client,etat);
			
			}
		
		return demande;
		
	}
  @Override
  public boolean updateDemande(int id) throws SQLException {
      boolean rowUpdated;
      String sql;
      PreparedStatement preparedStmt = null;
      Connection connection = daofactory.getConnection();
      sql = "UPDATE demande set etat = 'F' where id=?";
      preparedStmt = connection.prepareStatement(sql);
      System.out.println(preparedStmt);
      preparedStmt.setInt(1, id);
      rowUpdated = preparedStmt.executeUpdate() > 0;

      preparedStmt.close();

      return rowUpdated;
  }



	@Override
	public boolean deleteDemande(Demande dmd) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDemande(Demande dmd) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



}
