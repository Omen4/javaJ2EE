package com.mycompany.dao;

import java.util.List;

import com.mycompany.models.Epreuve;

public interface DaoEpreuve {
	List<Epreuve> ajouterEpreuve(Epreuve epreuve);
	List<Epreuve> supprimerEpreuve(Epreuve epreuve);
	List<Epreuve> modifierEpreuve(Epreuve epreuve);
	List<Epreuve> listerEpreuve();
	Epreuve lectureEpreuve(long idEpreuve);
	List<Epreuve> rechercherEpreuveParTournoi(long idTournoi);
	List<Epreuve> rechercheEpreuveParAnnee(int Annee);
	List<Epreuve> rechercherEpreuveParSexe(boolean isMale);
	List<Integer> getYears();
	
}
