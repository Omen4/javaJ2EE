package com.mycompany.models;

public class Epreuve {
	private long idEpreuve;
	private int anneeEpreuve;
	private boolean isMale;
	private Tournoi tournoi;
	
	
	public Epreuve(long idEpreuve, int anneeEpreuve,  boolean isMale, Tournoi tournoi ) {
		super();
		this.idEpreuve = idEpreuve;
		this.anneeEpreuve = anneeEpreuve;
		this.isMale = isMale;
		this.tournoi = tournoi;
	}


	public long getIdEpreuve() {
		return idEpreuve;
	}


	public void setIdEpreuve(long idEpreuve) {
		this.idEpreuve = idEpreuve;
	}


	public int getAnneeEpreuve() {
		return anneeEpreuve;
	}


	public void setAnneeEpreuve(int anneeEpreuve) {
		this.anneeEpreuve = anneeEpreuve;
	}


	public boolean isMale() {
		return isMale;
	}


	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}


	public Tournoi getTournoi() {
		return tournoi;
	}


	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}

	public String[] toArray(){
        String[] arr = {Long.toString(getIdEpreuve()), Integer.toString(getAnneeEpreuve()), isMale()? "H" : "F", getTournoi().getNomTournoi()};
        return arr;
	 }	
	
}
