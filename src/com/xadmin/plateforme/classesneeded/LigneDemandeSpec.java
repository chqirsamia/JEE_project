package com.xadmin.plateforme.classesneeded;

import com.xadmin.plateforme.bean.Carton;
import com.xadmin.plateforme.bean.CartonDemande;

public class LigneDemandeSpec {

	private CartonDemande cd;
	private Carton carton;
	
	
	public CartonDemande getCd() {
		return cd;
	}
	public void setCd(CartonDemande cd) {
		this.cd = cd;
	}
	public Carton getCarton() {
		return carton;
	}
	public void setCarton(Carton carton) {
		this.carton = carton;
	}
	
	
	public LigneDemandeSpec(CartonDemande cd, Carton carton) {
		super();
		this.cd = cd;
		this.carton = carton;
	}
	
	public LigneDemandeSpec() {
		super();
	}

}
