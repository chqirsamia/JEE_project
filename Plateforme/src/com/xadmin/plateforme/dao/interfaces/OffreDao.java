package com.xadmin.plateforme.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.xadmin.plateforme.bean.Offre;

public interface OffreDao {
	 public Offre findSpecificOffre(Offre offre) throws SQLException;
	 public List<Offre> findAllOffres() throws SQLException;
	 public int insertOffre(Offre offre) throws SQLException;
	 
	 public boolean updateOffre(Offre offre) throws SQLException;
	 public boolean deleteOffre(Offre offre) throws SQLException;
	 public List<Offre> selectAllOffres()throws SQLException;
	 public boolean deleteOffre(int id) throws SQLException;
	 public  Offre selectOffre(int id)throws SQLException;
}
