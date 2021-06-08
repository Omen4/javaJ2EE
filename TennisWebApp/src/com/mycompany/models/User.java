package com.mycompany.models;

public class User {
	private int id;
	private String email;
	private String password;
	private int profilId;
	
		
	public User() {
		super();
	}

	public User(int id, String email, String password, int profil) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.profilId = profil;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getProfilId() {
		return profilId;
	}


	public void setProfilId(int profil) {
		this.profilId = profil;
	}

}
