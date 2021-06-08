package com.mycompany.models;


public class Match {
	private long idMatch;
	private Epreuve epreuve;
	private Player player1;
	private Player player2;
	private int set1;
	private int set2;
	private int set3;
	private int set4;
	private int set5;
	
	
	public Match(long idMatch, Epreuve epreuve, Player player1, Player player2, int set1, int set2, int set3, int set4, int set5) {
		super();
		this.idMatch = idMatch;
		this.epreuve= epreuve;
		this.player1 = player1;
		this.player2 = player2;
		this.set1 = set1;
		this.set2 = set2;
		this.set3 = set3;
		this.set4 = set4;
		this.set5 = set5;
	}
	
	public Match() {
		
	}

	public long getIdMatch() {
		return idMatch;
	}
	public void setIdMatch(long idMatch) {
		this.idMatch = idMatch;
	}
	
	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	
	public long getVainqueurId() {
		return this.player1.getId();
	}

	public long getFinalisteId() {
		return this.player2.getId();
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	public int getSet1() {
		return set1;
	}
	public void setSet1(int set1) {
		this.set1 = set1;
	}
	public int getSet2() {
		return set2;
	}
	public void setSet2(int set2) {
		this.set2 = set2;
	}
	public int getSet3() {
		return set3;
	}
	public void setSet3(int set3) {
		this.set3 = set3;
	}
	public int getSet4() {
		return set4;
	}
	public void setSet4(int set4) {
		this.set4 = set4;
	}
	public int getSet5() {
		return set5;
	}
	public void setSet5(int set5) {
		this.set5 = set5;
	}
	
	public String[] toArray(){
        String[] arr = {Long.toString(getIdMatch()), getPlayer1().getNom(), getPlayer1().getPrenom(), getPlayer2().getNom(), getPlayer2().getPrenom(), Integer.toString(getSet1()), Integer.toString(getSet2()), Integer.toString(getSet3()), Integer.toString(getSet4()), Integer.toString(getSet5())};
        return arr;
	 }




	
}
