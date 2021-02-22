package com.xadmin.plateforme.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.xadmin.plateforme.bean.Demande;
import com.xadmin.plateforme.classesneeded.ListeDemandeSpec;

public interface DemandeDao {

	 public List<Demande> findDemandes(int id_client, String etat) throws SQLException;
	 public List<ListeDemandeSpec> findAllDmdsWithSpec(int id_client, String etat) throws SQLException;
	 public int insertDemande(int id) throws SQLException;
	 public boolean updateDemande(Demande dmd) throws SQLException;
	 public boolean deleteDemande(Demande dmd) throws SQLException;
}
