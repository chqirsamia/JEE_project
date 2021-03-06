package com.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bean.CartonDemande;
import com.classesneeded.LigneDemandeSpec;

public interface CartonDemandeDao {

	 public List<LigneDemandeSpec> findCartonDemande(int id_demande) throws SQLException;
	 public int addCartonDemande(CartonDemande cd) throws SQLException;
	 public boolean updateCartonDemande(CartonDemande cd) throws SQLException;
	 public boolean deleteCartonDemande(CartonDemande cd) throws SQLException; 
}