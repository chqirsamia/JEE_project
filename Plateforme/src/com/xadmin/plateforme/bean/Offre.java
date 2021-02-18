package com.xadmin.plateforme.bean;

public class Offre {
	private int id_offre;
    private String description;
    private int id;
    private float reduction_offre;  
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getID_offre() {
		return id_offre;
	}
	public void setID_offre(int id_offre) {
		this.id_offre = id_offre;
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
	 
	public Offre(int id, String Description,int id_offre,float reduction_offre) {
		super();
		this.id = id;
		this.description = Description;
		this.id_offre = id_offre;
		this.reduction_offre = reduction_offre;	
	}
	public Offre( String Description,int id,float reduction_offre) {
		super();
		this.description = Description;
		this.id_offre = id_offre;
		this.reduction_offre = reduction_offre;	
		
	}


}
