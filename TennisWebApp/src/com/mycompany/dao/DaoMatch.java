package com.mycompany.dao;

import java.util.List;

import com.mycompany.models.Match;


public interface DaoMatch {
	List<Match> ajouterMatch(Match Match);
	List<Match> supprimerMatch(Match Match);
	List<Match> modifierMatch(Match Match);
	List<Match> listerMatch();
	Match lectureMatch(long idMatch);
	List<Match> rechercherMatchParNom(String recherche);
	List<Match> rechercherMatchParTournoi(long idTournoi);
	List<Match> rechercheMatchParAnnee(int Annee);
	List<Match> rechercherMatchParSexe(boolean isMale);
	
}
