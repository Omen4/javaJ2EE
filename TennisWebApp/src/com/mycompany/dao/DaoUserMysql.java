package com.mycompany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.models.User;

public class DaoUserMysql implements DaoUser {

	public DaoUserMysql(){
	}
	
	@Override
	public  User search(String mail, String mdp) {
		User searchedUser = null;
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM utilisateurs WHERE login = ? AND password = ?");
			statement.setString(1, mail);
			statement.setString(2, mdp);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				searchedUser = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getInt("profil"));
			}else {
				searchedUser = null;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				MysqlSingleton.deconnection();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return searchedUser;
	}

	@Override
	public void create(User user) {
		try{
			if(this.search(user.getEmail(), user.getPassword()) == null) {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("INSERT INTO utilisateurs (login,password) VALUES (?,?)");			  
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				MysqlSingleton.deconnection();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
