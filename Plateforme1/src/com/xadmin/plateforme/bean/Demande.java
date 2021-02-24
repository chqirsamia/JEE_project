package com.xadmin.plateforme.bean;

public class Demande {
	private int id;
	 private int id_user;
    private int carton_moyen;
    private int carton_petit;
    private int carton_grand;
    private float prix_total; 
    private String etat; 
    public String  getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public float  getPrix_total() {
		return prix_total;
	}
	public void setPrix_total(float prix_total) {
		this.prix_total = prix_total;
	}
	
	public int getCarton_moyen() {
		return carton_moyen;
	}
	public void setCarton_moyen(int carton_moyen) {
		this.carton_moyen = carton_moyen;
	}
	public int getCarton_petit() {
		return carton_petit;
	}
	public void setCarton_petit(int carton_petit) {
		this.carton_petit = carton_petit;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCarton_grand() {
		return carton_grand;
	}
	public void setCarton_grand(int carton_grand) {
		this.carton_grand = carton_grand;
	}
	
	public Demande() {
        super();
    }
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	 
	public Demande (int id,float prix_total,int id_user,int carton_grand,int carton_moyen,int carton_petit ,String etat) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		this.prix_total = prix_total;
		this.etat = etat;
		
	}
	public Demande (float prix_total,int id_user,int carton_grand,int carton_moyen,int carton_petit,String etat ) {
		super();
		
		this.id_user = id_user;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		this.prix_total = prix_total;
		this.etat = etat;
	}


}
