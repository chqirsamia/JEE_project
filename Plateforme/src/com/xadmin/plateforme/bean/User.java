package com.xadmin.plateforme.bean;

public class User {
	private int id;
    private String nom;
    private String prenom;
    private String email;
    private String sexe;
    private String password;
    
    public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
        super();
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User(int id, String nom, String prenom, String email,String sexe, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.sexe=sexe;
		this.password = password;
		
	}
	public User(String nom, String prenom, String email,String sexe,String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.sexe=sexe;
		this.password = password;
		
	}


}
