package com.bean;

import java.sql.Date;

public class Demande {

	private int id;
	private Date date_creation;
	private int id_client;
	private String etat;//facture
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Demande(int id, Date date_creation, int id_client,String etat) {
		super();
		this.id = id;
		this.date_creation = date_creation;
		this.id_client = id_client;
		this.etat =etat;
	}
	public Demande() {
		super();
	}
	
	
}