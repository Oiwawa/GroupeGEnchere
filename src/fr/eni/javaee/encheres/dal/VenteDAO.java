package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;

public interface VenteDAO {

	//Mettre en vente un article / mettre aux encheres
	public void vendre(ArticleVendu art) throws DALException, SQLException;
	
	//Encherir sur un article
	public void encherir(Enchere ench) throws DALException, SQLException;
	
	//Concerne 1 seul article
	public void concerne(ArticleVendu art) throws DALException, SQLException;
	
	//Les ventes
	public List<ArticleVendu> listeVente(ArticleVendu art) throws DALException, SQLException;
	
	//Retrait
	public void lieuRetrait(Retrait ret) throws DALException, SQLException;
	
	//Ajouter un article dans une categorie
	public void categorieArticle(Categorie cat) throws DALException, SQLException;
}
