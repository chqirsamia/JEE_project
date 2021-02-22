package com.xadmin.plateforme.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.xadmin.plateforme.bean.Demande;

public interface DemandeDaoImp {
	public List<Demande> selectAllDemandes()throws SQLException;
	 public List<Demande> selectDemandesNT()throws SQLException;
	 public Demande selectDemande(int id)throws SQLException;
	 public boolean updateDemande(int id) throws SQLException;
}
