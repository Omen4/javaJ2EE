package com.mycompany.dao;

import com.mycompany.models.User;

public interface DaoUser {

	public User search(String mail, String mdp);
	public void create(User user);
	
}
