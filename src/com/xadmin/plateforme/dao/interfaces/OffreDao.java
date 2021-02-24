package com.xadmin.plateforme.dao.interfaces;

import java.util.List;

import com.xadmin.plateforme.bean.Offre;

public interface OffreDao {

	public List<Offre> listerOffre();
	public int getQteByOffre(int id_offre, int id_carton);
}
