package com.mycompany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.models.Tournoi;


public class DaoTournoiMysql implements DaoTournoi{
		
		public DaoTournoiMysql() {
		}

		@Override
		public List<Tournoi> ajouterTournoi(Tournoi tournoi) {
			try{					
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("INSERT INTO tournoi (nom, code) VALUES (?,?)");			  
				statement.setString(1, tournoi.getNomTournoi());
				statement.setString(2, tournoi.getCodeTournoi());
				statement.executeUpdate();
			}
			catch ( SQLException e ) {
				e.printStackTrace();
			}
			return this.listerTournoi();
		}

		@Override
		public List<Tournoi> supprimerTournoi(Tournoi tournoi) {
			try {
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("DELETE FROM tournoi WHERE id=?");			  
				statement.setLong(1, tournoi.getIdTournoi());
				statement.executeUpdate();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return this.listerTournoi();
		}

		@Override
		public List<Tournoi> modifierTournoi(Tournoi tournoi) {
			try {
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("UPDATE tournoi SET nom=?, code=? WHERE id=?");			  
				statement.setString(1, tournoi.getNomTournoi());
				statement.setString(2, tournoi.getCodeTournoi());
				statement.executeUpdate();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return this.listerTournoi();
		}

		@Override
		public List<Tournoi> listerTournoi() {
			List<Tournoi> tournois = new ArrayList<>();
			try {
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM tournoi");
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					tournois.add(new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code")));
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} 
			return tournois;
		}

		@Override
		public Tournoi lectureTournoi(long id) {
			Tournoi searchedTournoi = null;
			try {
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM tournoi WHERE id = ?");
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					searchedTournoi = new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code"));
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return searchedTournoi;
		}

		@Override
		public List<Tournoi> rechercherTournoi(String recherche) {
			List<Tournoi> tournois = new ArrayList<>();
			try {
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM tournoi WHERE nom LIKE %?%");
				statement.setString(1, recherche);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					tournois.add(new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code")));
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return tournois;
		}
		
		@Override
		public List<String> getTournaments(){
			List<String> tournamentOpt = new ArrayList<>();
			try {    
				PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT DISTINCT(NOM) as tournois FROM tennis.tournoi;");
				ResultSet rs = statement.executeQuery();
				DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
				while (rs.next()) {
					tournamentOpt.add((String)rs.getString("tournois"));
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return tournamentOpt;
		}
	
	}