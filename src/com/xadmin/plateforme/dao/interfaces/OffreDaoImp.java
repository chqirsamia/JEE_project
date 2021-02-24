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

public class OffreDaoImp implements OffreDao{

	private DaoFactory daofactory;
	
	public OffreDaoImp(DaoFactory daofactory) {
		super();
		this.daofactory = daofactory;
	}

	@Override
	public List<Offre> listerOffre() {
		List<Offre> offres = new ArrayList<Offre>();
		String sql = "select * from offre";
		Statement stmt;
		ResultSet rs;
		
		try {
			Connection con = daofactory.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offre o = new Offre();
				o.setId(rs.getInt("id_offre"));
				o.setCarton_petit(getQteByOffre(o.getId(), daofactory.getCartondao().getIdByType("P")));
				o.setCarton_moyen(getQteByOffre(o.getId(), daofactory.getCartondao().getIdByType("M")));
				o.setCarton_grand(getQteByOffre(o.getId(), daofactory.getCartondao().getIdByType("G")));
				o.setDescription(rs.getString("description"));
				o.setReduction_offre(rs.getFloat("reduction"));
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
			Connection con = daofactory.getConnection();
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
