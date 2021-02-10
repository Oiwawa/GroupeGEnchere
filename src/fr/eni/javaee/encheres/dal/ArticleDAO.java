package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;

public interface ArticleDAO {


	// -------------------ARTICLES-------------------//

	// Mettre en vente un article / mettre aux encheres
	public void insertArticle(ArticleVendu art) throws DALException, SQLException;

	// Les ventes
	public List<ArticleVendu> listeVente(ArticleVendu art) throws DALException, SQLException;

	// -------------------ENCHERES-------------------//

	// Encherir sur un article
	public void encherir(Enchere ench) throws DALException, SQLException;

	// Remporter une enchere
	public void remporterVente(ArticleVendu article) throws DALException, SQLException;


	// Afficher detail de la vente
	public void afficherDetailVente() throws DALException, SQLException;

	// Retrait
	public void lieuRetrait(Retrait ret) throws DALException, SQLException;

	// Ajouter un article dans une categorie
	public void categorieArticle(Categorie cat) throws DALException, SQLException;

}
