package com.bean;

public class CartonDemande {

	private int id_demande;
	private int id_carton;
	private int nbr;
	
	
	public int getId_carton() {
		return id_carton;
	}
	public void setId_carton(int id_carton) {
		this.id_carton = id_carton;
	}
	
	public int getId_demande() {
		return id_demande;
	}
	public void setId_demande(int id_demande) {
		this.id_demande = id_demande;
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbrCartonDemande) {
		nbr = nbrCartonDemande;
	}
	
	
	public CartonDemande(int id_carton, int id_demande, int nbrCartonDemande) {
		super();
		this.id_carton = id_carton;
		this.id_demande = id_demande;
		nbr = nbrCartonDemande;
	}
	public CartonDemande() {
		super();
	}
	
	
}
