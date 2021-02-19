package com.xadmin.plateforme.bean;

public class Offre {
	private int id_offre;
    private String description;
    private int carton_moyen;
    private int carton_petit;
    private int carton_grand;
    private int id;
    //private String type_carton;
    private float reduction_offre; 
    private String nom;
    private String prenom;
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public String getType_carton() {
		return type_carton;
	}
	public void setType_carton(String type_carton) {
		this.type_carton = type_carton;
	}*/
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	
	public int getID_offre() {
		return id_offre;
	}
	public void setID_offre(int id_offre) {
		this.id_offre = id_offre;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
	public Offre(int id, String nom,String prenom,String description,int id_offre,int carton_grand,int carton_moyen,int carton_petit ) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.id_offre = id_offre;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		
	}
	public Offre( int id, String nom,String prenom,float reduction_offre,String description,int carton_grand,int carton_moyen,int carton_petit ) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.description = description;
		this.carton_grand = carton_grand;
		this.carton_moyen = carton_moyen;
		this.carton_petit = carton_petit;
		this.reduction_offre = reduction_offre;
		
	}


}
