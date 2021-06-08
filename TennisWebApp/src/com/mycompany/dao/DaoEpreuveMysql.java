package com.mycompany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.models.Epreuve;


public class DaoEpreuveMysql implements DaoEpreuve {

	public DaoEpreuveMysql() {
		
	}

	@Override
	public List<Epreuve> ajouterEpreuve(Epreuve epreuve) {
		try{					
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("INSERT INTO epreuve (ANNEE,TYPE_EPREUVE,ID_TOURNOI) VALUES (?,?,?)");			  
			statement.setInt(1, epreuve.getAnneeEpreuve());
			statement.setString(2, epreuve.isMale() ? "H" : "F");
			statement.setLong(3, epreuve.getTournoi().getIdTournoi());
			statement.executeUpdate();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return this.listerEpreuve();
	}

	@Override
	public List<Epreuve> supprimerEpreuve(Epreuve epreuve) {
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("DELETE FROM epreuve WHERE id=?");			  
			statement.setLong(1, epreuve.getIdEpreuve());
			statement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this.listerEpreuve();
	}

	@Override
	public List<Epreuve> modifierEpreuve(Epreuve epreuve) {
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("UPDATE epreuve SET ANNEE=?, TYPE_EPREUVE=?, ID_TOURNOI=? WHERE ID=?");			  
			statement.setLong(1, epreuve.getAnneeEpreuve());
			statement.setString(2, epreuve.isMale() ? "H" : "F");
			statement.setLong(3, epreuve.getTournoi().getIdTournoi());
			statement.setLong(4, epreuve.getIdEpreuve());
			statement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this.listerEpreuve();
	}

	@Override
	public List<Epreuve> listerEpreuve() {
		List<Epreuve> epreuves = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM epreuve");
			ResultSet rs = statement.executeQuery();
			DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
			while (rs.next()){
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getInt("annee"),rs.getString("type_epreuve").equals("H"), daoTournoiMysql.lectureTournoi(rs.getLong("id_tournoi"))));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return epreuves;
	}

	@Override
	public Epreuve lectureEpreuve(long idEpreuve) {
		Epreuve searchedEpreuve = null;
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM epreuve WHERE id = ?");
			statement.setLong(1, idEpreuve);
			ResultSet rs  = statement.executeQuery();
			DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
			while (rs.next()) {
				searchedEpreuve = new Epreuve(rs.getLong("id"), rs.getInt("annee"),rs.getString("type_epreuve").equals("H"), daoTournoiMysql.lectureTournoi(rs.getLong("id_tournoi")));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return searchedEpreuve;
	}

	@Override
	public List<Epreuve> rechercherEpreuveParTournoi(long idTournoi) {
		List<Epreuve> epreuves = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM epreuve WHERE id_tournoi = ?");
			statement.setLong(1, idTournoi);
			ResultSet rs = statement.executeQuery();
			DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
			while (rs.next()) {
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getInt("annee"),rs.getString("type_epreuve").equals("H"), daoTournoiMysql.lectureTournoi(rs.getLong("id_tournoi"))));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return epreuves;
	}

	@Override
	public List<Epreuve> rechercheEpreuveParAnnee(int annee) {
		List<Epreuve> epreuves = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM epreuve WHERE annee = ?");
			statement.setInt(1, annee);
			ResultSet rs = statement.executeQuery();
			DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
			while (rs.next()) {
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getInt("annee"),rs.getString("type_epreuve").equals("H"), daoTournoiMysql.lectureTournoi(rs.getLong("id_tournoi"))));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return epreuves;
	}

	@Override
	public List<Epreuve> rechercherEpreuveParSexe(boolean isMale) {
		List<Epreuve> epreuves = new ArrayList<>();
		try {    
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM epreuve WHERE type_epreuve = ?");
			statement.setString(1, isMale ? "H" : "F");
			ResultSet rs = statement.executeQuery();
			DaoTournoiMysql daoTournoiMysql = new DaoTournoiMysql();
			while (rs.next()) {
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getInt("annee"),rs.getString("type_epreuve").equals("H"), daoTournoiMysql.lectureTournoi(rs.getLong("id_tournoi"))));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return epreuves;
	}
	
	@Override
	public List<Integer> getYears(){
		List<Integer> yearsOpt = new ArrayList<>();
		try {    
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT DISTINCT(ANNEE) as years FROM tennis.epreuve;");
			ResultSet rs = statement.executeQuery();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()) {
				yearsOpt.add((Integer)rs.getInt("years"));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return yearsOpt;
	}
	
}
