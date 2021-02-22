package com.xadmin.plateforme.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.xadmin.plateforme.bean.Carton;


public interface CartonDao {

	public int getIdByType(String type);
	public List<Carton> findAll () throws SQLException;
	public int updateCarton(Carton carton) throws SQLException;
	public int deleteCarton(int id_carton) throws SQLException;
}
