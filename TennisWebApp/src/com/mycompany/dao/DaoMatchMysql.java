package com.mycompany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.models.Epreuve;
import com.mycompany.models.Match;
import com.mycompany.models.Player;

public class DaoMatchMysql implements DaoMatch {

public DaoMatchMysql() {
		
	}

	@Override
	public List<Match> ajouterMatch(Match Match) {
		try{					
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("INSERT INTO score_vainqueur (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?);"
					+ "INSERT INTO match_tennis (ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?)");	  
			statement.setLong(1, Match.getIdMatch());
			statement.setInt(2, Match.getSet1());
			statement.setInt(3, Match.getSet2());
			statement.setInt(4, Match.getSet3());
			statement.setInt(5, Match.getSet4());
			statement.setInt(6, Match.getSet5());
			statement.setLong(6, Match.getPlayer1().getId());
			statement.setLong(7, Match.getPlayer2().getId());
			statement.executeUpdate();
		}
		catch ( SQLException e ) {
			e.printStackTrace();
		}
		return this.listerMatch();
	}

	@Override
	public List<Match> supprimerMatch(Match Match) {
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("DELETE FROM match_tennis WHERE id=?;"
					+ "DELETE FROM score_vainquere WHERE ID_MATCH = ?");			  
			statement.setLong(1, Match.getIdMatch());
			statement.setLong(2, Match.getIdMatch());
			statement.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this.listerMatch();
	}

	@Override
	public List<Match> modifierMatch(Match Match) {
//		try {
//			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("UPDATE Match SET ANNEE=?, TYPE_Match=?, ID_TOURNOI=? WHERE ID=?");			  
//			statement.setLong(1, Match.getAnneeMatch());
//			statement.setString(2, Match.isMale() ? "H" : "F");
//			statement.setLong(3, Match.getTournoi().getIdTournoi());
//			statement.setLong(4, Match.getIdMatch());
//			statement.executeUpdate();
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
		return this.listerMatch();
	}

//	@Override
//	public List<Match> listerMatch() {
//		List<Match> Matchs = new ArrayList<>();
//		try {
//			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement(
//					"SELECT score_vainqueur.ID_MATCH AS idMatch, "
//					+ "score_vainqueur.SET_1 AS set1, "
//					+ "score_vainqueur.SET_2 AS set2, "
//					+ "score_vainqueur.SET_3 AS set3, "
//					+ "score_vainqueur.SET_4 AS set4, "
//					+ "score_vainqueur.SET_5 AS set5, "
//					+ "match_tennis.ID_EPREUVE AS idEpreuve, "
//					+ "v.ID AS idVainqueur, v.NOM AS nomVainqueur, "
//					+ "v.PRENOM AS prenomVainqueur, v.SEXE AS sexeVainqueur, "
//					+ "f.ID AS idFinaliste, "
//					+ "f.NOM AS nomFinaliste, "
//					+ "f.PRENOM AS prenomFinaliste, "
//					+ "f.SEXE AS sexeFinaliste "
//					+ "FROM tennis.score_vainqueur "
//					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
//					+ "LEFT JOIN tennis.joueur AS v ON match_tennis.ID_VAINQUEUR = v.ID "
//					+ "LEFT JOIN tennis.joueur AS f ON match_tennis.ID_FINALISTE = f.ID;)");
//			ResultSet rs = statement.executeQuery();
//			try {
//				MysqlSingleton.deconnection();
//			} 
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
//			while (rs.next()){
//				
//				Matchs.add(new Match(
//						rs.getLong("id_match"),
//						new Player(rs.getLong("idVainqueur"), rs.getString() ), 
//						new Player(),
//						rs.getInt("SET_1"),
//						rs.getInt("SET_2"),
//						rs.getInt("SET_3"),
//						rs.getInt("SET_4"),
//						rs.getInt("SET_5")));
//
//			}
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		} 
//		return Matchs;
//	}

	@Override
	public List<Match> listerMatch() {
		List<Match> matchs = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement(
					"SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
					+ "LEFT JOIN tennis.epreuve ON match_tennis.id_epreuve = epreuve.ID;");
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));
				matchs.add(new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return matchs;
	}
	
	
	
	@Override
	public Match lectureMatch(long idMatch) {
		Match searchedMatch = null;
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement(
					"SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID WHERE score_vainqueur.ID_MATCH = ?;");
			statement.setLong(1, idMatch);
			statement.executeUpdate();
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();

			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("tennis.match_tennis.ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("tennis.match_tennis.ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));

				searchedMatch = new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return searchedMatch;
	}

	@Override
	public List<Match> rechercherMatchParTournoi(long idTournoi) {
		List<Match> matchs = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
					+ "LEFT JOIN tennis.epreuve ON tennis.match_tennis.ID_EPREUVE = epreuve.ID "
					+ "LEFT JOIN tennis.tournoi ON tennis.epreuve.ID_TOURNOI = tournoi.ID "
					+ "WHERE tournoi.ID = ?;");
			statement.setLong(1, idTournoi);
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));
				matchs.add(new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return matchs;
	}
	
	//Attention à l'articulation des querys!
	@Override
	public List<Match> rechercheMatchParAnnee(int annee) {
		List<Match> matchs = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement("SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
					+ "LEFT JOIN tennis.epreuve ON tennis.match_tennis.ID_EPREUVE = epreuve.ID "
					+ "WHERE epreuve.ANNEE = ?;");
			statement.setInt(1, annee);
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));
				matchs.add(new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return matchs;
	}

	@Override
	public List<Match> rechercherMatchParSexe(boolean isMale) {
		List<Match> matchs = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement(
					"SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
					+ "LEFT JOIN tennis.joueur ON tennis.match_tennis.ID_VAINQUEUR = tennis.joueur.id "
					+ "OR tennis.match_tennis.ID_FINALISTE = tennis.joueur.id "
					+ "WHERE joueur.SEXE = ?;");
			statement.setString(1, isMale ? "H" : "F");
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));
				matchs.add(new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return matchs;
	}

	@Override
	public List<Match> rechercherMatchParNom(String recherche) {
		List<Match> matchs = new ArrayList<>();
		try {
			PreparedStatement statement = MysqlSingleton.getInstance().prepareStatement(
					"SELECT * FROM tennis.score_vainqueur "
					+ "LEFT JOIN tennis.match_tennis ON ID_MATCH = match_tennis.ID "
					+ "LEFT JOIN tennis.joueur ON tennis.match_tennis.ID_VAINQUEUR = tennis.joueur.id "
					+ "OR tennis.match_tennis.ID_FINALISTE = tennis.joueur.id "
					+ "WHERE (joueur.NOM LIKE '%?%' "
					+ "OR joueur.PRENOM LIKE '%?%');");
			statement.setString(1, recherche);
			statement.setString(2, recherche);
			ResultSet rs = statement.executeQuery();
			
			DaoPlayerMysql daoPlayerMysql = new DaoPlayerMysql();
			DaoEpreuveMysql daoEpreuveMysql = new DaoEpreuveMysql();
			while (rs.next()){
				Player player1 = daoPlayerMysql.lecture(rs.getLong("ID_VAINQUEUR"));
				Player player2 = daoPlayerMysql.lecture(rs.getLong("ID_FINALISTE"));
				Epreuve epreuve = daoEpreuveMysql.lectureEpreuve(rs.getLong("id_epreuve"));
				matchs.add(new Match(rs.getLong("id_match"), epreuve, player1, player2,rs.getInt("SET_1"),rs.getInt("SET_2"),rs.getInt("SET_3"),rs.getInt("SET_4"),rs.getInt("SET_5")));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} 
		return matchs;
	}



}
