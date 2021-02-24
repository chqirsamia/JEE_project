package com.xadmin.plateforme.dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.plateforme.bean.Carton;
import com.xadmin.plateforme.dao.DaoFactory;

public class CartonDaoImpl implements CartonDao{

	private DaoFactory daofactory;
	public CartonDaoImpl(DaoFactory daofactory) {
		this.daofactory = daofactory;
	}
	
	@Override
	public List<Carton> findAll() throws SQLException {
		String sql;
		Statement stmt;
		ResultSet rs;
		List<Carton> listeCartons= new ArrayList<Carton>();
		
		Connection con = daofactory.getConnection();
		sql = "select * from Carton";
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			Carton c = new Carton();
			
			c.setId_carton(rs.getInt("id_carton"));			
			c.setType_carton(rs.getString("type_carton"));
			c.setLibelle_carton(rs.getString("libelle_carton"));
			c.setPrix(rs.getFloat("prix"));
			
			listeCartons.add(c);
		}
		
		rs.close();
		stmt.close();
		
		return listeCartons;
	}

	@Override
	public int updateCarton(Carton carton) throws SQLException {
		return 0;
	}

	@Override
	public int deleteCarton(int id_carton) throws SQLException {

		
		return 0;
	}

	@Override
	public int getIdByType(String type) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		int id = 0;
		Connection con;
		try {
			con = daofactory.getConnection();
			sql = "select id_carton from Carton where type_carton = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("id_carton");
				System.out.println("inside dao : "+id);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public float getPriceByType(String type) {
		String sql;
		PreparedStatement pstmt;
		ResultSet rs;
		float price = 0;
		Connection con;
		try {
			con = daofactory.getConnection();
			sql = "select prix from Carton where type_carton = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getFloat("prix");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return price;
	}

}