package com.classesneeded;

import com.bean.Carton;
import com.bean.CartonDemande;

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
