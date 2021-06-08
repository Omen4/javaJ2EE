package com.mycompany.dao;

import com.mycompany.models.Player;

import java.util.List;

public interface DaoPlayer {
	List<Player> ajouter(Player player);
	List<Player> supprimer(Player player);
	List<Player> modifier(Player player);
	List<Player> lister();
	Player lecture(long id);
	List<Player> rechercher(String recherche);

}
