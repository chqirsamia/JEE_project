package com.xadmin.plateforme.bean;

public class Carton {

	private int id_carton;
//	private enum TypeCarton {
//		PC, MC, GC
//	}
//	
	private String type_carton;
	private String libelle_carton;
	private float prix; 
	
	public int getId_carton() {
		return id_carton;
	}
	public void setId_carton(int id_carton) {
		this.id_carton = id_carton;
	}
	
	public String getType_carton() {
		return type_carton;
	}
	public void setType_carton(String type_carton) {
		this.type_carton = type_carton;
	}
	public String getLibelle_carton() {
		return libelle_carton;
	}
	public void setLibelle_carton(String libelle_carton) {
		this.libelle_carton = libelle_carton;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
	public Carton(int id_carton, String type_carton, String libelle_carton) {
		super();
		this.id_carton = id_carton;
		this.type_carton = type_carton;
		this.libelle_carton = libelle_carton;
	}
	
	public Carton(String type_carton, String libelle_carton) {
		super();
		this.type_carton = type_carton;
		this.libelle_carton = libelle_carton;
	}
	
	public Carton() {
		super();
	}
	
	
	
	
}
