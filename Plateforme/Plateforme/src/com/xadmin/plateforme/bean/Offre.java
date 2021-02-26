package com.xadmin.plateforme.bean;

public class Offre {
	private int id;
    private String description;
    private int carton_moyen;
    private int carton_petit;
    private int carton_grand;
    private int id_admin;
    private float reduction_offre; 
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setId(int id_offre) {
		this.id = id_offre;
	}
	
	public int getCarton_grand() {
		return carton_grand;
	}
	public void setCarton_grand(int carton_grand) {
		this.carton_grand = carton_grand;
	}
	
	public float getReduction_offre() {
		return reduction_offre;
	}
	public void setReduction_offre(float reduction_offre) {
		this.reduction_offre = reduction_offre;
	}
	public Offre() {
        super();
    }
	public int getId_admin() {
		return id_admin;
	}
	public void setId_admin(int id) {
		this.id_admin = id;
	}
	 
	public Offre(int id,float reduction_offre, String description,int id_offre,int carton_grand,int carton_moyen,int carton_petit ) {
		super();
		this.id_admin = id;
		this.description = description;
		this.id = id_offre;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		this.reduction_offre = reduction_offre;
		
	}
	public Offre( int id,float reduction_offre,String description,int carton_grand,int carton_moyen,int carton_petit ) {
		super();
		this.id_admin=id;
		this.description = description;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		this.reduction_offre = reduction_offre;
		
	}


}
