package com.mycompany.models;

public class Player {
		long id;
		String nom;
		String prenom;
		boolean isMale;
		
		public Player(long id, String nom, String prenom, boolean isMale) {
			super();
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.isMale = isMale;
		}

		public Player() {
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNom() {
			return this.nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return this.prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public boolean isMale() {
			return isMale;
		}

		public void setMale(boolean isMale) {
			this.isMale = isMale;
		}
		
		public String[] toArray(){
	        String[] arr = {Long.toString(getId()), getNom(), getPrenom(), isMale() ? "H" : "F"};
	        return arr;
		 }

		
}
