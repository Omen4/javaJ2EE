package com.mycompany.metier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.dao.DaoEpreuve;
import com.mycompany.dao.DaoEpreuveMysql;
import com.mycompany.dao.DaoMatch;
import com.mycompany.dao.DaoMatchMysql;
import com.mycompany.dao.DaoPlayer;
import com.mycompany.dao.DaoPlayerMysql;
import com.mycompany.dao.DaoTournoi;
import com.mycompany.dao.DaoTournoiMysql;
import com.mycompany.dao.DaoUser;
import com.mycompany.dao.DaoUserMysql;
import com.mycompany.dao.MysqlSingleton;
import com.mycompany.models.Epreuve;
import com.mycompany.models.Match;
import com.mycompany.models.Player;
import com.mycompany.models.Tournoi;


//TODO SUPPRIMER LES DECONNECTION DES DAOMYSQL (APPELÉES ICI)
public class MetierTennis {
	private DaoPlayer daoPlayer;
	private DaoUser daoUser;
	private DaoEpreuve daoEpreuve;
	private DaoTournoi daoTournoi;
	private DaoMatch daoMatch;
	
	
	public MetierTennis() {
		this.daoPlayer = new DaoPlayerMysql();
		this.daoUser = new DaoUserMysql();
		this.daoEpreuve = new DaoEpreuveMysql();
		this.daoTournoi = new DaoTournoiMysql();
		this.daoMatch = new DaoMatchMysql();
	
	}

	
//UTILISATEUR////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////		
	
	//TODO WEB USER PART + CRYPTAGE
	public DaoUser getDaoUser() {
		return this.daoUser;
	}

	
//JOUEUR/////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////////////	

	public List<Player> listerJoueurs() {
		List<Player> joueurs = this.daoPlayer.lister();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joueurs;
		
	}

	public Player lectureJoueur(long idJoueur) {
		Player joueur = this.daoPlayer.lecture(idJoueur);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joueur;
	}
	
	public void ajouterJoueur(Player joueur) {
		this.daoPlayer.ajouter(joueur);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierJoueur(Player joueur) {
		this.daoPlayer.modifier(joueur);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void supprimerJoueur(Player joueur) {
		this.daoPlayer.supprimer(joueur);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
//TOURNOI///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////	
	
	public List<Tournoi> listerTournois() {
		List<Tournoi> tournois = this.daoTournoi.listerTournoi();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tournois;
	}
	
	public Tournoi lectureTournoi(long idTournoi) {
		Tournoi tournoi = this.daoTournoi.lectureTournoi(idTournoi);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tournoi;
	}
	
	public void ajouterTournoi(Tournoi tournoi) {
		this.daoTournoi.ajouterTournoi(tournoi);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierTournoi(Tournoi tournoi) {
		this.daoTournoi.modifierTournoi(tournoi);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void supprimerTournoi(Tournoi tournoi) {
		this.daoTournoi.supprimerTournoi(tournoi);
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<String> getTournois(){
		List<String> optTournois = this.daoTournoi.getTournaments();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optTournois;
	}
	
//MATCH/////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////	

	public List<Match> listerMatchs() {
		List<Match> matchs = this.daoMatch.listerMatch();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchs;
	}

	
//EPREUVE///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////	
//////////////////////////////////////////////////////////////////////////////////////	
	public List<Epreuve> listerEpreuves(){
		List<Epreuve> epreuves = this.daoEpreuve.listerEpreuve();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return epreuves;
	}
	
	public List<Integer> getAnnees(){
		List<Integer> optAnnees = this.daoEpreuve.getYears();
		try {	
			MysqlSingleton.deconnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optAnnees;
	}


}