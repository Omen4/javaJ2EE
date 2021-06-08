package com.mycompany.models;

public class Tournoi {
	private long idTournoi;
	private String nomTournoi;
	private String codeTournoi;
		
	public Tournoi(long idTournoi, String nomTournoi, String codeTournoi) {
		super();
		this.idTournoi = idTournoi;
		this.nomTournoi = nomTournoi;
		this.codeTournoi = codeTournoi;
	}
	
	public Tournoi() {
		
	}

	public long getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(long idTournoi) {
		this.idTournoi = idTournoi;
	}

	public String getNomTournoi() {
		return nomTournoi;
	}

	public void setNomTournoi(String nomTournoi) {
		this.nomTournoi = nomTournoi;
	}

	public String getCodeTournoi() {
		return codeTournoi;
	}

	public void setCodeTournoi(String codeTournoi) {
		this.codeTournoi = codeTournoi;
	}
	
	public String[] toArray(){
        String[] arr = {Long.toString(getIdTournoi()), getNomTournoi(), getCodeTournoi()};
        return arr;
	 }

}
