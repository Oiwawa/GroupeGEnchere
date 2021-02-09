package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;

public interface VenteDAO {

	// -------------------USER-------------------//
	// Inscription au site
	public void inscription(Utilisateur user) throws DALException, SQLException;

	// Connexion au site
	public void connexion(Utilisateur user) throws DALException, SQLException;

	// Modification du profil
	public void updateUser(Utilisateur user) throws DALException, SQLException;

	// Suppression du compte par l'utilisateur
	public void deleteUser(Utilisateur user) throws DALException, SQLException;
	
	//Afficher un profil
	public void afficherProfil(Utilisateur user) throws DALException, SQLException;

	// -------------------ARTICLES-------------------//

	// Mettre en vente un article / mettre aux encheres
	public void vendre(ArticleVendu art) throws DALException, SQLException;

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
