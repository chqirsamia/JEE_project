package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.Carton;
import com.xadmin.plateforme.bean.CartonDemande;
import com.xadmin.plateforme.classesneeded.LigneDemandeSpec;
import com.xadmin.plateforme.dao.DaoFactory;

public class CartonDemandeImp implements CartonDemandeDao{

	private DaoFactory daofactory;
	
	
	public CartonDemandeImp(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}

	@Override
	public List<LigneDemandeSpec> findCartonDemande(int id_demande) throws SQLException {
		String sql;
        PreparedStatement preparedStmt = null;
        ResultSet rs;
        Connection con = daofactory.getConnection();
        List<LigneDemandeSpec> listeLigneDmd = new ArrayList<>();
        
        sql = "SELECT * FROM cartondemande cd inner join carton c on cd.id_carton = c.id_carton WHERE id_demande = ?";
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1,id_demande);
        rs = preparedStmt.executeQuery();
        
        while( rs.next() ) {
            CartonDemande cd = new CartonDemande();
            Carton c = new Carton();
            
            cd.setId_carton(rs.getInt("id_carton"));
            cd.setId_demande(rs.getInt("id_demande"));
            cd.setNbr(rs.getInt("NbrCartonDemande"));
            
            c.setId_carton(rs.getInt("id_carton"));
            c.setType_carton(rs.getString("type_carton"));
            c.setLibelle_carton(rs.getString("libelle_carton"));
            c.setPrix(rs.getFloat("prix"));
            
            LigneDemandeSpec ld = new LigneDemandeSpec(cd, c);
            listeLigneDmd.add(ld);
        } 

        rs.close();
        preparedStmt.close();

        return listeLigneDmd;
	}

	@Override
	public int addCartonDemande(CartonDemande cd) throws SQLException {
        String sql;
        PreparedStatement preparedStmt = null;
        Connection con = daofactory.getConnection();
        sql = "INSERT INTO cartondemande (id_demande, id_carton, nbrcartondemande) VALUES (?,?,?)";
        
        preparedStmt = con.prepareStatement(sql);
        preparedStmt.setInt(1, cd.getId_demande());
        preparedStmt.setInt(2, cd.getId_carton());
        preparedStmt.setInt(3, cd.getNbr());

        int nbr = preparedStmt.executeUpdate();
        
        preparedStmt.close();
        con.close();
        
        return nbr;
	}

	@Override
	public boolean updateCartonDemande(CartonDemande cd) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCartonDemande(CartonDemande cd) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
