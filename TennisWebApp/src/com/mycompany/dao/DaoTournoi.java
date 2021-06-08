package com.mycompany.dao;

import java.util.List;
import com.mycompany.models.Tournoi;

public interface DaoTournoi {
	List<Tournoi> ajouterTournoi(Tournoi tournoi);
	List<Tournoi> supprimerTournoi(Tournoi tournoi);
	List<Tournoi> modifierTournoi(Tournoi Tournoi);
	List<Tournoi> listerTournoi();
	Tournoi lectureTournoi(long id);
	List<Tournoi> rechercherTournoi(String recherche);
	List<String> getTournaments();
}
