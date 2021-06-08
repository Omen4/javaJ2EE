package com.mycompany.dao;

import com.mycompany.models.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoPlayerMysql implements DaoPlayer {
	
	public DaoPlayerMysql() {
	}

	@Override
	public List<Player> ajouter(Player player) {         
		try{					
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("INSERT INTO joueur (nom,prenom,sexe) VALUES (?,?,?)");			  
			statement.setString(1, player.getNom());
			statement.setString(2, player.getPrenom());
			statement.setString(3, player.isMale() ? "H" : "F");
			statement.executeUpdate();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return this.lister();
	}

	@Override
	public List<Player> supprimer(Player player) {
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("DELETE FROM joueur WHERE id=?");			  
			statement.setLong(1, player.getId());
			statement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this.lister();
	}
	
	public List<Player> modifier(Player player) {
		try {
	
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("UPDATE joueur SET nom=?, prenom=?, sexe=? WHERE id=?");			  
			statement.setString(1, player.getNom());
			statement.setString(2, player.getPrenom());
			statement.setString(3, player.isMale() ? "H" : "F");
			statement.setLong(4, player.getId());
			statement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this.lister();
	}
	
	@Override
	public List<Player> lister() {
		List<Player> players = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM joueur");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				players.add(new Player(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe").equals("H")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return players;
	}
	

	
	@Override
	public Player lecture(long id) {
		Player searchedPlayer = null;
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM joueur WHERE id = ?");
			statement.setLong(1, id);
			ResultSet rs  = statement.executeQuery();
			while (rs.next()) {
				searchedPlayer = new Player(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe").equals("H"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return searchedPlayer;
	}

	@Override
	public List<Player> rechercher(String recherche) {
		List<Player> players = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM joueur WHERE (nom LIKE '%?%' OR prenom LIKE '%?%')");
			statement.setString(1, recherche);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				players.add(new Player(rs.getLong("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("sexe").equals("H")));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return players;
	}

}
