package com.xadmin.plateforme.classesneeded;

import java.util.List;

import com.xadmin.plateforme.bean.Demande;

public class ListeDemandeSpec {

	private Demande demande;
	private List<LigneDemandeSpec> lds;
	
	
	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public List<LigneDemandeSpec> getLds() {
		return lds;
	}

	public void setLds(List<LigneDemandeSpec> lds) {
		this.lds = lds;
	}

	public ListeDemandeSpec(Demande demande, List<LigneDemandeSpec> lds) {
		super();
		this.demande = demande;
		this.lds = lds;
	}

	public ListeDemandeSpec() {
		super();
	}
	
	
}
